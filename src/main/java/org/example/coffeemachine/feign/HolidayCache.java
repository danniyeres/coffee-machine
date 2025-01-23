package org.example.coffeemachine.feign;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayCache {
    private final HolidayApiClient holidayApiClient;

    @Cacheable("holidays")
    public List<Holiday> getHolidays(int year, String countryCode) {
        return holidayApiClient.getHolidays(year, countryCode);
    }
}
