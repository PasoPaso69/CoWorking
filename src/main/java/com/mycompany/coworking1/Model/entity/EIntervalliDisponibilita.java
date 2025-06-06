/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Model.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import com.mycompany.coworking1.Model.enums.FasciaOrariaEnum;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * @author 39327
 */@Entity
@Table(name = "Intervalli_disponibilita")
public class EIntervalliDisponibilita {
  

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ufficio_id")
    private EUfficio ufficio;

    @Column(name = "data_inizio", nullable = false)
    private LocalDateTime dataInizio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FasciaOrariaEnum fascia;

    @Column(name = "data_fine", nullable = false)
    private LocalDateTime dataFine;

    public EIntervalliDisponibilita() {
        // id generato automaticamente
    }

    // Getter e Setter
    public String getId() {
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
        return "Intervallo Disponibilità (Ufficio: " + ufficio +
                ", Data Inizio: " + dataInizio +
                ", Data Fine: " + dataFine + ")";
    }
}


