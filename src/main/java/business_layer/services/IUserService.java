package business_layer.services;

import business_layer.dto.UserDto;

public interface IUserService {

    /**
     * Get the correlated user dto for a specific email.
     *
     * @param username
     * @return
     */
    UserDto getUserByUsername(String username);

    void updatePassword(String username, String password, String newPassword);

    void addUser(UserDto userDto);

    void updateUser(String profilePhoto, String username);
}
