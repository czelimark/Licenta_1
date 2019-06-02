package data_layer.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "ProjectMonth")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Project project;

    @ManyToOne(optional = false)
    private Month month;
}
