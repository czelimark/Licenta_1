package business_layer.services;

import business_layer.dto.UserDto;

public interface IUserService {

    /**
     * Get the correlated user dto for a specific email.
     *
     * @param email
     * @return
     */
    UserDto getUserByEmail(String email);

    void updatePassword(String email, String password, String newPassword);

    void addUser(UserDto userDto);
}
