package Data_Layer.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "Currencies")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"currencyName"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 3)
    private String currencyName;

    @ManyToOne
    @JoinColumn(name = "walletId", nullable = false)
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "idResource", nullable = false)
    private Resource resource;
}
