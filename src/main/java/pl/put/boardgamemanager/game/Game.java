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
    private Short minPlayers;

    @Column(name = "maxplayers", nullable = false)
    private Short maxPlayers;

    @Column(name = "avgtime", nullable = false)
    private Timestamp avgTime;

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

    public Timestamp getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Timestamp avgTime) {
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

    public void updateParams(Game game) {
        this.setName(game.getName());
        this.setPublisher(game.getPublisher());
        this.setMinPlayers(game.getMinPlayers());
        this.setMaxPlayers(game.getMaxPlayers());
        this.setAvgTime(game.getAvgTime());
        this.setDescription(game.getDescription());
    }

    public static Game fromDTO(GameDTO dto) {

        if(dto == null) return null;

        Game game = new Game();

        game.setId(dto.getId());
        game.setName(dto.getName());
        game.setPublisher(dto.getPublisher());
        game.setMinPlayers(dto.getMinPlayers());
        game.setMaxPlayers(dto.getMaxPlayers());
        game.setAvgTime(dto.getAvgTime());
        game.setDescription(dto.getDescription());

        return game;
    }

    public static GameDTO toDTO(Game game) {

        if(game == null) return null;

        GameDTO dto = new GameDTO();

        dto.setName(game.getName());
        dto.setPublisher(game.getPublisher());
        dto.setMinPlayers(game.getMinPlayers());
        dto.setMaxPlayers(game.getMaxPlayers());
        dto.setAvgTime(game.getAvgTime());
        dto.setDescription(game.getDescription());

        return dto;
    }

}
