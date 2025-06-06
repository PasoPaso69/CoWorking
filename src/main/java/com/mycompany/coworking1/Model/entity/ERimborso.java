/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Model.entity;

/**
 *
 * @author 39327
 */
import jakarta.persistence.*;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Rimborsi")
public class ERimborso {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @OneToOne
    @JoinColumn(name = "idSegnalazione", referencedColumnName = "id")
    private ESegnalazione segnalazione;

    @Column(nullable = false)
    private int importo;

    public ERimborso() {
        // Costruttore di default richiesto da Hibernate
    }

    // Getter e Setter

    public String getId() {
        return id;
    }

    public ESegnalazione getSegnalazione() {
        return segnalazione;
    }

    public ERimborso setSegnalazione(ESegnalazione segnalazione) {
        this.segnalazione = segnalazione;
        return this;
    }

    public int getImporto() {
        return importo;
    }

    public ERimborso setImporto(int importo) {
        this.importo = importo;
        return this;
    }

    @Override
    public String toString() {
        return "ERimborso(ID: " + id +
               ", Segnalazione: " + (segnalazione != null ? segnalazione.toString() : "null") +
               ", Importo: " + importo + ")";
    }
}


