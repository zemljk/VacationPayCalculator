package org.example.services;

import lombok.var;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static org.example.data.Holidays.getHolidaysList;

@Service
public class CalculatorServiceImpl implements CalculatorService{

    @Override
    public String calculatePayWithHolidays(Double averageSalary, Integer vacationDays, LocalDate startDate) {
        double vacationPayments = 0;
        if(averageSalaryIsValid(averageSalary) && vacationDaysIsValid(vacationDays) &&
                startDateIsValid(startDate)){
            int vacationDaysWithHolidays = getVacationDaysWithHolidays(startDate, vacationDays);
            vacationPayments = (averageSalary/12) / 29.3 * vacationDaysWithHolidays;
        }
        return String.valueOf(vacationPayments);
    }

    @Override
    public String calculatePay(Double averageSalary, Integer vacationDays) {
        double vacationPayments = 0;
        if(averageSalaryIsValid(averageSalary) && vacationDaysIsValid(vacationDays)){
            vacationPayments =(averageSalary/12) / 29.3 * vacationDays;
        }
        return String.valueOf(vacationPayments);
    }

    static Integer getVacationDaysWithHolidays(LocalDate startDate, Integer vacationDays) {
        int vacationDaysWithHolidays = 0;
        var holidayDays = getHolidaysList();

        while (vacationDays != 0) {
            int dayOfWeek = startDate.getDayOfWeek().getValue();

            if (dayOfWeek == 6 || dayOfWeek == 7) {
                vacationDays--;
                startDate = startDate.plusDays(1);
                continue;
            }
            if (holidayDays.get(startDate.getMonth())
                    .contains(startDate.getDayOfMonth())) {
                vacationDays--;
                startDate = startDate.plusDays(1);
                continue;
            }
            vacationDaysWithHolidays++;
            vacationDays--;
            startDate = startDate.plusDays(1);
        }
        return vacationDaysWithHolidays;
    }
    private boolean averageSalaryIsValid(Double averageSalary) {
        return averageSalary != null && averageSalary > 0;
    }

    private boolean vacationDaysIsValid(Integer vacationDays){
        return vacationDays != null && vacationDays > 0;
    }
    private boolean startDateIsValid(LocalDate startDate) {
        return startDate != null;
    }
}