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
public class ResourceDto implements Serializable {

    public static final long serialVersionUID = 87L;

    @NotNull
    @Size(min = 1, max = 100)
    private String resourceName;

    @NotNull
    @Size(max = 100)
    private String resourceUsage;
}
