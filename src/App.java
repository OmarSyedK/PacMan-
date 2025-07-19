import javax.swing.JFrame;


public class App {
    public static void main(String[] args) throws Exception {
        int rowCount = 21;
        int columnCount = 19;
        int pixelSize = 32;
        int boardWidth = columnCount * pixelSize;
        int boardHeight = rowCount * pixelSize;

        JFrame frame = new JFrame("PacMan");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);




    }
}
