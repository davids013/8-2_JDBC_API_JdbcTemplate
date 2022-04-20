package ru.netology.jdbcapi_jdbctemplate.db.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int orderId;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customers customerId;
    @Column(length = 128)
    private String productName;
    private int amount;

    public Orders(Customers customerId, String productName, int amount) {
        this.customerId = customerId;
        this.productName = productName;
        this.amount = amount;
    }
}
