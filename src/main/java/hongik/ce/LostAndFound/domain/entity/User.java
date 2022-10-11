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

    public User(String studentNumber, String studentName, String studentEmail, String studentNickname, String password) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentNickname = studentNickname;
        this.password = password;
    }

    @Id
    @Column(name = "sid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sId;

    @Column(name = "studentNumber")
    private String studentNumber;

    @Column(name = "studentName")
    private String studentName;

    @Column(name = "studentEmail")
    private String studentEmail;

    @Column(name = "studentNickname")
    private String studentNickname;

    @Column(name = "password")
    private String password;
}
