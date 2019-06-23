package data_layer.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Costs")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"costType"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cost implements Comparable<Cost> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 3)
    private String costType;

    @NotNull
    private Double cost;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idProject", nullable=false)
    private Project project;

    @Override
    public int compareTo(Cost c) {
        return this.id.compareTo(c.id);
    }
}
