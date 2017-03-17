/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.IslamicCalendar;
import com.ibm.icu.util.ULocale;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormatSymbols;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import javax.swing.WindowConstants;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
public class Calender implements Serializable{

    /**
     * main function to generate the calendar
     * @param args the command line arguments
     * @throws java.io.IOException Exception
     * @throws java.io.FileNotFoundException Exception
     * @throws java.lang.ClassNotFoundException Exception
     * @throws java.awt.AWTException Exception
     * @throws java.lang.InterruptedException Exception
     * @throws java.net.URISyntaxException Exception
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, AWTException, InterruptedException, URISyntaxException {
        boolean firstTime = true;
        Year year;
        if (firstTime == true){
            year = new Year(true);
            FileInputStream is = new FileInputStream("events2.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "unicode"));
            String line;
            try {
                line = br.readLine();
                while (line != null) {
                    line = br.readLine();
                    year.setEvent((int) Integer.parseInt(line.split(" ")[0]) - 1 , line.split(" ")[1], lineExceptFirstTwoParameter(line));
                }
                
            } catch(NullPointerException e ){
        
            } finally {
                br.close();
            }
            firstTime = false;
        }
        else {
            year = (Year) loadingObject("myobject");
        }
        System.err.println(year.getMemoFromADay(2, 7).getMemoText());
        UserInterface ui = new UserInterface(year);
        ui.setVisible(true);
        ui.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        ui.setSize(1600, 1780);
        ui.setResizable(false);
        ui.startSaving();
//        System.out.println(parseFarsiMonthToGregorian(1395, 0, 31));
//        System.out.println(parseFarsiMonthToIslamic(1395, 3, 31));
//        System.out.println(castToGregorian(1395, 2, 31)[0]);
        
    }
    
    /**
     * to save an object we can use this static method
     * @param obj which we want to save
     * @param name the name of exported file
     * @throws FileNotFoundException Exception
     * @throws IOException Exception
     */
    public static void savingObject(Object obj , String name) throws FileNotFoundException, IOException{
        FileOutputStream f_out = new FileOutputStream(name + ".db" , false);
        ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
        obj_out.writeObject(obj);
        obj_out.flush();
        f_out.flush();
        obj_out.close();
        f_out.close();
    }
    
