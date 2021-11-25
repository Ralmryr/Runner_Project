public class Camera {
    private double x;
    private double y;
    private final double k;
    private final double m;
    private final double f;
    private double[] prevState = {0, 0, 0, 0};
    private long prevTime = 0;

    public Camera(int x, int y){
        this.x = x;
        this.y = y;

        prevState[0] = x;
        prevState[2] = y;
        this.f = 20;
        this.m = 1;
        this.k = (f*f)/4; // Pour atteindre le régime critique du ressort
    }

    @Override
    public String toString(){
        return(this.x+","+this.y);
    }

    public void update(Hero hero, long time){
        if(prevTime==0) prevTime = time;
        double timeElapsed = 1e-9*(time - prevTime);
        double accelX = (k/m)*(hero.getPosX()-100-prevState[0])-(f/m)*prevState[1];
        double accelY = (k/m)*(hero.getPosY()-150-prevState[2])-(f/m)*prevState[3];
        double velocX = prevState[1] + accelX*timeElapsed;
        double velocY = prevState[3] + accelY*timeElapsed;
        double posX = prevState[0] + velocX*timeElapsed;
        double posY = prevState[2] + velocY*timeElapsed;
        prevTime = time;

        prevState[0] = posX;
        prevState[1] = velocX;
        prevState[2] = posY;
        prevState[3] = velocY;
        this.x = posX;
        this.y = posY;
    }

    public void reset() {
        prevState[0] = 0;
        prevState[1] = 0;
        prevState[2] = 50;
        prevState[3] = 0;
        prevTime = 0;
    }

    public void updateTime(long time) {
        prevTime = time;
    }

    public void incrementX(double value) {
        x += value;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
