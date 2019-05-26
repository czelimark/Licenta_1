package Business_Layer.Dto;

import Data_Layer.Domain.Cost;
import lombok.*;

import javax.persistence.*;
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
public class ProjectDto implements Serializable {

    public static final long serialVersionUID = 80L;

    @NotNull
    @Size(min = 1, max = 100)
    private String projectName;

    @NotNull
    @Size(max = 200)
    private String description;

    @NotNull
    private Date issueDate;

    @NotNull
    private Date closingDate;

    @NotNull
    private Double estimatedPrice;

    @NotNull
    private Double actualPrice;

    @NotNull
    private Double difference;

    @NotNull
    @Size(max = 100)
    private String comments;

    private PortfolioDto portfolio;

    private WalletDto wallet;

    private List<CostDto> costs;

    private List<MonthDto> months;
}
