package com.mycompany.coworking.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Table(name = "Indirizzi")
public class EIndirizzo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false, unique = true, length = 36)
    private UUID id;

    @Column(name = "via", nullable = false)
    private String via;

    @Column(name = "numero_civico", nullable = false)
    private String numeroCivico;

    @Column(name = "citta", nullable = false)
    private String citta;

    @Column(name = "provincia", length = 2, nullable = false)
    private String provincia;

    @Column(name = "cap", length = 5, nullable = false)
    private String cap;

    // Costruttore vuoto
    public EIndirizzo() {}

    // Getters
    public UUID getId() {
        return id;
    }

    public String getVia() {
        return via;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public String getCitta() {
        return citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCap() {
        return cap;
    }

    // Setters
    public void setVia(String via) {
        this.via = via;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    // toString
    @Override
    public String toString() {
        return "Indirizzo: " + via + " " + numeroCivico + ", " + citta + ", " + provincia + ", " + cap;
    }
}
