package hongik.ce.LostAndFound.domain.dto.lost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LostListByLocationRes {

    private String location;
    private Long count;
}
