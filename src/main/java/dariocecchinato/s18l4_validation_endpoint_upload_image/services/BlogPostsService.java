package dariocecchinato.s18l4_validation_endpoint_upload_image.services;


import dariocecchinato.s18l4_validation_endpoint_upload_image.entities.Autore;
import dariocecchinato.s18l4_validation_endpoint_upload_image.entities.BlogPost;
import dariocecchinato.s18l4_validation_endpoint_upload_image.payloads.PayloadBodyBlogPostDTO;
import dariocecchinato.s18l4_validation_endpoint_upload_image.exceptions.NotFoundException;
import dariocecchinato.s18l4_validation_endpoint_upload_image.repositories.BlogPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BlogPostsService {
    @Autowired
    private BlogPostsRepository blogPostsRepository;

    @Autowired
    private AutoriService autoriService;

    public Page<BlogPost> findAllBlogs (int page, int size, String sortBy){
        if(page > 10) page = 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogPostsRepository.findAll(pageable);
    }

    public BlogPost saveBlogPost(PayloadBodyBlogPostDTO body){

        Autore autore = autoriService.findAutoreById(UUID.fromString(body.autoreId()));
        BlogPost blogPost = new BlogPost(body.categoria(),body.titolo(),"https://fastly.picsum.photos/id/848/200/300.jpg?hmac=cNClhUSP4IM6ZT6RTqdeCOLWYEJYBNXaqdflgf_EqD8",body.contenuto(), body.tempoDiLettura());
        blogPost.setAutore(autore);


        return blogPostsRepository.save(blogPost);
    }

    public BlogPost findById(UUID blogPostId){
        return this.blogPostsRepository.findById(blogPostId).orElseThrow(()->new NotFoundException(blogPostId));
    }

    public BlogPost findByIdAndUpdate (UUID blogPostId, PayloadBodyBlogPostDTO body){
        BlogPost found = this.blogPostsRepository.findById(blogPostId).orElseThrow(()->new NotFoundException(blogPostId));
        found.setTitolo(body.titolo());
        found.setCategoria(body.categoria());
        found.setCover("https://fastly.picsum.photos/id/848/200/300.jpg?hmac=cNClhUSP4IM6ZT6RTqdeCOLWYEJYBNXaqdflgf_EqD8");
        found.setContenuto(body.contenuto());
        found.setTempoDiLettura(body.tempoDiLettura());
        return found;
    }

    public void findByIdAndDelete (UUID blogPostId){
       BlogPost found= this.blogPostsRepository.findById(blogPostId).orElseThrow(()->new NotFoundException(blogPostId));
       blogPostsRepository.delete(found);
    }
}
