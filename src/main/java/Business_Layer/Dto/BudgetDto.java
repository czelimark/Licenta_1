package Business_Layer.Dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDto implements Serializable {

    public static final long serialVersionUID = 83L;

    private Integer id;

    @NotNull
    @Size(max = 100)
    private String budgetDescription;

    @NotNull
    @Size(min = 1)
    private Double allocatedMoney;

    private ProjectDto project;

    private List<MonthDto> months;
}
