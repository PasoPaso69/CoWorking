/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Model.entity;

/**
 *
 * @author 39327
 */import jakarta.persistence.*;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Servizi_Aggiuntivi")
public class EServiziAggiuntivi {

      @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "idUfficio")
    private EUfficio ufficio;

    @Column(name = "nome_servizio", nullable = false)
    private String nomeServizio;

    public EServiziAggiuntivi() {
        // Costruttore di default richiesto da Hibernate
    }

    // Getter e Setter

    public String getId() {
        return id;
    }

    public EUfficio getUfficio() {
        return ufficio;
    }

    public EServiziAggiuntivi setUfficio(EUfficio ufficio) {
        this.ufficio = ufficio;
        return this;
    }

    public String getNomeServizio() {
        return nomeServizio;
    }

    public EServiziAggiuntivi setNomeServizio(String nomeServizio) {
        this.nomeServizio = nomeServizio;
        return this;
    }

    @Override
    public String toString() {
        return "EServiziAggiuntivi(Ufficio: " + (ufficio != null ? ufficio.toString() : "null") +
               ", Nome Servizio: " + nomeServizio + ")";
    }
}

