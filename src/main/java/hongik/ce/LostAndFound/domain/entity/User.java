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
