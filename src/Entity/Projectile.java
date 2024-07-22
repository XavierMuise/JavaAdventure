public class Projectile extends Entity {

    Entity User;
    public Projectile(Panel gp){
        super(gp);

    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.User = user;
        this.HP = maxHP;
    }

    public void update(){
        if(User == gp.player){

        }
        else{
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if(!gp.player.invincible && contactPlayer){
                int dmg = damage - gp.player.resistance;
                if(dmg < 0){
                    dmg = 0;
                }
                gp.player.HP -= dmg;
                gp.player.invincible = true;
                alive = false;

            }
        }
        switch (direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        HP--;
        if(HP <= 0){
            alive = false;
        }
        spriteCounter++;

        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
}
