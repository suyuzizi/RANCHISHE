import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body;
    private Direction currentDirection = Direction.RIGHT;
    private boolean alive = true; // 新增存活状态

    public Snake(int initialX, int initialY) {
        body = new LinkedList<>();
        body.add(new Point(initialX, initialY)); // 初始位置
    }

    public Snake(int initialX, int initialY, int initialLength) {
        body = new LinkedList<>();
        for (int i = 0; i < initialLength; i++) {
            body.add(new Point(initialX - i, initialY)); // 从右向左添加点
        }
    }

    public void move() {

        // 如果蛇已经不活着，则不进行移动
        if (!alive) {
            return;
        }

        Point head = new Point(body.getFirst());
        switch (currentDirection) {
            case UP:
                head.translate(0, -1);
                break;
            case DOWN:
                head.translate(0, 1);
                break;
            case LEFT:
                head.translate(-1, 0);
                break;
            case RIGHT:
                head.translate(1, 0);
                break;
        }
        body.addFirst(head);
        body.removeLast();
    }

    public void grow() {
        Point newHead = new Point(body.getFirst());
        switch (currentDirection) {
            case UP:
                newHead.translate(0, -1);
                break;
            case DOWN:
                newHead.translate(0, 1);
                break;
            case LEFT:
                newHead.translate(-1, 0);
                break;
            case RIGHT:
                newHead.translate(1, 0);
                break;
        }
        body.addFirst(newHead);
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Point getHeadPosition() {
        return body.getFirst();
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public void setCurrentDirection(Direction newDirection) {
        // 防止蛇直接掉头
        if ((this.currentDirection == Direction.UP && newDirection != Direction.DOWN) ||
                (this.currentDirection == Direction.DOWN && newDirection != Direction.UP) ||
                (this.currentDirection == Direction.LEFT && newDirection != Direction.RIGHT) ||
                (this.currentDirection == Direction.RIGHT && newDirection != Direction.LEFT)) {
            this.currentDirection = newDirection;
        }
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
