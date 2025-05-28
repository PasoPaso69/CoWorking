package com.mycompany.coworking.entity;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Segnalazioni")
public class ESegnalazione {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", unique = true, nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "prenotazione_id", nullable = false)
    private EPrenotazione prenotazione;

    @Column(nullable = false)
    private String commento;

    @OneToOne(mappedBy = "segnalazione", cascade = CascadeType.ALL, orphanRemoval = true)
    private ERimborso rimborso;

    public ESegnalazione() {
    }

    public UUID getId() {
        return id;
    }

    public EPrenotazione getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(EPrenotazione prenotazione) {
        this.prenotazione = prenotazione;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public ERimborso getRimborso() {
        return rimborso;
    }

    public void setRimborso(ERimborso rimborso) {
        this.rimborso = rimborso;
    }

    @Override
    public String toString() {
        return "ESegnalazione(ID: " + id + 
               ", Prenotazione: " + (prenotazione != null ? prenotazione.toString() : "null") + 
               ", Commento: " + commento + ")";
    }
}
