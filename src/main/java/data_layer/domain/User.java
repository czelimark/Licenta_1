package data_layer.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity(name = "Users")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class User implements Comparable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String username;

    @NotNull
    @Size(min = 8)
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

    @Size(min = 8, max = 15)
    private String phoneNumber;

    @Lob
    private String profilePhoto;

    @Builder
    public User(Integer id, String username, String password, String lastName, String firstName, Date birthDate, Boolean gender, String phoneNumber, String profilePhoto) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.profilePhoto = profilePhoto;
    }

    public String getRole() {
        return this.getClass().getSimpleName().toUpperCase();
    }

    @Override
    public int compareTo(User u) {
        return this.id.compareTo(u.id);
    }
}
