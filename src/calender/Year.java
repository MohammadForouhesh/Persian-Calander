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
public class Year implements Serializable{
    private ArrayList<Month> month;
    private final String[] monthName = {"فروردین" , "اردیبهشت" , "خرداد" , "تیر" , "مرداد" , "شهریور" , "مهر", "آبان" , "آذر" , "دی" , "بهمن" , "اسفند"};
    private final Boolean Intercalary;
    private EventDS[] eventDSYear;
    
    /**
     * constructor
     * @param Intercalary if it is kabise
     */
    public Year(Boolean Intercalary) {
        this.Intercalary = Intercalary;
        this.eventDSYear = new EventDS[365 + Calender.booleanIntConverter(this.Intercalary)];
        this.month = new ArrayList<>();
        for (int i = 0 , j = -30; i < 12; i++){
            if (i < 6) {
                this.month.add(new firstHalfMonth(i , (j+31)%7));
                for (int k = 0; k < 31 ; k++){
                    this.eventDSYear[k + i*31] = new EventDS(null , this.monthName[i], k);
                }
                j += 31;
            }
            else{
                if (Intercalary == false && i == 11){
                    this.month.add(new Month(i, 29 , (j+30)%7));    
                    for (int k = 0; k < 29 ; k++){
                        this.eventDSYear[k + (i-6)*30 + 186] = new EventDS(null , this.monthName[i], k);
                    }
                    continue;
                }
                if (i == 6){
                    j += 1;
                }
                this.month.add(new secondHalfMonth(i , (j+30)%7));
                for (int k = 0; k < 30 ; k++){
                    this.eventDSYear[k + (i-6)*30 + 186] = new EventDS(null , this.monthName[i], k);
                }
                j += 30;
            }
        }
    }
    
    /**
     * set the event by the month name
     * @param day belonging day
     * @param monthName name of month
     * @param event string event
     */
    public void setEvent(int day , String monthName, String event){
        int monthNum = 0;
        
        if (monthName.equalsIgnoreCase(this.monthName[1])){
            monthNum = 1;
        }
        else if (monthName.equalsIgnoreCase(this.monthName[2])){
            monthNum = 2;
        }
        else if (monthName.equalsIgnoreCase(this.monthName[3])){
            monthNum = 3;
        }
        else if (monthName.equalsIgnoreCase(this.monthName[4])){
            monthNum = 4;
        }
        else if (monthName.equalsIgnoreCase(this.monthName[5])){
            monthNum = 5;
        }
        else if (monthName.equalsIgnoreCase(this.monthName[6])){
            monthNum = 6;
        }
        else if (monthName.equalsIgnoreCase(this.monthName[7])){
            monthNum = 7;
        }
        else if (monthName.equalsIgnoreCase(this.monthName[8])){
            monthNum = 8;
        }
        else if (monthName.equalsIgnoreCase(this.monthName[9])){
            monthNum = 9;
        }
        else if (monthName.equalsIgnoreCase(this.monthName[10])){
            monthNum = 10;
        }
        else if (monthName.equalsIgnoreCase(this.monthName[11])){
            monthNum = 11;
        }
        
        if (monthNum < 6 && day < 31){
            this.eventDSYear[day + monthNum*31].setEvent(event);
        }
        
        else if (monthNum < 12 && day < 30){
            if (monthNum == 11 && this.Intercalary == false){
                if (day >= 29){
                    return;
                }
            }
            this.eventDSYear[day + (monthNum - 6)*30 + 186].setEvent(event);
        }
        
        this.month.get(monthNum).days.get(day).setEvents(event);
    }

    /**
     * get the month
     * @return parameter
     */
    public ArrayList<Month> getMonth() {
        return month;
    }
    
    /**
     * get memo from a day 
     * @param month belonging month
     * @param day wish day
     * @return memo DS
     */
    public MemoDS getMemoFromADay(int month , int day){
        return this.getMonth().get(month).getMemoFromADay(day);
    }
    
    /**
     * get event for a day
     * @param day belonging day
     * @param monthNum belonging month
     * @return parameter
     */
    public String getEventForADay(int day , int monthNum){
        if (monthNum < 6 && day < 31){
            return this.eventDSYear[day + monthNum*31].getEvent();
        }
        
        else if (monthNum < 12 && day < 30){
            if (monthNum == 11 && this.Intercalary == false){
                if (day >= 29){
                    return null;
                }
            }
            return this.eventDSYear[day + (monthNum - 6)*30 + 186].getEvent();
        }
        return null;
    }
    
    /**
     * get the occurrence from a day
     * @param month wish month
     * @param day wish day
     * @return parameter
     */
    public String getOccurrenceFromADay(int month, int day) {
        return this.getMonth().get(month).getDays().get(day).getOccurrence();
    }

    /**
     * set the occurrence for a day
     * @param text string occur
     * @param month wish month
     * @param day wish day
     */
    public void setOccurrenceForADay(String text, int month, int day) {
        this.getMonth().get(month).getDays().get(day).setOccurrence(text);
    }
    
    /**
     * set memo for a day
     * @param memo String memo
     * @param month wish month
     * @param day wish day
     */
    public void setMemoForADay(MemoDS memo , int month , int day){
        this.getMonth().get(month).setMemoForADay(memo, day);
    }
    
    /**
     * it was just for debuging.
     */
    public void query(){
        for (int j = 0; j < 12; j++){
            System.out.println(month.get(j).getSolarName());
            for (int i = 0; i < month.get(j).days.size() ; i++){
                System.out.printf("%3s " , month.get(j).getDays().get(i).getWeekNameForADay());
                if ((i+1) % 7 == 0){
                    System.out.println();
                }
            }
            System.out.println("\n");
        }
    }

}
