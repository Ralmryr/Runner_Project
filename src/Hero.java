public class Hero extends AnimatedThing{

    private final double[] prevState = {0, 0, 0, 0, 0, 0};
    private final double gravity;
    private double invincibility = 0;
    private double prevTime = 0;
    private int numberOfLives = 1;

    public Hero(double x, double y){
        super("heros.png",
                0.1,
                new int[] {6, 1, 1, 6, 1, 1},
                100,
                80,
                5,
                60,
                x,
                y);
        this.prevState[X] = x;
        this.prevState[VELOCX] = 500;
        this.prevState[Y] = y;
        this.gravity = 2000;
    }

    public void update(long time){
        if(prevTime==0) prevTime = time;
        if(isInvincible()) invincibility -= time-prevTime;

        double timeElapsed = 1e-9*(time-prevTime);
        double accelX = prevState[ACCELX];
        double accelY = prevState[ACCELY];
        double velocX = prevState[VELOCX]+timeElapsed*accelX;
        double velocY = prevState[VELOCY]+timeElapsed*accelY;
        double posX = prevState[X]+timeElapsed*velocX;
        double posY = prevState[Y]+timeElapsed*velocY;
        prevTime = time;

        if(posY>250){
            posY=250;
            velocY = 0;
        }
        prevState[X] = posX;
        prevState[VELOCX] = velocX;
        prevState[Y] = posY;
        prevState[VELOCY] = velocY;
        prevState[ACCELX] = 10;
        prevState[ACCELY] = this.gravity;

        super.setPosX(posX);
        super.setPosY(posY);

        if(velocY==0 && posY==250) super.setAttitude(0); // Sprite du joueur qui court
        else if(velocY>0) super.setAttitude(1); // Sprite du joueur qui descend
        else super.setAttitude(2); // Sprite du joueur qui saute
        super.update(time);
    }

    public void jump(){
        if(prevState[Y]==250) prevState[ACCELY] = -gravity*25; // Le heros doit être sur le sol avant de pouvoir sauter
    }

    public void takeDamage(){
        numberOfLives--;
        invincibility = 1e9;
        prevState[VELOCX] *= 0.8;
    }

    public void reset(){
        prevState[X] = 500;
        prevState[VELOCX] = 500;
        prevState[Y] = 250;
        prevState[VELOCY] = 0;
        prevState[ACCELX] = 10;
        prevState[ACCELY] = this.gravity;
        numberOfLives = 1;
        prevTime = 0;
    }

    public void updateTime(long time) {
        prevTime = time;
    }

    public boolean isInvincible(){
        return invincibility>0;
    }

    public boolean isDead() {
        return numberOfLives==0;
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }
}
