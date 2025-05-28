package com.mycompany.coworking.entity;

import javax.persistence.*;


/**
 * Classe che rappresenta il locatore, estende EProfilo.
 */
@Entity
@Table(name = "Profili_Locatori")
public class ELocatore extends EProfilo {

    @Column(name = "partita_iva", nullable = false, length = 20)
    private String partitaIva;

    public ELocatore() {
        // Costruttore vuoto richiesto da Hibernate
    }

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
