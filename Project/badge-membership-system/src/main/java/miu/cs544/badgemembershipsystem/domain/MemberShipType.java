package miu.cs544.badgemembershipsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.utils.enums.MemberShipTypeEnum;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberShipType {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private MemberShipTypeEnum type;// 1-> unlimited.  2->limited 3->Checker.
}
