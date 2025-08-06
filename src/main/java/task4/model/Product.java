package task4.model;

import jakarta.persistence.*;
import lombok.*;
import task4.enums.ProductType;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "account_number", nullable = false)
    private Long accountNumber;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductType productType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    @ToString.Exclude
    private User user;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
