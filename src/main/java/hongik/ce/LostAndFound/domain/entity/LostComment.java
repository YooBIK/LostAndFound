package hongik.ce.LostAndFound.domain.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LostComment")
public class LostComment {

    public LostComment(User user, Lost lost, String contents, String date) {
        this.user = user;
        this.lost = lost;
        this.contents = contents;
        this.date = date;
    }

    @Id
    @Column(name = "lostCommentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lostCommentId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lostId")
    private Lost lost;

    @Column(name = "contents")
    private String contents;

    @Column(name = "date")
    private String date;

}
