import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    Panel gp;
    Tile[] tile;

    int mapTileNum[][];

    public TileManager(Panel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        
        loadMap("resources/Maps/World01.txt");

    }

    public void getTileImage(){

        try{
            tile[0] = new Tile();
            tile[0].img = ImageIO.read(getClass().getResource("/resources/Tiles/Grass.png"));

            tile[1] = new Tile();
            tile[1].img = ImageIO.read(getClass().getResource("/resources/Tiles/Wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].img = ImageIO.read(getClass().getResource("/resources/Tiles/Water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].img = ImageIO.read(getClass().getResource("/resources/Tiles/Earth.png"));

            tile[4] = new Tile();
            tile[4].img = ImageIO.read(getClass().getResource("/resources/Tiles/Tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].img = ImageIO.read(getClass().getResource("/resources/Tiles/Sand.png"));



        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath){

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;

                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e){

        }


    }
    public void draw(Graphics g2){

       int worldCol = 0;
       int worldRow = 0;



       while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

           int tileNum = mapTileNum[worldCol][worldRow];

           int worldX = worldCol * gp.TileSize;
           int worldY = worldRow * gp.TileSize;

           int screenX = worldX - gp.player.worldX + gp.player.screenX;
           int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if(worldX + gp.TileSize> gp.player.worldX - gp.player.screenX &&
                    worldX - gp.TileSize< gp.player.worldX + gp.player.screenX &&
                    worldY + gp.TileSize> gp.player.worldY - gp.player.screenY &&
                    worldY - gp.TileSize< gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].img, screenX, screenY, gp.TileSize, gp.TileSize, null);
            }
           worldCol++;


           if(worldCol == gp.maxWorldCol){
               worldCol = 0;
               worldRow++;

           }

       }
    }
}
