package data_layer.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity(name = "Resources")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"resourceName"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource implements Comparable<Resource> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 100)
    private String resourceName;

    @Override
    public int compareTo(Resource r) {
        return this.id.compareTo(r.id);
    }
}
