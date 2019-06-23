package business_layer.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMonthDto implements Comparable<ProjectMonthDto> {

    public static final long serialVersionUID = 83L;

    private Integer id;

    private ProjectDto project;

    private MonthDto month;

    @Override
    public int compareTo(ProjectMonthDto p) {
        return this.id.compareTo(p.id);
    }
}
