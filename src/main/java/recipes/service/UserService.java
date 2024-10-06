package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import recipes.model.UserDTO;
import recipes.repository.UserRepository;
import recipes.exception.NoSuchUserException;
import recipes.exception.UserAlreadyExistsException;
import recipes.mapper.UserMapper;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Autowired
  PasswordEncoder passwordEncoder;


  public UserDTO findByEmail(String email) throws NoSuchUserException {
    if (userRepository.existsByEmail(email)) {
      return userMapper.toUserDTO(userRepository.findByEmail(email).get());
    }
    throw new NoSuchUserException();
  }

  public Long register(UserDTO userDTO) {
    if (userRepository.existsByEmail(userDTO.getEmail())) {
      throw new UserAlreadyExistsException();
    }

    userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    return userRepository.save(userMapper.toUser(userDTO)).getId();
  }
}
