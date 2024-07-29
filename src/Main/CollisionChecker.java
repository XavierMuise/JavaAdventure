public class CollisionChecker {

    Panel gp;

    public CollisionChecker(Panel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;

        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.TileSize;
        int entityRightCol = entityRightWorldX/gp.TileSize;

        int entityTopRow = entityTopWorldY/gp.TileSize;
        int entityBottomRow = entityBottomWorldY/gp.TileSize;

        int tileNum1;
        int tileNum2;
        int tileNum3;
        int tileNum4;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.TileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ){
                    entity.collisionOn = true;

                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.TileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ){
                    entity.collisionOn = true;

                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.TileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ){
                    entity.collisionOn = true;

                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.TileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ){
                    entity.collisionOn = true;

                }
                break;
            case "upLeft":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.TileSize;
                entityTopRow = (entityTopWorldY - entity.speed)/gp.TileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum3 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision || gp.tileM.tile[tileNum3].collision || gp.tileM.tile[tileNum4].collision ){
                    entity.collisionOn = true;

                }
                break;
            case "upRight":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.TileSize;
                entityTopRow = (entityTopWorldY - entity.speed)/gp.TileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum3 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision || gp.tileM.tile[tileNum3].collision || gp.tileM.tile[tileNum4].collision ){
                    entity.collisionOn = true;

                }
                break;
            case "downLeft":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.TileSize;
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.TileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum3 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision || gp.tileM.tile[tileNum3].collision || gp.tileM.tile[tileNum4].collision ){
                    entity.collisionOn = true;

                }
                break;
            case "downRight":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.TileSize;
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.TileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum3 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision || gp.tileM.tile[tileNum3].collision || gp.tileM.tile[tileNum4].collision ){
                    entity.collisionOn = true;

                }
                break;
        }

    }

    public int checkObject(Entity entity, boolean player){

        int index = 999;

        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[gp.currentMap][i] != null){
                //Get entity's solid area
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get object's solid area
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "upRight":
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "upLeft":
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "downRight":
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "downLeft":
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;

            }
        }
        return index;
    }

    // NPC OR ENEMY COLLISION
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;

        for(int i = 0; i < target.length; i++){
            if(target[i] != null){
                //Get entity's solid area
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get object's solid area
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "upRight":
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "upLeft":
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "downRight":
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "downLeft":
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }
        }
        return index;
    }



    public boolean checkPlayer(Entity entity ){

        boolean contactPlayer = false;

        //Get entity's solid area
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        //get object's solid area
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        switch(entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;

        }
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;

            return contactPlayer;
    }
}
