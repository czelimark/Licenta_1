package business_layer.dto;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDto implements Serializable {

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

    private UserDto user;
}
