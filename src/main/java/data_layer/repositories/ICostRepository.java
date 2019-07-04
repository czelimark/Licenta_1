package data_layer.repositories;

import data_layer.domain.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICostRepository extends JpaRepository<Cost, Integer> {

    @Query("SELECT c FROM Costs c inner join Projects p on c.project = p inner join Portfolios p2 on p.portfolio = p2 WHERE p2.id = (?1)")
    List<Cost> findByPortfolio(Integer id);

    @Modifying
    @Query("DELETE FROM Costs c WHERE c.id = (?1)")
    void deleteCostById(Integer id);
}
