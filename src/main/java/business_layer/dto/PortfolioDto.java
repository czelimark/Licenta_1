package business_layer.dto;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDto implements Comparable<PortfolioDto>, Serializable {

    public static final long serialVersionUID = 81L;

    private Integer id;

    @NotNull
    @Size(min = 1, max = 100)
    private String portfolioName;

    @NotNull
    @Size(max = 200)
    private String description;

    @NotNull
    private Date issueDate;

    @NotNull
    private UserDto user;

    @Override
    public int compareTo(PortfolioDto p) {
        return this.id.compareTo(p.id);
    }

}
