import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Thread;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;

public class Game extends Thread{

    private Window w;
    private Graphics vG;
    private boolean flg;
    private boolean blockFlag;
    private long startTime;
    private int dropTime;
    private int fps;
    private final int TILE_SIZE = 30;
    private Font font;
    private FontMetrics metrics;
    private EnumScreen screen;
    private ArrayList<Block> blocks;

    private final int cX = 3;
    private final int cY = 0;

    //private static boolean keyflg = true;
    private static int count = 0;


    public Game(){
        w = new Window();
        vG = w.getRender().getImg().getGraphics();

        flg = true;
        blockFlag = true;
        fps = 30;
        dropTime = 30;

        blocks = new ArrayList<>();

        screen = EnumScreen.START;

        //int count = 0;
        
        while(flg){
            startTime = System.currentTimeMillis();

            vG.setColor(Color.WHITE);
            vG.fillRect(0, 0, 316,640);

            switch(screen){
                case START:
                    vG.setColor(Color.BLUE);
                    font = new Font("SansSerif",Font.PLAIN,30);
                    vG.setFont(font);
                    metrics = vG.getFontMetrics(font);
                    vG.drawString("Enterキーを", 150 - (metrics.stringWidth("Enterキーを") / 2), 50);
                    vG.drawString("押して", 150 - (metrics.stringWidth("押して") / 2), 100);
                    vG.drawString("ゲームを始める", 150 - (metrics.stringWidth("ゲームを始める") / 2), 150);
                    if(Keyboard.isKeyPressed(KeyEvent.VK_ENTER)){
                        screen = EnumScreen.GAME;
                    }
                    break;
                case GAME:
                    for(int y = 0;y < 20;y ++){
                        for(int x = 0;x < 10;x ++){
                            if(Field.getField()[y][x] != 0) continue;
                            vG.setColor(Color.BLACK);
                            vG.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE,TILE_SIZE);
                        }
                    }

                    if(blockFlag){
                        blocks.add(new Block(cX,cY));
                        blockFlag = false;
                    }

                    if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE) && count == 0){
                        count ++;
                        Block b = blocks.get(blocks.size() - 1);
                        int[][][][] tmpB = new int[b.getBlocks().length][b.getBlocks()[0].length][4][4];
                            for(int i = 0;i < blocks.get(blocks.size() - 1).getBlocks().length;i ++){
                                for(int j = 0;j < blocks.get(blocks.size() - 1).getBlocks()[0].length;j ++){
                                    for(int y = 0;y < 4;y ++){
                                        for(int x = 0;x < 4;x ++){
                                            tmpB[i][j][y][x] = blocks.get(blocks.size() - 1).getBlocks()[i][j][y][x];
                                        }
                                    }
                                    
                                }
                            }
                        if(b.getBlockAngle() < 3){

                            // = new int[i][j][y][x]
                            //tmpB.setBlockAngle(1);
                            if(this.check(0, 0, tmpB[b.getBlockType()][b.getBlockAngle() + 1])){
                                blocks.get(blocks.size() - 1).setBlockAngle(1);
                            }
                        }else{
                            if(this.check(0, 0, tmpB[b.getBlockType()][0])) blocks.get(blocks.size() - 1).setBlockAngle(-3);
                        }
                        //if( this.check(0, 0, b) ){
                        //    System.out.println('a');
                        //    blocks.get(blocks.size() - 1).setBlockAngle(1);
                        //}else{
                        //    System.out.println("error");
                        //    if(blocks.get(blocks.size() - 1).getBlockAngle() == 3){
                        //        blocks.get(blocks.size() - 1).setBlockAngle(-3);
                        //    }else{
                        //        blocks.get(blocks.size() - 1).setBlockAngle(1);
                        //    }
                        //}
                    }

                    if(Keyboard.isKeyPressed(KeyEvent.VK_LEFT) && count == 0){
                        count ++;
                        Block b = blocks.get(blocks.size() - 1);
                        if( this.check(-1, 0, b.getBlocks()[b.getBlockType()][b.getBlockAngle()]) ){
                            blocks.get(blocks.size() - 1).setX(blocks.get(blocks.size() - 1).getX() - 1);
                        }
                    }

                    if(Keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && count == 0){
                        count ++;
                        Block b = blocks.get(blocks.size() - 1);
                        if( this.check(1, 0, b.getBlocks()[b.getBlockType()][b.getBlockAngle()]) ){
                            blocks.get(blocks.size() - 1).setX(blocks.get(blocks.size() - 1).getX() + 1);
                        }
                    }

                    Block b = blocks.get(blocks.size() - 1);
                    if(this.check(0, 1, b.getBlocks()[blocks.get(blocks.size() - 1).getBlockType()][blocks.get(blocks.size() - 1).getBlockAngle()])){
                        if(dropTime == 0){
                            b.update();
                        dropTime = 30;
                        }
                    }else{
                        for(int y = 0;y < 4;y ++){
                            for(int x = 0;x < 4;x ++){

                                if(b.getBlocks()[b.getBlockType()][b.getBlockAngle()][y][x] == 0) continue;

                                int nx = x + b.getX();
                                int ny = y + b.getY();

                                Field.setField(nx, ny);
                                blockFlag = true;
                            }
                        }
                    }
                    b.draw(vG,TILE_SIZE);

                    vG.setColor(Color.WHITE);
                    for(int y = 0;y < 21;y ++){
                        for(int x = 0;x < 11;x ++){
                            vG.drawLine(0, y * TILE_SIZE, 300, y * TILE_SIZE);
                            vG.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, 600);
                        }
                    }

                    break;
                case GAMEORVER:
                    break;
                default:
                    System.out.println("Error");
            }


            w.getRender().draw();

            try{
                long runTime = System.currentTimeMillis() - startTime;
                if(runTime < (1000 / fps)) {
                    Thread.sleep( (1000 / fps) - runTime );
                    dropTime --;
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    //private boolean check(int x,int y){
    //    Block b = blocks.get(blocks.size() - 1);
    //    int blockAngle = b.getBlockAngle();
    //    if(b.getBlockAngle() < 3){
    //        b.setBlockAngle(1);
    //    }else{
    //        b.setBlockAngle(-3);
    //    }
    //    return check(x, y, b);
    //    //if(check(x, y, b)){
    //    //    //return b.setBlockAngle(blockAngle,false);
    //    //    return true;
    //    //}else{
    //    //    return false;
    //    //}
    //}


    private boolean check(int x,int y,int[][] b){
        //int[][][][] block = b.getBlocks();
        for(int ny = 0;ny < 4;ny ++){
            for(int nx = 0;nx < 4;nx ++){
                //System.out.println(ny);
                //System.out.println(nx);
                //System.out.println(b.getBlockType());
                //System.out.println(b.getBlockAngle());
                if(b[ny][nx] == 0) continue;

                int checkX = x + nx + blocks.get(blocks.size() - 1).getX();
                int checkY = y + ny + blocks.get(blocks.size() - 1).getY();

                if(
                    checkX < 0 || checkY < 0 ||
                    checkX >= 10 || checkY >= 20 ||
                    Field.getField()[checkY][checkX] != 0
                ) return false;
            }
        }

        return true;
    }

    public static void setKeyFlg(){
        //keyflg = true;
        count = 0;
    }

}
