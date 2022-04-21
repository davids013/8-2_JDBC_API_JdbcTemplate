package ru.netology.jdbcapi_jdbctemplate.db.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int customerId;
    @Column(length = 32, nullable = false)
    private String name;
    @Column(length = 32)
    private String surname;
    private int age;
    @Column(length = 32, unique = true)
    private String phoneNumber;

    public Customers(String name, String surname, int age, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
}
