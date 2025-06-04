/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
/**
 *
 * @author 39327
 */

@Entity
@Table(name = "Profili_Locatori")
public class ELocatore extends EProfilo {



    // La partita IVA italiana è lunga 11 caratteri, qui riserviamo 15 per eventuali casi particolari
    @Column(name = "partita_iva", length = 15, nullable = false)
    private String partitaIva;

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    @Override
    public String toString() {
        return super.toString() + ", Partita IVA: " + partitaIva;
    }
}

