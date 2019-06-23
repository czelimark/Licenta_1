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
public class ProjectResourceDto implements Comparable<ProjectResourceDto>, Serializable {

    public static final long serialVersionUID = 88L;

    private Integer id;

    private ProjectDto project;

    private ResourceDto resource;

    @NotNull
    @Size(min = 1)
    private Double resourceCost;

    @Override
    public int compareTo(ProjectResourceDto p) {
        return this.id.compareTo(p.id);
    }
}
