import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10; // 蛇的身体大小
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final Snake snake;
    private Apple apple;
    private Timer timer;

    public Board() {
        initBoard();
        snake = new Snake(B_WIDTH / 2 / DOT_SIZE, B_HEIGHT / 2 / DOT_SIZE, 1); // 确保蛇的初始位置处于游戏面板中心
        apple = new Apple(5, 5); // 将苹果放在一个固定的位置，避免立即碰撞
        Controller controller = new Controller(snake);
        addKeyListener(controller);
        setFocusable(true);
        requestFocusInWindow();
    }

    public Board(int snakeSize) {
        initBoard();
        snake = new Snake(B_WIDTH / 2 / DOT_SIZE, B_HEIGHT / 2 / DOT_SIZE, snakeSize); // 确保蛇的初始位置处于游戏面板中心
        apple = new Apple(5, 5); // 将苹果放在一个固定的位置，避免立即碰撞
        Controller controller = new Controller(snake);
        addKeyListener(controller);
        setFocusable(true);
        requestFocusInWindow();
    }

    private void initBoard() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        initGame();
    }

    private void initGame() {
        // 初始化游戏逻辑，例如设置计时器用于游戏循环
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (snake.isAlive()) {
            // 绘制苹果
            g.setColor(Color.RED);
            g.fillRect(apple.getPosition().x * DOT_SIZE, apple.getPosition().y * DOT_SIZE, DOT_SIZE, DOT_SIZE);

            // 绘制蛇
            g.setColor(Color.GREEN);
            for (Point p : snake.getBody()) {
                g.fillRect(p.x * DOT_SIZE, p.y * DOT_SIZE, DOT_SIZE, DOT_SIZE);
            }
        } else {
            gameOver(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (snake.isAlive()) {
            snake.move();
            checkApple();
            checkCollision();
        }
        repaint();
    }

    private void checkApple() {
        if (snake.getHeadPosition().equals(apple.getPosition())) {
            snake.grow();
            apple.regenerateAppleRandomly(RAND_POS, RAND_POS); // 生成新的苹果位置
        }
    }

    private void checkCollision() {
        // 检查蛇头是否撞墙
        Point head = snake.getHeadPosition();
        if (head.x < 0 || head.x >= B_WIDTH / DOT_SIZE || head.y < 0 || head.y >= B_HEIGHT / DOT_SIZE) {
            snake.setAlive(false);
        }

        // 检查蛇是否撞到自身
        for (Point point : snake.getBody().subList(1, snake.getBody().size())) {
            if (head.equals(point)) {
                snake.setAlive(false);
                break;
            }
        }

        if (!snake.isAlive()) {
            timer.stop();
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        g.setColor(Color.RED);
        g.drawString(msg, (B_WIDTH - g.getFontMetrics().stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    public Snake getSnake() {
        return snake;
    }

    public Apple getApple() {
        return apple;
    }

    public int getDOT_SIZE() {
        return DOT_SIZE;
    }

    public int getB_WIDTH() {
        return B_WIDTH;
    }

}
