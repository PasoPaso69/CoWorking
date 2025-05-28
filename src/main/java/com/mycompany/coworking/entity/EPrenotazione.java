package com.mycompany.coworking.entity;
import com.mycompany.coworking.FasciaOrariaEnum;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Prenotazioni")
public class EPrenotazione {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false, unique = true, columnDefinition = "CHAR(36)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idUfficio", nullable = false)
    private EUfficio ufficio;

    @ManyToOne
    @JoinColumn(name = "idUtente", nullable = false)
    private EProfilo utente;

    @Enumerated(EnumType.STRING)
    @Column(name = "fascia", nullable = false)
    private FasciaOrariaEnum fascia;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @OneToOne(mappedBy = "prenotazione", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private EPagamento pagamento;

    @OneToOne(mappedBy = "prenotazione", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private ESegnalazione segnalazione;

    // Costruttore vuoto
    public EPrenotazione() {}

    // Getters e Setters
    public UUID getId() {
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

    public ESegnalazione getSegnalazione() {
        return segnalazione;
    }

    public void setSegnalazione(ESegnalazione segnalazione) {
        this.segnalazione = segnalazione;
    }

    @Override
    public String toString() {
        return "EPrenotazione{" +
                "id=" + id +
                ", ufficio=" + (ufficio != null ? ufficio.getId() : "null") +
                ", utente=" + (utente != null ? utente.getId() : "null") +
                ", fascia=" + fascia +
                ", data=" + data +
                '}';
    }
}
