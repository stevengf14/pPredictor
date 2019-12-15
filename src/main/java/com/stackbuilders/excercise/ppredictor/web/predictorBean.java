/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackbuilders.excercise.ppredictor.web;

import com.stackbuilders.excercise.ppredictor.model.Time;
import com.stackbuilders.excercise.ppredictor.services.PredictorService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Steven
 */
@ManagedBean(name = "predictorBean")
@SessionScoped
public class predictorBean {

    private final PredictorService predictorService = new PredictorService();

    private String date;
    private String plate;
    private String time;
    private String result;
    private boolean click;

    @PostConstruct
    public void init() {
        predictorService.setCalendarRules();
        click = false;
    }

    //Methods
    public void predict() {
        boolean circulates = predictorService.predict(getNumber(getPlate()), getDay(getDate()), getTime(getTime()));
        click = true;
        if (circulates == true) {
            result = "You can drive.";
        } else {
            result = "You can not drive.";
        }
    }

    public int getDay(String dateString) {
        int dayOfWeek = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        } catch (ParseException ex) {
            Logger.getLogger(predictorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dayOfWeek-1;
    }

    public int getNumber(String plate) {
        return Integer.valueOf(String.valueOf(plate.charAt(plate.length() - 1)));
    }

    public Time getTime(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        Time objTime = new Time();
        objTime.setHour(Integer.valueOf(st.nextToken()));
        objTime.setMinute(Integer.valueOf(st.nextToken()));
        return objTime;
    }

    //Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
