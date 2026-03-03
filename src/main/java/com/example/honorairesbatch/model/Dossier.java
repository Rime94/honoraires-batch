package com.example.honorairesbatch.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dossiers")
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", nullable = false, unique = true)
    private String reference;

    @Column(name = "montant_du", nullable = false, precision = 15, scale = 2)
    private BigDecimal montantDu;

    @Column(name = "montant_recupere", precision = 15, scale = 2)
    private BigDecimal montantRecupere = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutDossier statut = StatutDossier.EN_ATTENTE;

    @Column(name = "region")
    private String region;  // ← Changé de Region à String

    @Column(name = "agence")
    private String agence;  // ← Changé de Agence à String

    @Column(name = "phase", nullable = false)
    private String phase; // "AMIABLE" ou "CONTENTIEUX"

    @Column(name = "date_import", nullable = false)
    private LocalDate dateImport = LocalDate.now();

    @Column(name = "est_cloture", nullable = false)
    private Boolean estCloture = false;

    // Méthodes métier
    public boolean isAmiable() {
        return "AMIABLE".equalsIgnoreCase(phase);
    }

    public boolean isContentieux() {
        return "CONTENTIEUX".equalsIgnoreCase(phase);
    }

    public boolean isCloture() {
        return estCloture;
    }

    public boolean peutEtreAffecte() {
        return !estCloture && statut == StatutDossier.EN_ATTENTE;
    }

    public void clôturer() {
        this.estCloture = true;
        this.statut = StatutDossier.CLOTURE;
    }

// Getters et Setters manuels
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getReference() { return reference; }
public void setReference(String reference) { this.reference = reference; }

public BigDecimal getMontantDu() { return montantDu; }
public void setMontantDu(BigDecimal montantDu) { this.montantDu = montantDu; }

public BigDecimal getMontantRecupere() { return montantRecupere; }
public void setMontantRecupere(BigDecimal montantRecupere) { this.montantRecupere = montantRecupere; }

public StatutDossier getStatut() { return statut; }
public void setStatut(StatutDossier statut) { this.statut = statut; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getAgence() { return agence; }
    public void setAgence(String agence) { this.agence = agence; }


public String getPhase() { return phase; }
public void setPhase(String phase) { this.phase = phase; }

public LocalDate getDateImport() { return dateImport; }
public void setDateImport(LocalDate dateImport) { this.dateImport = dateImport; }

public Boolean getEstCloture() { return estCloture; }
public void setEstCloture(Boolean estCloture) { this.estCloture = estCloture; }
}