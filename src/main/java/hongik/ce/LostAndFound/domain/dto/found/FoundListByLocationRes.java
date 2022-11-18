package hongik.ce.LostAndFound.domain.dto.found;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoundListByLocationRes {
    String location;
    Long count;
}
