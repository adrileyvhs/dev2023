package com.dev2023.resources;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RestController
@RequestMapping(value = "/pdf")
public class PdfResource {
    @GetMapping("/pdf")
    public void gerarPDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"hello.pdf\"");
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Paragraph("Hello World!"));
            document.addTitle("TITULO DO PDF ");
            Integer total = 0;
            for (int i = 0; i <10 ; i++) {
                document.add(new Paragraph("Teste de GERAÇÃO DE PDF:   " + i));
                total =+i;

            }
            document.add(new Paragraph("Total :   " + total));
            document.close();
        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }
    }
    }

