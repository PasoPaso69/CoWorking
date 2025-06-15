/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Model.entity;

/**
 *
 * @author 39327
 */
import jakarta.persistence.*;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Segnalazioni")
public class ESegnalazione {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idUfficio", nullable = false)
    private EUfficio ufficio;

    @Column(nullable = false)
    private String commento;
    
    @Column(name = "is_hidden", nullable = false)
    private boolean solved = false;

    @OneToOne(mappedBy = "segnalazione", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private ERimborso rimborso;
    
     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private EProfilo utente;

    public ESegnalazione() {
        // Costruttore di default richiesto da Hibernate
    }

    // Getter e Setter

    public String getId() {
        return id;
    }

    public EUfficio getUfficio() {
        return ufficio;
    }

    public ESegnalazione setUfficio(EUfficio ufficio) {
        this.ufficio = ufficio;
        return this;
    }

    public String getCommento() {
        return commento;
    }

    public ESegnalazione setCommento(String commento) {
        this.commento = commento;
        return this;
    }

    public ERimborso getRimborso() {
        return rimborso;
    }
    
    public ESegnalazione setSolved(Boolean Solved){
        this.solved=Solved;
        return this;
    }
  
    
    public ESegnalazione setRimborso(ERimborso rimborso) {
        this.rimborso = rimborso;
        return this;
    }
    
    public ESegnalazione setUtente (EProfilo utente){
        this.utente = utente;
        return this;
    }
    public EProfilo getUtente(){
        return utente;
    }

    @Override
    public String toString() {
        return "ESegnalazione(ID: " + id +
               ", Ufficio: " + (ufficio != null ? ufficio.toString() : "null") +
               ", Commento: " + commento + ")";
    }
}


