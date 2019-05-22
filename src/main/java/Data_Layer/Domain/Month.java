package Data_Layer.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Months")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"monthName"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 10, max = 10)
    private String monthName;

    @ManyToOne
    @JoinColumn(name = "idBudget", nullable = false)
    private Budget budget;
}
