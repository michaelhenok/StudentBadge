package miu.cs544.badgemembershipsystem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.utils.enums.Intervals;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "plans_rule")
public class PlanRule {
    @Id
    @GeneratedValue
    private long id;//Auto Generated

    @Column(nullable = false)
    @NotNull(message = "entryTimes can not be black")
    private int numberOfEntry;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Intervals intervals;


}
