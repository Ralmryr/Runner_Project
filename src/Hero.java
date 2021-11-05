public class Hero extends AnimatedThing{

    private double[] prevState = {0, 0, 0, 0, 0, 0};
    private double gravity;
    private double invincibility = 2.5E8;

    public Hero(double x, double y){
        super("heros.png",
                0.05,
                6,
                100,
                80,
                5,
                60,
                x,
                y);
        this.prevState[0] = super.getPosX();
        this.prevState[1] = 500;
        this.prevState[2] = super.getPosY();
        this.gravity = 3000;
    }

    public void update(long time){
        double accelX = prevState[4];
        double accelY = prevState[5];
        double velocX = prevState[1]+super.getDurationBetweenFrames()*accelX;
        double velocY = prevState[3]+super.getDurationBetweenFrames()*accelY;
        double posX = prevState[0]+super.getDurationBetweenFrames()*velocX;
        double posY = prevState[2]+super.getDurationBetweenFrames()*velocY;
        if(posY>250){
            posY=250;
            velocY = 0;
        }
        prevState[0] = posX;
        prevState[1] = velocX;
        prevState[2] = posY;
        prevState[3] = velocY;
        prevState[4] = 10;
        prevState[5] = this.gravity;
        super.setPosX(posX);
        super.setPosY(posY);
        if(velocY==0 && posY==250) super.update(time); // Sprite du joueur qui court
        else if(velocY>0) super.getSprite().setViewport(chooseSprite(1, 1)); // Sprite du joueur qui descend
        else super.getSprite().setViewport(chooseSprite(0, 1)); // Sprite du joueur qui saute
    }

    public void jump(){
        if(prevState[2]==250) prevState[5] = -gravity*15; // Le heros doit être sur le sol avant de pouvoir sauter
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
