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
public class CurrencyDto implements Comparable<CurrencyDto>, Serializable {

    public static final long serialVersionUID = 85L;

    private Integer id;

    @NotNull
    @Size(max = 3)
    private String currencyName;

    @Override
    public int compareTo(CurrencyDto c) {
        return this.id.compareTo(c.id);
    }
}
