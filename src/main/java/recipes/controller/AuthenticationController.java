package recipes.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import recipes.model.UserDTO;
import recipes.service.UserService;

@RestController
@RequestMapping("api/")
@AllArgsConstructor
@Log4j2
public class AuthenticationController {

  UserService userService;

  @PostMapping("register")
  public ResponseEntity<Void> register(@Valid @RequestBody UserDTO userDTO) {
    log.info("Registering user: {}", userDTO);
    userService.register(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
