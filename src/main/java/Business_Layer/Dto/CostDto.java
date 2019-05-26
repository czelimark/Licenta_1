package Business_Layer.Dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostDto {

    public static final long serialVersionUID = 86L;

    @NotNull
    @Size(min = 3)
    private String costType;

    @NotNull
    private Double cost;

    @NotNull
    private ProjectDto project;
}
