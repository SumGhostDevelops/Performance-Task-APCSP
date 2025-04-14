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
    char direction = 'E'; // which direction the snake is facing

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
            case 'N':
                snakeY[0] = snakeY[0] - unit;
                break;
            case 'E':
                snakeX[0] = snakeX[0] + unit;
                break;
            case 'S':
                snakeY[0] = snakeY[0] + unit;
                break;
            case 'W':
                snakeX[0] = snakeX[0] - unit;
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
            g.setColor(new Color(159, 190, 227));
            g.setFont(new Font("Helvetica", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + Score, (0), g.getFont().getSize());

        }else{
            End(g);
        }
    }
    public void CreatePiece(){
        appleX = r.nextInt((int)(ScreenDim/unit))*unit;
        appleY = r.nextInt((int)(ScreenDim/unit))*unit;
    }
    public void CollisionChecker() {
        for (int i = snake_body; i > 0; i--) {
            if ((snakeX[i] == snakeX[0]) &&  (snakeY[i] == snakeY[0])){
                running = false;
            }
        }
        if ((snakeX[0] < 0) || (snakeX[0]) > ScreenDim){
            running = false;
        }
        if ((snakeY[0] < 0) || snakeY[0] > ScreenDim){
            running = false;
        }
        if (!running){
            t.stop();
        }
    }
    public void AppleChecker(){
    if((snakeX[0] == appleX) && (snakeY[0] == appleY)){
        snake_body++;
        Score++;
        CreatePiece();

    }

    }
    public void End(Graphics screen) {
        screen.setColor(new Color(125, 42, 64));


        screen.setFont(new Font("Helvetica", Font.BOLD, 50));
        FontMetrics metrics = getFontMetrics(screen.getFont());
        screen.drawString("Game Over :c", (ScreenDim - metrics.stringWidth("Game Over :c"))/2, ScreenDim/2);

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
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode()){

                case KeyEvent.VK_UP:
                    if(direction != 'S'){
                        direction = 'N';
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if(direction != 'N'){
                        direction = 'S';
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if(direction != 'W'){
                        direction = 'E';
                    }
                    break;

                case KeyEvent.VK_LEFT:
                    if(direction != 'E'){
                        direction = 'W';
                    }
                    break;
                case KeyEvent.VK_W:
                    if(direction != 'S'){
                        direction = 'N';
                    }
                    break;

                case KeyEvent.VK_S:
                    if(direction != 'N'){
                        direction = 'S';
                    }
                    break;

                case KeyEvent.VK_D:
                    if(direction != 'W'){
                        direction = 'E';
                    }
                    break;

                case KeyEvent.VK_A:
                    if(direction != 'E'){
                        direction = 'W';
                    }
                    break;


            }
        }

    }

}
