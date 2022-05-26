package proiect.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    @NotNull(message = "First name is required")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name is required")
    private String lastName;

    @Column(name = "active")
    private boolean active;

    @Column(name = "email")
    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Password is required")
    @Size(min = 5, message = "Password must have min 5 characters")
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PerfumeOrder> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Authority> listAuthorities = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<PerfumeOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<PerfumeOrder> orders) {
        this.orders = orders;
    }
}
