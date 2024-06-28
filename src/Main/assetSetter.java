public class assetSetter {

    Panel gp;

    public assetSetter(Panel gp){
        this.gp = gp;

    }

    public void setObject(){
       
    }

    public void setNPC(){
        gp.npc[0] = new Rebecca(gp);
        gp.npc[1] = new Stranger(gp);
    }
}
