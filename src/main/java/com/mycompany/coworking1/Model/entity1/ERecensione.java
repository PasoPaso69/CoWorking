/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Model.entity;

/**
 *
 * @author 39327
 */import jakarta.persistence.*;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Recensioni")
public class ERecensione {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "idPrenotazione", nullable = false)
    private EPrenotazione prenotazione;

    @Column(nullable = false)
    private int valutazione;

    @Column(nullable = false)
    private String commento;

    public ERecensione() {
        // Costruttore di default richiesto da Hibernate
    }

    // Getter e Setter

    public String getId() {
        return id;
    }

    public EPrenotazione getPrenotazione() {
        return prenotazione;
    }

    public ERecensione setPrenotazione(EPrenotazione prenotazione) {
        this.prenotazione = prenotazione;
        return this;
    }

    public int getValutazione() {
        return valutazione;
    }

    public ERecensione setValutazione(int valutazione) {
        this.valutazione = valutazione;
        return this;
    }

    public String getCommento() {
        return commento;
    }

    public ERecensione setCommento(String commento) {
        this.commento = commento;
        return this;
    }

    @Override
    public String toString() {
        return "ERecensione(ID: " + id +
               ", Prenotazione: " + (prenotazione != null ? prenotazione.toString() : "null") +
               ", Valutazione: " + valutazione +
               ", Commento: " + commento + ")";
    }
}

