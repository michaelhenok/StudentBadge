package miu.cs544.badgemembershipsystem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//Auto Generated
    @Column(nullable = false)
    @NotNull(message = "startDate cannot be null")
    private LocalDate startDate;//not null

    @Column(nullable = false)
    @NotNull(message = "endDate cannot be null")
    private LocalDate endDate;//not null

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;//memberships has one plan

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private MemberShipType memberShipType;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
