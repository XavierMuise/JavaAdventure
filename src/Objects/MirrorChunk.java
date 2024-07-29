import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class MirrorChunk extends Entity{

    public MirrorChunk(Panel gp){
        super(gp);
        down0 = SetUpImg("/Objects/MirrorChunk");
        down1 = SetUpImg("/Objects/MirrorChunk");
        down2 = SetUpImg("/Objects/MirrorChunk");
        direction = "down";
        solidArea = new Rectangle(0,0 , 32, 48);
    }

    public void rest(){
        gp.player.HP = gp.player.maxHP;
        gp.gameState = gp.statsState;
        gp.player.worldX = this.worldX;
        gp.player.worldY = this.worldY + gp.TileSize;
        gp.aSetter.setMON();
    }
}
