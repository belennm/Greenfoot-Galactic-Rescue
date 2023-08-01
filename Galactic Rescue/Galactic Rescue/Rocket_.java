import greenfoot.*;

public class Rocket_ extends Actor {
    private int health;
    private int maxHealth;
    private int grootCount;
    
    public Rocket_() {
        health = 100;
        maxHealth = 100;
        grootCount = 0;
    }
    
    public void act() {
        move(2);
        if (Greenfoot.isKeyDown("right")) {
            turn(2);
        }
        if (Greenfoot.isKeyDown("left")) {
            turn(-2);
        }
        
        get();
    }
    
    public void move(int speed) {
        double angle = Math.toRadians(getRotation());
        int dx = (int) Math.round(Math.cos(angle) * speed);
        int dy = (int) Math.round(Math.sin(angle) * speed);
        setLocation(getX() + dx, getY() + dy);
    }
    
    public void turn(int angle) {
        setRotation(getRotation() + angle);
    }
    
    public void decreaseHealth() {
        health--;
        if (health <= 0) {
            showGameOver();
            Greenfoot.stop();
        }
    }
    
    public int getHealth() {
        return health;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }
    
    public void get(){
        Actor groot;
        groot = getOneObjectAtOffset(0, 0, groot.class);
        if (groot != null){
            World world = getWorld();
            world.removeObject(groot);
            Greenfoot.playSound("yippee.wav");
            grootCount++;
            if (grootCount == 3) {
                showMissionSucceeded();
                Greenfoot.setWorld(new Game());
            }
        }
    }
    
    private void showGameOver() {
        World world = getWorld();
        world.showText("Game Over", world.getWidth() / 2, world.getHeight() / 2);
        Greenfoot.delay(20); // Delay para mostrar "Game Over" message
    }
    
    private void showMissionSucceeded() {
        World world = getWorld();
        world.showText("Mission Succeed", world.getWidth() / 2, world.getHeight() / 2);
        Greenfoot.delay(90); // delay para mostrar "Mission Succeeded" message
    }
}
