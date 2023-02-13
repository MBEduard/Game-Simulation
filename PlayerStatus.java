package Game;

public class PlayerStatus {
    private final String nickname;
    private int score;
    private int lives;
    private int health;
    private String weaponInHand;
    private Position position = new Position();

    public Position getPosition() {
        return position;
    }

    public void movePlayerTo(double x, double y){
        position = new Position(x,y);
    }
    private static String gameName = "Counter Strike";

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public PlayerStatus(String nickname){
        this.nickname = nickname;
    }

    public PlayerStatus(String nickname, int lives){
        this(nickname);
        this.lives = lives;
    }

    public PlayerStatus(String nickname, int lives, int score){
        this(nickname, lives);
        this.score = score;
    }
    private String getPlayerStatus(){
        return nickname;
    }

    public static boolean isArtefactPerfect(int artefactCode){
        int sum=0;
        for(int i = 1; i<=artefactCode/2;i++){
            if(artefactCode%i==0){
                sum+=i;
            }
        }
        return sum == artefactCode;
    }
    public static boolean isArtefactPrime(int artefactCode){
        for(int i = 2; i <=artefactCode/2; i++){
            if(artefactCode%i==0){
                return false;
            }
        }
        return true;
    }
    public static boolean isArtefactEven(int artefactCode){
        //1154
        int sumCif = 0;
        int copy = artefactCode;
        while (copy>0){
            sumCif+=copy%10;
            copy/=10;
        }
        if((artefactCode%2==0)&&(sumCif%3==0)){
            return true;
        }else return false;
    }

    public void findArtifact(int artefactCode){
        if (isArtefactPerfect(artefactCode)){
            lives++;
            score+=5000;
            health = 100;
            System.out.println(nickname + " found a perfect artifact");
        }
        else if(isArtefactPrime(artefactCode)){
            lives+=2;
            score+=1000;
            health+=25;
            if(health>100){
                health=100;
            }
            System.out.println(nickname + " found a prime artifact");
        }else if(isArtefactEven(artefactCode)){
            score-=3000;
            if(score<0){
                score = 0;
            }
            health-=25;
            if(health<=0){
                lives--;
                terminateIfLives0();
            }
            System.out.println(nickname + " triggered a trap");
        }else score+=artefactCode;
    }

    private boolean checkLives(int lives){
        if(lives>0){
            return false;
        }
        return true;
    }

    private void terminateIfLives0(){
        if(!checkLives(lives)){
            health = 100;
        }
        else {
            checkLives(lives);
            System.out.println("GAME OVER: " + nickname + " has been slain");
        }
    }

    public String getWeaponInHand() {
        return weaponInHand;
    }

    public boolean setWeaponInHand(String newWeaponInHand) {
        switch (newWeaponInHand) {
            case "knife":
                if (score > 1000) {
                    score -= 1000;
                    this.weaponInHand = newWeaponInHand;
                    System.out.println(nickname + " equipped " + weaponInHand);
                    return true;
                }
                break;
            case "sniper":
                if (score > 10000) {
                    score -= 10000;
                    this.weaponInHand = newWeaponInHand;
                    System.out.println(nickname + " equipped " + weaponInHand);
                    return true;
                }
                break;
            case "kalashnikov":
                if (score >20000) {
                    score -= 20000;
                    this.weaponInHand = newWeaponInHand;
                    System.out.println(nickname + " equipped " + weaponInHand);
                    return true;
                }
                break;
        }
        return false;
    }

    static String getGameName() {
        return gameName;
    }

    static void setGameName(String newGameName) {
        PlayerStatus.gameName = newGameName;
    }

    public String toString(){
        return "Player: " + nickname + " Lives: " + lives + " Score: " + score + " Position: " + position;
    }

    public boolean shouldPlayerAttack(PlayerStatus opponent){
        String weapon = getWeaponInHand();
        String opponentWeapon = opponent.getWeaponInHand();
        if(weapon.equals(opponentWeapon)){
            if(winPercent()>opponent.winPercent()){
                System.out.println(nickname + " wins");
                return true;
            }else{
                System.out.println(opponent.nickname + " wins");
                return false;
            }
        }else {
            if(Position.distance(position,opponent.position)>1000){
                switch(weapon){
                    case "sniper":
                        System.out.println(nickname + " wins");
                        return true;
                    case "kalashnikov":
                        if(opponentWeapon.equals("sniper")){
                            System.out.println(opponent.nickname + " wins");
                            return false;
                        }
                        System.out.println(nickname + " wins");
                        return true;
                    case "knife":
                        System.out.println(opponent.nickname + " wins");
                        return false;

                }
            }else {
                switch(weapon){
                    case "kalashnikov":
                        System.out.println(nickname + " wins");
                        return true;
                    case "sniper":
                        if(opponentWeapon.equals("kalashnikov")){
                            System.out.println(opponent.nickname + " wins");
                            return false;
                        }
                        System.out.println(nickname + " wins");
                        return true;
                    case "knife":
                        System.out.println(opponent.nickname + " wins");
                        return false;
                }
            }
        }
        return false;
    }
    private double winPercent() {
        return (3*health+score/1000d)/4d;
    }

}
