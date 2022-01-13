package com.app.pnrcheck;

import com.google.gson.annotations.SerializedName;

import java.util.List;
class Passenger{
    public String name;
    public String booking_status;
    public String current_status;

    public Passenger() {
    }

    public String getName() {
        return name;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public String getCurrent_status() {
        return current_status;
    }
}

class DepartureData{
    public String departure_date;
    public String departure_time;

    public DepartureData() {
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }
}

class ArrivalData{
    public String arrival_date;
    public String arrival_time;

    public ArrivalData() {
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }
}
public class PnrModel {
    public List<Passenger> passenger;
    public String boarding_station;
    public String reservation_upto;
    public DepartureData departure_data;
    public ArrivalData arrival_data;
    public String quota;
    @SerializedName("class")
    public String clas;
    public String chart_status;
    public String train_name;
    public String train_number;

    public PnrModel() {
    }

    public List<Passenger> getPassenger() {
        return passenger;
    }

    public String getBoarding_station() {
        return boarding_station;
    }

    public String getReservation_upto() {
        return reservation_upto;
    }

    public DepartureData getDeparture_data() {
        return departure_data;
    }

    public ArrivalData getArrival_data() {
        return arrival_data;
    }

    public String getQuota() {
        return quota;
    }

    public String getClas() {
        return clas;
    }

    public String getChart_status() {
        return chart_status;
    }

    public String getTrain_name() {
        return train_name;
    }

    public String getTrain_number() {
        return train_number;
    }
}
