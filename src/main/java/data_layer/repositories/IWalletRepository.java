package data_layer.repositories;

import data_layer.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IWalletRepository extends JpaRepository<Wallet, Integer> {

    @Query("SELECT w FROM Wallets w WHERE w.user.username = (?1)")
    List<Wallet> findByUser(String username);

    @Modifying
    @Query("DELETE FROM Wallets w WHERE w.id = (?1)")
    void deleteWalletById(Integer id);
}
