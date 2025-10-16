package boardgame.api.repository;

import boardgame.api.model.Boardgame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardgameRepository extends JpaRepository<Boardgame, Long> {}