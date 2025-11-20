package fr.karim.repository;

import fr.karim.entity.TennisGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TennisGameRepository extends JpaRepository<TennisGameEntity, Long> {
}
