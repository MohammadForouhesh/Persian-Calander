/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
public class OccurrenceUI extends JDialog implements Serializable , ActionListener{
    private String text;
    private JTextArea textTextArea = null;
    private Locale arabic = new Locale("ar", "KW");
    private ComponentOrientation arabicOrientation = ComponentOrientation.getOrientation(arabic);
    private final Frame owner;
    
    private int day;
    private int month;
    private Font font;
    private int timerSetHour;
    private int timerSetMinuate;
    private int timerSetSecond;
    private JPanel alarmJPanel;
    private JPanel comboBoxJPanel;
    private JPanel submiterJPanel;
    
    /**
     * constructor
     * @param owner parent frame
     * @param occurrence String 
     * @param bool parameter
     */
    public OccurrenceUI(Frame owner , String occurrence , boolean bool){
        this(owner, occurrence , ((UserInterface) owner).getExactNowDay() - 1);
        this.month = ((UserInterface) this.owner).getNowMonth();
    }
    
    /**
     * constructor
     * @param owner parent frame
     * @param text the text
     * @param day parameter day my last adding.
     */
    public OccurrenceUI(Frame owner ,String text , int day){
        super(owner , "رویداد خود را بنویسید");
        this.font = new Font("Arabic Typesetting", Font.PLAIN, 50);
        this.text = text;
        this.owner = owner;
        this.day = day;
        this.month = ((UserInterface) this.owner).getMomentaryNowMonth();
        this.alarmJPanel = null;
        this.comboBoxJPanel = new JPanel();
        this.submiterJPanel = new JPanel();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocation(1610, 0);
        this.setSize(new Dimension(800, 1600));

        this.setFont(this.font);

        this.textTextArea = new JTextArea();
        this.textTextArea.setPreferredSize(new Dimension(500, 500));
        this.textTextArea.applyComponentOrientation(arabicOrientation);
        this.textTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.textTextArea.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        if (this.text == null){
            String preview ="  " + "رویداد امروز....";
            this.textTextArea.setText(preview);
            this.textTextArea.setForeground(Color.GRAY);
            this.textTextArea.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    if (textTextArea.getForeground() == Color.GRAY){
                        textTextArea.setText(" ");
                        textTextArea.setForeground(Color.BLACK);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            this.textTextArea.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (textTextArea.getText().isEmpty()) {
                        textTextArea.setForeground(Color.GRAY);
                        textTextArea.setText("  " + "یادداشت امروز....");
                    }
                }
            });
        }
        else{
            this.textTextArea.setText(this.text);
            this.textTextArea.setForeground(Color.BLACK);
        }
        
        this.textTextArea.setFont(this.font);
        super.add(this.textTextArea);

        JMenuBar myBar = new JMenuBar();
        myBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JMenu myMenu = getFileMenu();
        myBar.add(myMenu); 
        this.setJMenuBar(myBar);
        this.setVisible(true);
        
    }
    /**
     * get the menu file
     * @return jmenu
     */
    private JMenu getFileMenu() {
        JMenu myMenu = new JMenu("ابزار");
        myMenu.setFont(this.font);
        myMenu.setComponentOrientation(arabicOrientation);
        
        JMenuItem myItem = new JMenuItem("زنگ هشدار");
        myItem.addActionListener(this);
        myItem.setFont(this.font);
        myItem.setComponentOrientation(arabicOrientation);
        myMenu.add(myItem);
        
        myItem = new JMenuItem("ذخیره سازی");
        myItem.addActionListener(this);
        myItem.setComponentOrientation(arabicOrientation);
        myMenu.add(myItem);
        myItem.setFont(this.font);
        
        return myMenu;
    }
    
    /**
     * set a timer to your frame
     */
    public void jTimerSeter(){
        JComboBox hourChoose;
        JComboBox minChoose;
        JComboBox secChoose;
        JButton submit;
        JButton cancel;
        String[] hoursOptions = new String[24];
        for (int i = 0; i < 24; i++) {
            hoursOptions[i] = i + "";
        }
        hourChoose = new JComboBox(hoursOptions);
        hourChoose.setFont(new Font("Arabic Typesetting", Font.PLAIN, 90));
        hourChoose.setPreferredSize(new Dimension(128, 128));
        hourChoose.addActionListener((ActionEvent e) -> {
            
            this.timerSetHour = ((JComboBox)e.getSource()).getSelectedIndex();
        });
        String[] minOptions = new String[60];
        for (int i = 0; i < 60; i++) {
            minOptions[i] = i + "";
        }
        minChoose = new JComboBox(minOptions);
        minChoose.setFont(new Font("Arabic Typesetting", Font.PLAIN, 90));
        minChoose.setPreferredSize(new Dimension(128, 128));
        minChoose.addActionListener((ActionEvent e) -> {
            
            this.timerSetMinuate = ((JComboBox)e.getSource()).getSelectedIndex();
        });
        String[] secOptions = new String[60];
        for (int i = 0; i < 60; i++) {
            secOptions[i] = i + "";
        }
        secChoose = new JComboBox(secOptions);
        secChoose.setFont(new Font("Arabic Typesetting", Font.PLAIN, 90));
        secChoose.setPreferredSize(new Dimension(128, 128));
        secChoose.addActionListener((ActionEvent e) -> {
            
            this.timerSetSecond = ((JComboBox)e.getSource()).getSelectedIndex();
        });
        this.comboBoxJPanel.add(hourChoose);
        this.comboBoxJPanel.add(minChoose);
        this.comboBoxJPanel.add(secChoose);
        this.alarmJPanel.add(comboBoxJPanel);
        this.add(this.comboBoxJPanel, BorderLayout.SOUTH);
        
        this.submiterJPanel = new JPanel(new GridLayout(1, 2));
        submit = new JButton("ثبت");
        submit.setFont(new Font("Arabic Typesetting", Font.PLAIN, 50));
        submit.setPreferredSize(new Dimension(128, 128));
        submit.setBackground(new Color(153, 217 , 234));
        submit.addActionListener((ActionEvent e) -> {
            
            if (this.textTextArea.getForeground() == Color.GRAY) {
                JLabel a = new JLabel("یادداشتی وارد ننموده اید.");
                a.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                a.setFont(this.font);
                JOptionPane.showMessageDialog(null, a, null, JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                System.out.println(Calender.timer(this.timerSetHour, this.timerSetMinuate, this.timerSetSecond));
            } catch (InterruptedException ex) {
                Logger.getLogger(OccurrenceUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(OccurrenceUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cancel = new JButton("لغو");
        cancel.setFont(new Font("Arabic Typesetting", Font.PLAIN, 50));
        cancel.setPreferredSize(new Dimension(128, 128));
        cancel.setBackground(new Color(153, 217 , 234));
        cancel.addActionListener((ActionEvent e) -> {
            
            setVisible(false);
        });
        this.submiterJPanel.add(submit);
        this.submiterJPanel.add(cancel);
        this.alarmJPanel.add(this.comboBoxJPanel);
        this.alarmJPanel.add(this.submiterJPanel);
        this.alarmJPanel.setBackground(new Color(253, 254 , 190));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = ((AbstractButton) e.getSource()).getText();
        if (cmd.equals("زنگ هشدار")) {
            if (this.alarmJPanel != null){
                this.alarmJPanel.removeAll();
                this.remove(this.alarmJPanel);
                this.comboBoxJPanel.removeAll();
                this.submiterJPanel.removeAll();
                this.alarmJPanel = null;
            }
            this.alarmJPanel = new JPanel();
            this.jTimerSeter();
            this.add(this.alarmJPanel , BorderLayout.SOUTH);
            this.validate();
            this.repaint();
        } 
        else if (cmd.equals("ذخیره سازی")) {
            this.text = this.textTextArea.getText();
            int month = ((UserInterface) this.owner).getMomentaryNowMonth();
            ((UserInterface) this.owner).getYear().setOccurrenceForADay(this.text, month, this.day);
            ((UserInterface) this.owner).setOccurrenceDay(null);

        }
    }
}

