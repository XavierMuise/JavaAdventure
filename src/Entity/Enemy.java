public class Enemy extends Entity{

    public int shardsDropped;
    public Enemy(Panel gp) {
        super(gp);
        type = Entity.enemy;
    }

    public void contactPlayer(){
        if(!gp.player.invincible) {

            int dmg = damage - gp.player.resistance;
            if(dmg < 0){
                dmg = 0;
            }
            gp.player.HP -= dmg;
            gp.player.invincible = true;
        }
    }

    public void damageReaction() {}

    public void update() {
        SetAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(contactPlayer){
            contactPlayer();
        }

        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;

        if (spriteCounter > 20) {
            if (spriteNum == 0) {
                spriteNum = 1;
            } else if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;

        }

        if(invincible){
            iframeCounter++;
            if(iframeCounter > 30){
                iframeCounter = 0;
                invincible = false;
            }
        }

    }
}
