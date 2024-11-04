package location.distcalc.repo;

import location.distcalc.model.Victim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VictimRepository extends JpaRepository<Victim,Long> {
}
