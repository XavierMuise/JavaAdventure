public class assetSetter {

    Panel gp;

    public assetSetter(Panel gp){
        this.gp = gp;

    }

    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 23 * gp.TileSize;
        gp.obj[0].worldY = 7 * gp.TileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23 * gp.TileSize;
        gp.obj[1].worldY = 40 * gp.TileSize;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 37 * gp.TileSize;
        gp.obj[2].worldY = 7 * gp.TileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 10 * gp.TileSize;
        gp.obj[3].worldY = 11 * gp.TileSize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 8 * gp.TileSize;
        gp.obj[4].worldY = 28 * gp.TileSize;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 12 * gp.TileSize;
        gp.obj[5].worldY = 22 * gp.TileSize;

        gp.obj[6] = new OBJ_Boots();
        gp.obj[6].worldX = 10 * gp.TileSize;
        gp.obj[6].worldY = 8 * gp.TileSize;

        gp.obj[7] = new OBJ_Chest(gp.obj[6]);
        gp.obj[7].worldX = 10 * gp.TileSize;
        gp.obj[7].worldY = 8 * gp.TileSize;

       
    }
}
