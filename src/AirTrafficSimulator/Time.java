package AirTrafficSimulator;

/**
 * Represents a time of day, storing hours, minutes, and seconds,
 * and providing basic operations for manipulating time values.
 */

public class Time {
    private int hour, minute, second;

    public Time(int hour, int minute, int second) {
        setSecond(second);
        setMinute(minute);
        setHour(hour);
    }

    public Time(Time other) {
        this.hour = other.hour;
        this.minute = other.minute;
        this.second = other.second;
    }

    public void setSecond(int second) {
        if (second < 0 || second > 59)
            throw new IllegalArgumentException();
        this.second = second;
    }

    public void setMinute(int minute) {
        if (minute < 0 || minute > 59)
            throw new IllegalArgumentException();
        this.minute = minute;
    }

    public void setHour(int hour) {
        if (hour < 0 || hour > 23)
            throw new IllegalArgumentException();
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minute;
    }

    public int getSeconds() {
        return second;
    }

    void addSecond() {
        second++;
        if  (second > 59) {
            second = 0;
            minute++;
        }
        if (minute > 59) {
            minute = 0;
            hour++;
        }
    }

    void addMinutes(int minutes) {
        this.minute += minutes;
        if (minute > 59) {
            hour += minute / 60;
            minute %= 60;
        }
    }

    public static Time addMinutes(Time t1, int minutes) {
        Time res = new Time(t1);
        res.addMinutes(minutes);
        return res;
    }

    void reset() {
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public int minutesDifference(Time other) {
        return (this.hour - other.hour) * 60 + this.minute - other.minute;
    }

    public String toString() {
        return String.format("%02d:%02d", hour % 24, minute);
    }
}
