/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import com.ibm.icu.util.IslamicCalendar;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.ULocale;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
public class UserInterface extends JFrame implements ActionListener ,  Serializable {
    private final JDay[] daysButton = new JDay[42];
    private final JWeekName[] weekNameButton = new JWeekName[7];
    private final String[] monthName = {"فروردین" , "اردیبهشت" , "خرداد" , "تیر" , "مرداد" , "شهریور" , "مهر", "آبان" , "آذر" , "دی" , "بهمن" , "اسفند"};
    private final String[] dayNames = {"شنبه" , "یکشنبه" , "دوشنبه" , "سه شنبه" , "چهارشنبه" , "پنج شنبه" , "جمعه"};
    private final String[] imageName = {"add", "left_arrow", "more", "right_arrow"};
    private String memoDay;
    private String occurrenceDay;
    
    private final JPanel buttonJPanel;
    private final JPanel upperJPanel;
    private final JPanel upperJPanelContainer;
    private ImageComponent[] upperJPanelTop = new ImageComponent[4];/////////////////////////////////////////////////////////////////////////
    private JPanel eventText;
    private JTextArea eventTextArea;
    private GridBagLayout gBLayout;
    private GridBagLayout gBLayoutUP;
    private GridBagConstraints gbc = new GridBagConstraints();
    private GridBagConstraints gbcUP = new GridBagConstraints();
    
    private int timerSetHour;
    private int timerSetMinuate;
    private int timerSetSecond;
    private JPanel alarmJPanel;
    private JPanel comboBoxJPanel;
    private JPanel submiterJPanel;

    private Year year;
    private Date date;
    private ULocale localIran;
    private Calendar pCal;
    private IslamicCalendar iCal;
    private GregorianCalendar gCal;
    private int nowMonth;
    private int momentaryNowMonth;
    private int exactNowDay;
    private int momentaryNowDay;
    
    private JMenuBar menuBar;
    private JMenu insertMenu;
    private JMenu editMenu;
    
    private JMenuItem insertMenuItemOccurrence;
    private JMenuItem insertMenuItemMemo;
    
    private JMenuItem editMenuItemOccurrence;
    private JMenuItem editMenuItemMemo;
    private JMenuItem copyMenuItemEvent;
    private JMenuItem copyMenuItemOccurrence;
    private JMenuItem copyMenuItemMemo;
    private JMenuItem copyMenuItemDate;
    
