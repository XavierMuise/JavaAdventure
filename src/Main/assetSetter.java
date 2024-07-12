public class assetSetter {

    Panel gp;

    public assetSetter(Panel gp){
        this.gp = gp;

    }

    public void setObject(){
        gp.obj[0] = new OBJ_hole(gp);
        gp.obj[0].worldX = 27 * gp.TileSize;
        gp.obj[0].worldY = 16 * gp.TileSize;
    }

    public void setChunks(){
        gp.chunks[0] = new MirrorChunk(gp);
        gp.chunks[0].worldX = 23 * gp.TileSize;
        gp.chunks[0].worldY = 21 * gp.TileSize;
    }

    public void setNPC(){
        gp.npc[0] = new Rebecca(gp);
        gp.npc[1] = new Stranger(gp);
    }

    public void setMON(){
        for(Enemy m : gp.mon){
            if(m != null){
                m = null;
            }
        }
        gp.mon[0] = new MON_Slime(gp, gp.TileSize * 23, gp.TileSize * 36);
        gp.mon[1] = new MON_Slime(gp, gp.TileSize * 23, gp.TileSize * 37);
        gp.mon[2] = new MON_Slime(gp, gp.TileSize * 23, gp.TileSize * 38);
    }
}
