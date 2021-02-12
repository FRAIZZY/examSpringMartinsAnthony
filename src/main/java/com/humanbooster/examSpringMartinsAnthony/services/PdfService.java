/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanbooster.examSpringMartinsAnthony.services;

import com.humanbooster.examSpringMartinsAnthony.model.Annonce;
import com.lowagie.text.DocumentException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author HB
 */
@Service
public class PdfService {
    
    public static String output = "src/main/resources/static/pdf/rest-with-spring.pdf";

    private String parseThymeleafTemplate(Annonce annonce) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();

        context.setVariable("annonce", annonce);

        return templateEngine.process("templates/annoncePdf.html", context);
    }

    public void generatePdfFromHtml(Annonce annonce) throws IOException, DocumentException {
        String html = this.parseThymeleafTemplate(annonce);
        OutputStream outputStream = new FileOutputStream(output);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }
}
