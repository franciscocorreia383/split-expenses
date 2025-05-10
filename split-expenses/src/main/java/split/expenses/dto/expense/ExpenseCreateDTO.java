package split.expenses.dto.expense;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import split.expenses.dto.group.GroupDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class ExpenseCreateDTO {
    private String description;
    private double total;
    private long groupId;
}
