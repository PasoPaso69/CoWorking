package com.mycompany.coworking.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Table(name = "Foto")
public class EFoto {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false, unique = true, length = 36)
    private UUID id;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ufficio_id") // Puoi rinominarlo in base al tuo schema
    private EUfficio ufficio;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "size")
    private int size;

    // Costruttore
    public EFoto() {}

    // Getters e Setters

    public UUID getId() {
        return id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public EUfficio getUfficio() {
        return ufficio;
    }

    public void setUfficio(EUfficio ufficio) {
        this.ufficio = ufficio;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "EFoto{" +
                "id=" + id +
                ", mimeType='" + mimeType + '\'' +
                ", size=" + size +
                '}';
    }
}
