package miu.cs544.badgemembershipsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
public class Badge {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Badge ID cannot be null")
    private String badgeID;
    private boolean isActive = true;
    private LocalDate issueDate = LocalDate.now();
    private String description;
    @ManyToOne
    @JsonIgnore
    private Member member;

}
