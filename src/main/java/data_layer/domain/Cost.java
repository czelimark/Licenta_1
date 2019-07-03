package data_layer.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Costs")
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
    @ManyToOne
    @JoinColumn(name="idProject", nullable=false)
    private Project project;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idResource", nullable=false)
    private Resource resource;

    @NotNull
    @Size(max = 100)
    private String description;

    private Integer quantity;

    @NotNull
    private Double cost;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idCurrency", nullable = false)
    private Currency currency;

    @Override
    public int compareTo(Cost c) {
        return this.id.compareTo(c.id);
    }
}
