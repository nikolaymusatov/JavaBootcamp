package edu.school21.ORM;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrmManager {
    private Connection connection;
    
    public OrmManager() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/java_chat",
                    "nikolajmusatov",
                    "balalayka");
            for (Class<?> c : this.findAnnotatedClasses()) {
                this.createTable(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void save(Object entity) throws SQLException, IllegalAccessException {
        List<Field> fields = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(OrmColumn.class))
                .toList();
        String tableName = entity.getClass()
                .getAnnotation(OrmEntity.class).table();
        String columnNames = fields.stream()
                .map(field -> field.getAnnotation(OrmColumn.class).name())
                .collect(Collectors.joining(", "));
        String placeholders = fields.stream()
                .map(field -> "?")
                .collect(Collectors.joining(", "));
        String sql = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + placeholders + ")";
        PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            field.setAccessible(true);
            ps.setObject(i + 1, field.get(entity));
        }
        ps.executeUpdate();
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            long id = generatedKeys.getInt(1);
            this.setIdToEntity(entity, id);
        }
    }
    
    public void update(Object entity) throws SQLException, IllegalAccessException {
        List<Field> fields = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(OrmColumn.class))
                .toList();
        String tableName = entity.getClass()
                .getAnnotation(OrmEntity.class).table();
        String setClause = fields.stream()
                .map(field -> field.getAnnotation(OrmColumn.class).name() + " = ?")
                .collect(Collectors.joining(", "));
        String sql = "UPDATE " + tableName + " SET " + setClause + " WHERE id = ?";
        Field idField = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(OrmColumnId.class))
                .findFirst()
                .orElse(null);
        PreparedStatement ps = connection.prepareStatement(sql);
        int index = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            ps.setObject(index++, field.get(entity));
        }
        if (idField != null) {
            idField.setAccessible(true);
            ps.setObject(index, idField.get(entity));
        }
        ps.executeUpdate();
    }
    
    public <T> T findById(Long id, Class<T> aClass) throws SQLException {
        String tableName = aClass.getAnnotation(OrmEntity.class).table();
        T entity = null;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE id = ?;");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                entity = createEntityFromResultSet(aClass, rs);
            }
        } catch (Exception e) {
            throw new SQLException("Error finding entity by ID: " + e.getMessage(), e);
        }
        return entity;
    }
    
    public void dropTable(Class<?> aClass) throws SQLException {
        String tableName = aClass.getAnnotation(OrmEntity.class).table();
        PreparedStatement ps = connection.prepareStatement(
                "DROP TABLE IF EXISTS " + tableName + ";"
        );
        ps.executeUpdate();
    }
    
    private void createTable(Class<?> c) throws SQLException {
        String tableName = c.getAnnotation(OrmEntity.class).table();
        StringBuilder sql =
                new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(OrmColumnId.class)) {
                sql.append("id SERIAL PRIMARY KEY, ");
            } else if (field.isAnnotationPresent(OrmColumn.class)) {
                OrmColumn ormColumn = field.getAnnotation(OrmColumn.class);
                if (field.getType().getSimpleName().equals("String")) {
                    sql.append(ormColumn.name() + " VARCHAR(" + ormColumn.length() + "), ");
                } else if (field.getType().getSimpleName().equals("Integer")) {
                    sql.append(ormColumn.name() + " INTEGER, ");
                } else if (field.getType().getSimpleName().equals("Double")) {
                    sql.append(ormColumn.name() + " DOUBLE PRECISION, ");
                } else if (field.getType().getSimpleName().equals("Boolean")) {
                    sql.append(ormColumn.name() + " BOOLEAN, ");
                } else if (field.getType().getSimpleName().equals("Long")) {
                    sql.append(ormColumn.name() + " BIGINT, ");
                }
            }
        }
        sql.delete(sql.length() - 2, sql.length());
        sql.append(");");
        PreparedStatement ps = this.connection.prepareStatement(sql.toString());
        ps.executeUpdate();
    }
    
    private Set<Class<?>> findAnnotatedClasses() {
        Reflections reflections = new Reflections("edu.school21.ORM",
                new SubTypesScanner(false));
        return reflections.getSubTypesOf(Object.class).stream()
                .filter(aClass -> aClass.isAnnotationPresent(OrmEntity.class))
                .collect(Collectors.toSet());
    }
    
    private void setIdToEntity(Object entity, long id) throws IllegalAccessException {
        Field idField = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(OrmColumnId.class)).findFirst().orElse(null);
        if (idField != null) {
            idField.setAccessible(true);
            idField.set(entity, id);
        }
    }
    
    private <T> T createEntityFromResultSet(Class<T> aClass, ResultSet rs) throws Exception {
        Constructor<T> constructor = aClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        T entity = constructor.newInstance();
        Field idField = Arrays.stream(aClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(OrmColumnId.class))
                .findFirst().orElse(null);
        if (idField != null) {
            idField.setAccessible(true);
            idField.set(entity, rs.getLong("id"));
        }
        List<Field> fields = Arrays.stream(aClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(OrmColumn.class))
                .toList();
        for (Field field : fields) {
            field.setAccessible(true);
            String columnName = field.getAnnotation(OrmColumn.class).name();
            Object value = rs.getObject(columnName);
            field.set(entity, value);
        }
        return entity;
    }
}
