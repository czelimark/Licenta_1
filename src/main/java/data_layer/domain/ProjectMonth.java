package data_layer.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "ProjectMonth")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMonth implements Comparable<ProjectMonth> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idProject")
    private Project project;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idMonth")
    private Month month;

    @Override
    public int compareTo(ProjectMonth p) {
        return this.id.compareTo(p.id);
    }
}
