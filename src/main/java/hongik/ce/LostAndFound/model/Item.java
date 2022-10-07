package hongik.ce.LostAndFound.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Item")
public class Item {
    private Long itemId;
    private String studentId;
    private String findLocation;
    private String storeLocation;
    private String imagePath;
    private String itemTitle;
    private String mainText;
}
