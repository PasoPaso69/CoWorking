package com.mycompany.coworking.entity;

import com.mycompany.coworking.entity.EPrenotazione;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-28T21:35:20")
@StaticMetamodel(EPagamento.class)
public class EPagamento_ { 

    public static volatile SingularAttribute<EPagamento, EPrenotazione> prenotazione;
    public static volatile SingularAttribute<EPagamento, Integer> importo;
    public static volatile SingularAttribute<EPagamento, UUID> id;

}