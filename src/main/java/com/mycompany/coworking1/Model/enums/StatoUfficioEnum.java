/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.coworking1.Model.enums;

/**
 *
 * @author 39327
 */
public enum StatoUfficioEnum {
        InAttesa("In attesa"),
    Approvato("Approvato"),
    NonApprovato("Non Approvato");

    private final String label;

    StatoUfficioEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
