import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
            setUpTile(0, "Grass", false);
            setUpTile(1, "Wall", true);
            setUpTile(2, "Water", true);
            setUpTile(3, "Earth", false);
            setUpTile(4, "Tree", true);
            setUpTile(5, "Sand", false);
    }

    public void setUpTile(int index, String imgPath, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].img = ImageIO.read(getClass().getResource("/resources/Tiles/" + imgPath + ".png"));
            tile[index].img = uTool.scaleImg(tile[index].img, gp.TileSize, gp.TileSize);
            tile[index].collision = collision;

        }catch(IOException e ){
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
                g2.drawImage(tile[tileNum].img, screenX, screenY, null);
            }
           worldCol++;


           if(worldCol == gp.maxWorldCol){
               worldCol = 0;
               worldRow++;

           }

       }
    }
}
