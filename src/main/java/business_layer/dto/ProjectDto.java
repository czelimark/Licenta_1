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
public class ProjectDto implements Comparable<ProjectDto>, Serializable {

    public static final long serialVersionUID = 80L;

    private Integer id;

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

    private Double actualPrice;

    private Double difference;

    @Size(max = 100)
    private String comments;

    private PortfolioDto portfolio;

    private WalletDto wallet;

    @Override
    public int compareTo(ProjectDto p) {
        return this.id.compareTo(p.id);
    }
}
