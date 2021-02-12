/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanbooster.examSpringMartinsAnthony.controller;

import com.humanbooster.examSpringMartinsAnthony.model.Annonce;
import com.humanbooster.examSpringMartinsAnthony.services.AnnonceService;
import com.humanbooster.examSpringMartinsAnthony.services.PdfService;
import com.lowagie.text.DocumentException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author HB
 */
@Controller
@RequestMapping(path = "/pdf")
public class ExportController {
    
    @Autowired
    private AnnonceService annonceService;
    
    @Autowired
    private PdfService pdfService;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void pdf(HttpServletResponse response, @PathVariable(name = "id", required = true) Integer id) throws IOException, DocumentException {
        Annonce annonce = this.annonceService.getAnnonce(id);

        this.pdfService.generatePdfFromHtml(annonce);

        InputStream inputStream = new FileInputStream(new File("src/main/resources/static/pdf/rest-with-spring.pdf"));
        IOUtils.copy(inputStream, response.getOutputStream());

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=export_projectManagers" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        response.flushBuffer();

    }
}
