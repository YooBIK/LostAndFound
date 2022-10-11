package hongik.ce.LostAndFound.domain.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Item")
public class Lost {

    @Id
    @Column(name = "lid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lid;

    @Column(name = "sid")
    private String sid;

    @Column(name = "findLocation")
    private String findLocation;

    @Column(name = "storeLocation")
    private String storeLocation;

    @Column(name = "imagePath")
    private String imagePath;

    @Column(name = "itemTitle")
    private String itemTitle;

    @Column(name = "contents")
    private String contents;
}
