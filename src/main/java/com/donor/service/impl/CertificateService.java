//package com.donor.service.impl;
//
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.donor.Certificate;
//import com.example.certificate.pdf.CertificatePdfGenerator;
//import com.example.certificate.repository.CertificateRepository;
//
//import io.minio.*;
//import io.minio.errors.*;
//
//@Service
//public class CertificateService {
//
//    private final CertificateRepository repo;
//    private final MinioClient minio;
//    private final String bucket;
//    private final String baseUrl;
//
//    public CertificateService(CertificateRepository repo, MinioClient minio,
//                              @Value("${minio.bucket}") String bucket,
//                              @Value("${app.base-url}") String baseUrl) {
//        this.repo = repo;
//        this.minio = minio;
//        this.bucket = bucket;
//        this.baseUrl = baseUrl;
//        ensureBucketExists();
//    }
//
//    private void ensureBucketExists() {
//        try {
//            boolean exists = minio.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
//            if (!exists) {
//                minio.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
//            }
//        } catch (Exception e) {
//            throw new IllegalStateException("MinIO bucket check/create failed", e);
//        }
//    }
//
//    @Transactional
//    public Certificate generateAndStoreCertificate(String donorName, String donorEmail, LocalDateTime donationDate, String badge) {
//        // persist metadata first
//        Certificate cert = new Certificate();
//        cert.setDonorName(donorName);
//        cert.setDonorEmail(donorEmail);
//        cert.setBadge(badge);
//        cert.setDonationDate(donationDate);
//        cert.setSerial(generateSerial());
//        cert.setGeneratedAt(LocalDateTime.now());
//        cert = repo.save(cert);
//
//        // generate verification URL (public endpoint that can verify certificate)
//        String verifyUrl = baseUrl + "/donor/certificate/" + cert.getId() + "/verify";
//
//        // generate PDF to byte array
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//            // load resources
//            InputStream fontIs = getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf");
//            InputStream logoIs = getClass().getResourceAsStream("/images/logo.png");
//
//            CertificatePdfGenerator.generateCertificate(
//                    donorName,
//                    donationDate.toLocalDate().toString(),
//                    cert.getSerial(),
//                    badge,
//                    verifyUrl,
//                    logoIs,
//                    fontIs,
//                    baos
//            );
//
//            byte[] pdfBytes = baos.toByteArray();
//            String objectName = "certificates/cert-" + cert.getId() + "-" + UUID.randomUUID() + ".pdf";
//
//            // upload to MinIO
//            try (ByteArrayInputStream bais = new ByteArrayInputStream(pdfBytes)) {
//                minio.putObject(PutObjectArgs.builder()
//                        .bucket(bucket)
//                        .object(objectName)
//                        .stream(bais, pdfBytes.length, -1)
//                        .contentType("application/pdf")
//                        .build());
//            }
//
//            // update cert with object key
//            cert.setMinioObjectName(objectName);
//            cert = repo.save(cert);
//            return cert;
//
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to generate or upload certificate PDF", e);
//        } catch (Exception ex) {
//            throw new RuntimeException("MinIO upload failed", ex);
//        }
//    }
//
//    private String generateSerial() {
//        return "BB-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
//    }
//
//    public InputStream getCertificatePdfStream(Long id) {
//        Certificate cert = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Certificate not found"));
//        if (cert.getMinioObjectName() == null) throw new IllegalStateException("Certificate PDF not generated yet");
//
//        try {
//            GetObjectArgs args = GetObjectArgs.builder()
//                    .bucket(bucket)
//                    .object(cert.getMinioObjectName())
//                    .build();
//            return minio.getObject(args); // caller must close stream
//        } catch (Exception e) {
//            throw new RuntimeException("Could not fetch PDF from storage", e);
//        }
//    }
//
//    // basic verify (for QR verification endpoint)
//    public boolean verifyCertificate(Long id, String serial) {
//        Certificate cert = repo.findById(id).orElse(null);
//        return cert != null && cert.getSerial().equals(serial);
//    }
//}
//
