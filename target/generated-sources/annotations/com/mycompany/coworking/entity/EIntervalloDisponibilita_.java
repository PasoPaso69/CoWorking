package com.mycompany.coworking.entity;

import com.mycompany.coworking.FasciaOrariaEnum;
import com.mycompany.coworking.entity.EUfficio;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-28T21:35:20")
@StaticMetamodel(EIntervalloDisponibilita.class)
public class EIntervalloDisponibilita_ { 

    public static volatile SingularAttribute<EIntervalloDisponibilita, EUfficio> ufficio;
    public static volatile SingularAttribute<EIntervalloDisponibilita, LocalDateTime> dataInizio;
    public static volatile SingularAttribute<EIntervalloDisponibilita, LocalDateTime> dataFine;
    public static volatile SingularAttribute<EIntervalloDisponibilita, FasciaOrariaEnum> fascia;
    public static volatile SingularAttribute<EIntervalloDisponibilita, UUID> id;

}