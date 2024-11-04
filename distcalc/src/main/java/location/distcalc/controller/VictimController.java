package location.distcalc.controller;

import location.distcalc.service.VictimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/victims")
public class VictimController {
    @Autowired
    private VictimService victimService;

    @PostMapping("/{id}/update-location")
    public ResponseEntity<String> updateLocation(@PathVariable Long id, @RequestBody Map<String, Double> location) {
        double latitude = location.get("latitude");
        double longitude = location.get("longitude");
        victimService.updateVictimLocation(id, latitude, longitude);
        return ResponseEntity.ok("Location updated successfully");
    }
}
