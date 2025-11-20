package com.donor.controller;


import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadCertificate(@PathVariable String fileName) {
        try {
            String filePath = "src/main/resources/static/certificates/" + fileName;
            byte[] fileData = Files.readAllBytes(Paths.get(filePath));
            ByteArrayResource resource = new ByteArrayResource(fileData);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

//    private final CertificateService service;
//
//    public CertificateController(CertificateService service) {
//        this.service = service;
//    }
//
//    // 1) Generate certificate (sync) - you would call this after a successful donation
//    @PostMapping("/certificate/generate")
//    public ResponseEntity<Map<String, Object>> generate(@RequestBody GenerateRequest req) {
//        Certificate cert = service.generateAndStoreCertificate(req.getDonorName(), req.getDonorEmail(), req.getDonationDate(), req.getBadge());
//        return ResponseEntity.ok(Map.of(
//                "id", cert.getId(),
//                "serial", cert.getSerial(),
//                "generatedAt", cert.getGeneratedAt(),
//                "downloadEndpoint", "/donor/certificate/" + cert.getId() + "/download"
//        ));
//    }
//
//    // 2) Download certificate (streams PDF from MinIO)
//    @GetMapping("/certificate/{id}/download")
//    public ResponseEntity<byte[]> download(@PathVariable Long id) {
//        try (InputStream is = service.getCertificatePdfStream(id); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//            is.transferTo(baos);
//            byte[] pdf = baos.toByteArray();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_PDF);
//            headers.setContentDisposition(ContentDisposition.attachment().filename("certificate-" + id + ".pdf").build());
//            headers.setContentLength(pdf.length);
//
//            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("Error: " + e.getMessage()).getBytes());
//        }
//    }
//
//    // 3) Simple verify endpoint used by QR (example)
//    @GetMapping("/certificate/{id}/verify")
//    public ResponseEntity<String> verify(@PathVariable Long id, @RequestParam(required = false) String serial) {
//        boolean ok = service.verifyCertificate(id, serial == null ? "" : serial);
//        if (ok) return ResponseEntity.ok("Certificate verified");
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid certificate");
//    }
//
//    // DTO for generate request
//    public static class GenerateRequest {
//        private String donorName;
//        private String donorEmail;
//        private LocalDateTime donationDate;
//        private String badge;
//
//        public String getDonorName() { return donorName; }
//        public void setDonorName(String donorName) { this.donorName = donorName; }
//        public String getDonorEmail() { return donorEmail; }
//        public void setDonorEmail(String donorEmail) { this.donorEmail = donorEmail; }
//        public LocalDateTime getDonationDate() { return donationDate; }
//        public void setDonationDate(LocalDateTime donationDate) { this.donationDate = donationDate; }
//        public String getBadge() { return badge; }
//        public void setBadge(String badge) { this.badge = badge; }
//    }
//}