    /**
     * constructor.
     * @param year this year
     * @throws AWTException Exception
     */
    public UserInterface(Year year) throws AWTException{
        super("My Calendar");
        this.setIconImage(new ImageIcon("img\\agenda.png").getImage());
        
        this.year = year;
        
        this.eventText = new JPanel();
        this.buttonJPanel = new JPanel();
        this.upperJPanel = new JPanel();
        this.alarmJPanel = new JPanel();
        this.comboBoxJPanel = new JPanel();
        this.submiterJPanel = new JPanel();
        this.upperJPanelContainer = new JPanel();
        this.gBLayout = new GridBagLayout();
        this.gBLayoutUP = new GridBagLayout();
        this.eventTextArea = new JTextArea();
        this.buttonJPanel.setLayout(this.gBLayout);
        this.upperJPanelContainer.setLayout(this.gBLayout);
        this.upperJPanel.setLayout(new BorderLayout());
        this.upperJPanel.setPreferredSize(new Dimension(1600, 384));
        this.occurrenceDay = new String();
        
        this.date = new Date();
        this.gCal = new GregorianCalendar();
        this.iCal = new IslamicCalendar(date);
        this.localIran = new ULocale("fa_IR@calendar=persian");
        this.pCal = Calendar.getInstance(this.localIran);
        this.gCal.setTime(this.pCal.getTime());
        System.out.println(gCal.get(Calendar.DAY_OF_MONTH));
        this.nowMonth = pCal.get(Calendar.MONTH);
        this.momentaryNowMonth = this.nowMonth;
        this.exactNowDay = pCal.get(Calendar.DAY_OF_MONTH);
        this.momentaryNowDay = this.exactNowDay - 1;
        
        for(int i = 0; i < 4; i++){
            this.upperJPanelTop[i] = new ImageComponent(i);
        }
        
        this.menuBar = new JMenuBar();
        this.menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        // build the File menu
        this.insertMenu = new JMenu("درج");
        this.insertMenu.setFont(new Font("Arabic Typesetting", Font.PLAIN, 50));
        this.insertMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.insertMenuItemOccurrence = new JMenuItem("اضافه کردن رویداد");
        this.insertMenuItemOccurrence.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        this.insertMenuItemOccurrence.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.insertMenuItemOccurrence.addActionListener(this);
        
        
        this.insertMenuItemMemo = new JMenuItem("اضافه کردن یادداشت");
        this.insertMenuItemMemo.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        this.insertMenuItemMemo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.insertMenuItemMemo.addActionListener(this);
        
        this.insertMenu.add(insertMenuItemOccurrence);
        this.insertMenu.add(insertMenuItemMemo);
        // build the Edit menu
        
        this.editMenu = new JMenu("ویرایش");
        this.editMenuItemOccurrence = new JMenuItem("ویرایش رویدادها");
        this.editMenuItemMemo = new JMenuItem("ویرایش یادداشت های روز مربوطه");
        this.copyMenuItemEvent = new JMenuItem("کپی کردن متن مناسبت");
        this.copyMenuItemOccurrence = new JMenuItem("کپی کردن متن رویداد");
        this.copyMenuItemMemo = new JMenuItem("کپی کردن متن یادداشت");
        this.copyMenuItemDate = new JMenuItem("کپی کردن تاریخ");
        
        this.editMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.editMenuItemOccurrence.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.editMenuItemMemo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.copyMenuItemEvent.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.copyMenuItemOccurrence.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.copyMenuItemMemo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.copyMenuItemDate.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        this.editMenu.setFont(new Font("Arabic Typesetting", Font.PLAIN, 50));
        this.editMenuItemOccurrence.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        this.editMenuItemMemo.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        this.copyMenuItemEvent.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        this.copyMenuItemOccurrence.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        this.copyMenuItemMemo.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        this.copyMenuItemDate.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        
        this.editMenuItemOccurrence.addActionListener(this);
        this.editMenuItemMemo.addActionListener(this);
        this.copyMenuItemEvent.addActionListener(this);
        this.copyMenuItemOccurrence.addActionListener(this);
        this.copyMenuItemMemo.addActionListener(this);
        this.copyMenuItemDate.addActionListener(this);
        
        this.editMenuItemOccurrence.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        this.editMenuItemMemo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        this.copyMenuItemEvent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        this.copyMenuItemOccurrence.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        this.copyMenuItemMemo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        this.copyMenuItemDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        
        this.editMenu.add(this.editMenuItemOccurrence);
        this.editMenu.add(this.editMenuItemMemo);
        this.editMenu.add(this.copyMenuItemEvent);
        this.editMenu.add(this.copyMenuItemOccurrence);
        this.editMenu.add(this.copyMenuItemMemo);
        this.editMenu.add(this.copyMenuItemDate);
        // add menus to menubar
        this.menuBar.add(this.insertMenu);
        this.menuBar.add(this.editMenu);
        // put the menubar on the frame
        this.setJMenuBar(this.menuBar);
        
        
        this.eventText.setPreferredSize(new Dimension(1600, 200));
        this.eventTextArea.setPreferredSize(new Dimension(1580, 180));
        this.eventTextArea.setFont(new Font("Arabic Typesetting", Font.PLAIN, 50));
        this.eventText.setBorder(new MatteBorder(5, 5, 5, 5, Color.YELLOW));
        this.eventTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.eventTextArea.append(this.year.getEventForADay(this.pCal.get(Calendar.DAY_OF_MONTH)-1, this.nowMonth));
        this.eventTextArea.setEditable(false);
        this.eventText.add(eventTextArea);
        
        this.update(0);
        this.drawArrows();
        
        this.displayTray(this.year, this.momentaryNowMonth, this.pCal.get(Calendar.DAY_OF_MONTH));
        super.add(this.upperJPanel, BorderLayout.NORTH);
        this.add(this.eventText , BorderLayout.SOUTH);
    }
    
    /**
     *
     * @param situation parameter
     */
    public void update(int situation){
        this.buttonJPanel.removeAll();
        this.upperJPanelContainer.removeAll();
        if ((situation == 0) || (situation == 1) || (situation == -1)){
            this.momentaryNowMonth += situation;
            this.drawDayName();
            this.drawDays(this.year.getMonth().get(this.momentaryNowMonth));
            this.drawMonthName(year.getMonth().get(this.momentaryNowMonth));
        }
        else{
            ;
        }
        super.add(this.buttonJPanel, BorderLayout.CENTER);
    }
    
