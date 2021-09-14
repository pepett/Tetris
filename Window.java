import javax.swing.JFrame;

public class Window extends JFrame{

    private Render render;

    public Window(){

        this.render = new Render();
        this.add(render);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tetris");
        this.pack();
        this.setSize(316,640);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        this.addKeyListener(new Keyboard());
    }

    public Render getRender(){
        return this.render;
    }

}
