import java.lang.Thread;

public class Launcher{

    private Launcher(){
        new Thread(new Game()).start();
    }

    public static void main(String[] args) {
        new Launcher();
    }
}