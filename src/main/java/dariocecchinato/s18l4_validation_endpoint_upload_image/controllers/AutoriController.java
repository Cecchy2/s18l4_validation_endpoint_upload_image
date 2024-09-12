package dariocecchinato.s18l4_validation_endpoint_upload_image.controllers;


import dariocecchinato.s18l4_validation_endpoint_upload_image.entities.Autore;
import dariocecchinato.s18l4_validation_endpoint_upload_image.exceptions.BadRequestException;
import dariocecchinato.s18l4_validation_endpoint_upload_image.payloads.AutorePayloadDTO;
import dariocecchinato.s18l4_validation_endpoint_upload_image.services.AutoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autori")
public class AutoriController {
    @Autowired
    private AutoriService autoriService;

    @GetMapping
    public Page<Autore> getAllAutori(@RequestParam(defaultValue = "0")int page,
                                     @RequestParam(defaultValue = "10")int size,
                                     @RequestParam(defaultValue = "nome")String sortby){
        return this.autoriService.findAll(page, size, sortby);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autore save(@RequestBody @Validated AutorePayloadDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()){
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        }else{
            Autore newAutore =this.autoriService.save(body);
            return newAutore;
        }

    }

    @GetMapping("/{autoreId}")
    public Autore getAutoreById(@PathVariable UUID autoreId){
        return autoriService.findAutoreById(autoreId);
    }

    @PutMapping("/{autoreId}")
    public Autore getAutoreByIdAndUpdate(@PathVariable UUID autoreId, @RequestBody Autore body){
        return autoriService.findAutoreByIdAndUpdate(autoreId, body);
    }

    @DeleteMapping("/{autoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete (@PathVariable UUID autoreId){
        autoriService.findByIdAndDelete(autoreId);
    }

}
