package com.mycompany.coworking.entity;

import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-28T21:35:20")
@StaticMetamodel(EProfilo.class)
public class EProfilo_ { 

    public static volatile SingularAttribute<EProfilo, LocalDate> dataNascita;
    public static volatile SingularAttribute<EProfilo, String> cognome;
    public static volatile SingularAttribute<EProfilo, String> nome;
    public static volatile SingularAttribute<EProfilo, UUID> id;
    public static volatile SingularAttribute<EProfilo, Boolean> isAdmin;
    public static volatile SingularAttribute<EProfilo, String> idUtente;
    public static volatile SingularAttribute<EProfilo, String> telefono;

}