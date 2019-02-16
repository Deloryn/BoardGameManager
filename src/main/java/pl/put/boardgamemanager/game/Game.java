package pl.put.boardgamemanager.game;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "games", schema = "public", catalog = "postgres")
public class Game {

    @SequenceGenerator(name = "games_seq", sequenceName = "games_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "games_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotBlank
    @Column(name = "publisher", nullable = false, length = 100)
    private String publisher;

    @Column(name = "minplayers", nullable = false)
    private Short minplayers;

    @Column(name = "maxplayers", nullable = false)
    private Short maxplayers;

    @Column(name = "avgtime", nullable = false)
    private Timestamp avgtime;

    @Column(name = "description", length = 300)
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Short getMinplayers() {
        return minplayers;
    }

    public void setMinplayers(Short minplayers) {
        this.minplayers = minplayers;
    }

    public Short getMaxplayers() {
        return maxplayers;
    }

    public void setMaxplayers(Short maxplayers) {
        this.maxplayers = maxplayers;
    }

    public Timestamp getAvgtime() {
        return avgtime;
    }

    public void setAvgtime(Timestamp avgtime) {
        this.avgtime = avgtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
                Objects.equals(name, game.name) &&
                Objects.equals(publisher, game.publisher) &&
                Objects.equals(minplayers, game.minplayers) &&
                Objects.equals(maxplayers, game.maxplayers) &&
                Objects.equals(avgtime, game.avgtime) &&
                Objects.equals(description, game.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publisher, minplayers, maxplayers, avgtime, description);
    }
}
