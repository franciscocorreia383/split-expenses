package split.expenses.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import split.expenses.dto.participant.ParticipantDTO;
import split.expenses.dto.user.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class GroupDTO {

    private long id;

    private String name;

    private UserDTO createdBy;

    private LocalDateTime createdAt;

    private List<ParticipantDTO> participants;


}
