package dariocecchinato.s18l4_validation_endpoint_upload_image.controllers;


import dariocecchinato.s18l4_validation_endpoint_upload_image.entities.BlogPost;
import dariocecchinato.s18l4_validation_endpoint_upload_image.exceptions.BadRequestException;
import dariocecchinato.s18l4_validation_endpoint_upload_image.payloads.PayloadBodyBlogPostDTO;
import dariocecchinato.s18l4_validation_endpoint_upload_image.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostsController {
    @Autowired
    private BlogPostsService blogPostsService;

    @GetMapping
    public Page<BlogPost> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy){
        return this.blogPostsService.findAllBlogs(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost creaBlog(@RequestBody @Validated PayloadBodyBlogPostDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()){
            String messages= validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel Payload. " +messages);
        }else{
            return this.blogPostsService.saveBlogPost(body);
        }

    }

    @GetMapping("/{blogPostId}")
    public BlogPost getBlogById(@PathVariable UUID blogPostId){
        return this.blogPostsService.findById(blogPostId);
    }

    @PutMapping("/{blogPostId}")
    public BlogPost getBlogByIdAndUpdate(@PathVariable UUID blogPostId, @RequestBody PayloadBodyBlogPostDTO body){
        return blogPostsService.findByIdAndUpdate(blogPostId,body);
    }

    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getBlogByIdAndDelete(@PathVariable UUID blogPostId){
        blogPostsService.findByIdAndDelete(blogPostId);
    }
}
