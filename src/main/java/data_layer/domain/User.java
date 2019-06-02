package data_layer.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity(name = "Users")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String username;

    @NotNull
    @Size(min = 8, max = 32)
    private String password;

    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;

    @NotNull
    private Date birthDate;

    @NotNull
    private Boolean gender;

    @NotNull
    @Size(min = 8, max = 15)
    private String phoneNumber;

    @Lob
    private String profilePhoto;

    @OneToMany(mappedBy = "user")
    private List<Portfolio> portfolios;

    @OneToMany(mappedBy = "user")
    private List<Wallet> wallets;

    @Builder
    public User(String username, String password, String lastName, String firstName, Date birthDate, Boolean gender, String phoneNumber, String profilePhoto, List<Portfolio> portfolios, List<Wallet> wallets) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.profilePhoto = profilePhoto;
        this.portfolios = portfolios;
        this.wallets = wallets;
    }

    public String getRole() {
        return this.getClass().getSimpleName().toUpperCase();
    }
}
