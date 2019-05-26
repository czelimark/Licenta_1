package Data_Layer.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Entity(name = "Users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String email;

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
    private String phoneNumber;

    @Lob
    private String profilePhoto;

    @OneToMany(mappedBy = "user")
    private List<Portfolio> portfolios;

    @OneToMany(mappedBy = "user")
    private List<Wallet> wallets;

    @Builder
    public User(String email, String password, String lastName, String firstName, Date birthDate, Boolean gender, String phoneNumber, String profilePhoto, List<Portfolio> portfolios, List<Wallet> wallets) {
        this.email = email;
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
}
