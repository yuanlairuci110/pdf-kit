package com.pdftest;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 * Created by lichao on 2017/12/8.
 */
public class PDFTest {
    public static void main(String[] args) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter writer = PdfWriter.getInstance(document,     new FileOutputStream("D:/pdftest/ITextTest.pdf"));
            document.open();
            Anchor anchorTarget = new Anchor("First page of the document.");
            anchorTarget.setName("BackToTop");
            Paragraph paragraph1 = new Paragraph();

            paragraph1.setSpacingBefore(50);

            paragraph1.add(anchorTarget);
            document.add(paragraph1);

            document.add(new Paragraph("Some more text on the first page with different color and font type.",
                    FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(0, 255, 0, 0))));

            Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1",

                    FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,

                            new CMYKColor(0, 255, 255,17)));

            //document.add(title11);
            Paragraph title1 = new Paragraph("Chapter 1",

                    FontFactory.getFont(FontFactory.HELVETICA,

                            18, Font.BOLDITALIC, new CMYKColor(0, 255, 255,17)));

            Chapter chapter1 = new Chapter(title1, 1);

            chapter1.setNumberDepth(0);

            Section section1 = chapter1.addSection(title11);

            //document.add(title1);
            Paragraph someSectionText = new Paragraph("This text comes as part of section 1 of chapter 1.");

            section1.add(someSectionText);

            someSectionText = new Paragraph("Following is a 3 X 2 table.");

            section1.add(someSectionText);

            PdfPTable t = new PdfPTable(3);

            t.setSpacingBefore(25);

            t.setSpacingAfter(25);

            PdfPCell c1 = new PdfPCell(new Phrase("Header1"));

            t.addCell(c1);

            PdfPCell c2 = new PdfPCell(new Phrase("Header2"));

            t.addCell(c2);

            PdfPCell c3 = new PdfPCell(new Phrase("Header3"));

            t.addCell(c3);

            for(int i=0;i< 100;i++){
                t.addCell("1.1" + i);

                t.addCell("1.2" +i);

                t.addCell("1.3"+i);
            }
            section1.add(t);

            document.add(section1);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
