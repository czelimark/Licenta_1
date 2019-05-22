package Business_Layer.Dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "ProjectCosts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCostDto implements Serializable {

    public static final long serialVersionUID = 88L;

    private ProjectDto project;

    private ResourceDto resource;

    @NotNull
    @Size(min = 1)
    private Double projectCost;
}
