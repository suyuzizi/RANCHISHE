import javax.swing.*;

public class SnakeGame {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // 创建窗口
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建游戏面板并添加到窗口
        Board board = new Board();
        frame.add(board);

        // 调整窗口大小以适应面板首选大小和布局
        frame.pack();

        // 确保窗口居中
        frame.setLocationRelativeTo(null);

        // 显示窗口
        frame.setVisible(true);

        // 请求焦点以便键盘事件可以被正确处理
        board.requestFocusInWindow();
    }
}
