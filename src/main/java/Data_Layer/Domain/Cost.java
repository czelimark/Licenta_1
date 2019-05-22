package Data_Layer.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity(name = "Costs")
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
    @Size(min = 1)
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "idResursa", nullable = false)
    private Resource resource;
}
