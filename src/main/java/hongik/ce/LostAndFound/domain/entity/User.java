package hongik.ce.LostAndFound.domain.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "User")
public class User {

    public User(String studentNumber, String studentName, String studentEmail, String studentNickname) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentNickname = studentNickname;
    }

    @Id
    @Column(name = "SID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "SNUM")
    private String studentNumber;

    @Column(name = "SNAME")
    private String studentName;

    @Column(name = "SEMAIL")
    private String studentEmail;

    @Column(name = "NNAME")
    private String studentNickname;
}
