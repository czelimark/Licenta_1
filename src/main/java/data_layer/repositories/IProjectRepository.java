package data_layer.repositories;

import data_layer.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Integer> {

    @Query("SELECT p FROM Projects p WHERE p.portfolio.id = (?1)")
    List<Project> findByPortfolio(Integer id);

    @Modifying
    @Query("DELETE FROM Projects p WHERE p.id = (?1)")
    void deleteProjectById(Integer id);
}
