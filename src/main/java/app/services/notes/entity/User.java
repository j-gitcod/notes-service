package app.services.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserUser")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private String email;

	@Column(nullable=false)
	@Length(min = 8, message = "Password must be at least 8 characters")
	private String password;

	@Column(nullable = false,columnDefinition = "TIMESTAMP")
	private LocalDateTime created;

	@Column(nullable = true, columnDefinition = "TIMESTAMP")
	private LocalDateTime updated;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private List<Note> notes;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private List<Authority> authorities;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@PrePersist
	protected void prePersist() {
		if (this.created == null) created = LocalDateTime.now();
	}

	@PreUpdate
	protected void preUpdate() {
		this.updated = LocalDateTime.now();
	}
}