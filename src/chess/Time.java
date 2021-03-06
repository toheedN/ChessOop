package chess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This is the Time Class.
 * It contains all the required variables and functions related to the timer on the Main GUI
 * It uses a Timer Class
 */

public class Time {
    private final JLabel label;
    private final Timer countdownTimer;
    private int Timerem;

    public Time(JLabel passedLabel) {
        countdownTimer = new Timer(1000, new CountdownTimerListener());
        this.label = passedLabel;
        setTimerem(Main.getTimeRemaining());
    }

    //A function that starts the timer
    public void stop() {
        getCountdownTimer().stop();
    }

    //A function that starts the timer
    public void start() {
        getCountdownTimer().start();
    }

    //A function that resets the timer
    public void reset() {
        setTimerem(Main.getTimeRemaining());
    }

    public Timer getCountdownTimer() {
        return countdownTimer;
    }

    public int getTimerem() {
        return Timerem;
    }

    public void setTimerem(int timerem) {
        Timerem = timerem;
    }

    public JLabel getLabel() {
        return label;
    }

    //A function that is called after every second. It updates the timer and takes other necessary actions
    private class CountdownTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int min, sec;
            if (getTimerem() > 0) {
                min = getTimerem() / 60;
                sec = getTimerem() % 60;
                getLabel().setText(String.valueOf(min) + ":" + (sec >= 10 ? String.valueOf(sec) : "0" + String.valueOf(sec)));
                setTimerem(getTimerem() - 1);
            } else {
                getLabel().setText("Time's up!");
                reset();
                start();
                Main.getMainboard().changechance();
            }
        }
    }
}