package data_layer.repositories;

import data_layer.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IResourceRepository extends JpaRepository<Resource, Integer> {

    @Query("SELECT r FROM Resources r WHERE r.id = (?1)")
    Optional<Resource> findById(Integer id);
}
