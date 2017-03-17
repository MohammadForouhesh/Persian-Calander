/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.io.Serializable;

/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
public class Day implements Serializable {
    private String[] dayName = {"شنبه" , "یکشنبه" , "دوشنبه" , "سه شنبه" , "چهارشنبه" , "پنج شنبه" , "جمعه"};
    private String weekNameForADay;
    private int solarDayNumber;
    private MemoDS memos;
    private String events;
    private int numDayName;
    private String occurrence;
    
    /**
     * create a day in solar Calendar
     * @param solarDayNumber the number of day in month
     * @param numDayName the name of that day in week
     */
    public Day(int solarDayNumber , int numDayName){
        this.solarDayNumber = solarDayNumber;
        this.weekNameForADay = this.dayName[numDayName];
        this.numDayName = numDayName;
        this.events = null;
        this.memos = null;
    }
    
    /**
     * create a day in solar Calendar
     * @param solarDayNumber the number of day in month
     * @param numDayName the name of that day in week
     * @param memos the memo of that day
     */
    public Day(int solarDayNumber , int numDayName, MemoDS memos){
        this(solarDayNumber, numDayName);
        this.memos = memos;
        this.events = null;
    }
    
    /**
     * create a day in solar Calendar
     * @param solarDayNumber the number of day in month
     * @param numDayName the name of that day in week
     * @param memos the memo of that day
     * @param events the event of that day
     */
    public Day(int solarDayNumber, int numDayName, MemoDS memos, String events){
        this(solarDayNumber , numDayName);
        this.memos = memos;
        this.events = events;
    }
    
    /**
     * get offset of day in week
     * @return offset of day in week
     */
    public int getNumDayName() {
        return numDayName;
    }

    /**
     * get offset of day in month
     * @return offset of day in month
     */
    public int getSolarDayNumber() {
        return solarDayNumber;
    }

    /**
     * get the name of day in week
     * @return week name
     */
    public String getWeekNameForADay() {
        return weekNameForADay;
    }

    /**
     * get the day memo
     * @return memoDS for a day
     */
    public MemoDS getMemos() {
        return memos;
    }

    /**
     * get the event of a day
     * @return event of a day
     */
    public String getEvents() {
        return events;
    }

    /**
     * get the array of the dayName of week
     * @return name of day in week
     */
    public String[] getDayName() {
        return dayName;
    }

    /**
     * set the name of a name in week
     * @param dayName parameter
     */
    public void setDayName(String[] dayName) {
        this.dayName = dayName;
    }

    /**
     * get the occurrence of day
     * @return occurrence
     */
    public String getOccurrence() {
        return occurrence;
    }
    /**
     * Set the memo text.
     * @param str 
     */
    void setMemoText(String str) {
        this.memos.setText(str);
    }
    /**
     * get the memo text
     * @return parameter
     */
    public String getMemoText() {
        return this.memos.getText();
    }
    
    /**
     * set the occurrence
     * @param occurrence parameter
     */
    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }
    
    /**
     * set the week name of a day
     * @param weekNameForADay parameter
     */
    public void setWeekNameForADay(String weekNameForADay) {
        this.weekNameForADay = weekNameForADay;
    }

    /**
     * set memo of a day
     * @param memos parameter
     */
    public void setMemos(MemoDS memos) {
        this.memos = memos;
    }

    /**
     * set event of a day
     * @param events parameter
     */
    public void setEvents(String events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "" + (solarDayNumber + 1);
    }

    
    
    
}
