package com.mycompany.coworking.entity;
import javax.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Servizi_Aggiuntivi")
public class EServiziAggiuntivi {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", unique = true, nullable = false)
    private UUID id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ufficio_id") // puoi personalizzare il nome della colonna
    private EUfficio ufficio;

    @Column(name = "nome_servizio", nullable = false)
    private String nomeServizio;

    public EServiziAggiuntivi() {
    }

    public UUID getId() {
        return id;
    }

    public EUfficio getUfficio() {
        return ufficio;
    }

    public void setUfficio(EUfficio ufficio) {
        this.ufficio = ufficio;
    }

    public String getNomeServizio() {
        return nomeServizio;
    }

    public void setNomeServizio(String nomeServizio) {
        this.nomeServizio = nomeServizio;
    }

    @Override
    public String toString() {
        return "EServiziAggiuntivi(Ufficio: " + (ufficio != null ? ufficio.toString() : "null") + ", Nome Servizio: " + nomeServizio + ")";
    }
}
