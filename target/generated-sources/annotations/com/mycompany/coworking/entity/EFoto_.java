package com.mycompany.coworking.entity;

import com.mycompany.coworking.entity.EUfficio;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-05-28T21:35:20")
@StaticMetamodel(EFoto.class)
public class EFoto_ { 

    public static volatile SingularAttribute<EFoto, EUfficio> ufficio;
    public static volatile SingularAttribute<EFoto, Integer> size;
    public static volatile SingularAttribute<EFoto, UUID> id;
    public static volatile SingularAttribute<EFoto, String> mimeType;
    public static volatile SingularAttribute<EFoto, byte[]> content;

}