    /**
     * drawing days in this function
     * @param month the belonging month
     */
    public void drawDays(Month month){
        int[] arabDays = Calender.castToHejri(1395, this.momentaryNowMonth + 1, month.getQuantityOfDays());
        int[] gregDays = Calender.castToGregorian(1395, this.momentaryNowMonth , month.getQuantityOfDays());
        for(int count = 0 , i = 0 , j = 0; j < month.getQuantityOfDays() ; count++){
            if (month.getDays().get(0).getNumDayName() - i > 0){
                    this.daysButton[i] = new JDay();
                    this.daysButton[count].setVisible(false);
                    i++;
            }
            else{
                try{
                    String arab = Calender.parseArabicNum("" + arabDays[j]);
                    String fars = Calender.parseArabicNum("" + (month.getDays().get(j).getSolarDayNumber() + 1));
                    this.daysButton[count] = new JDay(fars , arab, "" + gregDays[j]); 
                    j++;
                }
                catch(IndexOutOfBoundsException e) {
                    this.daysButton[count] = new JDay();
                    this.daysButton[count].setVisible(false);
                }
            }
            this.daysButton[count].setFont(new Font("Arabic Typesetting", Font.PLAIN, 70));
            this.daysButton[count].setBackground(new Color(253, 254 , 190));
            if (month.getSolarName().equals(this.monthName[this.nowMonth]) && month.getDays().get(j - 1).getSolarDayNumber() + 1 == this.pCal.get(Calendar.DAY_OF_MONTH)){
                this.daysButton[count].setBackground(new Color(153, 217 , 234));
            }
            else if(count > 5 && month.getDays().get(j - 1).getNumDayName() == 6 ){
                this.daysButton[count].setBackground(new Color(250, 164 , 112));
            }
            this.daysButton[count].setPreferredSize(new Dimension(228 , 228));
            this.daysButton[count].addMouseListener(new MouseHandeler(this.year, this.momentaryNowMonth, count, this));
            
            if (i == 6 || (i == 5 && count == 31)){
                this.setSize(1600, 1970);
            }
            else {
                this.setSize(1600, 1780);
            }
            
            String firstLine = this.monthName[this.momentaryNowMonth] + (j); 
            String nextStr = month.getDays().get(j != 0 ? j - 1 : j).getEvents();
            String secondLine[];
            if (nextStr != null){
                secondLine = nextStr.split("؛");
                //System.out.println(secondLine.length);
            }
            else{
                secondLine = new String[0];
            }
            String str = "<html>" + firstLine + "<br>";
            for (String secondLine1 : secondLine) {
                str += secondLine1 + "<br>";
            }
            str += "</html>";
            this.daysButton[count].setToolTipText(str);
            
            this.daysButton[count].addActionListener(this);
            
            this.gbc.fill = GridBagConstraints.BOTH;
            this.gbc.anchor = GridBagConstraints.NORTHWEST;
            this.gbc.ipady = 40;
            this.gbc.gridx = 7 - (count % 7);
            this.gbc.gridy = ((int) count / 7) + 1;
            this.gbc.weightx = 33;
            this.gbc.weighty = 20;
            this.buttonJPanel.add(this.daysButton[count] , gbc);
        }
    }
    
    /**
     * to draw arrows use this function .
     */
    public void drawArrows(){
        JPanel east = new JPanel();
        JPanel west = new JPanel();
        east.setLayout(new BorderLayout());
        west.setLayout(new BorderLayout());
        for (int i = 0 ; i < 4 ; i++){
            this.upperJPanelTop[i].setPreferredSize(new Dimension(128, 128));
            this.upperJPanelTop[i].setMinimumSize(new Dimension(128, 128));
            this.upperJPanelTop[i].setBackground(new Color(238, 238 , 238));
            this.upperJPanelTop[i].setLocation(50, 50);
            this.upperJPanelTop[i].addActionListener(this);
            if (i == 0){
                west.add(this.upperJPanelTop[i] ,BorderLayout.NORTH);
            }
            else if (i == 1){
                west.add(this.upperJPanelTop[i] ,BorderLayout.SOUTH);
            }
            else if (i == 2){
                east.add(this.upperJPanelTop[i] ,BorderLayout.NORTH);
            }
            else if (i == 3){
                east.add(this.upperJPanelTop[i] ,BorderLayout.SOUTH);
            }
            this.upperJPanel.add(east ,BorderLayout.EAST);
            this.upperJPanel.add(west ,BorderLayout.WEST);
        }
    }
    
