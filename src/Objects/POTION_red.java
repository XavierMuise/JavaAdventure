public class POTION_red extends superObject {
    public POTION_red(Panel gp) {
        super(gp);
        img = SetUpImg("/Objects/RedPotion");
        name = "[Red Potion]";
        description = "Heals 3 hearts";
        canPickUp = true;
    }

    public boolean use(){
        if(gp.player.HP != gp.player.maxHP) {
            if (gp.player.HP + 6 > gp.player.maxHP) {
                gp.player.HP = gp.player.maxHP;
            } else {
                gp.player.HP += 6;
            }
            return true;
        }
        return false;
    }

}
