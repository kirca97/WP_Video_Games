package mk.kirca.games.demo.repository;

import mk.kirca.games.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
