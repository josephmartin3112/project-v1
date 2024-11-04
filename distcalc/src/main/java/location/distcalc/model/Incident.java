package location.distcalc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incidents")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // e.g., "Fire", "Accident"
    private double latitude;
    private double longitude;
    private String status; // e.g., "Open", "Closed"

    @OneToOne
    private ResponseUnit assignedUnit;
}
