package com.fletrax.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FullVehicleData {

    private long ident;
    private int device_id;
    private String device_name;
    private long server_timestamp;
    private long timestamp;
    private boolean position_valid;
    private double position_latitude;
    private double position_longitude;
    private double position_altitude;
    private double position_direction;
    private double position_speed;
    private int position_satellites;
    private int alarm_code;
    private boolean engine_ignition_status;
    private boolean movement_status;
    private int gsm_signal_level;
    private double position_hdop;
    private double external_battery_voltage;
    private long total_mileage;
    private String din;
    private String protocol;

    // Getters and Setters for all the fields
    public long getIdent() {
        return ident;
    }

    public void setIdent(long ident) {
        this.ident = ident;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public long getServer_timestamp() {
        return server_timestamp;
    }

    public void setServer_timestamp(long server_timestamp) {
        this.server_timestamp = server_timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isPosition_valid() {
        return position_valid;
    }

    public void setPosition_valid(boolean position_valid) {
        this.position_valid = position_valid;
    }

    public double getPosition_latitude() {
        return position_latitude;
    }

    public void setPosition_latitude(double position_latitude) {
        this.position_latitude = position_latitude;
    }

    public double getPosition_longitude() {
        return position_longitude;
    }

    public void setPosition_longitude(double position_longitude) {
        this.position_longitude = position_longitude;
    }

    public double getPosition_altitude() {
        return position_altitude;
    }

    public void setPosition_altitude(double position_altitude) {
        this.position_altitude = position_altitude;
    }

    public double getPosition_direction() {
        return position_direction;
    }

    public void setPosition_direction(double position_direction) {
        this.position_direction = position_direction;
    }

    public double getPosition_speed() {
        return position_speed;
    }

    public void setPosition_speed(double position_speed) {
        this.position_speed = position_speed;
    }

    public int getPosition_satellites() {
        return position_satellites;
    }

    public void setPosition_satellites(int position_satellites) {
        this.position_satellites = position_satellites;
    }

    public int getAlarm_code() {
        return alarm_code;
    }

    public void setAlarm_code(int alarm_code) {
        this.alarm_code = alarm_code;
    }

    public boolean isEngine_ignition_status() {
        return engine_ignition_status;
    }

    public void setEngine_ignition_status(boolean engine_ignition_status) {
        this.engine_ignition_status = engine_ignition_status;
    }

    public boolean isMovement_status() {
        return movement_status;
    }

    public void setMovement_status(boolean movement_status) {
        this.movement_status = movement_status;
    }

    public int getGsm_signal_level() {
        return gsm_signal_level;
    }

    public void setGsm_signal_level(int gsm_signal_level) {
        this.gsm_signal_level = gsm_signal_level;
    }

    public double getPosition_hdop() {
        return position_hdop;
    }

    public void setPosition_hdop(double position_hdop) {
        this.position_hdop = position_hdop;
    }

    public double getExternal_battery_voltage() {
        return external_battery_voltage;
    }

    public void setExternal_battery_voltage(double external_battery_voltage) {
        this.external_battery_voltage = external_battery_voltage;
    }

    public long getTotal_mileage() {
        return total_mileage;
    }

    public void setTotal_mileage(long total_mileage) {
        this.total_mileage = total_mileage;
    }

    public String getDin() {
        return din;
    }

    public void setDin(String din) {
        this.din = din;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return "FullVehicleData{" +
                "ident=" + ident +
                ", device_id=" + device_id +
                ", device_name='" + device_name + '\'' +
                ", server_timestamp=" + server_timestamp +
                ", timestamp=" + timestamp +
                ", position_valid=" + position_valid +
                ", position_latitude=" + position_latitude +
                ", position_longitude=" + position_longitude +
                ", position_altitude=" + position_altitude +
                ", position_direction=" + position_direction +
                ", position_speed=" + position_speed +
                ", position_satellites=" + position_satellites +
                ", alarm_code=" + alarm_code +
                ", engine_ignition_status=" + engine_ignition_status +
                ", movement_status=" + movement_status +
                ", gsm_signal_level=" + gsm_signal_level +
                ", position_hdop=" + position_hdop +
                ", external_battery_voltage=" + external_battery_voltage +
                ", total_mileage=" + total_mileage +
                ", din='" + din + '\'' +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}
