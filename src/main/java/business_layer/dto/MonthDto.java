package business_layer.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthDto implements Serializable {

    public static final long serialVersionUID = 84L;

    @NotNull
    @Size(min = 10, max = 10)
    private String monthName;
}
