////package com.donor.service.impl;
////
////package com.donor.utils;
////
////import java.awt.Font;
////import java.io.FileOutputStream;
////import java.time.format.DateTimeFormatter;
////
////import javax.swing.text.Document;
////
////import com.donor.entities.Donation;
////import com.donor.entities.Donor;
////import com.itextpdf.text.*;
////import com.itextpdf.text.pdf.PdfWriter;
////
////public class CertificateGenerator {
////
////    private static final String BASE_PATH = "src/main/resources/static/certificates/";
////
////    public static String generateCertificate(Donor donor, Donation donation) {
////        try {
////            String fileName = donor.getDonorId() + "_donation_" + donation.getDonationId() + ".pdf";
////            String filePath = BASE_PATH + fileName;
////
////            Document document = new Document(PageSize.A4);
////            PdfWriter.getInstance(document, new FileOutputStream(filePath));
////            document.open();
////
////            // Certificate Header
////            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.RED);
////            Paragraph title = new Paragraph("Certificate of Appreciation", titleFont);
////            title.setAlignment(Element.ALIGN_CENTER);
////            document.add(title);
////            document.add(Chunk.NEWLINE);
////
////            // Donor info
////            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);
////            Paragraph content = new Paragraph(
////                    "This certificate is proudly presented to\n\n" +
////                            donor.getName() +
////                            "\n\nin recognition of their blood donation on " +
////                            donation.getDonationDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")) +
////                            ".\n\nThank you for your contribution to saving lives!",
////                    normalFont);
////            content.setAlignment(Element.ALIGN_CENTER);
////            document.add(content);
////
////            document.add(Chunk.NEWLINE);
////            document.add(new Paragraph("Issued by BloodBank System", normalFont));
////            document.close();
////
////            return "/certificates/" + fileName; // Return relative path for UI display
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            throw new RuntimeException("Error generating certificate: " + e.getMessage());
////        }
////    }
////}
////
//
//
//
//
//package com.example.certificate.pdf;
//
//import com.google.zxing.*;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import org.apache.pdfbox.pdmodel.*;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.font.PDType0Font;
//import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
//import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.time.format.DateTimeFormatter;
//
//public class CertificatePdfGenerator {
//
//    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd MMM yyyy");
//
//    /**
//     * Generate a certificate PDF into the provided OutputStream.
//     * Uses embedded font and images from resources.
//     *
//     * @param donorName donor full name
//     * @param donationDate formatted date string
//     * @param serial certificate serial
//     * @param badge badge text (nullable)
//     * @param verificationUrl URL encoded in QR
//     * @param logoInputStream logo resource (nullable)
//     * @param fontInputStream TTF font stream (required)
//     * @param out OutputStream where PDF will be written
//     * @throws IOException on failure
//     */
//    public static void generateCertificate(
//            String donorName,
//            String donationDate,
//            String serial,
//            String badge,
//            String verificationUrl,
//            InputStream logoInputStream,
//            InputStream fontInputStream,
//            OutputStream out
//    ) throws IOException {
//
//        try (PDDocument doc = new PDDocument()) {
//            PDPage page = new PDPage(PDRectangle.A4);
//            doc.addPage(page);
//
//            PDType0Font font = PDType0Font.load(doc, fontInputStream);
//
//            PDRectangle mediaBox = page.getMediaBox();
//            float w = mediaBox.getWidth();
//            float h = mediaBox.getHeight();
//
//            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
//                // border
//                float margin = 40f;
//                cs.setStrokingColor(Color.DARK_GRAY);
//                cs.setLineWidth(2f);
//                cs.addRect(margin, margin, w - 2 * margin, h - 2 * margin);
//                cs.stroke();
//
//                // Title
//                cs.beginText();
//                cs.setFont(font, 26);
//                cs.newLineAtOffset(w / 2 - 130, h - 120);
//                cs.showText("Certificate of Appreciation");
//                cs.endText();
//
//                // Subtitle
//                cs.beginText();
//                cs.setFont(font, 12);
//                cs.newLineAtOffset(w / 2 - 120, h - 150);
//                cs.showText("This certificate is awarded to");
//                cs.endText();
//
//                // Recipient (centered)
//                cs.beginText();
//                cs.setFont(font, 20);
//                float nameWidth = font.getStringWidth(donorName) / 1000 * 20;
//                cs.newLineAtOffset((w - nameWidth) / 2, h - 190);
//                cs.showText(donorName);
//                cs.endText();
//
//                // donation line
//                cs.beginText();
//                cs.setFont(font, 12);
//                cs.newLineAtOffset(120, h - 230);
//                cs.showText("For donating blood on " + donationDate);
//                cs.endText();
//
//                // Badge on right
//                if (badge != null && !badge.isBlank()) {
//                    cs.beginText();
//                    cs.setFont(font, 14);
//                    cs.newLineAtOffset(w - 220, h - 200);
//                    cs.showText(badge);
//                    cs.endText();
//                }
//
//                // Serial bottom-left
//                cs.beginText();
//                cs.setFont(font, 10);
//                cs.newLineAtOffset(120, 80);
//                cs.showText("Certificate ID: " + serial);
//                cs.endText();
//
//                // Signature placeholder bottom-right
//                cs.beginText();
//                cs.setFont(font, 12);
//                cs.newLineAtOffset(w - 260, 120);
//                cs.showText("Authorized Signature");
//                cs.endText();
//
//                // Logo top-right if provided
//                if (logoInputStream != null) {
//                    byte[] logoBytes = logoInputStream.readAllBytes();
//                    PDImageXObject img = PDImageXObject.createFromByteArray(doc, logoBytes, "logo");
//                    float imgWidth = 100;
//                    float imgHeight = imgWidth * ((float) img.getHeight() / img.getWidth());
//                    cs.drawImage(img, w - margin - imgWidth, h - margin - imgHeight - 10, imgWidth, imgHeight);
//                }
//
//                // QR code bottom-right
//                if (verificationUrl != null && !verificationUrl.isBlank()) {
//                    BufferedImage qr = generateQrImage(verificationUrl, 150, 150);
//                    PDImageXObject qrImg = LosslessFactory.createFromImage(doc, qr);
//                    cs.drawImage(qrImg, w - margin - 150, 60, 120, 120);
//                }
//            }
//
//            doc.save(out);
//        }
//    }
//
//    private static BufferedImage generateQrImage(String text, int width, int height) {
//        try {
//            BitMatrix matrix = new MultiFormatWriter()
//                    .encode(text, BarcodeFormat.QR_CODE, width, height, null);
//            return MatrixToImageWriter.toBufferedImage(matrix);
//        } catch (WriterException e) {
//            throw new IllegalStateException("Could not generate QR", e);
//        }
//    }
//}
//
