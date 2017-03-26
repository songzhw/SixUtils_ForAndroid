package ca.six.router2.compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import ca.six.router2.annotation.Path;

@SupportedAnnotationTypes("ca.six.router2.annotation.Path")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedOptions("moduleName")
public class PathProcessor extends AbstractProcessor {
    private Elements elementUtils = null;
    private Filer filer = null;
    private Messager messager = null;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    private void error(Element element, String message, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(message, args), element);
    }

    private void log(Element element, String message, Object... args) {
        messager.printMessage(Diagnostic.Kind.NOTE, String.format(message, args), element);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        List<TypeElement> types = new ArrayList<>();
        for(Element element : roundEnvironment.getElementsAnnotatedWith(Path.class)) {
            types.add((TypeElement) element);
        }

        String moduleName = processingEnv.getOptions().get("moduleName");
        if(moduleName != null) {
            log(null, "szw Processor process() module = " + moduleName);
        } else {
            error(null, "No option 'moduleName' passed to processor");
        }

        return true;
    }


}
