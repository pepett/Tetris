import java.awt.Graphics;
import java.awt.Color;
import java.lang.Thread;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;

public class Game {

    private Window w;
    private Graphics vG;
    private boolean flg;
    private long startTime;
    private int fps;
    private final int TILE_SIZE = 30;
    //private Font font;
    //private FontMetrics metrics;
    private EnumScreen screen;


    public Game(){
        w = new Window();
        vG = w.getRender().getImg().getGraphics();

        flg = true;
        fps = 30;

        screen = EnumScreen.START;
        
        while(flg){
            startTime = System.currentTimeMillis();

            vG.setColor(Color.WHITE);
            vG.fillRect(0, 0, 316,640);

            switch(screen){
                case START:
                    vG.setColor(Color.BLUE);
                    Font font = new Font("SansSerif",Font.PLAIN,30);
                    vG.setFont(font);
                    FontMetrics metrics = vG.getFontMetrics(font);
                    vG.drawString("Enterキーを", 150 - (metrics.stringWidth("Enterキーを") / 2), 50);
                    vG.drawString("押して", 150 - (metrics.stringWidth("押して") / 2), 100);
                    vG.drawString("ゲームを始める", 150 - (metrics.stringWidth("ゲームを始める") / 2), 150);
                    if(Keyboard.isKeyPressed(KeyEvent.VK_ENTER)){
                        screen = EnumScreen.GAME;
                    }
                    break;
                case GAME:
                    vG.setColor(Color.BLACK);
                    vG.fillRect(0, 0, 316,640);

                    vG.setColor(Color.WHITE);
                    for(int y = 0;y < 21;y ++){
                        for(int x = 0;x < 11;x ++){
                            vG.drawLine(0, y * TILE_SIZE + 0, 300, y * TILE_SIZE + 0);
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
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}
