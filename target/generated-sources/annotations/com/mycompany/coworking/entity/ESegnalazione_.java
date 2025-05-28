package com.mycompany.coworking.entity;

import com.mycompany.coworking.entity.EPrenotazione;
import com.mycompany.coworking.entity.ERimborso;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-28T21:35:20")
@StaticMetamodel(ESegnalazione.class)
public class ESegnalazione_ { 

    public static volatile SingularAttribute<ESegnalazione, EPrenotazione> prenotazione;
    public static volatile SingularAttribute<ESegnalazione, ERimborso> rimborso;
    public static volatile SingularAttribute<ESegnalazione, String> commento;
    public static volatile SingularAttribute<ESegnalazione, UUID> id;

}