package dariocecchinato.s18l4_validation_endpoint_upload_image.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class PayloadBodyBlogPost {
    private UUID id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;
    private String autoreId;

    public PayloadBodyBlogPost(String categoria, String titolo, String cover, String contenuto, int tempoDiLettura, String autoreId) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.autoreId = autoreId;
    }
}
