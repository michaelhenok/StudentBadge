package miu.cs544.badgemembershipsystem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "FirstName cannot be null")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "LastName cannot be null")
    private String lastName;

    @Column(nullable = false)
    @NotNull(message = "DOB cannot be null")
    private LocalDate DOB;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "Phone cannot be null")
    private String phone;

    @OneToMany(mappedBy = "member")
    private List<Badge> badges;

    @OneToOne(mappedBy = "member")
    private User user;

    @OneToMany(mappedBy = "member")
    private List<Membership> memberships;

    public Badge getActiveBadge() {
        Badge active = badges.stream().filter(b -> b.isActive()).findFirst().orElse(null);
        return active;
    }


}
