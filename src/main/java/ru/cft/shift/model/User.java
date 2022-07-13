package ru.cft.shift.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    private String password;

    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    private List<ClientService> clientServices;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Order> orders;
}
