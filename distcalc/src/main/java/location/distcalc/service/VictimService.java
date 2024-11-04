package location.distcalc.service;

import location.distcalc.model.Victim;
import location.distcalc.repo.VictimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VictimService {
        @Autowired
        private VictimRepository victimRepository;

        public void updateVictimLocation(Long victimId, double latitude, double longitude) {
            Victim victim = victimRepository.findById(victimId).orElseThrow();
            victim.setLatitude(latitude);
            victim.setLongitude(longitude);
            victimRepository.save(victim);
        }
}
