package split.expenses.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import split.expenses.dto.user.UserDTO;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class GroupCreateDTO {
    private String name;
    private UserDTO createdBy;
    private LocalDateTime createdAt;
}
