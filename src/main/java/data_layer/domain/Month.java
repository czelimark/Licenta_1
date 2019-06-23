package data_layer.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "Months")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"monthName"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Month implements Comparable<Month> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 3, max = 10)
    private String monthName;

    private Integer nrOfProjects;

    @Override
    public int compareTo(Month m) {
        return this.id.compareTo(m.id);
    }
}
