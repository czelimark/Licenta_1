package Business_Layer.Dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

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
