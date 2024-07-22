public class PROJ_FireBall extends Projectile {

    public PROJ_FireBall(Panel gp){
        super(gp);
        speed = 5;
        maxHP = 200;
        HP = maxHP;
        damage = 3;
        alive = false;

        getProjImg();
    }

    public void getProjImg(){
        up0 = SetUpImg("/PROJECTILES/FireBallUp");
        up1 = SetUpImg("/PROJECTILES/FireBallUp");
        up2 = SetUpImg("/PROJECTILES/FireBallUp");

        down0 = SetUpImg("/PROJECTILES/FireBallDown");
        down1 = SetUpImg("/PROJECTILES/FireBallDown");
        down2 = SetUpImg("/PROJECTILES/FireBallDown");

        left0 = SetUpImg("/PROJECTILES/FireBallLeft");
        left1 = SetUpImg("/PROJECTILES/FireBallLeft");
        left2 = SetUpImg("/PROJECTILES/FireBallLeft");

        right0 = SetUpImg("/PROJECTILES/FireBallRight");
        right1 = SetUpImg("/PROJECTILES/FireBallRight");
        right2 = SetUpImg("/PROJECTILES/FireBallRight");
    }
}
