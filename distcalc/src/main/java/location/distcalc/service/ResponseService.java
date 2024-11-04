package location.distcalc.service;

import location.distcalc.model.Incident;
import location.distcalc.model.ResponseUnit;
import location.distcalc.repo.IncidentRepository;
import location.distcalc.repo.ResponseUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {
    @Autowired
    private ResponseUnitRepository responseUnitRepository;

    @Autowired
    private IncidentRepository incidentRepository;

    private static final int EARTH_RADIUS_KM = 6371;

    public void assignNearestUnitToIncident(Incident incident) {
        List<ResponseUnit> availableUnits = responseUnitRepository.findAll().stream()
                .filter(unit -> unit.getType().equals(incident.getType()) && unit.isAvailable())
                .sorted(Comparator.comparing(unit -> calculateDistance(
                        incident.getLatitude(), incident.getLongitude(),
                        unit.getLatitude(), unit.getLongitude())))
                .collect(Collectors.toList());

        if (!availableUnits.isEmpty()) {
            ResponseUnit nearestUnit = availableUnits.get(0);
            nearestUnit.setAvailable(false);
            responseUnitRepository.save(nearestUnit);

            incident.setAssignedUnit(nearestUnit);
            incident.setStatus("Assigned");
            incidentRepository.save(incident);
        }
    }

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c; // Distance in kilometers
    }
}
