import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{

    //SCREEN SETTINGS  

    final int orignalTileSize = 16; //16x16 tile 
    final int scale = 3; // scale up to make bigger

    public final int TileSize = orignalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16; // 16 columns of tiles
    public final int maxScreenRow = 12; // 12 rows of tiles
    final int ScreenWidth = TileSize * maxScreenCol; // 768 pixels wide
    final int ScreenHeight = TileSize * maxScreenRow; // 576 pixels tall

    //WORLD SETTINGS
    public final int maxWorldRow = 50;
    public final int maxWorldCol = 50;
    public final int worldWidth = TileSize * maxScreenCol;
    public final int worldHeight = TileSize * maxScreenRow;

    int FPS = 60;


    TileManager tileM = new TileManager(this);
    KeyHandler KH = new KeyHandler();
    Thread gameThread; // Keeps the program running til something stops it
    CollisionChecker cChecker = new CollisionChecker(this);
    assetSetter aSetter = new assetSetter(this);
    Player player = new Player(this, KH);


    public superObject[] obj = new superObject[10];
    public Panel(){

        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KH);
        this.setFocusable(true);
    }

    public void setUpGame(){
        aSetter.setObject();
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    // create a game loop
    public void run() {


        double drawInterval = 1000000000/FPS; // we draw the screen 60 times per second 
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            //UPDATE : Update info 
            update();
            //DRAW : Draw the screen with the updated info
            repaint(); // calls paintComponent Method 

            
            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000; // Turn to milli seconds

                if(remainingTime < 0){
                    remainingTime =0;
                } 

                Thread.sleep((long) remainingTime); // Pause the game loop til the next frame

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }    
    }  

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //Tiles
        tileM.draw(g2);

        //Objects
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //Players
        player.draw(g2, TileSize);
        g2.dispose();

    }
}
