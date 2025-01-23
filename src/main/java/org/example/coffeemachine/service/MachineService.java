package org.example.coffeemachine.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeemachine.feign.Holiday;
import org.example.coffeemachine.feign.HolidayCache;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class MachineService {

    private final HolidayCache holidayCache;


    /**
     * @param date       - date to check
     * @param countryCode - country code
     * @return true if the date is a holiday, false otherwise
     */
    public boolean isHoliday(LocalDate date, String countryCode) {
        var holidays = holidayCache.getHolidays(date.getYear(), countryCode);
        for (Holiday holiday : holidays) {
            if (holiday.date().equals(date.toString())) {
                return true;
            }
        }
        return false;
    }


    /**
     * @return true if the machine is active, false otherwise
     * The machine is active from 8 to 18 on weekdays
     * and is not active on weekends and holidays
     */
    public boolean isMachineActive() {
//        var now = LocalDateTime.now();
//        var today = now.toLocalDate();
//
//        boolean weekend = today.getDayOfWeek() == DayOfWeek.SATURDAY || today.getDayOfWeek() == DayOfWeek.SUNDAY;
//        boolean isHoliday = isHoliday(today, "KZ");
//
//        if (weekend || isHoliday) {
//            log.info("Machine is not active on weekends or holidays");
//            return false;
//        }
//
//        int hour = now.getHour();
//        log.info("Current hour: {}", hour);
//        return hour >= 8 && hour <= 18;
        return true;
    }
}
