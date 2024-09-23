package org.example.services;

import java.time.LocalDate;

public interface CalculatorService {
    String calculatePayWithHolidays(Double averageSalary, Integer vacationDays, LocalDate startDate);
    String calculatePay(Double averageSalary, Integer vacationDays);
}