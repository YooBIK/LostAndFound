package hongik.ce.LostAndFound.domain.entity;

import lombok.*;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "found")
public class Found {

    @Id
    @Column(name = "foundId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String foundId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @Column(name = "findLocation")
    private String findLocation;

    @Column(name = "storeLocation")
    private String storeLocation;

    @Column(name = "detailLocation")
    private String detailLocation;

    @Column(name = "imagePath")
    private String imagePath;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "date")
    private String date;
}