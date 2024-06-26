package edu.school21.reflection;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {
    private Scanner scan;
    
    public static void main(String[] args) {
        Program program = new Program();
        try (Scanner scan = new Scanner(System.in)) {
            Set<Class<?>> classes = program.findClassesInPackage(
                "edu.school21.reflection.classes");
            program.printClassesNames(classes);
            System.out.println("Enter class name:");
            String className = scan.nextLine();
            program.printClass(className);
            Object object = program.createObject(className, scan);
            program.updateObject(object, scan);
            program.callMethod(object, scan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Set<Class<?>> findClassesInPackage(String packageName) {
        Reflections reflections = new Reflections(packageName,
                new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(Object.class));
    }
    
    public void printClassesNames(Set<Class<?>> classes) {
        System.out.println("Classes:");
        for (Class<?> c : classes) {
            System.out.println(" - " + c.getSimpleName());
        }
        System.out.println("---------------------------");
    }
    
    public void printClass(String className) throws Exception {
        Class<?> c =
                Class.forName("edu.school21.reflection.classes." + className);
        System.out.println("---------------------------");
        System.out.println("fields:");
        for (Field f : c.getDeclaredFields()) {
            System.out.println("\t" + f.getType().getSimpleName() + " " +
                    f.getName());
        }
        System.out.println("methods:");
        for (Method m : c.getDeclaredMethods()) {
            String params = Arrays.stream(m.getParameterTypes())
                    .map(Class::getSimpleName)
                    .collect(Collectors.joining(", "));
            System.out.println("\t" + m.getReturnType().getSimpleName()
                    + " " + m.getName() + "(" + params + ")");
        }
        System.out.println("---------------------------");
    }
    
    public Object createObject(String className, Scanner scan) throws Exception {
        Object object = null;
        System.out.println("Let’s create an object.");
        Class<?> c =
                Class.forName("edu.school21.reflection.classes." + className);
        List<String> fieldsList = Stream.of(c.getDeclaredFields())
                .map(Field::getName).toList();
        Constructor<?> constructorWithParams = Stream.of(c.getConstructors())
                .filter(constructor -> constructor.getParameterTypes().length > 0)
                .findFirst().orElse(null);
        if (constructorWithParams != null) {
            Class<?>[] parameterTypes = constructorWithParams.getParameterTypes();
            Object[] parameters = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                System.out.print("Enter value for parameter '"
                        + fieldsList.get(i) + "': ");
                String input = scan.nextLine();
                parameters[i] = castType(input, parameterTypes[i]);
            }
            object = constructorWithParams.newInstance(parameters);
            System.out.println("Object created: " + object);
            System.out.println("---------------------------");
        }
        return object;
    }
    
    public Object castType(String data, Class<?> toClass) {
        if (toClass == int.class) {
            return Integer.parseInt(data);
        } else if (toClass == double.class) {
            return Double.parseDouble(data);
        } else if (toClass == boolean.class) {
            return Boolean.parseBoolean(data);
        } else {
            return data;
        }
    }
    
    public void updateObject(Object object, Scanner scan) throws Exception {
        System.out.println("Enter name of the field for changing:");
        String fieldToUpdate = scan.nextLine();
        Field field = Arrays.stream(object.getClass()
                .getDeclaredFields())
                .filter(f -> f.getName().equals(fieldToUpdate))
                .findFirst().orElse(null);
        if (field != null) {
            System.out.println("Enter " + field.getType().getSimpleName() +
                    " value:");
            field.setAccessible(true);
            field.set(object, castType(scan.nextLine(), field.getType()));
            System.out.println("Object updated: " + object);
        } else {
            System.out.println("No such field");
        }
        System.out.println("---------------------------");
    }
    
    public void callMethod(Object object, Scanner scan) throws InvocationTargetException, IllegalAccessException {
        System.out.println("Enter name of the method for call:");
        String methodToCall = scan.nextLine();
        Method method = Stream.of(object.getClass()
                .getDeclaredMethods())
                .filter(m -> (m.getName() + "(" + Stream.of(m.getParameterTypes())
                        .map(Class::getSimpleName)
                        .collect(Collectors.joining(", ")) + ")")
                        .equals(methodToCall))
                .findFirst()
                .orElse(null);
        if (method != null) {
            Class<?>[] paramsClasses = method.getParameterTypes();
            Object[] parameters = new Object[paramsClasses.length];
            for (int i = 0; i < paramsClasses.length; i++) {
                System.out.println("Enter " + paramsClasses[i].getSimpleName() + " value:");
                parameters[i] = castType(scan.nextLine(), paramsClasses[i]);
            }
            method.setAccessible(true);
            System.out.println("Method returned:\n" + method.invoke(object,
                    parameters));
        } else {
            System.out.println("No such method");
        }
    }
}