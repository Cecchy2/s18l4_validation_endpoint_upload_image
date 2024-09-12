package dariocecchinato.s18l4_validation_endpoint_upload_image.payloads;

import java.time.LocalDate;

public record AutorePayloadDTO(
        String nome,
        String cognome,
        String email,
        LocalDate dataDiNascita) {
}
