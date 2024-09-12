package dariocecchinato.s18l4_validation_endpoint_upload_image.payloads;

import java.time.LocalDateTime;

public record ErrorPayloadDTO(String message, LocalDateTime errorTime) {
}
