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

    public User(String studentNumber, String password, String userName, String userEmail, String userNickname,String userPhoneNumber) {
        this.studentNumber = studentNumber;
        this.password = password;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userPhoneNumber = userPhoneNumber;
    }

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "studentNumber")
    private String studentNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "studentName")
    private String userName;

    @Column(name = "studentEmail")
    private String userEmail;

    @Column(name = "phoneNumber")
    private String userPhoneNumber;
    @Column(name = "nickname")
    private String userNickname;


}
