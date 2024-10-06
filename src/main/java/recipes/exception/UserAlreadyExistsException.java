package recipes.exception;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException() {
    super("UserAlreadyExistsException");
  }
}
