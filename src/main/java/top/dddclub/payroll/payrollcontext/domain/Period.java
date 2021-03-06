package top.dddclub.payroll.payrollcontext.domain;

import top.dddclub.payroll.payrollcontext.domain.exceptions.InvalidDateException;

import java.time.LocalDate;
import java.time.YearMonth;

public class Period {
    private LocalDate beginDate;
    private LocalDate endDate;

    public Period(LocalDate beginDate, LocalDate endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Period(YearMonth yearMonth) {
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();
        int firstDay = 1;
        int lastDay = yearMonth.lengthOfMonth();

        this.beginDate = LocalDate.of(year, month, firstDay);
        this.endDate = LocalDate.of(year, month, lastDay);
    }

    public Period(int year, int month) {
        if (month < 1 || month > 12) {
            throw new InvalidDateException("Invalid month value.");
        }

        int firstDay = 1;
        int lastDay = YearMonth.of(year, month).lengthOfMonth();

        this.beginDate = LocalDate.of(year, month, firstDay);
        this.endDate = LocalDate.of(year, month, lastDay);
    }

    public LocalDate beginDate() {
        return beginDate;
    }

    public LocalDate endDate() {
        return endDate;
    }

    public boolean contains(LocalDate date) {
        if (date.isEqual(beginDate) || date.isEqual(endDate)) {
            return true;
        }
        return date.isAfter(beginDate) && date.isBefore(endDate);
    }
}
