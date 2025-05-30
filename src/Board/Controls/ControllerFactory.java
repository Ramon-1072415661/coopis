package Board.Controls;

public  class ControllerFactory {
    public static Controller getController(int initialPosition, int columnStart, int columnEnd,Controls buttons){
        return switch (buttons){
            case WASD -> getP1Controller(initialPosition,columnStart,columnEnd);
            case ARROWS -> getP2Controller(initialPosition,columnStart,columnEnd);
        };

    }
    private static Controller getP1Controller(int initialPosition, int columnStart, int columnEnd){
        return new AWSDCommandController(initialPosition,columnStart,columnEnd);
    }
    private static Controller getP2Controller(int initialPosition, int columnStart, int columnEnd){
        return new ArrowCommandController(initialPosition,columnStart,columnEnd);
    }
}
