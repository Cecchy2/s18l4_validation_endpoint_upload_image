package dariocecchinato.s18l4_validation_endpoint_upload_image.exceptions;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String msg){
		super(msg);
	}
}