    /**
     * draw month name b this function to your panel
     * @param month wish month
     */
    public void drawMonthName(Month month){
        JLabel solar = new JLabel();
        JLabel gregorian = new JLabel();
        JLabel hejri = new JLabel();
        String greg = Calender.parseFarsiMonthToGregorian(1395, this.momentaryNowMonth, month.getQuantityOfDays());
        String hej = Calender.parseFarsiMonthToIslamic(1395, this.momentaryNowMonth + 1, month.getQuantityOfDays());
        solar.setText(this.monthName[this.momentaryNowMonth]);
        gregorian.setText(greg);
        hejri.setText(hej);
        solar.setFont(new Font("Arabic Typesetting", Font.PLAIN, 150));
        gregorian.setFont(new Font("Arabic Typesetting", Font.PLAIN, 80));
        hejri.setFont(new Font("Arabic Typesetting", Font.PLAIN, 80));
        this.gbcUP.gridy = 0;
        this.upperJPanelContainer.add(solar , this.gbcUP);
        this.gbcUP.gridy = 1;
        this.upperJPanelContainer.add(gregorian , this.gbcUP);
        this.gbcUP.gridy = 2;
        this.upperJPanelContainer.add(hejri , this.gbcUP);
        this.upperJPanel.add(this.upperJPanelContainer , BorderLayout.CENTER);
    }
    
    /**
     *draw week name day .
     */ 
    public void drawDayName(){
        for (int i = 0 ; i < 7 ; i++){
            this.weekNameButton[i] = new JWeekName(this.dayNames[i]);
            this.weekNameButton[i].setFont(new Font("Arabic Typesetting", Font.PLAIN, 70));
            this.weekNameButton[i].setPreferredSize(new Dimension(228, 50));
            this.weekNameButton[i].setMinimumSize(new Dimension(228, 50));
            this.weekNameButton[i].setMaximumSize(new Dimension(228, 50));
            this.weekNameButton[i].setBackground(new Color(153, 217 , 234));
            this.gbc.fill = GridBagConstraints.BOTH;
            this.gbc.anchor = GridBagConstraints.NORTHWEST;
            this.gbc.ipady = 40;
            this.gbc.gridx = 7 - i;
            this.gbc.gridy = 0;
            this.gbc.weightx = 33;
            this.gbc.weighty = 20;
            this.buttonJPanel.add(this.weekNameButton[i], this.gbc);
        }
    }
    
    /**
     * if you want to set a timer in your frame.
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
            
            try {
                System.out.println(Calender.timer(this.timerSetHour, this.timerSetMinuate, this.timerSetSecond));
            } catch (InterruptedException ex) {
                Logger.getLogger(OccurrenceUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
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
    
    /**
     * each second this function save the whole calendar
     * @throws InterruptedException Exception
     * @throws IOException Exception
     */
    public void startSaving() throws InterruptedException, IOException{
        Thread t1 = new Thread();
        t1.start();
        while(this.isEnabled()){
            t1.sleep(100);
            Calender.savingObject(this.year , "myobject");
        }
        t1.interrupt();
    }
    
    /**
     * get the momentary day
     * @return parameter
     */
    public int getMomentaryNowDay(){
        return this.momentaryNowDay;
    }

    /**
     * get the year
     * @return parameter
     */
    public Year getYear() {
        return year;
    }

    /**
     * get the exact month
     * @return parameter
     */
    public int getNowMonth() {
        return nowMonth;
    }

    /**
     * get the momentary month
     * @return parameter
     */
    public int getMomentaryNowMonth() {
        return momentaryNowMonth;
    }

    /**
     * get the exact day
     * @return parameter
     */
    public int getExactNowDay() {
        return exactNowDay;
    }
    
    /**
     * get the memo day
     * @return parameter
     */
    public String getMemoDay(){
        return this.memoDay;
    }
    
