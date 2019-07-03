package business_layer.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto implements Comparable<ResourceDto>, Serializable {

    public static final long serialVersionUID = 87L;

    private Integer id;

    @NotNull
    @Size(min = 1, max = 100)
    private String resourceName;

    @Override
    public int compareTo(ResourceDto r) {
        return this.id.compareTo(r.id);
    }
}
