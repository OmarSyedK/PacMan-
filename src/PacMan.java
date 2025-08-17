import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class PacMan extends JPanel implements ActionListener, KeyListener {
    class Block{
        int x;
        int y;
        int width;
        int height;
        Image image;
        int startX;
        int startY;

        char direction = 'R';
        int velocityX = 0;
        int velocityY = 0;

        Block(int x, int y, int width, int height, Image image){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.image = image;
            this.startX = x;
            this.startY = y;
        }

        void updateDirection(char direction){
            char prevDirection = this.direction;
            this.direction = direction;
            updateVelocity();
            this.x += this.velocityX;
            this.y += this.velocityY;
            for (Block wall: walls) {
                if (collision(this, wall)) {
                    this.x -= this.velocityX;
                    this.y -= this.velocityY;
                    this.direction = prevDirection;
                    updateVelocity();
                }
            }
        }

        void updateVelocity(){
            if (this.direction == 'U') {
                this.velocityX = 0;
                this.velocityY = -pixelSize/4;
            } else if (this.direction == 'D') {
                this.velocityX = 0;
                this.velocityY = pixelSize/4;
            } else if (this.direction == 'L') {
                this.velocityX = -pixelSize/4;
                this.velocityY = 0;
            } else if (this.direction == 'R') {
                this.velocityX = pixelSize/4;
                this.velocityY = 0;
            }

        }

        void reset(){
            this.x = this.startX;
            this.y = this.startY;
        }
    }
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

    //X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    private String[] tileMap = {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X       X    X",
        "XXXX XXXX XXXX XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXrXX X XXXX",
        "O       bpo       O",
        "XXXX X XXXXX X XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXXXX X XXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X  X     P     X  X",
        "XX X X XXXXX X X XX",
        "X    X   X   X    X",
        "X XXXXXX X XXXXXX X",
        "X                 X",
        "XXXXXXXXXXXXXXXXXXX" 
    };

        HashSet<Block> walls;
        HashSet<Block> ghosts;
        HashSet<Block> foods;
        Block pacman;

        Timer gameLoop;
        char[] directions = {'U', 'D', 'L', 'R'};
        Random random = new Random();
        int score = 0;
        int lives = 3;
        boolean gameOver = false;
        boolean gameWon = false;
        char nextDirection = ' ';

