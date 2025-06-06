/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Model.entity;

import jakarta.persistence.*;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;


/**
 *
 * @author 39327
 */
@Entity
@Table(name = "Indirizzi")
public class EIndirizzo {
   

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @Column(nullable = false)
    private String via;

    @Column(name = "numero_civico", nullable = false)
    private String numeroCivico;

    @Column(nullable = false)
    private String citta;

    @Column(length = 2, nullable = false)
    private String provincia;

    @Column(length = 5, nullable = false)
    private String cap;

    public EIndirizzo() {
        // id sarà generato automaticamente
    }

    // Getter e Setter
    public String getId() {
        return id;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    @Override
    public String toString() {
        return "Indirizzo: " + via + " " + numeroCivico + ", " + citta + ", " + provincia + ", " + cap;
    }
}


