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
public class EventDS implements Serializable {
    private String event;
    private final String monthName;
    private final int dayNumber;
    
    /**
     * create an EventDS to manage your data about events
     * @param event the String which read from file 
     * @param monthName the month name 
     * @param dayNumber day offset in month
     */
    public EventDS(String event, String monthName, int dayNumber) {
        this.event = event;
        this.monthName = monthName;
        this.dayNumber = dayNumber;
    }

    /**
     * get the event 
     * @return text of event
     */
    public String getEvent() {
        return event;
    }

    /**
     * get the offset of month
     * @return parameter
     */
    public String getMonthNumber() {
        return monthName;
    }

    /**
     * get the offset of day
     * @return parameter
     */
    public int getDayNumber() {
        return dayNumber;
    }

    /**
     * set the event text
     * @param event parameter
     */
    public void setEvent(String event) {
        if (this.event == null){
            this.event =  event;
        }
        else{
            if (this.event.length() + event.length() >= 70){
                this.event += event;
            }
            else{
                this.event += "\n" + event;
            }
        }
    }
    
    @Override
    public String toString() {
        return "EventDS{" + "event=" + event + ", monthName=" + monthName + ", dayNumber=" + dayNumber + '}';
    }
    
    
}
