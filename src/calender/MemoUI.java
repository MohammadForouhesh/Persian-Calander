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
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.WindowConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
public class MemoUI extends JDialog implements Serializable , ActionListener {
    
    private String memo;
    private JTextArea memoTextArea = null;
    private Locale arabic = new Locale("ar", "KW");
    private ComponentOrientation arabicOrientation = ComponentOrientation.getOrientation(arabic);
    private final Frame owner;
    private int day;
    private int month;
    private Font font;
    private JLabel myJLabel;
    private JPanel webPreviwe;
    private JButton buttonURL;
            
    /**
     * constructor 
     * @param owner parent frame
     * @param memoDS memo data Structure
     * @param bool parameter
     * @throws java.io.IOException Exception
     */
    public MemoUI(Frame owner , MemoDS memoDS , boolean bool) throws IOException{
        this(owner, memoDS, ((UserInterface) owner).getMomentaryNowDay());
        this.month = ((UserInterface) this.owner).getMomentaryNowMonth();
    }
    
    /**
     * constructor 
     * @param owner parent frame
     * @param memoDS memo data Structure
     * @param day day number
     * @throws java.io.IOException Exception
     */
    public MemoUI(Frame owner ,MemoDS memoDS, int day) throws IOException{
        super(owner , "یادداشت خود را بنویسید");
        this.font = new Font("Arabic Typesetting", Font.PLAIN, 50);
        this.memo = memoDS.getText();
        this.myJLabel = new JLabel();
        this.webPreviwe = new JPanel();
        
        if(memoDS.getMyJLabel() != null){
            this.myJLabel = memoDS.getMyJLabel();
            this.add(this.myJLabel , BorderLayout.WEST);
            this.myJLabel.setVisible(true);
            this.revalidate();
            this.repaint();
        }
        
        if(memoDS.getWebPreviwe() != null){
            this.webPreviwe = memoDS.getWebPreviwe();
            this.add(this.webPreviwe , BorderLayout.SOUTH);
            this.webPreviwe.setVisible(true);
            this.revalidate();
            this.repaint();
        }
        
        this.myJLabel.setBackground(Color.WHITE);
        this.webPreviwe.setBackground(Color.WHITE);
        this.owner = owner;
        this.day = day;
        this.month = ((UserInterface) this.owner).getMomentaryNowMonth();
        
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocation(1600, 0);
        this.setSize(new Dimension(1100, 1600));
        this.setFont(this.font);
        
        this.memoTextArea = new JTextArea();
        this.memoTextArea.setPreferredSize(new Dimension(500, 500));
        this.memoTextArea.applyComponentOrientation(arabicOrientation);
        this.memoTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.memoTextArea.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        if (this.memo == null){
            String preview ="  " + "یادداشت امروز....";
            this.memoTextArea.setText(preview);
            this.memoTextArea.setForeground(Color.GRAY);
            this.memoTextArea.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    if (memoTextArea.getForeground() == Color.GRAY){
                        memoTextArea.setText(" ");
                        memoTextArea.setForeground(Color.BLACK);
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
            this.memoTextArea.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (memoTextArea.getText().isEmpty()) {
                        memoTextArea.setForeground(Color.GRAY);
                        memoTextArea.setText("  " + "یادداشت امروز....");
                    }
                }
            });
        }
        
        else{
            this.memoTextArea.setText(this.memo);
            this.memoTextArea.setForeground(Color.BLACK);
        }
        
        this.memoTextArea.setFont(this.font);
        super.add(this.memoTextArea);
        
        JMenuBar myBar = new JMenuBar();
        myBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JMenu myMenu = getFileMenu();
        myBar.add(myMenu); 
        this.setJMenuBar(myBar);
        this.setVisible(true);
        
    }
    /**
     * get the menu
     * @return jmenu
     */
    private JMenu getFileMenu() {
        JMenu myMenu = new JMenu("ابزار");
        myMenu.setFont(this.font);
        myMenu.setComponentOrientation(arabicOrientation);
        
        JMenu myMenuInner = new JMenu("ضمیمه کردن");
        myMenuInner.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        myMenuInner.setComponentOrientation(arabicOrientation);
        myMenu.add(myMenuInner);
        
        JMenuItem myItem = new JMenuItem("ضمیمه کردن تصویر");
        myItem.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        myItem.setComponentOrientation(arabicOrientation);
        myItem.addActionListener(this);
        myMenuInner.add(myItem);
        
        myItem = new JMenuItem("ضمیمه کردن ادرس وب");
        myItem.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        myItem.setComponentOrientation(arabicOrientation);
        myItem.addActionListener(this);
        myMenuInner.add(myItem);
        
        myItem = new JMenuItem("ذخیره سازی");
        myItem.addActionListener(this);
        myItem.setComponentOrientation(arabicOrientation);
        myMenu.add(myItem);
        myItem.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        
        return myMenu;
    }

    /**
     * get the memo filed
     * @return string
     */
    public String getMemo() {
        return memo;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = ((AbstractButton) e.getSource()).getText();
        if (cmd.equals("ضمیمه کردن تصویر")) {
            if (this.myJLabel != null){
                this.myJLabel.removeAll();
                this.remove(this.myJLabel);
            }
                JFileChooser jfc = new JFileChooser("C:\\main\\04-Programming\\01-java\\calender");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG FILES", "png", "png");
                jfc.setFileFilter(filter);
                int result = jfc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = jfc.getSelectedFile();
                    try {
                        this.myJLabel = new JLabel(new ImageIcon(ImageIO.read(file)));
                        this.add(myJLabel , BorderLayout.WEST);
                    } catch (IOException ex) {
                        Logger.getLogger(MemoUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.revalidate();
                    this.repaint();
                }
                jfc.setVisible(true);
        }
        
        else if (cmd.equals("ضمیمه کردن ادرس وب")) {
            if (this.webPreviwe != null){
                this.webPreviwe.removeAll();
                this.remove(this.webPreviwe);
            }
            try {
                this.buttonURL = Calender.buttonURL();
            } catch (URISyntaxException ex) {
                Logger.getLogger(MemoUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.webPreviwe = new JPanel();
            this.webPreviwe.add(this.buttonURL, BorderLayout.NORTH);
            this.webPreviwe.setBackground(Color.WHITE);
            
            try {
                this.webPreviwe.add(Calender.webPreviwe(this.buttonURL.getName()) , BorderLayout.SOUTH);
            } catch (IOException ex) {
                Logger.getLogger(MemoUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.add(this.webPreviwe , BorderLayout.SOUTH);
            this.revalidate();
            this.repaint();
        }
        else if (cmd.equals("ذخیره سازی")) {
            if (this.memoTextArea.getForeground() == Color.GRAY) {
                    JLabel a = new JLabel("یادداشتی وارد ننموده اید.");
                    a.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                    a.setFont(new Font("Arabic Typesetting", Font.PLAIN, 50));
                    JOptionPane.showMessageDialog(null, a, null, JOptionPane.ERROR_MESSAGE);
                    return;
                }
            
            this.memo = memoTextArea.getText();
            this.myJLabel = myJLabel;
            MemoDS memoDS = new MemoDS(this.memo , this.day , this.month, this.myJLabel , this.webPreviwe);
            
            ((UserInterface) this.owner).getYear().setMemoForADay(memoDS , this.month, this.day);
            
            System.err.println("memo memo 2 is \t"+ ((UserInterface) this.owner).getYear().getMemoFromADay(this.month, this.day).getMemoText());
            ((UserInterface) this.owner).setMemoDay(null);

        }
    }   
    
    @Override
    public String toString() {
        return "MemoDS{" + "memo=" + memo + '}';
    }
    
}
