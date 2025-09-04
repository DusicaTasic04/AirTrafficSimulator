package AirTrafficSimulator;

/**
 * Represents a time of day, storing hours, minutes, and seconds,
 * and providing basic operations for manipulating time values.
 */
public class Time {
    int hour, minute, second;

    public Time(int hour, int minute, int second) {
        setSecond(second);
        setMinute(minute);
        setHour(hour);
    }

    private void setSecond(int second) {
        if (second < 0 || second > 59)
            throw new IllegalArgumentException();
        this.second = second;
    }

    private void setMinute(int minute) {
        if (minute < 0 || minute > 59)
            throw new IllegalArgumentException();
        this.minute = minute;
    }

    private void setHour(int hour) {
        if (hour < 0 || hour > 23)
            throw new IllegalArgumentException();
        this.hour = hour;
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
        if (hour > 23) {
            hour = 0;
        }
    }

    void addMinutes(int minutes) {
        this.minute += minutes;
        if (minute > 59) {
            hour += minute / 60;
            minute %= 60;
        }
    }

    void reset() {
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}
