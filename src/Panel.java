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
    char direction = 'D'; // which direction the snake is facing

    //Apple variables
    int Score;
    int appleX;
    int appleY;



    Panel(){
    r = new Random();
    this.setPreferredSize(new Dimension(ScreenDim,ScreenDim));
    this.setBackground(Color.black);
    this.setFocusable(true);
    this.addKeyListener(new KeyDetect());
    Start();
    }
    public void Start(){
        CreatePiece();
        running = true;
        t = new Timer(GLOBAL_OFFSET, this);
        t.start();
    }
    public void move(){
        for( int i = snake_body; i > 0; i--){
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }
        switch(direction){
            case 'W':
                snakeY[0] = snakeY[0] - unit;
                break;
            case 'S':
                snakeY[0] = snakeY[0] + unit;
                break;
            case 'A':
                snakeX[0] = snakeX[0] - unit;
                break;
            case 'D':
                snakeX[0] = snakeX[0] + unit;
                break;

        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {

        if (running) {

            for (int i = 0; i <( ScreenDim / unit); i++) {

                g.drawLine(i * unit, 0, i * unit, ScreenDim);
                g.drawLine(0, i * unit, ScreenDim, i * unit);
                g.setColor(new Color(105, 105, 105));
            }
            g.setColor(Color.yellow);
            g.fillRect(appleX,appleY, unit,unit);

            for(int i = 0 ; i < snake_body ; i++){
                if (i != 0){
                   g.setColor(new Color(135, 87, 186));
                   g.fillRect(snakeX[i],snakeY[i], unit, unit);
                } else{
                    g.setColor(new Color(80, 47, 115));
                    g.fillRect(snakeX[i],snakeY[i], unit, unit);
                }
            }
        }
    }
    public void CreatePiece(){
        appleX = r.nextInt((int)(ScreenDim/unit))*unit;
        appleY = r.nextInt((int)(ScreenDim/unit))*unit;
    }
    public void CollisionChecker(){

    }
    public void AppleChecker(){

    }
    public void End(Graphics screen){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            move();
            AppleChecker();
            CollisionChecker();

        }
        repaint();
    }

    public class KeyDetect extends KeyAdapter{
        public void KeyPressed(KeyEvent e)
        {

        }

    }

}
