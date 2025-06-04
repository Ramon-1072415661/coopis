package Board.Controls;

public  class ControllerFactory {
    public static Controller getController(int initialPosition, int columnStart, int columnEnd,Controls buttons){
        return switch (buttons){
            case WASD -> new AWSDCommandController(initialPosition,columnStart,columnEnd);
            case ARROWS -> new ArrowCommandController(initialPosition,columnStart,columnEnd);
        };
    }
}
