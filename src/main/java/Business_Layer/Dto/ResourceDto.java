package Business_Layer.Dto;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
@Entity(name = "Resources")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto implements Serializable {

    public static final long serialVersionUID = 87L;

    private Integer id;

    @NotNull
    @Size(min = 1, max = 100)
    private String resourceName;

    @NotNull
    @Size(max = 100)
    private String usage;

    private List<CurrencyDto> currencies;

    private List<CostDto> costs;
}
