package pl.put.boardgamemanager.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.boardgamemanager.model.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

}