package model;

public class Date {

    public boolean isLeapYear;
    private int day, month, year;
    public static final String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int daysInMonth(int month){
        if(month > 13 || month < 1){
            return -1;
        }

        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if(isLeapYear(this.year)){
                    return 29;
                }
                return 28;
            default:
                return 30;
        }

    }

    public boolean isLeapYear(int year){
        if(year % 4 == 0){
            if(year % 100 == 0){
                if(year % 400 == 0){
                    return true;
                }

                return false;
            }

            return true;
        }

        return false;
    }

    public void incDay(){
        if(day == daysInMonth(month)){
            day = 1;
            if(month == 12){
                month = 1;
                year++;
            } else {
                month++;
            }
        } else {
            day++;
        }
    }

    public void incDays(int days){
        for(int i = 0; i < days; i++){
            incDay();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Date)){
            return false;
        }
        Date other = (Date) obj;
        return day == other.getDay() &&
                month == other.getMonth() &&
                year == other.getYear();
    }

    public String toString(){
        return day + "/" + month + "/" + year;

    }
}
