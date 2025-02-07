package com.example.pdf_demo.util;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Component
public class PdfGenaratorUtil {
    @Autowired
    private TemplateEngine templateEngine;

    public String createPdf(String templatename, Map<String, Object> map) throws IOException, DocumentException {
        String fileNameUrl = "";

        Context ctx = new Context();

        if (map != null) {
            for (Map.Entry<String, Object> pair : map.entrySet()) {
                ctx.setVariable(pair.getKey(), pair.getValue());
            }
        }

        String processedHtml = templateEngine.process(templatename, ctx);
        FileOutputStream os = null;

        if(!map.containsKey("ID")) {
            throw new IllegalArgumentException("Map must contain an 'ID' key");
        }

        String studentId = map.get("ID").toString();

        try {
            final File outputFile = File.createTempFile("Student_" + studentId + "_", ".pdf");
            os = new FileOutputStream(outputFile);

            ITextRenderer itr = new ITextRenderer();
            itr.setDocumentFromString(processedHtml);
            itr.layout();
            itr.createPDF(os, false);
            itr.finishPDF();

            fileNameUrl = outputFile.getName();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ignored) {
                }
            }
        }

        return fileNameUrl;

    }
}
