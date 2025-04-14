import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.random.*;

public class Panel extends JPanel implements ActionListener {

    // Game Settings
    static int ScreenDim = 500; // dimension of screen.
    static int unit = 20; // how big each part of the grid.
    static int total_units = ((ScreenDim * 2)/ unit); // Gets the total amount of squares in the window
    static int GLOBAL_OFFSET = 50; //how fast the game is going
    boolean running = false;
    Timer t;
    Random r;

    //Snake Variables
    final int snakeX[] = new int[total_units]; //X positions of all snake bodyparts
    final int snakeY[] = new int[total_units]; //Y positions of all snake body parts
    int snake_body = 5; // starting snake body
    char direction; // which direction the snake is facing

    //Apple variables
    int Score;
    int appleX;
    int appleY;



    Panel(){

    }
    public void Start(){

    }
    public void move(){

    }
    public void Paint(Graphics paint){

    }
    public void Draw(Graphics draw){

    }
    public void CollisionChecker(){

    }
    public void AppleChecker(){

    }
    public void End(Graphics screen){

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public class KeyDetect extends KeyAdapter{
        public void KeyPressed(KeyEvent e)
        {

        }

    }

}
