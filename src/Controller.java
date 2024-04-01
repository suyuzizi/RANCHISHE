import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private Snake snake;

    public Controller(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        System.out.println("Key pressed: " + KeyEvent.getKeyText(e.getKeyCode()));

        // 检查当前方向，防止蛇“掉头”
        Snake.Direction currentDirection = snake.getCurrentDirection();

        if (key == KeyEvent.VK_LEFT && currentDirection != Snake.Direction.RIGHT) {
            snake.setCurrentDirection(Snake.Direction.LEFT);
        }
        if (key == KeyEvent.VK_RIGHT && currentDirection != Snake.Direction.LEFT) {
            snake.setCurrentDirection(Snake.Direction.RIGHT);
        }
        if (key == KeyEvent.VK_UP && currentDirection != Snake.Direction.DOWN) {
            snake.setCurrentDirection(Snake.Direction.UP);
        }
        if (key == KeyEvent.VK_DOWN && currentDirection != Snake.Direction.UP) {
            snake.setCurrentDirection(Snake.Direction.DOWN);
        }
    }

}
