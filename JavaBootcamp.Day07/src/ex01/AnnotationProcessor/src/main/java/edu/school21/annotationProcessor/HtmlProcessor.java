package edu.school21.annotationProcessor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.io.File;
import java.io.FileWriter;
import java.util.Set;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_21)
@SupportedAnnotationTypes({"edu.school21.annotationProcessor.HtmlForm",
        "edu.school21.annotationProcessor.HtmlInput"})
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        System.out.println("INIT");
    }
    
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
            System.out.println("PROCESSING " + element.getSimpleName());
            String fileName =
                    "target/generated-sources/annotations/" +
                            element.getAnnotation(HtmlForm.class).fileName();
            File htmlFile = new File(fileName);
            try (FileWriter writer = new FileWriter(htmlFile, false)) {
                String action = element.getAnnotation(HtmlForm.class).action();
                String method = element.getAnnotation(HtmlForm.class).method();
                writer.write("<form action=\"" + action + "\" method=\"" + method + "\">\n");
                
                for (Element subelement : element.getEnclosedElements()) {
                    if (subelement.getKind() == ElementKind.FIELD) {
                        String type = subelement.getAnnotation(HtmlInput.class).type();
                        String name = subelement.getAnnotation(HtmlInput.class).name();
                        String placeholder = subelement.getAnnotation(HtmlInput.class).placeholder();
                        writer.write("\t<input type=\"" + type + "\" name=\"" +
                                name + "\" placeholder=\"" + placeholder + "\">\n");
                    }
                }
                
                writer.write("""
                        \t<input type="submit" value="Send">
                        </form>
                        """);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}