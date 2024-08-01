public class SWORD_knight extends superObject{

    public SWORD_knight(Panel gp) {
        super(gp);
        attackScale = 2;
        img = SetUpImg("/Objects/Straight_sword");
        name = "[Knight's Sword]";
        description = "Sword used in the \ngreat Mirror war. \nIn good condition.";
        canPickUp = true;
        price = 25;
    }
}
