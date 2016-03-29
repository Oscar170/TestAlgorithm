/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author oscar
 */
public class TimeInfo {

    private int quantity;
    private long time;
    private String tipe;

    public TimeInfo(int quantity, long time, String tipe) {
        this.quantity = quantity;
        this.time = time;
        this.tipe = tipe;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getTime() {
        return time;
    }

    public String getTipe() {
        return tipe;
    }

    @Override
    public String toString() {
        return "TimeInfo{" + "quantity=" + quantity + ", time={" + time + " ns, " + time/1000000 +" ms, " + time/1000000000 +" s} , tipe=" + tipe + '}';
    }
}
