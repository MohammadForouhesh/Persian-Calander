/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;
import javax.swing.JButton;

/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
public class JDay extends JButton implements Serializable{
    String gregorianDay;
    String lunarDay;
    
    /**
     * constructor.
     */
    public JDay() {
        this(null, null , null);
    }

    /**
     * constructor.
     * @param text the showing text
     * @param gregorianDay the day in greg Calendar
     * @param lunarDay the day in Islamic Calendar
     */
    public JDay(String text, String lunarDay, String gregorianDay) {
        super(text);
        this.setName(text);
        this.lunarDay = lunarDay;
        this.gregorianDay = gregorianDay;
    }
    
    @Override
    public void paintComponent(Graphics g) {
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
        g2.setFont(new Font("Arabic Typesetting", Font.PLAIN, 50));
        if (this.lunarDay.length() == 2){
            g2.drawString(this.lunarDay , 25, 160);
        }
        else{
            g2.drawString(this.lunarDay , 35, 160);
        }
        //g2.drawString(this.gregorianDay , 30, 160);
        if (this.lunarDay.length() == 2){
            g2.drawString(this.gregorianDay, 165, 160);
        }
        else{
            g2.drawString(this.gregorianDay, 165, 160);
        }
        g2.dispose();
    }
    
}

