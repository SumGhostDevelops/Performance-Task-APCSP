import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;

public class Panel extends JPanel implements ActionListener {

    // Game Settings
    static int Dimensions = 500; // dimension of screen.
    static int unit = 20; // how big each part of the grid.
    static int Tick = 80; //how fast the game is going
    boolean running = false;
    Timer t;
    Random r;
    File BackgroundMusic = new File("/Users/sumghost/IdeaProjects/Performance-Task-APCSP/src/ASGORE.wav");
    boolean MusicOn = true;
    File CoinCollect = new File("/Users/sumghost/IdeaProjects/Performance-Task-APCSP/src/pickupCoin.wav");

    //Snake Variables
    final int snakeX[] = new int[ ((Dimensions * 2)/ unit)]; //X positions of all snake bodyparts
    final int snakeY[] = new int[ ((Dimensions * 2)/ unit)]; //Y positions of all snake body parts
    int snake_body = 5; // starting snake body
    char direction = 'E'; // which direction the snake is facing

    //Apple variables

    int Score;  // this keeps the amount of apples the snake eats

    //apples lacation
    int appleX;
    int appleY;


    Panel(){




    //i feel like this is obvious
    this.setPreferredSize(new Dimension(Dimensions, Dimensions));
    this.setBackground(Color.black);
    this.setFocusable(true);
    //this adds a key detecter to 'listen' to any keys pressed and gives it to the keydetect function
    this.addKeyListener(new KeyDetect());
    //start the game :D

    r = new Random();

    Start();
    }
    public void Start() {
        // this creates the first appple
        CreatePiece();
        running = true;
        //the longer the number, the longer has to wait for the next input.. think of it like a minceaft tick
        // im writing this at 4am please be gentle with ma brain  its in  millseconds
        t = new Timer(Tick, this);

        // plays background music
        AudioInputStream audioInputStream;
        Clip BGM;
        if (MusicOn) {
        try {

                audioInputStream = AudioSystem.getAudioInputStream(BackgroundMusic);
                BGM = AudioSystem.getClip();
                BGM.open(audioInputStream);
                BGM.start();
                BGM.loop(Clip.LOOP_CONTINUOUSLY);
                if (!running){
                    BGM.close();
                }
            } catch(UnsupportedAudioFileException e){
                throw new RuntimeException(e);
            } catch(IOException e){
                throw new RuntimeException(e);
            } catch(LineUnavailableException e){
                throw new RuntimeException(e);
            }
        }
        t.start();
    }
        public void move(){
        // It makes all of the snake parts
        for( int i = snake_body; i > 0; i--){
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }
        switch(direction){
            //for each cardinal direction, it moves the snake that direction
            case 'N':
                snakeY[0] -= unit;
                break;
            case 'E':
                snakeX[0] += unit;
                break;
            case 'S':
                snakeY[0] += unit;
                break;
            case 'W':
                snakeX[0] -= unit;
                break;


        }
    }

    //also pretty self-explainatory, it draws the shapes
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {

        if (running) {
            // this makes the grid
            for (int i = 0; i <( Dimensions / unit); i++) {

                g.drawLine(i * unit, 0, i * unit, Dimensions);
                g.drawLine(0, i * unit, Dimensions, i * unit);
                g.setColor(new Color(105, 105, 105));
            }
            //draws the apple
            g.setColor(Color.yellow);
            g.fillRect(appleX,appleY, unit,unit);

            //draws the snakes body using a for loop that iterates thru its arrays.
            for(int i = 0 ; i < snake_body ; i++){
                if (i != 0){
                    //everything that isn't the head will be colored a light purple
                   g.setColor(new Color(135, 87, 186));
                   g.fillRect(snakeX[i],snakeY[i], unit, unit);
                } else{
                    //the head will be colored differenly
                    g.setColor(new Color(80, 47, 115));
                    g.fillRect(snakeX[i],snakeY[i], unit, unit);
                }
            }
            //draws the score
            g.setColor(new Color(159, 190, 227));
            g.setFont(new Font("Helvetica", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + Score, (0), g.getFont().getSize());

        }else{
            //if the programs sets running to false, it puts the game into a gameover screen.
            End(g);
        }
    }
    public void CreatePiece(){
        //sets a random position for the apple

        appleX = r.nextInt((int)(Dimensions /unit))*unit;
        appleY = r.nextInt((int)(Dimensions /unit))*unit;
    }
    public void CollisionChecker() {
        for (int i = snake_body; i > 0; i--) {
            //checks if the snake runs into itseld
            if ((snakeX[i] == snakeX[0]) &&  (snakeY[i] == snakeY[0])){
                running = false;
            }
        }
        //this checks if the snake hits the border
        if ((snakeX[0] < 0) || (snakeX[0]) > Dimensions){
            running = false;
        }
        if ((snakeY[0] < 0) || snakeY[0] > Dimensions){
            running = false;
        }
    }
    public void AppleChecker(){
        //if the snakes head hits the apple, it makes a new one and adds to the score
    if((snakeX[0] == appleX) && (snakeY[0] == appleY)){
        snake_body++;
        Score++;
        CreatePiece();
        AudioInputStream audioInputStream2;
        try {
            audioInputStream2 = AudioSystem.getAudioInputStream(CoinCollect);
            Clip Coin_Collect = AudioSystem.getClip();
            Coin_Collect.open(audioInputStream2);
            Coin_Collect.start();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

    }

    }
    public void End(Graphics screen) {
        //draws the end screen
        screen.setColor(new Color(125, 42, 64));
        screen.setFont(new Font("Helvetica", Font.BOLD, 50));
        FontMetrics metrics = getFontMetrics(screen.getFont());
        FontMetrics metrics2 = getFontMetrics(screen.getFont());
        screen.drawString("Score: " + Score, (Dimensions - metrics.stringWidth("Score: " + Score))/2, screen.getFont().getSize());
        screen.drawString("Game Over :c", (Dimensions - metrics.stringWidth("Game Over :c"))/2, Dimensions /2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //this will see at anypoint that if any key is presed / action is performed
        if (running){
            move();
            AppleChecker();
            CollisionChecker();

        }else{

        }
        repaint();
    }

    public class KeyDetect extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e)
        {
            //all keybinds
            switch (e.getKeyCode()){
// if the current direction of the snake is opposite of where it wants to go, it doesn't go to avoid dying.
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
                    case KeyEvent.VK_N:
                        running = false;
                        break;
                case KeyEvent.VK_R:
                    if(!running){
                        new ProgramFrame();

                    }
                    break;


//haii hello

            }
        }

    }

}
