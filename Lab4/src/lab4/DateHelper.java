/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateHelper {

    private GregorianCalendar timeStart; //время запуска
    private GregorianCalendar timeReal; //нынешнее время
    private int durationOneRound;//длительность одного раунда

    public DateHelper(int durationOneRound) {
        this.durationOneRound = durationOneRound;
        timeStart = new GregorianCalendar();
        timeStart.add(Calendar.YEAR, -1500);
        timeReal = (GregorianCalendar) timeStart.clone();
    }

    public String getFormattedStartDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyy  HH:mm");
        return format.format(timeStart.getTime());
    }

    public void skipTime() {
        timeReal.add(Calendar.MINUTE, durationOneRound);
    }

    public String getFormattedDiff() {
        int countYEAR = timeReal.get(Calendar.YEAR) - timeStart.get(Calendar.YEAR);
        int countMONTH = timeReal.get(Calendar.MONTH) - timeStart.get(Calendar.MONTH);
        int countDAY_OF_MONTH = timeReal.get(Calendar.DAY_OF_MONTH) - timeStart.get(Calendar.DAY_OF_MONTH);
        int countHOUR_OF_DAY = timeReal.get(Calendar.HOUR_OF_DAY) - timeStart.get(Calendar.HOUR_OF_DAY);
        int countMINUTE = timeReal.get(Calendar.MINUTE) - timeStart.get(Calendar.MINUTE);

        if (countMINUTE < 0) {
            countMINUTE += 60;
            --countHOUR_OF_DAY;
        }
        if (countHOUR_OF_DAY < 0) {
            countHOUR_OF_DAY += 24;
            --countDAY_OF_MONTH;
        }
        if (countDAY_OF_MONTH < 0) {
            countDAY_OF_MONTH += 30;//не 30, а по-разному
            --countMONTH;
        }
        if (countMONTH < 0) {
            countMONTH += 12;
            --countYEAR;
        }
        return (countYEAR > 0 ? "Лет:" + countYEAR : "")
                + ((countMONTH > 0 ? " Месяцев:" + countMONTH : ""))
                + ((countDAY_OF_MONTH > 0 ? " Дней:" + countDAY_OF_MONTH : ""))
                + ((countHOUR_OF_DAY > 0 ? " Часов:" + countHOUR_OF_DAY : ""))
                + " Минут:" + countMINUTE;
    }
}
