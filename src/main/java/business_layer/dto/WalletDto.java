package business_layer.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto implements Comparable<WalletDto>, Serializable {

    public static final long serialVersionUID = 82L;

    private Integer id;

    @NotNull
    @Size(min = 1, max = 50)
    private String walletName;

    @NotNull
    @Size(max = 100)
    private String description;

    @NotNull
    @Size(min = 1)
    private Double allocatedMoney;

    @NotNull
    private UserDto user;

    @NotNull
    private CurrencyDto currency;

    @Override
    public int compareTo(WalletDto w) {
        return this.id.compareTo(w.id);
    }
}
