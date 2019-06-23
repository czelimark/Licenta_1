package data_layer.domain;

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
public class ProjectResource implements Comparable<ProjectResource> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idProject")
    private Project project;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idResource")
    private Resource resource;

    @NotNull
    @Size(min = 1)
    private Double resourceCost;

    @Override
    public int compareTo(ProjectResource p) {
        return this.id.compareTo(p.id);
    }
}
