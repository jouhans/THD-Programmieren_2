package thd.game.managers;

class TooManyGameObjectsException extends RuntimeException {
    protected TooManyGameObjectsException(String message) {
        super(message);
    }
}
