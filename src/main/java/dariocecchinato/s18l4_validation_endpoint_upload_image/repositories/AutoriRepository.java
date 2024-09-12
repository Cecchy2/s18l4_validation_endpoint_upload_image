package dariocecchinato.s18l4_validation_endpoint_upload_image.repositories;


import dariocecchinato.s18l4_validation_endpoint_upload_image.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutoriRepository extends JpaRepository<Autore, UUID> {

    boolean existsByEmail(String email);

    Optional<Autore> findByEmail(String email);
}
