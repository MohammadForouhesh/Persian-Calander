/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.awt.Frame;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
public class MemoDS implements Serializable {
    private String memoText;
    private int day;
    private int month;
    private JLabel myJLabel;
    private JPanel webPreviwe;
    
    /**
     * constructor.
     * @param memo the string memo
     * @param day the belonging day
     * @param month the belonging month
     */
    public MemoDS(String memo, int day, int month) {
        this(memo, day, month, null , null);
    }

    /**
     * constructor.
     * @param memoText parameter
     * @param day the belonging day
     * @param month the belonging month
     * @param myJLabel the photo label
     * @param webPreviwe the webPreviwe panel
     */
    public MemoDS(String memoText, int day, int month, JLabel myJLabel , JPanel webPreviwe) {
        this.memoText = memoText;
        this.day = day;
        this.month = month;
        this.myJLabel = myJLabel;
        this.webPreviwe = webPreviwe;
    }

    /**
     * access the webPreviwe panel
     * @return parameter
     */
    public JPanel getWebPreviwe() {
        return webPreviwe;
    }

    /**
     * mutate the webPreviwe panel
     * @param webPreviwe parameter
     */
    public void setWebPreviwe(JPanel webPreviwe) {
        this.webPreviwe = webPreviwe;
    }
    
    /**
     * access the memo text
     * @return parameter
     */
    public String getMemoText() {
        return memoText;
    }

    /**
     * mutate the memo text
     * @param memoText parameter
     */
    public void setMemoText(String memoText) {
        this.memoText = memoText;
    }

    /**
     * access the day
     * @return parameter
     */
    public int getDay() {
        return day;
    }

    /**
     * mutate the day
     * @param day parameter
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * access the month
     * @return parameter
     */
    public int getMonth() {
        return month;
    }

    /**
     * mutate the month
     * @param month parameter
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * access the photo label
     * @return parameter
     */
    public JLabel getMyJLabel() {
        return myJLabel;
    }

    /**
     * mutate the photo label
     * @param myJLabel parameter
     */
    public void setMyJLabel(JLabel myJLabel) {
        this.myJLabel = myJLabel;
    }
    
    /**
     * mutate the text
     * @param str parameter
     */
    public void setText(String str) {
        this.memoText = str;
    }

    /**
     * access the text
     * @return parameter
     */
    public String getText() {
        return this.memoText;
    }
}
