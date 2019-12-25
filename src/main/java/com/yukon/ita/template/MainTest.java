package com.yukon.ita.template;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

public class MainTest {

    public static void main(String[] args) throws Exception {


        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);

        // Where do we load the templates from:
        cfg.setClassForTemplateLoading(MainTest.class,  "/templates/");

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);


        // 2.1. Prepare the template input:

        Map<String, Object> input = new HashMap<String, Object>();

        input.put("title", "example");

        input.put("exampleObject", new TemplateDTO(1,"Java object", "me"));

        List<TemplateDTO> systems = new ArrayList<TemplateDTO>();
        systems.add(new TemplateDTO(1,"Fast send", "ftl"));
        systems.add(new TemplateDTO(2,"Many recipients", "html"));
        systems.add(new TemplateDTO(3,"old recipients", "exe"));

        input.put("systems", systems);

        // 2.2. Get the template

        Template template = cfg.getTemplate("HelloWorld.ftl");

        // 2.3. Generate the output

        // Write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);

        // For the sake of example, also write output into a file:
        Writer fileWriter = new FileWriter(new File("output.html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }

    }

}