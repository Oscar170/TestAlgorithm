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
public class Timer {

    private long timeInit;

    public Timer(boolean start) {
        if (start) {
            timeInit = System.nanoTime();
        }
    }

    public void start() {
        timeInit = System.nanoTime();
    }

    public long stop() {
        return (System.nanoTime() - timeInit);
    }
}
