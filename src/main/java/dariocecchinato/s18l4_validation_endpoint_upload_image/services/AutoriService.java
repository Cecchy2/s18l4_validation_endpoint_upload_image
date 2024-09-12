package dariocecchinato.s18l4_validation_endpoint_upload_image.services;


import dariocecchinato.s18l4_validation_endpoint_upload_image.entities.Autore;
import dariocecchinato.s18l4_validation_endpoint_upload_image.exceptions.BadRequestException;
import dariocecchinato.s18l4_validation_endpoint_upload_image.exceptions.NotFoundException;
import dariocecchinato.s18l4_validation_endpoint_upload_image.repositories.AutoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AutoriService {
    @Autowired
    private AutoriRepository autoriRepository;


public Page<Autore> findAll(int page, int size, String sortby){
    if(page > 10) page = 10;
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortby));
    return this.autoriRepository.findAll(pageable);
}

public Autore save(Autore body){
   if (this.autoriRepository.existsByEmail(body.getEmail())) {throw new BadRequestException("L' email" + body.getEmail() + " è già in uso");
   } else {
       body.setAvatar("https://ui-avatars.com/api/?name="+ body.getNome()+ "+" + body.getCognome());
       return this.autoriRepository.save(body);}
}

public Autore findAutoreById(UUID autoreId){
   return this.autoriRepository.findById(autoreId).orElseThrow(()->new NotFoundException(autoreId));
}

public Autore findAutoreByIdAndUpdate(UUID autoreId, Autore updatedBody){
    this.autoriRepository.findByEmail(updatedBody.getEmail()).ifPresent(autore -> {
        throw new BadRequestException("L' email " + updatedBody.getEmail() + " è già in uso");
    });
    Autore found = this.findAutoreById(autoreId);
    found.setNome(updatedBody.getNome());
    found.setCognome(updatedBody.getCognome());
    found.setEmail(updatedBody.getEmail());
    found.setDataDiNascita(updatedBody.getDataDiNascita());
    found.setAvatar("https://ui-avatars.com/api/?name="+ updatedBody.getNome()+ "+" + updatedBody.getCognome());
    return this.autoriRepository.save(found);
}

public void findByIdAndDelete(UUID autoreId){
   Autore found = this.findAutoreById(autoreId);
   autoriRepository.delete(found);
}
}
