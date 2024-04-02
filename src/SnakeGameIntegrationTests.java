import org.testng.Assert;
import org.testng.annotations.Test;
import java.awt.*;
import java.awt.event.ActionEvent;
public class GameIntegrationTests {
    @Test
    public void testSnakeHitsWall() {
        Board board = new Board();
        Snake snake = board.getSnake();
        // 假设蛇初始⽅向向右，将蛇移动到靠近右边界的位置
        for (int i = 0; i < board.getB_WIDTH() / board.getDOT_SIZE() - 2;
             i++) {
            board.actionPerformed(new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, null));
        }
        // 再次移动，蛇应撞墙
        board.actionPerformed(new ActionEvent(this,
                ActionEvent.ACTION_PERFORMED, null));
        Assert.assertFalse(snake.isAlive(), "蛇撞墙后应该死亡。");
    }
    @Test
    public void testSnakeHitsItself() {
        Board board = new Board(10);
        Snake snake = board.getSnake();
        // 模拟操作使蛇形成⼀个圈并撞到⾃身
        changeDirectionAndMove(board, Snake.Direction.DOWN);
        changeDirectionAndMove(board, Snake.Direction.LEFT);
        changeDirectionAndMove(board, Snake.Direction.UP);
        // 检查蛇是否撞到⾃⼰
        Assert.assertFalse( snake.isAlive(), "蛇撞到⾃身后应该死亡。");
    }
    private void changeDirectionAndMove(Board board, Snake.Direction
            newDirection) {
        board.getSnake().setCurrentDirection(newDirection);
        board.actionPerformed(new ActionEvent(this,
                ActionEvent.ACTION_PERFORMED, null));
    }
    @Test
    public void testAppleRegeneratesAfterBeingEaten() {
        Board board = new Board();
        Apple apple = board.getApple();
        Snake snake = board.getSnake();
        // 将苹果放置在蛇的下⼀步位置
        Point nextPoint = (Point)snake.getHeadPosition().clone();
        nextPoint.translate(1, 0);
        // 假设蛇向右移动
        apple.setPosition(nextPoint.x, nextPoint.y);
        Point oldApplePos = new Point(apple.getPosition());
        // 蛇吃到苹果
        board.actionPerformed(new ActionEvent(this,
                ActionEvent.ACTION_PERFORMED, null));
        // 检查苹果是否重新⽣成于新位置
        Assert.assertNotEquals(oldApplePos, apple.getPosition(), "蛇吃掉苹果后，苹果应该在新位置重新⽣成。");
    }
}