package Data_Layer.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "ProjectCosts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idProject", nullable = false)
    private Project project;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idResource", nullable = false)
    private Resource resource;

    @NotNull
    @Size(min = 1)
    private Double projectCost;
}
