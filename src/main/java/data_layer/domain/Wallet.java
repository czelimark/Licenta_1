package data_layer.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Wallets")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"walletName"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 50)
    private String walletName;

    @NotNull
    @Size(max = 100)
    private String walletDescription;

    @NotNull
    @Size(min = 1)
    private Double allocatedMoney;

    @ManyToOne
    private User user;

    @ManyToOne
    private Currency currency;
}
