package com.mycompany.coworking.entity;
import com.mycompany.coworking.StatoUfficioEnum;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Uffici")
public class EUfficio {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char") // usa uuid-char per memorizzare UUID come stringa (36 caratteri)
    @Column(name = "id", updatable = false, nullable = false, unique = true, length = 36)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idLocatore")
    private ELocatore locatore;

    @OneToMany(mappedBy = "ufficio", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EFoto> foto = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "idIndirizzo")
    private EIndirizzo indirizzo;

    @Column(nullable = false)
    private String titolo;

    @OneToMany(mappedBy = "ufficio", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EIntervalloDisponibilita> intervalliDisponibilita = new HashSet<>();

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

    @OneToMany(mappedBy = "ufficio", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EServiziAggiuntivi> serviziAggiuntivi = new HashSet<>();

    // Costruttore
    public EUfficio() {
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public ELocatore getLocatore() {
        return locatore;
    }

    public void setLocatore(ELocatore locatore) {
        this.locatore = locatore;
    }

    public EIndirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(EIndirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Set<EFoto> getFoto() {
        return foto;
    }

    public void addFoto(EFoto foto) {
        this.foto.add(foto);
        foto.setUfficio(this);
    }

    public void removeFoto(EFoto foto) {
        this.foto.remove(foto);
        foto.setUfficio(null);
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Set<EIntervalloDisponibilita> getIntervalliDisponibilita() {
        return intervalliDisponibilita;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getNumeroPostazioni() {
        return numeroPostazioni;
    }

    public void setNumeroPostazioni(int numeroPostazioni) {
        this.numeroPostazioni = numeroPostazioni;
    }

    public float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public LocalDateTime getDataCaricamento() {
        return dataCaricamento;
    }

    public void setDataCaricamento(LocalDateTime dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
    }

    public LocalDateTime getDataCancellazione() {
        return dataCancellazione;
    }

    public void setDataCancellazione(LocalDateTime dataCancellazione) {
        this.dataCancellazione = dataCancellazione;
    }

    public StatoUfficioEnum getStato() {
        return stato;
    }

    public void setStato(StatoUfficioEnum stato) {
        this.stato = stato;
    }

    public LocalDateTime getDataApprovazione() {
        return dataApprovazione;
    }

    public void setDataApprovazione(LocalDateTime dataApprovazione) {
        this.dataApprovazione = dataApprovazione;
    }

    public LocalDateTime getDataRifiuto() {
        return dataRifiuto;
    }

    public void setDataRifiuto(LocalDateTime dataRifiuto) {
        this.dataRifiuto = dataRifiuto;
    }

    public String getMotivoRifiuto() {
        return motivoRifiuto;
    }

    public void setMotivoRifiuto(String motivoRifiuto) {
        this.motivoRifiuto = motivoRifiuto;
    }

    public Set<EServiziAggiuntivi> getServiziAggiuntivi() {
        return serviziAggiuntivi;
    }

    public void addServizioAggiuntivo(EServiziAggiuntivi servizio) {
        this.serviziAggiuntivi.add(servizio);
        servizio.setUfficio(this);
    }

    public void removeServizioAggiuntivo(EServiziAggiuntivi servizio) {
        this.serviziAggiuntivi.remove(servizio);
        servizio.setUfficio(null);
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
