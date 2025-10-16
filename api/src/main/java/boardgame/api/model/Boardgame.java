package boardgame.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "boardgames")
public class Boardgame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // constructors, getters, setters
    public Boardgame() {}
    public Boardgame(String title) { this.title = title; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
