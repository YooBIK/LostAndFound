package hongik.ce.LostAndFound.domain.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Table(name = "UploadFile")
public class UploadFile {
    private String uploadFilename;  // 작성자가 업로드한 파일명
    private String storeFilename;   // 서버 내부에서 관리하는 파일명
}