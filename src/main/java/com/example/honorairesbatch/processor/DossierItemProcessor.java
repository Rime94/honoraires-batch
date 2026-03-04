package com.example.honorairesbatch.processor;

import com.example.honorairesbatch.model.Dossier;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DossierItemProcessor implements ItemProcessor<Dossier, Dossier> {

    @Override
    public Dossier process(Dossier dossier) throws Exception {
        // ✅ NE FAIT RIEN - Retourne simplement le dossier
        // Le statut reste EN_ATTENTE (valeur par défaut dans Dossier.java)
        return dossier;
    }
}