public class Hero extends AnimatedThing{

    private double[] prevState = {0, 0, 0, 0, 0, 0};
    private double gravity;
    private double invincibility = 2.5E8;
    private double prevTime;

    public Hero(double x, double y){
        super("heros.png",
                0.1,
                6,
                100,
                80,
                5,
                60,
                x,
                y);
        this.prevState[X] = super.getPosX();
        this.prevState[VELOCX] = 500;
        this.prevState[Y] = super.getPosY();
        this.gravity = 3000;
    }

    public void update(long time){
        if(prevTime==0) prevTime = time;
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
        if(velocY==0 && posY==250) super.update(time); // Sprite du joueur qui court
        else if(velocY>0) super.getSprite().setViewport(chooseSprite(1, 1)); // Sprite du joueur qui descend
        else super.getSprite().setViewport(chooseSprite(0, 1)); // Sprite du joueur qui saute
    }

    public void jump(){
        if(prevState[Y]==250) prevState[ACCELY] = -gravity*15; // Le heros doit être sur le sol avant de pouvoir sauter
    }

    public void subInvincibility(double time){
        this.invincibility -= time;
    }

    public boolean isInvincible(){
        return invincibility>0;
    }

    public void setInvincibility(double invincibility){
        this.invincibility = invincibility;
    }
}
