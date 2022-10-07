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
    @Column(name = "IID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(name = "SID")
    private String studentId;

    @Column(name = "FLOC")
    private String findLocation;

    @Column(name = "SLOC")
    private String storeLocation;

    @Column(name = "IMGPATH")
    private String imagePath;

    @Column(name = "TITLE")
    private String itemTitle;

    @Column(name = "CONTENTS")
    private String mainText;
}
