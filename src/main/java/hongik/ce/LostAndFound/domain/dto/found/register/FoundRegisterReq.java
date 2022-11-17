package hongik.ce.LostAndFound.domain.dto.found.register;

import hongik.ce.LostAndFound.domain.entity.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoundRegisterReq {

    private Long userId;
    private String title;
    private String category;
    private String lost_location;
    private String lost_detail;
    private String store_location;
    private String store_detail;
    private String content;
    private MultipartFile imagefile;
}
