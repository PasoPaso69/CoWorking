package com.mycompany.coworking.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Recensioni")
public class ERecensione {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false, unique = true, columnDefinition = "CHAR(36)")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "idPrenotazione", nullable = false, unique = true)
    private EPrenotazione prenotazione;

    @Column(name = "valutazione", nullable = false)
    private int valutazione;

    @Column(name = "commento", nullable = false)
    private String commento;

    public ERecensione() {}

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public EPrenotazione getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(EPrenotazione prenotazione) {
        this.prenotazione = prenotazione;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    @Override
    public String toString() {
        return "ERecensione{" +
                "id=" + id +
                ", prenotazione=" + (prenotazione != null ? prenotazione.getId() : "null") +
                ", valutazione=" + valutazione +
                ", commento='" + commento + '\'' +
                '}';
    }
}
