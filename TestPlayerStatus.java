package Game;

public class TestPlayerStatus {

    public static void main(String[] args) {
        System.out.println("Welcome to " + PlayerStatus.getGameName());
        PlayerStatus player1 = new PlayerStatus("Counter-Terrorist",1,15500);
        player1.setWeaponInHand("sniper");
        player1.findArtifact(6);
        PlayerStatus player2 = new PlayerStatus("Terrorist",6,20500);
        player2.setWeaponInHand("kalashnikov");
        player2.findArtifact(306);
        player1.movePlayerTo(1000,1000);
        player2.movePlayerTo(1,1);
        System.out.println(player1.shouldPlayerAttack(player2));
    }

}
