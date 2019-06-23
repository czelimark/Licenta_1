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
public class CostDto implements Comparable<CostDto>, Serializable {

    public static final long serialVersionUID = 86L;

    private Integer id;

    @NotNull
    @Size(min = 3)
    private String costType;

    @NotNull
    private Double cost;

    @NotNull
    private ProjectDto project;

    @Override
    public int compareTo(CostDto c) {
        return this.id.compareTo(c.id);
    }
}
