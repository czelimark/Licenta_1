package data_layer.repositories;

import data_layer.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPortfolioRepository extends JpaRepository<Portfolio, Integer> {

    @Query("SELECT p FROM Portfolios p WHERE p.user.username = (?1)")
    List<Portfolio> findByUser(String username);

    @Modifying
    @Query("DELETE FROM Portfolios p WHERE p.id = (?1)")
    void deletePortfolioById(Integer id);
}
