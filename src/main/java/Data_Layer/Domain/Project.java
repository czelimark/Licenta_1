package Data_Layer.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity(name = "Projects")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"projectName"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 100)
    private String projectName;

    @NotNull
    @Size(max = 200)
    private String description;

    @NotNull
    private Date issueDate;

    @NotNull
    private Date closingDate;

    @NotNull
    private Double estimatedPrice;

    @NotNull
    private Double actualPrice;

    @NotNull
    private Double difference;

    @NotNull
    @Size(max = 100)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "idPortfolio", nullable = false)
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "idWallet", nullable = false)
    private Wallet wallet;

    @OneToMany(mappedBy = "project")
    private List<Budget> budgets;
}