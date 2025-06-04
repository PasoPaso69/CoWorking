/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Model.entity;
import com.mycompany.coworking1.Model.entity.EUfficio;
import org.hibernate.annotations.GenericGenerator;


/**
 *
 * @author 39327
 */
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Foto")
public class EFoto {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ufficio_id")
    private EUfficio ufficio;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "size")
    private int size;

    public EFoto() {
        // id verrà generato automaticamente
    }

    // Getters and setters
    public String getId() {
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
        return "EFoto(id=" + id + ", mimeType=" + mimeType + ", size=" + size + ")";
    }
}

