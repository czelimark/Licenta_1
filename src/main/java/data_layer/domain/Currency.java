package data_layer.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "Currencies")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"currencyName"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency implements Comparable<Currency> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 3)
    private String currencyName;

    @Override
    public int compareTo(Currency c) {
        return this.id.compareTo(c.id);
    }
}
