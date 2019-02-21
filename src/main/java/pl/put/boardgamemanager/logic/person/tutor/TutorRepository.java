package pl.put.boardgamemanager.logic.person.tutor;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.person.tutor.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

}