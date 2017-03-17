/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
class TreyMouseHandeler implements MouseListener {
    private JPopupMenu rclick;
    private Year year;
    private int month;
    private int day;
    private JFrame owner;
    /**
     * constructor
     * @param owner parent frame
     * @param year this year
     * @param month the belonging month
     * @param day the belonging day
     */
    public TreyMouseHandeler (JFrame owner , Year year, int month, int day) {
        rclick = new JPopupMenu("Popup");
        this.year = year;
        this.month = month;
        this.day = day;
        this.owner = owner;
        JMenuItem item;
        item = new JMenuItem("نمایش پنجره برنامه");
        item.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        item.setFont(new Font("Arabic Typesetting", Font.PLAIN, 30));
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                owner.setVisible(true);
                rclick.setVisible(false);
            }
        });
        rclick.add(item);
        
        item = new JMenuItem("درج");
        item.setFont(new Font("Arabic Typesetting", Font.PLAIN, 30));
        item.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MemoUI memoUI = new MemoUI(owner , year.getMemoFromADay(month, day) , false);
                    System.out.println(".actionPerformed()" + day + "///" + month);
                } catch (IOException ex) {
                    Logger.getLogger(TreyMouseHandeler.class.getName()).log(Level.SEVERE, null, ex);
                }
                rclick.setVisible(false);
            }
        });
        

        rclick.add(item);
        item = new JMenuItem("ویرایش رویداد");
        item.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        item.setFont(new Font("Arabic Typesetting", Font.PLAIN, 30));
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                OccurrenceUI occurrenceUI = new OccurrenceUI(owner , null , false);
                rclick.setVisible(false);
            }
        });
        
        
        rclick.add(item);
        item = new JMenuItem("ویرایش یادداشت");
        item.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        item.setFont(new Font("Arabic Typesetting", Font.PLAIN, 30));
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MemoUI memoUI = new MemoUI(owner , year.getMemoFromADay(month, day) , false);
                } catch (IOException ex) {
                    Logger.getLogger(TreyMouseHandeler.class.getName()).log(Level.SEVERE, null, ex);
                }
                rclick.setVisible(false);
            }
        });
        rclick.add(item);
        item = new JMenuItem("خروج از برنامه");
        item.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        item.setFont(new Font("Arabic Typesetting", Font.PLAIN, 30));
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                owner.setVisible(false);
                owner.dispose();
                System.exit(0);
            }
        });
        rclick.add(item);
        
        try {
            Calender.savingObject(this.year , "myobject");
        } catch (IOException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            owner.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            rclick.show(e.getComponent(), e.getX(), e.getY());
        }
        else
            rclick.setVisible(false);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            rclick.show(e.getComponent(), e.getX(), e.getY());
        }
        else
            rclick.setVisible(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

