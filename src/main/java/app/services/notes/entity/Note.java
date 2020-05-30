package app.services.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Note")
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=50)
	private String title;

	@Column(length=1000)
	private String description;

	@Column(nullable = false,columnDefinition = "TIMESTAMP")
	private LocalDateTime created;

	@Column(nullable = true, columnDefinition = "TIMESTAMP")
	private LocalDateTime updated;

	public Note(String title, String description, User user) {
		this.title = title;
		this.description = description;
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name="email", nullable=false)
	private User user;

	@PrePersist
	protected void prePersist() {
		if (this.created == null) created = LocalDateTime.now();
	}

	@PreUpdate
	protected void preUpdate() {
		this.updated = LocalDateTime.now();
	}
}