    /**
     * set memo day
     * @param memoDay parameter
     */
    public void setMemoDay(String memoDay) {
        this.memoDay = memoDay;
    }
    
    /**
     * set occur in day
     * @param occurrence parameter
     */
    public void setOccurrenceDay(String occurrence) {
        this.occurrenceDay = occurrence;
    }
    /**
     * this is for system trey
     * @param year this year
     * @param month exact month
     * @param day exact day
     * @throws AWTException Exception
     */
    private void displayTray(Year year, int month, int day) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        ImageIcon icon;
        icon = new ImageIcon("img\\" + (day) + ".png");
        Image image = icon.getImage();
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Calendar_HosseinFT");
        TreyMouseHandeler tml = new TreyMouseHandeler(this , year, month, day);
        trayIcon.addMouseListener(tml);
        tray.add(trayIcon);
        trayIcon.displayMessage("Calendar Started", "Click Here To See", TrayIcon.MessageType.INFO);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) { 
        JButton JBClicked = new JButton("");
        AbstractButton JMIClicked;
        try{
            JBClicked = (JButton) event.getSource();
        }
        catch(ClassCastException e){
            ;
        }
        finally{
            JMIClicked = (AbstractButton) event.getSource();
        }
        
        if (JMIClicked.equals(this.insertMenuItemOccurrence)){
            OccurrenceUI occurrenceUI = new OccurrenceUI(this, this.year.getOccurrenceFromADay(this.momentaryNowMonth, this.momentaryNowDay) , this.momentaryNowDay);
        }
        
        else if (JMIClicked.equals(this.insertMenuItemMemo)){
            try {
                MemoUI memoUI = new MemoUI(this, this.year.getMemoFromADay(this.momentaryNowMonth , this.momentaryNowDay), this.momentaryNowDay);
            } catch (IOException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(this.momentaryNowDay);
        }
        
        else if (JMIClicked.equals(this.editMenuItemOccurrence)){
            OccurrenceUI OccurrenceUI = new OccurrenceUI(this, this.year.getOccurrenceFromADay(this.momentaryNowMonth , this.momentaryNowDay) , false);
        }
        
        else if (JMIClicked.equals(this.editMenuItemMemo)){
            try {
                MemoUI memoUI = new MemoUI(this, this.year.getMemoFromADay(this.momentaryNowMonth , this.momentaryNowDay), this.momentaryNowDay);
            } catch (IOException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(this.momentaryNowDay);
        }
        
        else if (JMIClicked.equals(this.copyMenuItemEvent)){
            String myString = this.year.getEventForADay(this.momentaryNowDay, this.momentaryNowMonth);
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
        }
        
        else if (JMIClicked.equals(this.copyMenuItemOccurrence)){
            String myString = this.year.getOccurrenceFromADay(this.momentaryNowDay, this.momentaryNowMonth);
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
        }
        
        else if (JMIClicked.equals(this.copyMenuItemMemo)){
            String myString = this.year.getMemoFromADay(this.momentaryNowMonth, this.momentaryNowDay).getMemoText();
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
        }
        
        else if (JMIClicked.equals(this.copyMenuItemDate)){
            
            String myString = "1395/" + this.momentaryNowMonth + "/" + this.momentaryNowDay ;
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
        }
        
        else if (JBClicked.getName().equals("next")){
            //System.err.println("JBClicked Name : " + JBClicked.getName());
            this.update(1);
            this.upperJPanelContainer.revalidate();
            this.upperJPanelContainer.repaint();
            this.buttonJPanel.revalidate();
            this.buttonJPanel.repaint();
        }
        
        else if (JBClicked.getName().equals("previous")){
            //System.err.println("JBClicked Name : " + JBClicked.getName());
            this.update(-1);
            this.upperJPanelContainer.revalidate();
            this.upperJPanelContainer.repaint();            
            this.buttonJPanel.revalidate();
            this.buttonJPanel.repaint();
        }
        
        else if (JBClicked.getName().equals("alarm-clock")){
            if (this.alarmJPanel != null){
                this.alarmJPanel.removeAll();
                this.comboBoxJPanel.removeAll();
                this.submiterJPanel.removeAll();
                this.alarmJPanel = null;
            }
            this.alarmJPanel = new JPanel();
            this.jTimerSeter();
            JFrame jf = new JFrame();
            jf.setSize(new Dimension(800, 215));
            jf.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            jf.setResizable(false);
            jf.add(this.alarmJPanel);
            jf.setVisible(true);
        }
        
        else if (JBClicked.getName().equals("edit")){
            try {
                MemoUI memoUI = new MemoUI(this, this.year.getMemoFromADay(this.momentaryNowMonth , this.momentaryNowDay), this.momentaryNowDay);
            } catch (IOException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        else if (JBClicked.getParent() == this.buttonJPanel){
            this.momentaryNowDay = (int) Integer.parseInt(JBClicked.getName()) - 1;
            
            this.eventTextArea.removeAll();
            this.eventTextArea.setText(year.getEventForADay(this.momentaryNowDay, this.momentaryNowMonth));
            
            //
        }
    }
}
/**
 * 
 * @author MohammadHosseinForouheshTehrani
 */

class MouseHandeler implements MouseListener {
    private JPopupMenu rclick;
    private JMenu editDayMenu;
    private JMenu insertDayMenu;
    private JMenuItem insertOccurrenceDayMenu;
    private JMenuItem insertMemoDayMenu;
    private JMenuItem editOccurrenceDayMenu;
    private JMenuItem editMemoDayMenu;
    
    private JFrame owner;
    private Year year;
    private int month;
    private int day;
    
    /**
     *  constructor.
     * @param year this year
     * @param month wish month
     * @param day wish day
     * @param parentCompunent parent frame 
     */
    public MouseHandeler (final Year year, final int month, final int day, final JFrame parentCompunent/*, JLabel btn*/) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.owner = owner;
        
        this.rclick = new JPopupMenu("Popup");
        this.insertDayMenu = new JMenu("درج");
        this.editDayMenu = new JMenu("ویرایش");
        this.insertOccurrenceDayMenu = new JMenuItem("درج رویداد");
        this.insertMemoDayMenu = new JMenuItem("درج یادداشت"); 
        this.editOccurrenceDayMenu = new JMenuItem("ویرایش رویداد");
        this.editMemoDayMenu = new JMenuItem("ویرایش یادداشت"); 
        
        
        this.insertDayMenu.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        this.insertDayMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        this.insertOccurrenceDayMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.insertOccurrenceDayMenu.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        this.insertOccurrenceDayMenu.addActionListener((ActionEvent e) -> {
            OccurrenceUI occurrenceUI = new OccurrenceUI(parentCompunent, year.getOccurrenceFromADay(this.month, this.day), this.day);
        });
        this.insertMemoDayMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.insertMemoDayMenu.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        this.insertMemoDayMenu.addActionListener((ActionEvent e) -> {
            try {
                MemoUI memoUI = new MemoUI(parentCompunent, year.getMemoFromADay(this.month, this.day) , this.day);
            } catch (IOException ex) {
                Logger.getLogger(MouseHandeler.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.insertDayMenu.add(this.insertOccurrenceDayMenu);
        this.insertDayMenu.add(this.insertMemoDayMenu);
        this.rclick.add(insertDayMenu); 
        
        this.editDayMenu.setFont(new Font("Arabic Typesetting", Font.BOLD, 40));
        this.editDayMenu.setMnemonic(KeyEvent.VK_P);
        this.editDayMenu.setToolTipText("Alt + P");
        this.editDayMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
//      item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        this.editOccurrenceDayMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.editOccurrenceDayMenu.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        this.editOccurrenceDayMenu.addActionListener((ActionEvent e) -> {
            OccurrenceUI occurrenceUI = new OccurrenceUI(parentCompunent, year.getOccurrenceFromADay(this.month, this.day) , this.day);
        });
        
//      item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
        this.editMemoDayMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.editMemoDayMenu.setFont(new Font("Arabic Typesetting", Font.PLAIN, 40));
        this.editMemoDayMenu.addActionListener((ActionEvent e) -> {
            try {
                MemoUI memoUI = new MemoUI(parentCompunent, year.getMemoFromADay(this.month, this.day) , this.day);
            } catch (IOException ex) {
                Logger.getLogger(MouseHandeler.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.editDayMenu.add(this.editOccurrenceDayMenu);
        this.editDayMenu.add(this.editMemoDayMenu);
        this.rclick.add(this.editDayMenu);
        try {
            Calender.savingObject(this.year , "myobject");
        } catch (IOException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            rclick.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            rclick.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
}