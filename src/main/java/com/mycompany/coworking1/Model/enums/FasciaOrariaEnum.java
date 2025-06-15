/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.coworking1.Model.enums;

public enum FasciaOrariaEnum {
    MATTINA("Mattina"),
    POMERIGGIO("Pomeriggio");

    private final String label;

    FasciaOrariaEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static FasciaOrariaEnum fromLabel(String label) {
        for (FasciaOrariaEnum fascia : values()) {
            if (fascia.label.equalsIgnoreCase(label)) {
                return fascia;
            }
        }
        throw new IllegalArgumentException("Fascia oraria non valida: " + label);
    }
}

