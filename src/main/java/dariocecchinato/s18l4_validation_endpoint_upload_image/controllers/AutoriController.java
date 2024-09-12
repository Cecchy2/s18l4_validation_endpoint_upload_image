package dariocecchinato.s18l4_validation_endpoint_upload_image.controllers;


import dariocecchinato.s18l4_validation_endpoint_upload_image.entities.Autore;
import dariocecchinato.s18l4_validation_endpoint_upload_image.services.AutoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public Autore save(@RequestBody Autore body){
        return this.autoriService.save(body);
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
