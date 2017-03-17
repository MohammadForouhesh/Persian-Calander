/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;
import javax.swing.JButton;

/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
public class JWeekName extends JButton implements Serializable {
    /**
     * constructor.
     */
    public JWeekName() {
    }
    /**
     * constructor.
     * @param text the name of the days in week 
     */
    public JWeekName(String text) {
        super(text);
    }
    /**
     * this is the most critical function of our package.
     * the g2.draw draw the top line and g2.drawLine draw the bottom line
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (getModel().isPressed()) {
            getModel().setPressed(false);
            g.setColor(g.getColor());
            g2.fillRect(3, 3, getWidth() - 1, getHeight() - 6);
        }
        super.paintComponent(g);

        g2.draw(new RoundRectangle2D.Double(0, 0, (getWidth() + 3), 0, 0, 0));
        g2.drawLine(0, getHeight() - 2, getWidth() +1, getHeight() - 2);

        g2.dispose();
    }
    
}
