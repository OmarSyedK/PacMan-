# Pac-Man Clone in Java 🕹️


## Overview

Welcome to my Java-based Pac-Man clone! This project is a tribute to the classic arcade game, built from the ground up using **Java Swing** and **AWT** for the graphical user interface and event handling. The game board is rendered on a `JPanel`, and the logic is driven by a `javax.swing.Timer` to create the main game loop. The goal was to recreate the iconic gameplay mechanics, including maze navigation, pellet-chomping action, and ghost avoidance.

---

## 📸 Screenshots

*Gameplay in action:*

![In-Game Screenshot](https://github.com/user-attachments/assets/cee11b3c-6185-4246-be9f-ef926997639b)

*Game Over screen:*

![Game Over Screen](https://github.com/user-attachments/assets/f38b1e94-8e23-453d-91b0-fa4454e8aa53)

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

## ⚙️ Technical Breakdown

This project uses a straightforward and effective design:

* **Game Loop:** A `javax.swing.Timer` is configured to fire every 50ms (driving a 20 FPS frame rate). Each tick calls the `actionPerformed` method, which updates the game state (`move()`) and redraws the screen (`repaint()`).
* **Entity Management:** All game objects (Pac-Man, ghosts, walls, food) are instances of a flexible inner `Block` class. They are stored in `HashSet` collections for efficient access and management.
* **Map Generation:** The game level is loaded from a `String[]` called `tileMap`. The code iterates through this array, creating `Block` objects based on the character codes ('X' for wall, 'P' for Pac-Man, etc.). This makes designing new levels as simple as editing a text-based map.
* **Collision Detection:** A simple and efficient bounding-box collision method checks for intersections between Pac-Man, ghosts, walls, and food pellets.

---

## 🚀 Getting Started

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
3.  **Compile all Java files:**
    ```sh
    javac *.java
    ```
4.  **Run the main game file:**
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

