package com.example.demo.employee;

import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "EmployeeIdCard")
@Table(name = "employee_id_card", uniqueConstraints = {
        @UniqueConstraint(name = "employee_id_card_number_unique", columnNames = "card_number")
})
public class EmployeeIdCard {
    @Id
    @SequenceGenerator(name = "employee_card_id_sequence", sequenceName = "employee_card_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_card_id_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "card_number", nullable = false, length = 14)
    private String cardNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "employee_id_fk"))
    private Employee employee;

    public EmployeeIdCard(String cardNumber, Employee employee) {
        this.cardNumber = cardNumber;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return "EmployeeIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", employee=" + employee +
                '}';
    }
}
