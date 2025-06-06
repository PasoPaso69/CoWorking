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
@Table(name = "Pagamenti")
public class EPagamento {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;
    
    
    @OneToOne
    
    private EPrenotazione prenotazione;

    @Column(nullable = false)
    private int importo;

    public EPagamento() {
        // id generato automaticamente
    }

    public String getId() {
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
        return "EPagamento(ID: " + id + ", Prenotazione: " + prenotazione + ", Importo: " + importo + ")";
    }
}


