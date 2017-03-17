/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
public class ImageComponent extends JButton implements Serializable{
    
    private String[] imageName = {"next" , "edit", "previous", "alarm-clock"};
    private BufferedImage[] image = new BufferedImage[4];
    int number;

    /**
     * constructor for this class
     * @param i the index of imageName
     */
    public ImageComponent(int i) {    
        this.number = i;
        try{
            this.image[number] = ImageIO.read(new File("img\\" + imageName[number] + ".png"));
        }
        catch(IOException ex){
            System.err.println(imageName[number]+ ".png" + " not found: " + ex);
        }
        this.setName(this.imageName[number]);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        if (getModel().isPressed()) {
            g.setColor(g.getColor());
            g2.fillRect(3, 3, getWidth() - 6, getHeight() - 6);
        }
        super.paintComponent(g);
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1.2f));
        g2.draw(new RoundRectangle2D.Double(1, 1, (getWidth() - 3), (getHeight() - 3), 12, 8));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(4, getHeight() - 3, getWidth() - 4, getHeight() - 3);
        
        g2.drawImage(image[number], 0, 0, null);
        
        g2.dispose();
        
    }
}
