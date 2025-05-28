package com.mycompany.coworking.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Rimborsi")
public class ERimborso {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false, unique = true, columnDefinition = "CHAR(36)")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "idSegnalazione", referencedColumnName = "id", nullable = false)
    private ESegnalazione segnalazione;

    @Column(name = "importo", nullable = false)
    private int importo;

    public ERimborso() {
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public ESegnalazione getSegnalazione() {
        return segnalazione;
    }

    public void setSegnalazione(ESegnalazione segnalazione) {
        this.segnalazione = segnalazione;
    }

    public int getImporto() {
        return importo;
    }

    public void setImporto(int importo) {
        this.importo = importo;
    }

    @Override
    public String toString() {
        return "ERimborso{" +
                "id=" + id +
                ", segnalazione=" + (segnalazione != null ? segnalazione.getId() : "null") +
                ", importo=" + importo +
                '}';
    }
}
