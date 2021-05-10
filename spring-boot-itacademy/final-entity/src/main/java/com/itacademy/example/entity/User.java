package com.itacademy.example.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Entity
@Table(name = "users")
public class User extends AEntity<Integer> {

    @Size(min = 5, message = "Не меньше 5 знаков")
    @Column(name = "name", nullable = true, length = 255)
    private String username;

    @Column(name = "salary", nullable = true, length = 10)
    private int salary;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Pet> pets;

    @Size(min = 5, message = "Не меньше 5 знаков")
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public void setPets(List<Pet> pets) {
        this.pets = pets;
        for (Pet pet : this.pets) {
            pet.setUser(this);
        }
    }

    public User(@Size(min = 5, message = "Не меньше 5 знаков") String username, int salary) {
        this.username = username;
        this.salary = salary;
    }
}
