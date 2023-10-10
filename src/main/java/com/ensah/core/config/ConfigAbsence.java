package com.ensah.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigAbsence {


    // Injection de la valeur de Seuil qui est dans fichier de Properties //
    @Value("${seuil.suppression.absence}")
    private int seuilSuppressionAbsence;

    public int getSeuilSuppressionAbsence() {
        return seuilSuppressionAbsence;
    }
}
