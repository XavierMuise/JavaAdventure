import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{

    //SCREEN SETTINGS  
    boolean fullScreen = false;
    final int orignalTileSize = 16; //16x16 tile 
    final int scale = 3; // scale up to make bigger

    public final int TileSize = orignalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 20; // 20 columns of tiles
    public final int maxScreenRow = 12; // 12 rows of tiles
    final int ScreenWidth = TileSize * maxScreenCol; // 960 pixels wide
    final int ScreenHeight = TileSize * maxScreenRow; // 576 pixels tall

    // FULL SCREEN
    int screenWidth2 = ScreenWidth;
    int screenHeight2 = ScreenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;


    //WORLD SETTINGS
    public final int maxWorldRow = 50;
    public final int maxWorldCol = 50;
    public final int worldWidth = TileSize * maxScreenCol;
    public final int worldHeight = TileSize * maxScreenRow;
    public final int maxMap = 10;
    public int currentMap = 0;

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
    public EventHandler EH = new EventHandler(this);

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int statsState = 4;
    public final int inventoryState = 5;
    public final int deathState = 6;
    public final int transitionState = 7;


    // ENTITY AND OBJECT
    Player player = new Player(this, KH);
    public superObject[][] obj = new superObject[maxMap][10];
    public NPC[][] npc = new NPC[maxMap][10];
    public Enemy[][] mon = new Enemy[maxMap][10];
    public MirrorChunk[][] chunks = new MirrorChunk[maxMap][10];
    public Projectile[][] proj = new Projectile[maxMap][10];

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
        aSetter.setMON();
        aSetter.setChunks();
        playMusic(0);
        gameState = titleState;

        tempScreen =  new BufferedImage(ScreenWidth, ScreenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();
    }

    public void fullScreen(){

        // local screen device
        GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice GD = GE.getDefaultScreenDevice();
        GD.setFullScreenWindow(Main.window);

        // get fullscreen width / height
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
        fullScreen = true;

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
            drawToTempScreen();
            drawToScreen();

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
            for(NPC e : npc[currentMap]){
                if(e != null) {
                    e.update();
                }
            }
            // ENEMIES
            for(int i = 0; i < mon[currentMap].length; i++){
                if(mon[currentMap][i] != null) {
                    if(mon[currentMap][i] .alive && !mon[currentMap][i] .dying) {
                        mon[currentMap][i] .update();
                    } else if(!mon[currentMap][i] .alive) {
                        mon[currentMap][i]  = null;
                    }

                }
            }

            // chunks
            for (MirrorChunk chunk : chunks[currentMap]) {
                if (chunk != null) {
                    chunk.update();
                }
            }
            // PROJECTILES
            for (Projectile projectile : proj[currentMap]) {
                if (projectile != null) {
                    if (projectile.alive) {
                        projectile.update();
                    }
                }
            }

        } else if (gameState == pauseState) {

        }
    }
    public void drawToTempScreen(){

        long drawStart = 0;
        drawStart = System.nanoTime();

        if(gameState == titleState) {
            ui.draw(g2);
        } else {

            //Tiles
            tileM.draw(g2);

            //Objects
            for (superObject superObject : obj[currentMap]) {
                if (superObject != null) {
                    superObject.draw(g2, this);
                }
            }
            // NPC
            for(NPC entity : npc[currentMap]) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            // Enemies
            for(Enemy entity : mon[currentMap]){
                if(entity != null) {
                    entity.draw(g2);
                }
            }
            //chunks
            for(MirrorChunk chunk : chunks[currentMap]){
                if(chunk != null){
                    chunk.draw(g2);
                }
            }

            for(Projectile p : proj[currentMap]){
                if(p != null && p.alive){
                    p.draw(g2);
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
            System.out.println("X : " + (player.worldX/TileSize) + ", Y: " + (player.worldY/TileSize));
        }

    }

    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
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
