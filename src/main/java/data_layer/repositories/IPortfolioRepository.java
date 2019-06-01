package data_layer.repositories;

import data_layer.domain.Portfolio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPortfolioRepository extends JpaRepository<Portfolio, Integer> {

    @Query("SELECT p FROM Portfolios p WHERE p.user.email = (?1)")
    List<Portfolio> findByUser(String email);

    @Query("SELECT p FROM Portfolios p WHERE p.portfolioName = (?1)")
    Optional<Portfolio> findByName(String portfolioName);

    @Modifying
    @Query("DELETE FROM Portfolios p WHERE p.id = (?1)")
    void deletePortfolioById(Integer id);
}
