package business_layer.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMonthDto {

    public static final long serialVersionUID = 83L;

    private ProjectDto project;

    private MonthDto month;
}
