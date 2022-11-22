package hongik.ce.LostAndFound.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "lost")
@DynamicInsert
public class Lost {


    @Id
    @Column(name = "lostId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lostId;

    public Lost(User user, String title, String contents, String date,String location) {
        this.user = user;
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.location = location;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "location")
    private String location;

    @Column(name = "date")
    private String date;

    @Column(name = "hit", columnDefinition = "INTEGER default 0")
    private Long hit;
}