    /**
     * to load an object use this static method
     * @param name the name of imported file
     * @return the loaded object
     * @throws FileNotFoundException Exception
     * @throws IOException Exception
     * @throws ClassNotFoundException Exception
     */
    public static Object loadingObject(String name) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream f_in = new FileInputStream(name + ".db");
        ObjectInputStream obj_in = new ObjectInputStream (f_in);
        // Read an object
        Object obj = obj_in.readObject();
        if (obj instanceof Vector){
            // Cast object to a Vector
            Vector vec = (Vector) obj;
        }
        obj_in.close();
        f_in.close();
        return obj;
    }
    
    /**
     * to cast boolean to integer
     * @param bool the value which is gonna cast to integer
     * @return an integer
     */
    public static int booleanIntConverter(boolean bool){
        if (bool == true){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    /**
     * when we have a data with use less first two parameters
     * @param line the whole line contents
     * @return the line without first two parameters
     */
    public static String lineExceptFirstTwoParameter(String line){
        String[] str = line.split(" ");
        String total = new String("");
        for (int i = 2 ; i < str.length ; i++){
            total += " " + str[i];
        }
        return total;
    }
    
    /**
     * to parse a Latin style number to Arabic Style
     * @param str the Latin number 
     * @return Arabic style number
     */
    public static String parseArabicNum(String str){
        char[] arabicChars = {'٠','١','٢','٣','٤','٥','٦','٧','٨','٩'};
        StringBuilder builder = new StringBuilder();
        for(int i =0;i<str.length();i++){
            if(Character.isDigit(str.charAt(i))){
                builder.append(arabicChars[(int)(str.charAt(i))-48]);
            }
            else{
                builder.append(str.charAt(i));
            }
        }
        return builder.toString();
    }
    
    /**
     * to cast a month in a year to Gregorian calendar 
     * @param year this year
     * @param month desired month
     * @param lastDay quantity of days in month
     * @return array of days in Gregorian Calendar
     */
    public static int[] castToGregorian(int year, int month, int lastDay){
        Date date = new Date();
        ULocale localIran = new ULocale("fa_IR@calendar=persian");
        Calendar pCal = Calendar.getInstance(localIran);
        Calendar gCal = Calendar.getInstance(Locale.US);
        pCal.set(1395, month, 1);
        gCal.setTime(pCal.getTime());
        int[] day = new int[lastDay];
        int i = 0;
        while(i < lastDay){
            day[i] = gCal.get(Calendar.DAY_OF_MONTH);
            pCal.set(1395, month, i + 2);
            gCal.setTime(pCal.getTime());
            i++;
        }
        return day;
    }
    
    /**
     * to cast a month in a year to Hejri calendar 
     * @param year this year
     * @param month desired month
     * @param lastDay quantity of days in month
     * @return array of days in Hejri Calendar
     */
    public static int[] castToHejri(int year, int month, int lastDay){
        Date date = new Date();
        ULocale localIran = new ULocale("fa_IR@calendar=persian");
        Calendar pCal = Calendar.getInstance(localIran);
        IslamicCalendar iCal = new IslamicCalendar(date);
        pCal.set(1395, month, 0);
        iCal.setTime(pCal.getTime());
        int[] day = new int[lastDay];
        int i = 0;
        while(i < lastDay){
            day[i] = iCal.get(Calendar.DAY_OF_MONTH);
            pCal.set(1395, month, i+1);
            iCal.setTime(pCal.getTime());
            i++;
        }
        return day;
    }
    
    /**
     * when we want to know that actual month is covered which month(s) in Hejri Calendar
     * @param year this year
     * @param month desired month
     * @param lastDay quantity of days in month
     * @return mapped month(s) in Hejri Calendar
     */
    public static String parseFarsiMonthToIslamic(int year, int month , int lastDay){
        Date date = new Date();
        ULocale localIran = new ULocale("fa_IR@calendar=persian");
        Calendar pCal = Calendar.getInstance(localIran);
        IslamicCalendar iCal = new IslamicCalendar(date);
        pCal.set(1395, month, 0);
        iCal.setTime(pCal.getTime());
        String str1 = getMonthFromIntIslamic(iCal.get(Calendar.MONTH));
        pCal.set(1395, month, lastDay);
        iCal.setTime(pCal.getTime());
        String str2 = getMonthFromIntIslamic(iCal.get(Calendar.MONTH));
        return str1 + " - " + str2 + " " + iCal.get(Calendar.YEAR);
    }
    
    /**
     * when we want to know that actual month is covered which month(s) in Gregorian Calendar
     * @param year this year
     * @param month desired month
     * @param lastDay quantity of days in month
     * @return mapped month(s) in Gregorian Calendar
     */
    public static String parseFarsiMonthToGregorian(int year, int month , int lastDay){
        Date date = new Date();
        ULocale localIran = new ULocale("fa_IR@calendar=persian");
        Calendar pCal = Calendar.getInstance(localIran);
        GregorianCalendar gCal = new GregorianCalendar();
        pCal.set(1395, month, 0);
        gCal.setTime(pCal.getTime());
        String str1 = getMonthFromIntGregorian(gCal.get(Calendar.MONTH));
        pCal.set(1395, month, lastDay);
        gCal.setTime(pCal.getTime());
        String str2 = getMonthFromIntGregorian(gCal.get(Calendar.MONTH));
        if (str1 != str2){
            return str1 + " - " + str2 + " " + gCal.get(Calendar.YEAR);
        }
        return str1 + gCal.get(Calendar.YEAR);
    }
    
    /**
     * use it when you want to know which is the n th month of Gregorian Calendar 
     * @param num the number of month
     * @return the name of the month
     */
    public static String getMonthFromIntGregorian(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
    
    /**
     * use it when you want to know which is the n th month of Islamic Calendar 
     * @param num the number of month
     * @return the name of the month
     */
    public static String getMonthFromIntIslamic(int num) {
        String[] arabMonth = {"محرم",
                            "صفر","ربيع الاول",
                            "ربيع الثاني","جمادي الاولي" ,
                            "جمادي الثانيه", "جمادي الثانيه","رجب ", 
                            "شعبان", "رمضان",
                            "شوال", "ذوالقعده",
                            "ذوالحجه"};
        
        String month = arabMonth[num];
        
        return month;
    }
    
    /**
     * use when you want to create a timer and an alert pane
     * @param hour hour you want
     * @param min minute you want
     * @param sec second you want
     * @return if it is reached , or is not actually reachable
     * @throws InterruptedException Exception
     * @throws IOException Exception
     */
    public static String timer(int hour , int min , int sec) throws InterruptedException, IOException{
        JAlarm alarm = new JAlarm(hour, min, sec);
        JLabel a = new JLabel("هشدار زمان به پایان رسید");
        a.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        a.setFont(new Font("Arabic Typesetting", Font.PLAIN, 50));
        int h = ZonedDateTime.now().getHour();
        int m = ZonedDateTime.now().getMinute();
        int s = ZonedDateTime.now().getSecond();
        int sleep = (hour - h)*3600 + (min - m) * 60 + (sec - s);
        if (sleep > 0){
            JAlarm alarm2 = new JAlarm(h , m , s);
            Thread t1 = new Thread();
            t1.start();
            while(!alarm.equals(alarm2)){
                t1.sleep(1000);
                h = ZonedDateTime.now().getHour();
                m = ZonedDateTime.now().getMinute();
                s = ZonedDateTime.now().getSecond();
                alarm2 = new JAlarm(h , m , s);
                System.out.println(h +":"+ m +":"+ s);
            }
            t1.interrupt();
            for(int i = 0 ; i < 60; i++){
                Toolkit.getDefaultToolkit().beep();
                try{
                    Thread.sleep(10);
                }catch(Exception e){
                }
            }
            JOptionPane.showMessageDialog(null, a, null, JOptionPane.WARNING_MESSAGE);
            return "catch";
        }
        
        else if (sleep == 0){
            for(int i = 0 ; i < 60; i++){
                Toolkit.getDefaultToolkit().beep();
                try{
                Thread.sleep(10);
                }catch(Exception e){
                }
            }
            JOptionPane.showMessageDialog(null, a, null, JOptionPane.WARNING_MESSAGE);
            return "catch";
        }
        
        else{
            return "Untouchable";
        }
        
    }
    
    /**
     * it create a JButton with a url text
     * @return the url JButton
     * @throws URISyntaxException Exception
     */
    public static JButton buttonURL() throws URISyntaxException{
        String str;
        str = JOptionPane.showInputDialog("url?");
        final URI uri = new URI("http://" + str);
        class OpenUrlAction implements ActionListener {
            @Override 
            public void actionPerformed(ActionEvent e) {
                open(uri);
            }
        }
        JButton button = new JButton();
        button.setText("<HTML> <FONT color=\"#000099\"><U>" + str + "</U></FONT>"
                        + "</HTML>");
        button.setName(str);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setBackground(Color.WHITE);
        button.setToolTipText(uri.toString());
        button.setFont(new Font("Arabic Typesetting", Font.BOLD, 70));
        button.addActionListener(new OpenUrlAction());
        return button;
    }
    
    /**
     * to open a url connection
     * @param uri the url address we want to open
     */
    public static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) { /* TODO: error handling */ }
        } else { /* TODO: error handling */ }
    }
    
    /**
     * use when we want to shoe a web page in our panel
     * @param str the name of the address of that page
     * @return the panel which contains web page
     * @throws IOException Exception
     */
    public static JPanel webPreviwe(String str) throws IOException{
        JPanel webPreviwe = new JPanel();
        final JEditorPane pane = new JEditorPane("http://" + str);
        pane.setEditable(false);
        ToolTipManager.sharedInstance().registerComponent(pane);
        HyperlinkListener l = new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (HyperlinkEvent.EventType.ACTIVATED == e.getEventType()) {
                    try {
                        pane.setPage(e.getURL());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        };
        
        pane.addHyperlinkListener(l);
        pane.setEditable(false);
        webPreviwe.add(pane);
        
        return webPreviwe;
    }
}
