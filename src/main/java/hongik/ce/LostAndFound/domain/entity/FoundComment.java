package hongik.ce.LostAndFound.domain.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FoundComment")
public class FoundComment {

    public FoundComment(User user, Found found, String contents, String date) {
        this.user = user;
        this.found = found;
        this.contents = contents;
        this.date = date;
    }

    @Id
    @Column(name = "foundCommentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foundCommentId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "foundId")
    private Found found;


    @Column(name = "contents")
    private String contents;

    @Column(name = "date")
    private String date;


}
