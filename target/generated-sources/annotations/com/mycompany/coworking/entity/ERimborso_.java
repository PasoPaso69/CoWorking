package com.mycompany.coworking.entity;

import com.mycompany.coworking.entity.ESegnalazione;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-28T21:35:20")
@StaticMetamodel(ERimborso.class)
public class ERimborso_ { 

    public static volatile SingularAttribute<ERimborso, ESegnalazione> segnalazione;
    public static volatile SingularAttribute<ERimborso, Integer> importo;
    public static volatile SingularAttribute<ERimborso, UUID> id;

}