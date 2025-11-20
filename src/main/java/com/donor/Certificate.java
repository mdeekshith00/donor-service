package com.donor;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String donorName;

    private String donorEmail;

    private String badge; // Bronze/Silver/Gold etc

    private String serial; // unique serial / certificate id

    private LocalDateTime donationDate;

    private String minioObjectName; // e.g. certificates/cert-123.pdf

    private LocalDateTime generatedAt;

}
