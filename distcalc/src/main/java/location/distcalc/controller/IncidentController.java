package location.distcalc.controller;

import location.distcalc.model.Incident;
import location.distcalc.repo.IncidentRepository;
import location.distcalc.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private ResponseService responseService;

    @PostMapping("/report")
    public ResponseEntity<String> reportIncident(@RequestBody Incident incident) {
        incident.setStatus("Open");
        incidentRepository.save(incident);
        responseService.assignNearestUnitToIncident(incident);
        return ResponseEntity.ok("Incident reported and unit assigned.");
    }
}
