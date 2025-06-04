/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Model.entity;
import com.mycompany.coworking1.Model.enums.FasciaOrariaEnum;

/**
 *
 * @author 39327
 */
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "Prenotazioni")
public class EPrenotazione {

        @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "ufficio_id")
    private EUfficio ufficio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private EProfilo utente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FasciaOrariaEnum fascia;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

  
    @OneToOne(mappedBy = "prenotazione", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private EPagamento pagamento;

    public EPrenotazione() {
        // Costruttore vuoto richiesto da Hibernate
    }

    // Getters e Setters

    public String getId() {
        return id;
    }

    public EUfficio getUfficio() {
        return ufficio;
    }

    public void setUfficio(EUfficio ufficio) {
        this.ufficio = ufficio;
    }

    public EProfilo getUtente() {
        return utente;
    }

    public void setUtente(EProfilo utente) {
        this.utente = utente;
    }

    public FasciaOrariaEnum getFascia() {
        return fascia;
    }

    public void setFascia(FasciaOrariaEnum fascia) {
        this.fascia = fascia;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public EPagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(EPagamento pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        return "EPrenotazione(ID: " + id + 
               ", Ufficio: " + (ufficio != null ? ufficio.toString() : "null") + 
               ", Utente: " + (utente != null ? utente.toString() : "null") + 
               ", Fascia: " + (fascia != null ? fascia.name() : "null") + 
               ", Data: " + (data != null ? data.toString() : "null") + ")";
    }
}


