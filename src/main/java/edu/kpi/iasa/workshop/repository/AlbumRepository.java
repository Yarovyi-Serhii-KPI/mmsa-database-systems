package edu.kpi.iasa.workshop.repository;

import edu.kpi.iasa.workshop.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findById(Long id);
}
