import javax.swing.*;

public class ProgramFrame extends JFrame {
    ProgramFrame(){
   //this method is the frame/border for the program.

        this.add(new Panel()) ; // creates an instance of the actual game


        this.setVisible(true);
        this.setTitle("Sigma Game Hatsune Miku");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the program close when the x button is hit
        this.pack(); // its like the flex box of jframe
        this.setLocationRelativeTo(null); //sets the window to the middle of the screen.
    }
}

