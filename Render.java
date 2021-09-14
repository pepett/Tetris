import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Render extends JPanel{

    private BufferedImage image;

    public Render(){
        super();
        this.image = new BufferedImage(316,640,BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(){
        this.repaint();
    }

    public BufferedImage getImg(){
        return this.image;
    }

    
}