package hongik.ce.LostAndFound.domain.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "lost")
public class Lost {


    @Id
    @Column(name = "lostId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lostId;

    public Lost(User user, Category category, String title, String contents, String date) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "date")
    private String date;
}
