package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.services.CalculatorServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping
@AllArgsConstructor
public class CalculatorController {

    private CalculatorServiceImpl calculatorService;

    @GetMapping("/calculate")
    public String calculate(@RequestParam(value = "averageSalary", required = true) Double averageSalary,
                            @RequestParam(value="vacationDays", required = true) Integer vacationDays,
                            @RequestParam(value = "startDate", required = false)
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate){
        String vacationPayments;

        if (startDate != null) {
            vacationPayments = calculatorService.calculatePayWithHolidays(averageSalary, vacationDays, startDate);
        } else {
            vacationPayments = calculatorService.calculatePay(averageSalary, vacationDays);
        }
        return vacationPayments;
    }

}