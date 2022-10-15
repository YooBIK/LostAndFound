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
}
