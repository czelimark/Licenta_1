package Business_Layer.Dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "Costs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostDto implements Serializable {

    public static final long serialVersionUID = 86L;

    private Integer id;

    @NotNull
    @Size(min = 1)
    private Double cost;

    private ResourceDto resource;
}
