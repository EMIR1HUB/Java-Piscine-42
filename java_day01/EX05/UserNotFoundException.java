public class UserNotFoundException extends RuntimeException {

    @Override
    public String toString() {
        return "Нет пользователя с таким ID";
    }
}
