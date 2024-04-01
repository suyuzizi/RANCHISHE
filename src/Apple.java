import java.awt.*;
import java.util.Random;

public class Apple {
    private Point position;
    private Random rand = new Random();

    // 构造函数允许指定苹果位置
    public Apple(int x, int y) {
        position = new Point(x, y);
    }

    // 重新生成一个随机位置的苹果
    public void regenerateAppleRandomly(int maxX, int maxY) {
        Random rand = new Random();
        int x = rand.nextInt(maxX);
        int y = rand.nextInt(maxY);
        this.setPosition(x, y);
    }

    // 允许手动设置苹果位置
    public void setPosition(int x, int y) {
        position.setLocation(x, y);
    }

    public Point getPosition() {
        return position;
    }
}
