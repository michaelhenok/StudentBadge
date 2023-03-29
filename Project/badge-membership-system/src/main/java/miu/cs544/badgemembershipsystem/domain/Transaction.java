package miu.cs544.badgemembershipsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.utils.enums.TransactionStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Generated
    private long id;

    @Column(nullable = false)
    private LocalDateTime entryDate;  //not null

    private String remark;   //description of the transaction

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne
    private Member checker;

    @ManyToOne
    private Badge badge;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Plan plan;


}
