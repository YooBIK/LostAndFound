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
@Table(name = "found")
@DynamicInsert
public class Found {

    @Id
    @Column(name = "foundId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foundId;

    public Found(User user,String title,Category category,String lost_location,String lost_detail,String store_location,String store_detail,String content,String date) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.lost_location = lost_location;
        this.lost_detail = lost_detail;
        this.store_location = store_location;
        this.store_detail = store_detail;
        this.content = content;
        this.date = date;
    }


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @Column(name = "lost_location")
    private String lost_location;

    @Column(name = "lost_detail")
    private String lost_detail;

    @Column(name = "store_location")
    private String store_location;

    @Column(name = "store_detail")
    private String store_detail;

//    @Column(name = "imagePath")
//    private String imagePath;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private String date;

    @Column(name = "hit", columnDefinition = "INTEGER default 0")
    private Long hit;
}
