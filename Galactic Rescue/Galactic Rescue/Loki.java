import greenfoot.*;

public class Loki extends Actor {
    private int damage;
    private Rocket_ rocket;
    private int speed = 2; // velocidad de movimiento
    private int direction = 1; // dirección de movimiento 1 hacia la derecha, -1 hacia la izquierda

    public Loki(Rocket_ rocket) {
        damage = 1;
        this.rocket = rocket;
    }

    public void act() {
        // Mueve a Loki de izquierda a derecha
        moveHorizontally();

        // Verifica colisiones con el objeto Rocket
        checkCollision();
    }

    public void checkCollision() {
        if (isTouching(Rocket_.class)) {
            rocket.decreaseHealth();
            Game game = (Game) getWorld();
            game.showGameOver();
        }
    }

    private void moveHorizontally() {
        int x = getX();
        int newX = x + (speed * direction);
        setLocation(newX, getY());

        // cambia la dirección cuando Loki alcanza los límites de la pantalla
        if (newX <= 0 || newX >= getWorld().getWidth() - 1) {
            direction *= -1;
        }
    }
}
