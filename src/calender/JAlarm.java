/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * @author MohammadHosseinForouheshTehrani
 */
public class JAlarm extends Clock {
    public int alarmHour;
    public int alarmMinute;
    public int alarmSecond;
    //Constructors
    /**
     * Constructors.
     */
    public JAlarm(){
        super();
        alarmHour = 0;
        alarmMinute = 0;
        alarmSecond = 0;
    }
    /**
     * Constructors
     * @param alarmHour the clock hour
     * @param alarmMinute the clock minutes
     * @param alarmSecond the clock second
     */
    public JAlarm(int alarmHour, int alarmMinute, int alarmSecond){
        super();
        setAlarmHour(alarmHour);
        setAlarmMinute(alarmMinute);
        setAlarmSecond(alarmSecond);
    }
    /**
     * set hour
     * @param alarmH parameter
     */
    public void setAlarmHour(int alarmH){
        if((alarmH >= 0) && (alarmH <= 24))
            this.alarmHour = alarmH;
        else
            System.out.println("Fatal error: invalid alarm hour");
    }
    /**
     * set minutes
     * @param alarmM parameter
     */
    public void setAlarmMinute(int alarmM){
        if((alarmM >= 0) && (alarmM <= 59))
            this.alarmMinute = alarmM;
        else
            System.out.println("Fatal error: invalid alarm minute");
    }
    
    /**
     * set second
     * @param alarmS parameter
     */
    public void setAlarmSecond(int alarmS){
        if((alarmS >= 0) && (alarmS <= 59))
            this.alarmSecond = alarmS;
        else
            System.out.println("Fatal error: invalid alarm second");
    }
    //Mutators
    /**
     * Mutator for hour
     * @return int parameter
     */
    public int getAlarmHour(){
        return this.alarmHour;
    }
    /**
     * Mutator for MINUTES
     * @return int parameter
     */
    public int getAlarmMinute(){
        return this.alarmMinute;
    }
    /**
     * Mutator for second
     * @return int parameter
     */
    public int getAlarmSecond(){
        return this.alarmSecond;
    }
    
    /**
     * get the current time to come to alarm
     * @return String parameter
     */
    public String getCurrentAlarmTime(){
        return "The alarm is set to " + this.alarmHour + ":" + this.alarmMinute + ":" + this.alarmSecond;
    }
    //Facilitators
    /**
     * Facilitators for string
     * @return String parameter
     */
    
    @Override
    public String toString(){
        return "The current time is " + ZonedDateTime.now().getHour()+ ":" + ZonedDateTime.now().getMinute() + ":" + ZonedDateTime.now().getSecond()+ 
                "\nThe alarm is set to " + getAlarmHour() + ":" + getAlarmMinute() + ":" + getAlarmSecond();
    }
    /**
     * when JAlarm is equal to another
     * @param o the object we want to examine
     * @return boolean
     */
    public boolean equals(Object o){
        if(o == null)
            return false;
        else if(getClass() != o.getClass())
            return false;
        else{       
            JAlarm otherClock = (JAlarm) o;       
            return((getAlarmHour() == otherClock.getAlarmHour()) && (getAlarmMinute() == otherClock.getAlarmMinute())             
                    && (getAlarmSecond() == otherClock.getAlarmSecond()) && (this.alarmHour == otherClock.alarmHour)&& 
                    (this.alarmMinute == otherClock.alarmMinute) && (this.alarmSecond == otherClock.alarmSecond)); 
        } 
 }

    @Override
    public ZoneId getZone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Clock withZone(ZoneId zone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Instant instant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
