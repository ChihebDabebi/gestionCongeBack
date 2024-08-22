package com.tenstep.gestionConge.utils;

import java.io.IOException;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfWriter;
import com.tenstep.gestionConge.Models.Historique;
import jakarta.servlet.http.HttpServletResponse;

public class PdfGenerator {

    public void generate(List<Historique> historiqueList, HttpServletResponse response) throws DocumentException, IOException {
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        // Opening the created document to change it
        document.open();

        // Creating font for title
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        // Creating paragraph for title
        Paragraph paragraphTitle = new Paragraph("Historique Details", fontTitle);
        // Aligning the title paragraph in the document
        paragraphTitle.setAlignment(Paragraph.ALIGN_CENTER);
        // Adding the title paragraph to the document
        document.add(paragraphTitle);

        // Creating font for content
        Font fontContent = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontContent.setSize(12);

        // Adding some spacing after the title
        document.add(new Paragraph(" "));

        // Iterating the list of historiques
        for (Historique historique : historiqueList) {
            // Creating a paragraph for each historique
            Paragraph historiqueParagraph = new Paragraph();
            historiqueParagraph.setFont(fontContent);

            // Adding historique details


            historiqueParagraph.add(new Phrase("Date: ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
            historiqueParagraph.add(historique.getDate() + "\n");

            historiqueParagraph.add(new Phrase("Details: ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
            historiqueParagraph.add(historique.getDetails() + "\n");

            // Adding some spacing after each historique
            historiqueParagraph.add(new Phrase("\n"));

            // Adding the historique paragraph to the document
            document.add(historiqueParagraph);
        }

        // Closing the document
        document.close();
    }
}
