package hongik.ce.LostAndFound.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Student")
public class Student {
    private String studentId;
    private String studentName;
    private String studentEmail;
    private String studentNickname;
}
