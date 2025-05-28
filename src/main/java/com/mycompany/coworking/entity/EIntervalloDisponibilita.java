package com.mycompany.coworking.entity;

import com.mycompany.coworking.FasciaOrariaEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Intervalli_disponibilita")
public class EIntervalloDisponibilita {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false, unique = true, columnDefinition = "CHAR(36)")
    private UUID id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ufficio_id") // nome della foreign key, assicurati sia corretto
    private EUfficio ufficio;

    @Column(name = "data_inizio", nullable = false)
    private LocalDateTime dataInizio;

    @Column(name = "fascia", nullable = false)
    @Enumerated(EnumType.STRING)
    private FasciaOrariaEnum fascia;

    @Column(name = "data_fine", nullable = false)
    private LocalDateTime dataFine;

    // Costruttore vuoto richiesto da JPA
    public EIntervalloDisponibilita() {
    }

    // Getter e Setter
    public UUID getId() {
        return id;
    }

    public EUfficio getUfficio() {
        return ufficio;
    }

    public void setUfficio(EUfficio ufficio) {
        this.ufficio = ufficio;
    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public FasciaOrariaEnum getFascia() {
        return fascia;
    }

    public void setFascia(FasciaOrariaEnum fascia) {
        this.fascia = fascia;
    }

    @Override
    public String toString() {
        return "EIntervalloDisponibilita{" +
                "ufficio=" + (ufficio != null ? ufficio.getId() : "null") +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", fascia=" + fascia +
                '}';
    }
}
