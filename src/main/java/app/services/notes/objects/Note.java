package app.services.notes.objects;

import app.services.notes.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Note implements Serializable {

	private int id;
	private String title;
	private String description;
	private LocalDateTime created;
	private LocalDateTime updated;

}