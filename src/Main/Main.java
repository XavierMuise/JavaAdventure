import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) {
    
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure");


        Panel gamePanel = new Panel();
        window.add(gamePanel);

        window.pack(); // makes the window fit the preferred size of our gaamePanel

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();


    }
}