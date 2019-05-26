package Business_Layer.Services;

import Business_Layer.Dto.UserDto;

public interface IUserService {

    /**
     * Get the correlated user dto for a specific username.
     *
     * @param username
     * @return
     */
    UserDto getUserByUsername(String username);

    void updatePassword(String username, String password, String newPassword);

    void addUser(UserDto userDto);
}
