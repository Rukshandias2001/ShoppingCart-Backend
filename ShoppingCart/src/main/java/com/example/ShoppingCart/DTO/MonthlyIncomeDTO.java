package com.example.ShoppingCart.DTO;

public class MonthlyIncomeDTO {
    private Integer year;
    private Integer month;
    private Double income;


    public MonthlyIncomeDTO(Integer year, Integer month, Double income) {
        this.year = year;
        this.month = month;
        this.income = income;
    }

    public MonthlyIncomeDTO() {

    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }
}
