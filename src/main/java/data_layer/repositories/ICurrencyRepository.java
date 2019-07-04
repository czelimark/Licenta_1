package data_layer.repositories;

import data_layer.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICurrencyRepository extends JpaRepository<Currency, Integer> {

    @Query("SELECT c FROM Currencies c WHERE c.id = (?1)")
    Optional<Currency> findById(Integer id);

    @Query("SELECT c FROM Currencies c WHERE c.currencyName = (?1)")
    Optional<Currency> findByName(String currencyName);
}
