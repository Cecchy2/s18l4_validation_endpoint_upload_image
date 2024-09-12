package dariocecchinato.s18l4_validation_endpoint_upload_image.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AutorePayloadDTO(
        @NotEmpty(message = "Devi inserire il tuo nome")
        @Size(min = 3, max = 20, message = "Il nome deve essere compreso fra 3 e 20 caratteri")
        String nome,
        @NotEmpty(message = "Devi inserire il tuo cognome")
        String cognome,
        @NotEmpty(message = "Devi inserire la tua email")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotNull
        LocalDate dataDiNascita) {
}
