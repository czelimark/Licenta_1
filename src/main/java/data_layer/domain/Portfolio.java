package data_layer.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity(name = "Portfolios")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"portfolioName"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio implements Comparable<Portfolio> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 100)
    private String portfolioName;

    @NotNull
    @Size(max = 200)
    private String description;

    @NotNull
    private Date issueDate;

    @ManyToOne
    @JoinColumn(name="idUser", nullable=false)
    private User user;

    @Override
    public int compareTo(Portfolio p) {
        return this.id.compareTo(p.id);
    }
}
