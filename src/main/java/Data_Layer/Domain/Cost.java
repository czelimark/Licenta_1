package Data_Layer.Domain;

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
public class Cost {

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
    private Project project;
}
