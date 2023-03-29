package miu.cs544.badgemembershipsystem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//Auto Generated

    @Column(nullable = false)
    @NotBlank(message = "name cannot be null")
    private String name;//not null, length=255

    @Column(nullable = false, length = 4000)
    @NotBlank(message = "description cannot be null")
    private String description;//could be null,  length=4000

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rule_id")
    private List<PlanRule> rules;     //list of allowed roles

    @OneToMany
    @JoinColumn(name = "plan_id")
    private List<Location> locations;

}
