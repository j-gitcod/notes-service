package app.services.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Authorities")
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true, nullable=false)
    private int id;

    @Column(nullable=false)
    private String authority;

    public Authority(String authority, User user) {
        this.authority = authority;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="email", nullable=false)
    private User user;

    @Column(nullable = false,columnDefinition = "TIMESTAMP")
    private LocalDateTime created;

    @Column(nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime updated;

    @PrePersist
    protected void prePersist() {
        if (this.created == null) created = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updated = LocalDateTime.now();
    }
}
