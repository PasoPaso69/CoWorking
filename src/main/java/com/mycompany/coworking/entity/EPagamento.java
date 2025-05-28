package com.mycompany.coworking.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Pagamenti")
public class EPagamento {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false, unique = true, columnDefinition = "CHAR(36)")
    private UUID id;

    @OneToOne(mappedBy = "pagamento")
    private EPrenotazione prenotazione;

    @Column(name = "importo", nullable = false)
    private int importo;

    // Costruttore vuoto per JPA
    public EPagamento() {}

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public EPrenotazione getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(EPrenotazione prenotazione) {
        this.prenotazione = prenotazione;
    }

    public int getImporto() {
        return importo;
    }

    public void setImporto(int importo) {
        this.importo = importo;
    }

    @Override
    public String toString() {
        return "EPagamento{" +
                "id=" + id +
                ", prenotazione=" + (prenotazione != null ? prenotazione.getId() : "null") +
                ", importo=" + importo +
                '}';
    }
}
