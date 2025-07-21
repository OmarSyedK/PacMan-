import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class PacMan extends JPanel {
        private int rowCount = 21;
        private int columnCount = 19;
        private int pixelSize = 32;
        private int boardWidth = columnCount * pixelSize;
        private int boardHeight = rowCount * pixelSize;

        private Image wallImage;
        private Image blueGhostImage;
        private Image redGhostImage;
        private Image pinkGhostImage;
        private Image orangeGhostImage;

        private Image pacmanUpImage;
        private Image pacmanDownImage;
        private Image pacmanLeftImage;
        private Image pacmanRightImage;

PacMan() {
    setPreferredSize(new Dimension(boardWidth, boardHeight));
    setBackground(Color.BLACK);

    //load images
    wallImage = new ImageIcon(getClass().getResource("./wall.png")).getImage();
    blueGhostImage = new ImageIcon(getClass().getResource("./blueGhost.png")).getImage();
    redGhostImage = new ImageIcon(getClass().getResource("./redGhost.png")).getImage();
    pinkGhostImage = new ImageIcon(getClass().getResource("./pinkGhost.png")).getImage();
    orangeGhostImage = new ImageIcon(getClass().getResource("./orangeGhost.png")).getImage();

    pacmanDownImage = new ImageIcon(getClass().getResource("./pacmanDown.png")).getImage();
    pacmanLeftImage = new ImageIcon(getClass().getResource("./pacmanLeft.png")).getImage();
    pacmanRightImage = new ImageIcon(getClass().getResource("./pacmanRight.png")).getImage();
    pacmanUpImage = new ImageIcon(getClass().getResource("./pacmanUp.png")).getImage();
    }
}
