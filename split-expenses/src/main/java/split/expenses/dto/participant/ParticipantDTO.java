package split.expenses.dto.participant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import split.expenses.dto.group.GroupDTO;
import split.expenses.dto.user.UserDTO;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class ParticipantDTO {
    private int id;
    private UserDTO user;
    private GroupDTO group;
    private LocalDateTime createdAt;

}
