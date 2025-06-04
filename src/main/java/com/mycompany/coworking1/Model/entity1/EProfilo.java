/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Model.entity;

/**
 *
 * @author 39327
 */import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Profili")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("utente") // Valore di default per questa classe base
public class EProfilo {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;



    @Column(length = 40, nullable = false)
    private String name;

    @Column(length = 40, nullable = false)
    private String surname;

    @Column(length = 20, nullable = false)
    private String phone;
     @Column(nullable = false)
    private String email;
     
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private boolean admin = false;

    public EProfilo() {
        // Costruttore di default richiesto da Hibernate
    }

    // Getter e Setter

    public String getId() {
        return id;
    }
    
    public EProfilo getId(String id){
        this.id=id;
        return this;
    }

    public String getEmail() {
        return email;
    }
    public EProfilo setPassword(String password){
        this.password = password;
        return this;
    }
    
    public String getPassword(){
        return password;
    }

    public EProfilo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public EProfilo setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public EProfilo setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public EProfilo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public LocalDate getDob() {
        return dob;
    }

    public EProfilo setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public boolean isAdmin() {
        return admin;
    }

    public EProfilo setAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }

    @Override
    public String toString() {
        return "EProfilo(ID: " + id +
               ", Nome: " + name +
               ", Cognome: " + surname +
               ", Telefono: " + phone +
               ", Data di Nascita: " + dob +
               ", Admin: " + (admin ? "Sì" : "No") + ")";
    }
}


