public class Camera {
    private double x;
    private double y;
    private double k;
    private double m;
    private double f;
    private double[] prevState = {0, 0, 0, 0};
    private double timeBetweenUpdates;

    public Camera(int x, int y){
        this.x = x;
        this.y = y;

        prevState[0] = x;
        this.f = 20;
        this.m = 1;
        this.k = (f*f)/4; // Pour atteindre le régime critique du ressort
        timeBetweenUpdates = 0.016;
    }

    @Override
    public String toString(){
        return(this.x+","+this.y);
    }

    public void update(Hero hero){
        double accelX = (k/m)*(hero.getPosX()-100-prevState[0])-(f/m)*prevState[1];
        double accelY = (k/m)*(hero.getPosY()-150-prevState[2])-(f/m)*prevState[3];
        double velocX = prevState[1] + accelX*timeBetweenUpdates;
        double velocY = prevState[3] + accelY*timeBetweenUpdates;
        double posX = prevState[0] + velocX*timeBetweenUpdates;
        double posY = prevState[2] + velocY*timeBetweenUpdates;
        prevState[0] = posX;
        prevState[1] = velocX;
        prevState[2] = posY;
        prevState[3] = velocY;
        this.x = posX;
        this.y = posY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTimeBetweenUpdates() {
        return timeBetweenUpdates;
    }
}
