# Pac-Man Clone in Java 🕹️


## Overview

Welcome to my Java-based Pac-Man clone! This project is a tribute to the classic arcade game, built from the ground up using **Java Swing** and **AWT** for the graphical user interface and event handling. The game board is rendered on a `JPanel`, and the logic is driven by a `javax.swing.Timer` to create the main game loop. The goal was to recreate the iconic gameplay mechanics, including maze navigation, pellet-chomping action, and ghost avoidance.

---

## 📸 Screenshots

*Gameplay in action:*

![In-Game Screenshot](https://github.com/user-attachments/assets/3509c203-2150-4270-bd4e-3238fae17cb0)

*Game Over screen:*

![Game Over Screen](https://github.com/user-attachments/assets/f46a3f29-1b70-475e-a57e-b2c6b98bdb76)

---

## 🌟 Features

* **Classic Gameplay:** Experience the authentic Pac-Man feel. Navigate the maze, eat all the pellets, and avoid the ghosts!
* **Tile-Based Map:** The game maze is easily customizable through a simple `String` array in the code.
* **Basic Ghost AI:** Ghosts move with a basic random AI, changing direction upon collision with walls, creating an unpredictable challenge.
* **Scoring and Lives:** A complete scoring system tracks eaten pellets, and you have three lives to beat the level.
* **Dynamic Rendering:** The game state is redrawn every frame using `paintComponent`, allowing for smooth animations.
* **Keyboard Controls:** Responsive keyboard input for precise Pac-Man movement.

---

## 🛠️ Built With

* [**Java**](https://www.java.com/) - The core programming language.
* [**Java Swing**](https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html) - Used for creating the `JFrame` window and `JPanel` for rendering.
* [**AWT**](https://docs.oracle.com/javase/8/docs/api/java/awt/package-summary.html) - Used for graphics, drawing, and handling keyboard events.

---



## 🚀 How to run

Follow these instructions to get a copy of the project up and running on your local machine.

### Prerequisites

* Java Development Kit (JDK) 8 or higher installed.

### Installation & Running the Game

1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/your-username/your-repository-name.git](https://github.com/your-username/your-repository-name.git)
    ```
2.  **Navigate to the project directory:**
    ```sh
    cd your-repository-name
    ```
3.  **Create a `Main.java` file:**
    Your `PacMan.java` is a `JPanel`, so it needs a `JFrame` to be displayed. Create a new file named `Main.java` in the same directory and add the following code:
    ```java
    import javax.swing.*;

    public class Main {
        public static void main(String[] args) {
            JFrame frame = new JFrame("Pac-Man");
            PacMan pacmanGame = new PacMan();
            frame.add(pacmanGame);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            pacmanGame.requestFocus();
        }
    }
    ```
4.  **Compile all Java files:**
    ```sh
    javac *.java
    ```
5.  **Run the main game file:**
    ```sh
    java Main
    ```

---

## 🎮 How to Play

* **Objective:** Clear the maze of all pellets without getting caught by the ghosts.
* **Controls:**
    * **Arrow Keys:** Use the `UP`, `DOWN`, `LEFT`, and `RIGHT` arrow keys to move Pac-Man.
* **Restart:** After a "Game Over" or "You Won" screen, press any key to restart the game.

---
