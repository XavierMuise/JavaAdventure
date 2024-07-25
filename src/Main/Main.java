import javax.swing.JFrame;

public class Main{

    public static JFrame window;
    public static boolean fs;
    public static config cg;
    public static void main(String[] args) {
        cg =  new config();
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure");
        cg.loadConfig();

        if(fs){
            window.setUndecorated(true);
        }

        Panel gamePanel = new Panel();

        window.add(gamePanel);

        window.pack(); // makes the window fit the preferred size of our gamePanel

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        if(fs){
            gamePanel.fullScreen();
        }
        gamePanel.setUpGame();
        gamePanel.startGameThread();


    }
}