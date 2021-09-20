import java.awt.Graphics;
import java.awt.Color;

public class Block {    

    private int x;
    private int y;
    private int blockType;
    private int blockAngle;
    private int[][][][] BLOCKS = {
        {
            {
                {0,0,0,0,},
                {1,1,0,0,},
                {0,1,1,0,},
                {0,0,0,0,},
            },
            {
                {0,0,1,0,},
                {0,1,1,0,},
                {0,1,0,0,},
                {0,0,0,0,},
            },
            {
                {0,0,0,0,},
                {1,1,0,0,},
                {0,1,1,0,},
                {0,0,0,0,},
            },
            {
                {0,0,1,0,},
                {0,1,1,0,},
                {0,1,0,0,},
                {0,0,0,0,},
            },
        },
        {
            {
                {0,0,0,0,},
                {1,1,1,1,},
                {0,0,0,0,},
                {0,0,0,0,},
            },
            {
                {0,0,1,0,},
                {0,0,1,0,},
                {0,0,1,0,},
                {0,0,1,0,},
            },
            {
                {0,0,0,0,},
                {1,1,1,1,},
                {0,0,0,0,},
                {0,0,0,0,},
            },
            {
                {0,0,1,0,},
                {0,0,1,0,},
                {0,0,1,0,},
                {0,0,1,0,},
            },
        },
        {
            {
                {0,0,0,0,},
                {0,0,1,0,},
                {1,1,1,0,},
                {0,0,0,0,},
            },
            {
                {0,1,0,0,},
                {0,1,0,0,},
                {0,1,1,0,},
                {0,0,0,0,},
            },
            {
                {0,0,0,0,},
                {0,1,1,1,},
                {0,1,0,0,},
                {0,0,0,0,},
            },
            {
                {0,1,1,0,},
                {0,0,1,0,},
                {0,0,1,0,},
                {0,0,0,0,},
            },
        },
        {
            {
                {0,0,0,0,},
                {0,1,1,0,},
                {0,1,1,0,},
                {0,0,0,0,},
            },
            {
                {0,0,0,0,},
                {0,1,1,0,},
                {0,1,1,0,},
                {0,0,0,0,},
            },
            {
                {0,0,0,0,},
                {0,1,1,0,},
                {0,1,1,0,},
                {0,0,0,0,},
            },
            {
                {0,0,0,0,},
                {0,1,1,0,},
                {0,1,1,0,},
                {0,0,0,0,},
            },
        },
        {
            {
                {0,0,0,0,},
                {0,0,1,1,},
                {0,1,1,0,},
                {0,0,0,0,},
            },
            {
                {0,1,0,0,},
                {0,1,1,0,},
                {0,0,1,0,},
                {0,0,0,0,},
            },
            {
                {0,0,0,0,},
                {0,0,1,1,},
                {0,1,1,0,},
                {0,0,0,0,},
            },
            {
                {0,1,0,0,},
                {0,1,1,0,},
                {0,0,1,0,},
                {0,0,0,0,},
            },
        },
        {
            {
                {0,0,0,0,},
                {0,1,0,0,},
                {0,1,1,1,},
                {0,0,0,0,},
            },
            {
                {0,1,1,0,},
                {0,1,0,0,},
                {0,1,0,0,},
                {0,0,0,0,},
            },
            {
                {0,0,0,0,},
                {1,1,1,0,},
                {0,0,1,0,},
                {0,0,0,0,},
            },
            {
                {0,0,1,0,},
                {0,0,1,0,},
                {0,1,1,0,},
                {0,0,0,0,},
            },
        },
        {
            {
                {0,0,0,0,},
                {0,1,1,1,},
                {0,0,1,0,},
                {0,0,0,0,},
            },
            {
                {0,0,1,0,},
                {0,1,1,0,},
                {0,0,1,0,},
                {0,0,0,0,},
            },
            {
                {0,0,0,0,},
                {0,0,1,0,},
                {0,1,1,1,},
                {0,0,0,0,},
            },
            {
                {0,0,1,0,},
                {0,0,1,1,},
                {0,0,1,0,},
                {0,0,0,0,},
            },
        },
    };

    public Block(int x,int y){
        this.x = x;
        this.y = y;
        blockType = shuffle(BLOCKS.length);
        blockAngle = shuffle(BLOCKS[0].length);
    }

    private int shuffle(int len){
        return (int)Math.floor( Math.random() * len );
    }

    public void draw(Graphics g,int size){
        for(int y = 0;y < 4;y ++){
            for(int x = 0;x < 4;x ++){
                if(BLOCKS[blockType][blockAngle][y][x] == 0) continue;

                int cx = (x + this.x) * size;
                int cy = (y + this.y) * size;

                g.setColor(Color.RED);
                g.fillRect(cx, cy, size, size);
            }
        }
    }

    public void update(){
        this.y ++;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getBlockType(){
        return this.blockType;
    }

    public int getBlockAngle(){
        return this.blockAngle;
    }

    public int[][][][] getBlocks(){
        return this.BLOCKS;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setBlockAngle(int n){
        this.blockAngle += n;
    }

}