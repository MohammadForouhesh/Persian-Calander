/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
public class Month implements Serializable {
    private final String solarName;
    private final String[] monthName = {"فروردین" , "اردیبهشت" , "خرداد" , "تیر" , "مرداد" , "شهریور" , "مهر", "آبان" , "آذر" , "دی" , "بهمن" , "اسفند"};
    private int numDayName;

    /**
     *
     */
    protected ArrayList<Day> days;
    private int quantityOfDays;

    /**
     * constructor of month 
     * @param solarNumber the offset of month
     * @param quantityOfDays number of days
     * @param numDayName days starting number
     */
    public Month(int solarNumber, int quantityOfDays, int numDayName) {
        this.solarName = monthName[solarNumber];
        this.numDayName = numDayName;
        this.quantityOfDays = quantityOfDays;
        this.days = new ArrayList<>();
        for (int i = 0; i < quantityOfDays; i++ , numDayName++){
            days.add(new Day(i , (numDayName%7) , new MemoDS(null, i , solarNumber)));
        }
    }

    /**
     * get the number of days
     * @return parameter
     */
    public int getQuantityOfDays() {
        return quantityOfDays;
    }
    
    /**
     * get the name of days
     * @return parameter
     */
    public int getNumDayName() {
        return numDayName;
    }
    
    /**
     * get the solar day name
     * @return parameter
     */
    public String getSolarName() {
        return this.solarName;
    }
    
    /**
     * get days
     * @return parameter
     */
    public ArrayList<Day> getDays() {
        return this.days;
    }
    
    /**
     * get memo for a day
     * @param day the wish day
     * @return MemoDS
     */
    public MemoDS getMemoFromADay(int day) { 
        return this.getDays().get(day).getMemos();
    }
    
    /**
     * set memo for a day
     * @param memo MemoDS we want to add
     * @param day wish day
     */
    public void setMemoForADay(MemoDS memo , int day){
        this.getDays().get(day).setMemos(memo);
    }
    
    /**
     * edit memo for a day
     * @param memoEdited edited memo
     * @param day wish day
     */
    public void editMemoForADay(String memoEdited , int day){
        String str = this.getDays().get(day).getMemoText();
        this.getDays().get(day).setMemoText(str + memoEdited);
    }
    
    @Override
    public String toString() {
        return new String(this.solarName);
    }
}
