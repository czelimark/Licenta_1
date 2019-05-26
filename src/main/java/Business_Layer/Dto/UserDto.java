package Business_Layer.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersioUID = 8L;

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

    private List<PortfolioDto> portfolios;

    private List<WalletDto> wallets;

    @Builder
    public UserDto(String email, String password, String lastName, String firstName, Date birthDate, Boolean gender, String phoneNumber, String profilePhoto, List<PortfolioDto> portfolios, List<WalletDto> wallets) {
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
