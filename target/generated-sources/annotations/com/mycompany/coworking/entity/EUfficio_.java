package com.mycompany.coworking.entity;

import com.mycompany.coworking.StatoUfficioEnum;
import com.mycompany.coworking.entity.EFoto;
import com.mycompany.coworking.entity.EIndirizzo;
import com.mycompany.coworking.entity.EIntervalloDisponibilita;
import com.mycompany.coworking.entity.ELocatore;
import com.mycompany.coworking.entity.EServiziAggiuntivi;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-28T21:35:20")
@StaticMetamodel(EUfficio.class)
public class EUfficio_ { 

    public static volatile SingularAttribute<EUfficio, ELocatore> locatore;
    public static volatile SingularAttribute<EUfficio, String> titolo;
    public static volatile SingularAttribute<EUfficio, EIndirizzo> indirizzo;
    public static volatile SetAttribute<EUfficio, EIntervalloDisponibilita> intervalliDisponibilita;
    public static volatile SetAttribute<EUfficio, EServiziAggiuntivi> serviziAggiuntivi;
    public static volatile SingularAttribute<EUfficio, LocalDateTime> dataApprovazione;
    public static volatile SingularAttribute<EUfficio, StatoUfficioEnum> stato;
    public static volatile SingularAttribute<EUfficio, String> descrizione;
    public static volatile SetAttribute<EUfficio, EFoto> foto;
    public static volatile SingularAttribute<EUfficio, Integer> numeroPostazioni;
    public static volatile SingularAttribute<EUfficio, LocalDateTime> dataRifiuto;
    public static volatile SingularAttribute<EUfficio, Float> superficie;
    public static volatile SingularAttribute<EUfficio, LocalDateTime> dataCancellazione;
    public static volatile SingularAttribute<EUfficio, Integer> prezzo;
    public static volatile SingularAttribute<EUfficio, UUID> id;
    public static volatile SingularAttribute<EUfficio, LocalDateTime> dataCaricamento;
    public static volatile SingularAttribute<EUfficio, String> motivoRifiuto;

}