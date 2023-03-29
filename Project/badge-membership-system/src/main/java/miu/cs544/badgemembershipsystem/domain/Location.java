package miu.cs544.badgemembershipsystem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//Auto Generated
    @Column(nullable = false, length = 255)
    @NotBlank(message = "Name cannot be null")
    @Size(max = 255, min = 8)
    private String name;//not null, length=255
    @Size(max = 4000)
    private String description;//no more than 4000
    @Max(32767)
    @Min(30)
    private short capacity;//the max value is 32767, should not less than zero, default value=30
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull(message = "Location Type cannot be null")
    @JoinColumn(name = "LType_id")
    private LocationType locationType;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "L_id")
    private List<TimeSlot> slots;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
}