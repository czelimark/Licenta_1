package data_layer.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Wallets")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"walletName"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet implements Comparable<Wallet> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 50)
    private String walletName;

    @NotNull
    @Size(max = 100)
    private String description;

    @NotNull
    @Min(value = 1)
    private Double allocatedMoney;

    @ManyToOne
    @JoinColumn(name="idUser", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="idCurrency", nullable=false)
    private Currency currency;

    @Override
    public int compareTo(Wallet w) {
        return this.id.compareTo(w.id);
    }
}
