package dariocecchinato.s18l4_validation_endpoint_upload_image.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;


public record PayloadBodyBlogPostDTO(
        @NotNull(message = "Inserisci la categoria")
                @Size(min = 5, max = 30, message = "La categoria è troppo corta o troppo lunga")
        String categoria,
        @NotNull(message = "Inserisci il titolo")
        @Size(min = 5, max = 30, message = "Il Titolo è troppo corto o troppo lungo")
        String titolo,
        @NotNull(message = "Non hai inserito contenuto")
        String contenuto,
        int tempoDiLettura,
        String autoreId){}
