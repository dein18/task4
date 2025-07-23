package task4.exeption;

public class EntityNotFoundExeption extends RuntimeException {
    public EntityNotFoundExeption(String message) {
        super(message);
    }
}
