package com.mycompany.coworking.entity;

import com.mycompany.coworking.FasciaOrariaEnum;
import com.mycompany.coworking.entity.EPagamento;
import com.mycompany.coworking.entity.EProfilo;
import com.mycompany.coworking.entity.ESegnalazione;
import com.mycompany.coworking.entity.EUfficio;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-28T21:35:20")
@StaticMetamodel(EPrenotazione.class)
public class EPrenotazione_ { 

    public static volatile SingularAttribute<EPrenotazione, EUfficio> ufficio;
    public static volatile SingularAttribute<EPrenotazione, EProfilo> utente;
    public static volatile SingularAttribute<EPrenotazione, LocalDateTime> data;
    public static volatile SingularAttribute<EPrenotazione, ESegnalazione> segnalazione;
    public static volatile SingularAttribute<EPrenotazione, FasciaOrariaEnum> fascia;
    public static volatile SingularAttribute<EPrenotazione, UUID> id;
    public static volatile SingularAttribute<EPrenotazione, EPagamento> pagamento;

}