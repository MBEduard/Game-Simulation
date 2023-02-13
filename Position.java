package Game;

public class Position {
    private double x;
    private double y;

    public double getPositionX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Position(){

    }

    public static double distance(Position player1, Position player2){
        return Math.sqrt((player1.x-player2.x)*(player1.x-player2.x) + (player1.y-player2.y)*(player1.y-player2.y));
    }

    public String toString(){
        return "PositionX: "+ x + " PositionY: " +y;
    }
}
