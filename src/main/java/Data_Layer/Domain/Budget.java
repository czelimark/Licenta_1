package Data_Layer.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "Budgets")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 100)
    private String budgetDescription;

    @NotNull
    @Size(min = 1)
    private Double allocatedMoney;

    @ManyToOne
    @JoinColumn(name = "idProject", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "budget")
    private List<Month> months;
}
