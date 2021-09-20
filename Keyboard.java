import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Keyboard extends KeyAdapter {
    private static ArrayList<Integer> keys = new ArrayList<>();


    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if(!keys.contains(e.getKeyCode())){
            keys.add(e.getKeyCode());
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        Game.setKeyFlg();
        keys.remove((Integer)e.getKeyCode());
    }

    public static boolean isKeyPressed(int key){
        return keys.contains(key);
    }
    
}
