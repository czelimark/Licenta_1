package Data_Layer.Domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Data
@Entity(name = "Resources")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"resourceName"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 100)
    private String resourceName;

    @NotNull
    @Size(max = 100)
    private String usage;

    @OneToMany(mappedBy = "resource")
    private List<Currency> currencies;

    @OneToMany(mappedBy = "resource")
    private List<Cost> costs;
}