PacMan() {
    setPreferredSize(new Dimension(boardWidth, boardHeight));
    setBackground(Color.BLACK);
    addKeyListener(this);
    setFocusable(true);

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

    loadMap();

    for (Block ghost: ghosts) {
        char newDirection = directions[random.nextInt(4)];
        ghost.updateDirection(newDirection); 
    }

    gameLoop = new Timer(50, this); // 20 FPS (1000ms / 50ms)
    gameLoop.start();
    }
    public void loadMap(){
        walls = new HashSet<Block>();
        ghosts = new HashSet<Block>();
        foods = new HashSet<Block>();

        //Iterate through the tile map
        for(int r = 0; r < rowCount; r++){
            for(int c = 0; c < columnCount; c++){
                String row = tileMap[r];
                char tileMapChar = row.charAt(c);
                int x = c * pixelSize;
                int y = r * pixelSize;

                if (tileMapChar == 'X') {        //Wall
                    Block wall = new Block(x,y,pixelSize,pixelSize,wallImage);
                    walls.add(wall);
                } else if (tileMapChar == 'b'){   //blue ghost
                    Block ghost = new Block(x, y, pixelSize, pixelSize, blueGhostImage);
                    ghosts.add(ghost);
                } else if (tileMapChar == 'r'){   //red ghost
                    Block ghost = new Block(x, y, pixelSize, pixelSize, redGhostImage);
                    ghosts.add(ghost);
                } else if (tileMapChar == 'o'){   //orange ghost
                    Block ghost = new Block(x, y, pixelSize, pixelSize, orangeGhostImage);
                    ghosts.add(ghost);
                } else if (tileMapChar == 'p'){   //pink ghost
                    Block ghost = new Block(x, y, pixelSize, pixelSize, pinkGhostImage);
                    ghosts.add(ghost);
                }  else if (tileMapChar == 'P'){   //Pacman
                    pacman = new Block(x, y, pixelSize, pixelSize, pacmanRightImage); 
                } else if (tileMapChar == ' '){   //Food
                    Block food = new Block(x+14, y+14, 4, 4, null);
                    foods.add(food);
                }
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        g.drawImage(pacman.image, pacman.x, pacman.y, pacman.width, pacman.height, null);

        for (Block wall: walls){
            g.drawImage(wall.image, wall.x, wall.y, wall.width, wall.height, null);
        }

        for (Block ghost: ghosts){
            g.drawImage(ghost.image, ghost.x, ghost.y, ghost.width, ghost.height, null);
        }

        g.setColor(Color.WHITE);
        for (Block food: foods){
            g.fillRect(food.x, food.y, food.width, food.height);
        }
        
        //score
        g.setFont(new Font ("Arial", Font.BOLD, 18));
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf(score), pixelSize/2, pixelSize/2);
        }
        else {
            g.drawString("x" + String.valueOf(lives) + " Score: " + String.valueOf(score), pixelSize/2, pixelSize/2);
        }
        if (gameWon) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            g2d.setColor(Color.ORANGE);
            g2d.drawString("You Won!", boardWidth/2 - 120, boardHeight/2);
        }
    }

    public void move(){
        if (pacman.direction == ' ') {
            // Pac-Man is idle until user presses a key
            pacman.velocityX = 0;
            pacman.velocityY = 0;
            return; 
}

        pacman.x += pacman.velocityX;
        pacman.y += pacman.velocityY;
        //Check for collision with walls
        for (Block wall: walls) {
            if (collision(pacman, wall)) {
                pacman.x -= pacman.velocityX;
                pacman.y -= pacman.velocityY;
                break;
            }
        }

        for (Block ghost: ghosts) {
            if ( collision(pacman, ghost)){
                lives -= 1;
                if (lives <= 0){
                    gameOver = true;
                    return;
                }
                resetPositions();
            }
            if (ghost.y == pixelSize*9 && ghost.direction != 'U' && ghost.direction != 'D'){
                ghost.updateDirection('U');
            }
            ghost.x += ghost.velocityX;
            ghost.y += ghost.velocityY;
            for (Block wall: walls) {
                if (collision(ghost, wall) || ghost.x < 0 || ghost.x + ghost.width > boardWidth){
                    ghost.x -= ghost.velocityX;
                    ghost.y -= ghost.velocityY;
                    char newDirection = directions[random.nextInt(4)];
                    ghost.updateDirection(newDirection);
                }
            }
        }
        
        //Check for collision with food
        Block foodEaten = null;
        for (Block food: foods) {
            if (collision(pacman, food)) {
                foodEaten = food;
                score += 10;
            }
        }
        foods.remove(foodEaten);

        if (foods.isEmpty() && !gameOver) {
            gameWon = true;
            gameLoop.stop();
        }

        if (pacman.y == pixelSize*9 && pacman.direction != 'U' && pacman.direction != 'D'){
            if (pacman.x < -pacman.width){
                pacman.x = boardWidth;
            } else if(pacman.x > boardWidth){
                pacman.x = -pacman.width;
            }
            
        }
        // Check if Pac-Man is aligned with grid
if (pacman.x % pixelSize == 0 && pacman.y % pixelSize == 0) {
    // Temporarily test new direction
    int oldX = pacman.x;
    int oldY = pacman.y;

    pacman.updateDirection(nextDirection);

    // If it collides, revert to current direction
    boolean blocked = false;
    for (Block wall : walls) {
        if (collision(pacman, wall)) {
            blocked = true;
            break;
        }
    }

    if (blocked) {
        pacman.x = oldX;
        pacman.y = oldY;
        pacman.updateDirection(pacman.direction); // keep moving current way
    }
}
    if (pacman.direction == 'U') {
    pacman.image = pacmanUpImage;
} else if (pacman.direction == 'D') {
    pacman.image = pacmanDownImage;
} else if (pacman.direction == 'L') {
    pacman.image = pacmanLeftImage;
} else if (pacman.direction == 'R') {
    pacman.image = pacmanRightImage;
}


    }

    public boolean collision(Block a, Block b){
        return a.x < b.x + b.width &&
               a.x + a.width > b.x &&
               a.y < b.y + b.height &&
               a.y + a.height > b.y; 
    }

    public void resetPositions(){
        pacman.reset();
        pacman.velocityX = 0;
        pacman.velocityY = 0;
        for (Block ghost: ghosts) {
            ghost.reset();
            char newDirection = directions[random.nextInt(4)];
            ghost.updateDirection(newDirection);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    move();
    repaint();
    if (gameOver){
        gameLoop.stop();
    }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (gameOver || gameWon) {
            loadMap();
            resetPositions();;
            lives = 3;
            score = 0;
            gameOver = false;
            gameWon = false;
            gameLoop.start();
            return;
        }
        //System.out.println("Key Released: " + e.getKeyCode() );
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            nextDirection = 'U';   
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            nextDirection = 'D';
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            nextDirection = 'L';
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            nextDirection = 'R';
        }
        if (pacman.direction == 'U') {
            pacman.image = pacmanUpImage;
        } else if (pacman.direction == 'D') {
            pacman.image = pacmanDownImage;
        } else if (pacman.direction == 'L') {
            pacman.image = pacmanLeftImage;
        } else if (pacman.direction == 'R') {
            pacman.image = pacmanRightImage;
        }
        if (pacman.direction == ' ' && nextDirection != ' ') {
            pacman.updateDirection(nextDirection);
}
    }
    }
