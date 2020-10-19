package tuanlm.hr.app.models.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	private int id;
	private String title;
	private String shortContent;
	private String content;
	private LocalDateTime createDate;
	private boolean active;
	private int authorId;
}
