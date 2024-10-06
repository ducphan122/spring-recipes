package recipes.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import recipes.model.UserDTO;
import recipes.repository.entity.User;
import recipes.config.CustomUserDetails;

@Component
@AllArgsConstructor
public class UserMapper {

    public UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getPassword());
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public CustomUserDetails toUserDetails(User user) {
        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword());
    }
}
