package com.example.honorairesbatch.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "honoraires")
public class Honoraire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference_dossier", nullable = false)
    private String referenceDossier;

    @Column(name = "montant_du", nullable = false, precision = 15, scale = 2)
    private BigDecimal montantDu;

    @Column(name = "montant_honoraire", nullable = false, precision = 15, scale = 2)
    private BigDecimal montantHonoraire;

    @Column(name = "phase", nullable = false)
    private String phase;

    @Column(name = "taux_applique", precision = 5, scale = 2)
    private BigDecimal tauxApplique;

    @Column(name = "date_calcul", nullable = false)
    private LocalDateTime dateCalcul = LocalDateTime.now();

    @Column(name = "statut_facture")
    private String statutFacture = "A_FACTURER";
    // Getters et Setters manuels
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReferenceDossier() { return referenceDossier; }
    public void setReferenceDossier(String referenceDossier) { this.referenceDossier = referenceDossier; }

    public BigDecimal getMontantDu() { return montantDu; }
    public void setMontantDu(BigDecimal montantDu) { this.montantDu = montantDu; }

    public BigDecimal getMontantHonoraire() { return montantHonoraire; }
    public void setMontantHonoraire(BigDecimal montantHonoraire) { this.montantHonoraire = montantHonoraire; }

    public String getPhase() { return phase; }
    public void setPhase(String phase) { this.phase = phase; }

    public BigDecimal getTauxApplique() { return tauxApplique; }
    public void setTauxApplique(BigDecimal tauxApplique) { this.tauxApplique = tauxApplique; }

    public LocalDateTime getDateCalcul() { return dateCalcul; }
    public void setDateCalcul(LocalDateTime dateCalcul) { this.dateCalcul = dateCalcul; }

    public String getStatutFacture() { return statutFacture; }
    public void setStatutFacture(String statutFacture) { this.statutFacture = statutFacture; }
}