package pl.put.boardgamemanager.game;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "games", schema = "public", catalog = "postgres")
public class Game {

    @SequenceGenerator(name = "games_seq", sequenceName = "games_seq", allocationSize = 1)
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
    private Short minPlayers;

    @Column(name = "maxplayers", nullable = false)
    private Short maxPlayers;

    @Column(name = "avgtime", nullable = false)
    private Integer avgTime;

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

    public Short getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(Short minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Short getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Short maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Integer avgTime) {
        this.avgTime = avgTime;
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
                Objects.equals(minPlayers, game.minPlayers) &&
                Objects.equals(maxPlayers, game.maxPlayers) &&
                Objects.equals(avgTime, game.avgTime) &&
                Objects.equals(description, game.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publisher, minPlayers, maxPlayers, avgTime, description);
    }

    public void updateParamsFrom(GameDTO dto) {
        this.setName(dto.getName());
        this.setPublisher(dto.getPublisher());
        this.setMinPlayers(dto.getMinPlayers());
        this.setMaxPlayers(dto.getMaxPlayers());
        this.setAvgTime(dto.getAvgTime());
        this.setDescription(dto.getDescription());
    }

    public GameDTO toDTO() {
        GameDTO dto = new GameDTO();

        dto.setId(id);
        dto.setName(name);
        dto.setPublisher(publisher);
        dto.setMinPlayers(minPlayers);
        dto.setMaxPlayers(maxPlayers);
        dto.setAvgTime(avgTime);
        dto.setDescription(description);

        return dto;
    }

}
