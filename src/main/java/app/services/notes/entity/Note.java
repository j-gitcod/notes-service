package app.services.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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

	@ManyToOne
	@JoinColumn(name="email", nullable=false)
	private User user;
}