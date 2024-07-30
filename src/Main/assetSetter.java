public class assetSetter {

    Panel gp;

    public assetSetter(Panel gp){
        this.gp = gp;

    }

    public void setObject(){
        gp.obj[0][0] = new OBJ_hole(gp);
        gp.obj[0][0].worldX = 27 * gp.TileSize;
        gp.obj[0][0].worldY = 16 * gp.TileSize;

        gp.obj[0][1] = new OBJ_Key(gp);
        gp.obj[0][1].worldX = 23 * gp.TileSize;
        gp.obj[0][1].worldY = 23 * gp.TileSize;

        gp.obj[0][2] = new SWORD_knight(gp);
        gp.obj[0][2].worldX = 11 * gp.TileSize;
        gp.obj[0][2].worldY = 9 * gp.TileSize;

        gp.obj[0][3] = new POTION_red(gp);
        gp.obj[0][3].worldX = 11 * gp.TileSize;
        gp.obj[0][3].worldY = 11 * gp.TileSize;

        gp.obj[0][4] = new OBJ_Boots(gp);
        gp.obj[0][4].worldX = 9 * gp.TileSize;
        gp.obj[0][4].worldY = 11 * gp.TileSize;

        // HOUSE
        gp.obj[1][0] = new OBJ_Door(gp, true);
        gp.obj[1][0].worldX = 12 * gp.TileSize;
        gp.obj[1][0].worldY = 13 * gp.TileSize;


    }

    public void setChunks(){
        gp.chunks[0][0] = new MirrorChunk(gp);
        gp.chunks[0][0].worldX = 23 * gp.TileSize;
        gp.chunks[0][0].worldY = 21 * gp.TileSize;
    }

    public void setNPC(){
        gp.npc[0][0] = new Rebecca(gp);
        gp.npc[0][1] = new Stranger(gp);

        gp.npc[1][0] = new Merchant(gp);
        gp.npc[1][0].worldX = gp.TileSize * 13;
        gp.npc[1][0].worldY = gp.TileSize * 7;

    }

    public void setMON(){
        for(Enemy m : gp.mon[0]){
            if(m != null){
                m = null;
            }
        }
        gp.mon[0][0] = new MON_Slime(gp, gp.TileSize * 23, gp.TileSize * 36);
        gp.mon[0][1] = new MON_Slime(gp, gp.TileSize * 23, gp.TileSize * 37);
        gp.mon[0][2] = new MON_Slime(gp, gp.TileSize * 23, gp.TileSize * 38);

        gp.mon[0][3] = new MON_FireShard(gp, gp.TileSize * 36, gp.TileSize * 7);
        gp.mon[0][4] = new MON_FireShard(gp, gp.TileSize * 40, gp.TileSize * 8);
        gp.mon[0][5] = new MON_FireShard(gp, gp.TileSize * 35, gp.TileSize * 11);
    }

    public void addProj(Projectile p){
        for(int i = 0; i < gp.proj[gp.currentMap].length; i++){
            if(gp.proj[gp.currentMap][i] == null){
                gp.proj[gp.currentMap][i] = p;
                return;
            }
        }
    }
}
