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

    // SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler KH = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundEffects = new Sound();
    Thread gameThread; // Keeps the program running til something stops it
    CollisionChecker cChecker = new CollisionChecker(this);
    assetSetter aSetter = new assetSetter(this);
    UI ui = new UI(this);

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    // ENTITY AND OBJECT
    Player player = new Player(this, KH);
    public superObject[] obj = new superObject[10];
    public Entity[] npc = new Entity[10];

    public Panel(){
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KH);
        this.setFocusable(true);
    }

    public void setUpGame(){
        aSetter.setObject();
        aSetter.setNPC();
        playMusic(0);
        gameState = titleState;
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

        if(gameState == playState) {
            //PLAYER
            player.update();

            //NPC
            for(Entity e : npc){
                if(e != null) {
                    e.update();
                }
            }
        } else if (gameState == pauseState) {

        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // Debug

        long drawStart = 0;
        drawStart = System.nanoTime();

        if(gameState == titleState) {
            ui.draw(g2);
        } else {

            //Tiles
            tileM.draw(g2);

            //Objects
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }


            //Player
            player.draw(g2, TileSize);
            // UI
            ui.draw(g2);

        }
        // debug 2
        long drawEnd = 0;
        drawEnd = System.nanoTime();
        long time = drawEnd - drawStart;

        if (KH.debugMode) {
            System.out.println(time);
        }

        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int i){
        soundEffects.setFile(i);
        soundEffects.play();
    }
}
