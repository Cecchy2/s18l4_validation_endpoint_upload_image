package dariocecchinato.s18l4_validation_endpoint_upload_image.repositories;


import dariocecchinato.s18l4_validation_endpoint_upload_image.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogPostsRepository extends JpaRepository<BlogPost, UUID> {
    Optional<BlogPost> findById (UUID blogPostId);

    BlogPost findByTitolo (String titolo);


}
