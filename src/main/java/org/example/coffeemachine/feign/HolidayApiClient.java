package org.example.coffeemachine.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "holidayApi", url = "https://date.nager.at/api/v3/publicholidays")
public interface HolidayApiClient {
    @GetMapping("/{year}/{countryCode}")
    List<Holiday> getHolidays(@PathVariable int year, @PathVariable String countryCode);
}

