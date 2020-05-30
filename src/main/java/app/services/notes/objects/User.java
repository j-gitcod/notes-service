package app.services.notes.objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
  private String username;
  private String password;
  private String[] roles;

  public User(String username, String password, String... roles) {
    this.username = username;
    this.password = password;
    this.roles = roles;
  }

  // Getter and Setter methods
}