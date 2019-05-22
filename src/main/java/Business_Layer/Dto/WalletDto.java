package Business_Layer.Dto;

import Data_Layer.Domain.Wallet;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity(name = "Wallets")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto implements Serializable {

    public static final long serialVersionUID = 82L;

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

    private UserDto user;

    private CurrencyDto currency;

    private List<CurrencyDto> currencies;

    private List<ProjectDto> projects;
}
