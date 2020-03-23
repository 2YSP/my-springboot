package cn.sp.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 * Created by 2YSP on 2020/3/1.
 * 自定义注解处理器
 *
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)// 表示所支持的Java版本
@SupportedAnnotationTypes("cn.sp.annotation.MyGetter")// 表示该处理器要处理的注解
public class MyGetterProcessor extends AbstractProcessor {


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        StringBuilder builder = new StringBuilder()
                .append("package cn.sp.annotation;\n\n")
                .append("public class GeneratedClass{\n\n")// open class
                .append("\tpublic String getMessage(){\n")// open method
                .append("\treturn \"");

        for(Element element : roundEnv.getElementsAnnotatedWith(MyGetter.class)){
            String objType = element.getSimpleName().toString();
            // this is appending to the return statement
            builder.append(objType).append(" say hello!\\n");
        }
        builder.append("\";\n")// end return
                .append("\t}\n")// end method
                .append("}\n");// end class
        try {
            // write the file
            JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile("cn.sp.annotation.GeneratedClass");
            Writer writer = sourceFile.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        }catch (IOException e){

        }
        return true;
    }


}
