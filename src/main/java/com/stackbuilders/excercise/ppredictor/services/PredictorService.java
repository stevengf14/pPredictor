/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackbuilders.excercise.ppredictor.services;

import com.stackbuilders.excercise.ppredictor.model.Time;
import java.time.LocalTime;
import java.util.Calendar;

/**
 *
 * @author Steven
 */
public class PredictorService {

    private final Time firstTimeInit = new Time(7, 0);
    private final Time firstTimeFinish = new Time(9, 30);
    private final Time secTimeInit = new Time(16, 0);
    private final Time secTimeFinish = new Time(19, 30);

    private Calendar calendarFirstInit;
    private Calendar calendarFirstFinish;
    private Calendar calendarSecInit;
    private Calendar calendarSecFinish;
    
    
    /**
      * This method predict if a plate at date and time about the rules
      * of "Pico y Placa" is legal to circulate or not.
      *
      * @param number
      * @param day
      * @param hour
      * @return true if the car can circulate or false if not.
      */
    public boolean predict(int number, int day, Time hour) {
        if ((number == 1 || number == 2) && day == 1) {
            return compareTime(hour);
        }
        if ((number == 3 || number == 4) && day == 2) {
            return compareTime(hour);
        }
        if ((number == 5 || number == 6) && day == 3) {
            return compareTime(hour);
        }
        if ((number == 7 || number == 8) && day == 4) {
            return compareTime(hour);
        }
        if ((number == 9 || number == 0) && day == 5) {
            return compareTime(hour);
        }
        return true;
    }

    /**
     * This method compare the time recived with the rules of the time about
     * "Pico y Placa"
     *
     * @param time
     * @return true if the car can circulate or false if not.
     */
    public boolean compareTime(Time time) {
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.set(Calendar.HOUR_OF_DAY, time.getHour());
        calendarTime.set(Calendar.MINUTE, time.getMinute());
        
        if ((calendarTime.after(calendarFirstInit) && calendarTime.before(calendarFirstFinish)) || 
                ((calendarTime.get(Calendar.HOUR)==(calendarFirstInit.get(Calendar.HOUR))) &&  (calendarTime.get(Calendar.MINUTE)==(calendarFirstInit.get(Calendar.MINUTE))))|| 
                ((calendarTime.get(Calendar.HOUR)==(calendarFirstFinish.get(Calendar.HOUR))) &&  (calendarTime.get(Calendar.MINUTE)==(calendarFirstFinish.get(Calendar.MINUTE))))) {
            return false;
        } else if ((calendarTime.after(calendarSecInit) && calendarTime.before(calendarSecFinish)) || 
                ((calendarTime.get(Calendar.HOUR)==(calendarSecInit.get(Calendar.HOUR))) &&  (calendarTime.get(Calendar.MINUTE)==(calendarSecInit.get(Calendar.MINUTE))))|| 
                ((calendarTime.get(Calendar.HOUR)==(calendarSecFinish.get(Calendar.HOUR))) &&  (calendarTime.get(Calendar.MINUTE)==(calendarSecFinish.get(Calendar.MINUTE))))) {
            return false;
        } else {
            return true;
        }
        
    }

    public void setCalendarRules() {
        calendarFirstInit = Calendar.getInstance();
        calendarFirstInit.set(Calendar.HOUR_OF_DAY, firstTimeInit.getHour());
        calendarFirstInit.set(Calendar.MINUTE, firstTimeInit.getMinute());

        calendarFirstFinish = Calendar.getInstance();
        calendarFirstFinish.set(Calendar.HOUR_OF_DAY, firstTimeFinish.getHour());
        calendarFirstFinish.set(Calendar.MINUTE, firstTimeFinish.getMinute());

        calendarSecInit = Calendar.getInstance();
        calendarSecInit.set(Calendar.HOUR_OF_DAY, secTimeInit.getHour());
        calendarSecInit.set(Calendar.MINUTE, secTimeInit.getMinute());

        calendarSecFinish = Calendar.getInstance();
        calendarSecFinish.set(Calendar.HOUR_OF_DAY, secTimeFinish.getHour());
        calendarSecFinish.set(Calendar.MINUTE, secTimeFinish.getMinute());
    }
}
