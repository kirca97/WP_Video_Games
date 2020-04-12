package mk.kirca.games.demo.repository;

import mk.kirca.games.demo.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
    List<VideoGame> findByCategoryId(Long categoryId);

    List<VideoGame> findByCategoryIdAndManufacturerId(Long categoryId, Long manufacturerId);
}
