package com.example.honorairesbatch.processor;

import com.example.honorairesbatch.model.Dossier;
import com.example.honorairesbatch.model.Honoraire;
import com.example.honorairesbatch.model.StatutDossier;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class DossierItemProcessor implements ItemProcessor<Dossier, Honoraire> {

    // Barème des honoraires selon la phase
    private static final Map<String, BigDecimal> BAREMES = new HashMap<>();
    private static final BigDecimal HONORAIRE_MINIMUM = new BigDecimal("50.00");

    static {
        BAREMES.put("AMIABLE", new BigDecimal("0.10"));      // 10% pour l'amiable
        BAREMES.put("CONTENTIEUX", new BigDecimal("0.15"));  // 15% pour le contentieux
    }

    @Override
    public Honoraire process(Dossier dossier) throws Exception {

        // 1. Validation du dossier
        if (dossier.getMontantDu() == null || dossier.getMontantDu().compareTo(BigDecimal.ZERO) <= 0) {
            dossier.setStatut(StatutDossier.EN_ATTENTE);
            return null; // Ignorer ce dossier
        }

        // 2. Vérifier si le dossier est clôturé
        if (dossier.getEstCloture()) {
            dossier.setStatut(StatutDossier.CLOTURE);
            return null; // Ne pas calculer d'honoraires pour les dossiers clôturés
        }

        // 3. Récupérer le taux selon la phase
        BigDecimal taux = BAREMES.get(dossier.getPhase().toUpperCase());
        if (taux == null) {
            dossier.setStatut(StatutDossier.EN_ATTENTE);
            return null; // Phase inconnue
        }

        // 4. Calcul de l'honoraire
        BigDecimal montantBase = dossier.getMontantDu();
        BigDecimal honoraireCalcule = montantBase.multiply(taux);

        // Application du minimum
        BigDecimal honoraireFinal = honoraireCalcule.max(HONORAIRE_MINIMUM);

        // Arrondi à 2 décimales
        honoraireFinal = honoraireFinal.setScale(2, RoundingMode.HALF_UP);

        // 5. Mise à jour du statut du dossier
        dossier.setStatut(StatutDossier.EN_COURS);

        // 6. Création de l'honoraire
        Honoraire honoraire = new Honoraire();
        honoraire.setReferenceDossier(dossier.getReference());
        honoraire.setMontantDu(dossier.getMontantDu());
        honoraire.setMontantHonoraire(honoraireFinal);
        honoraire.setPhase(dossier.getPhase());
        honoraire.setTauxApplique(taux.multiply(new BigDecimal("100"))); // En pourcentage
        honoraire.setDateCalcul(LocalDateTime.now());

        return honoraire;
    }
}