/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Model.entity;
import com.mycompany.coworking1.Model.entity.EFoto;
import com.mycompany.coworking1.Model.enums.StatoUfficioEnum;
import com.mycompany.coworking1.Model.entity.EIntervalliDisponibilita;
/**
 *
 * @author 39327
 */import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Uffici")
public class EUfficio {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idLocatore")
    private ELocatore locatore;

    @OneToMany(mappedBy = "ufficio", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<EFoto> foto = new HashSet<>();

    @Column(name = "is_hidden", nullable = false)
    private boolean isHidden = false;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idIndirizzo")
    private EIndirizzo indirizzo;

    @Column(nullable = false)
    private String titolo;

    @OneToMany(mappedBy = "ufficio", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<EIntervalliDisponibilita> intervalliDisponibilita = new HashSet<>();

    @Column(nullable = false)
    private int prezzo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descrizione;

    @Column(name = "numero_postazioni", nullable = false)
    private int numeroPostazioni;

    @Column(nullable = false)
    private float superficie;

    @Column(name = "data_caricamento", nullable = false)
    private LocalDateTime dataCaricamento;

    @Column(name = "data_cancellazione")
    private LocalDateTime dataCancellazione;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoUfficioEnum stato;

    @Column(name = "data_approvazione")
    private LocalDateTime dataApprovazione;

    @Column(name = "data_rifiuto")
    private LocalDateTime dataRifiuto;

    @Column(name = "motivo_rifiuto")
    private String motivoRifiuto;

    @OneToMany(mappedBy = "ufficio", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<ESegnalazione> segnalazioni = new HashSet<>();

    @OneToMany(mappedBy = "ufficio", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<EServiziAggiuntivi> serviziAggiuntivi = new HashSet<>();

    public EUfficio() {
        // Costruttore vuoto richiesto da Hibernate
    }

    // Getters e Setters

    public String getId() {
        return id;
    }

    public ELocatore getLocatore() {
        return locatore;
    }

    public EUfficio setLocatore(ELocatore locatore) {
        this.locatore = locatore;
        return this;
    }

    public Set<EFoto> getFoto() {
        return foto;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public EUfficio setHidden(boolean hidden) {
        isHidden = hidden;
        return this;
    }

    public EIndirizzo getIndirizzo() {
        return indirizzo;
    }

    public EUfficio setIndirizzo(EIndirizzo indirizzo) {
        this.indirizzo = indirizzo;
        return this;
    }

    public String getTitolo() {
        return titolo;
    }

    public EUfficio setTitolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    public Set<EIntervalliDisponibilita> getIntervalliDisponibilita() {
        return intervalliDisponibilita;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public EUfficio setPrezzo(int prezzo) {
        this.prezzo = prezzo;
        return this;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public EUfficio setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public int getNumeroPostazioni() {
        return numeroPostazioni;
    }

    public EUfficio setNumeroPostazioni(int numeroPostazioni) {
        this.numeroPostazioni = numeroPostazioni;
        return this;
    }

    public float getSuperficie() {
        return superficie;
    }

    public EUfficio setSuperficie(float superficie) {
        this.superficie = superficie;
        return this;
    }

    public LocalDateTime getDataCaricamento() {
        return dataCaricamento;
    }

    public EUfficio setDataCaricamento(LocalDateTime dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
        return this;
    }

    public LocalDateTime getDataCancellazione() {
        return dataCancellazione;
    }

    public EUfficio setDataCancellazione(LocalDateTime dataCancellazione) {
        this.dataCancellazione = dataCancellazione;
        return this;
    }

    public StatoUfficioEnum getStato() {
        return stato;
    }

    public EUfficio setStato(StatoUfficioEnum stato) {
        this.stato = stato;
        return this;
    }

    public LocalDateTime getDataApprovazione() {
        return dataApprovazione;
    }

    public EUfficio setDataApprovazione(LocalDateTime dataApprovazione) {
        this.dataApprovazione = dataApprovazione;
        return this;
    }

    public LocalDateTime getDataRifiuto() {
        return dataRifiuto;
    }

    public EUfficio setDataRifiuto(LocalDateTime dataRifiuto) {
        this.dataRifiuto = dataRifiuto;
        return this;
    }

    public String getMotivoRifiuto() {
        return motivoRifiuto;
    }

    public EUfficio setMotivoRifiuto(String motivoRifiuto) {
        this.motivoRifiuto = motivoRifiuto;
        return this;
    }

    public Set<ESegnalazione> getSegnalazioni() {
        return segnalazioni;
    }

    public Set<EServiziAggiuntivi> getServiziAggiuntivi() {
        return serviziAggiuntivi;
    }

    // Metodi per gestione bidirezionale delle relazioni

    public EUfficio addFoto(EFoto foto) {
        if (this.foto.add(foto)) {
            foto.setUfficio(this);
        }
        return this;
    }

    public EUfficio removeFoto(EFoto foto) {
        if (this.foto.remove(foto)) {
            if (foto.getUfficio() == this) {
                foto.setUfficio(null);
            }
        }
        return this;
    }

    public EUfficio addServizioAggiuntivo(EServiziAggiuntivi servizio) {
        if (this.serviziAggiuntivi.add(servizio)) {
            servizio.setUfficio(this);
        }
        return this;
    }

    public EUfficio removeServizioAggiuntivo(EServiziAggiuntivi servizio) {
        if (this.serviziAggiuntivi.remove(servizio)) {
            if (servizio.getUfficio() == this) {
                servizio.setUfficio(null);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return "EUfficio{" +
                "id=" + id +
                ", locatore=" + locatore +
                ", indirizzo=" + indirizzo +
                ", titolo='" + titolo + '\'' +
                ", prezzo=" + prezzo +
                ", descrizione='" + descrizione + '\'' +
                ", numeroPostazioni=" + numeroPostazioni +
                ", superficie=" + superficie +
                ", dataCaricamento=" + dataCaricamento +
                ", dataCancellazione=" + dataCancellazione +
                ", stato=" + stato +
                ", dataApprovazione=" + dataApprovazione +
                ", dataRifiuto=" + dataRifiuto +
                ", motivoRifiuto='" + motivoRifiuto + '\'' +
                '}';
    }
}


