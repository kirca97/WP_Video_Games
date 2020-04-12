package mk.kirca.games.demo.repository;

import mk.kirca.games.demo.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
