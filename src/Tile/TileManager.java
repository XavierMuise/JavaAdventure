import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    Panel gp;
    Tile[] tile;

    int mapTileNum[][][];

    public TileManager(Panel gp){
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        
        loadMap("resources/Maps/World02.txt", 0);
        loadMap("resources/Maps/interior01.txt", 1);
    }

    public void getTileImage(){

        // PLACEHOLDERS
        setUpTile(0, "Grass", false);
        setUpTile(1, "Grass", false);
        setUpTile(2, "Grass", false);
        setUpTile(3, "Grass", false);
        setUpTile(4, "Grass", false);
        setUpTile(5, "Grass", false);
        setUpTile(6, "Grass", false);
        setUpTile(7, "Grass", false);
        setUpTile(8, "Grass", false);
        setUpTile(9, "Grass", false);

        // GRASS
        setUpTile(10, "Grass", false);
        setUpTile(11, "TallGrass", false);

        // WATER
        setUpTile(12, "Water", true);
        setUpTile(13, "ShimmeringWater", true);
        setUpTile(14, "GrassWaterTopLeft", true);
        setUpTile(15, "GrassWaterTop", true);
        setUpTile(16, "GrassWaterTopRight", true);
        setUpTile(17, "GrassWaterLeft", true);
        setUpTile(18, "GrassWaterRight", true);
        setUpTile(19, "GrassWaterBottomLeft", true);
        setUpTile(20, "GrassWaterBottom", true);
        setUpTile(21, "GrassWaterBottomRight", true);
        setUpTile(22, "WaterGrassTopLeft", true);
        setUpTile(23, "WaterGrassTopRight", true);
        setUpTile(24, "WaterGrassBottomLeft", true);
        setUpTile(25, "WaterGrassBottomRight", true);

        // PATHS
        setUpTile(26, "Path", false);
        setUpTile(27, "PathGrassBottomRight", false);
        setUpTile(28, "PathGrassBottom", false);
        setUpTile(29, "PathGrassBottomLeft", false);
        setUpTile(30, "PathGrassRight", false);
        setUpTile(31, "PathGrassLeft", false);
        setUpTile(32, "PathGrassTopRight", false);
        setUpTile(33, "PathGrassTop", false);
        setUpTile(34, "PathGrassTopLeft", false);
        setUpTile(35, "GrassPathTopLeft", false);
        setUpTile(36, "GrassPathTopRight", false);
        setUpTile(37, "GrassPathBottomLeft", false);
        setUpTile(38, "GrassPathBottomRight", false);

        // MISC
        setUpTile(39, "Earth", false);
        setUpTile(40, "Wall", true);
        setUpTile(41, "Tree", true);

        // INSIDE
        setUpTile(42, "house", false);
        setUpTile(43, "floor01", false);
        setUpTile(44, "table", false);
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
    public void loadMap(String filePath, int map){

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                    col++;

                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception ignored){

        }


    }
    public void draw(Graphics g2){

       int worldCol = 0;
       int worldRow = 0;



       while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

           int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

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
