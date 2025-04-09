package com.book.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.Writer;

public class ThymeleafUtil {

    public static final TemplateEngine engine ;
    static {
        try{
            engine = new TemplateEngine();
            ClassLoaderTemplateResolver r = new ClassLoaderTemplateResolver();
            engine.setTemplateResolver(r);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static void process(String templateName,IContext context,Writer writer){
        engine.process(templateName, context, writer);
    }
}
