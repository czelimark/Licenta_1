package business_layer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Comparable<UserDto>, Serializable {

    private static final long serialVersioUID = 8L;

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

    @NotNull
    private String phoneNumber;

    @Lob
    private String profilePhoto;

    private String role;

    @Builder
    public UserDto(Integer id, String username, String password, String lastName, String firstName, Date birthDate, Boolean gender, String phoneNumber, String profilePhoto, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.profilePhoto = profilePhoto;
        this.role = role;
    }

    @Override
    public int compareTo(UserDto u) {
        return this.id.compareTo(u.id);
    }
}
