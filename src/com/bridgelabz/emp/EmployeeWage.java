package com.bridgelabz.emp;

import java.util.Scanner;

public class EmployeeWage {

    private static final int FULL_DAY_HOUR = 8;
    private static final int PART_DAY_HOUR = 4;

    private static CompanyEmpWage companyEmpWage = new CompanyEmpWage();

    public static EmpWageBuilder computeEmployeeWage(Company company) {
        int days = 0;
        int empHours = 0;
        EmpWageBuilder empWageBuilder = new EmpWageBuilder();
        while (days < company.getWorkingDaysPerMonth() && empHours <= company.getMaxWorkingHours()) {
            int todayEmpWage = 0;
            days++;
            int empCheck = (int) Math.floor(Math.random() * 10) % 3;
            switch (empCheck) {
                case 0:
                    todayEmpWage = FULL_DAY_HOUR;
                    break;
                case 1:
                    todayEmpWage = PART_DAY_HOUR;
                    break;
                case 2:
                    todayEmpWage = 0;
                    break;
            }
            empHours = empHours + todayEmpWage;
            empWageBuilder.getDailyWage().add(todayEmpWage * company.getEmpWagePerHour());
        }
        int totalEmpWage = empHours * company.getEmpWagePerHour();

        empWageBuilder.setCompany(company);
        empWageBuilder.setTotalHours(empHours);
        empWageBuilder.setTotalDays(days);
        empWageBuilder.setTotalEmpWage(totalEmpWage);
        companyEmpWage.getEmpWageBuilderList().add(empWageBuilder);
        return empWageBuilder;
    }

    public static void main(String[] args) {
        Company jio = new Company("Jio", 20, 60, 20);
        Company airtel = new Company("Airtel", 15, 80, 25);
        Company suzuki = new Company("Suzuki", 26, 180, 30);
        computeEmployeeWage(jio);
        computeEmployeeWage(airtel);
        computeEmployeeWage(suzuki);
        for (int i = 0; i < companyEmpWage.getEmpWageBuilderList().size(); i++) {
            System.out.println(companyEmpWage.getEmpWageBuilderList().get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the company name to get details of wage: ");
        String companyName = scanner.nextLine();
        boolean isCompanyFound = false;
        for (int i = 0; i < companyEmpWage.getEmpWageBuilderList().size(); i++) {
            if (companyEmpWage.getEmpWageBuilderList().get(i).getCompany().getCompanyName().equals(companyName)) {
                System.out.println("Found company and here are the details");
                System.out.println(companyEmpWage.getEmpWageBuilderList().get(i));
                isCompanyFound = true;
                break;
            }
        }
        if (!isCompanyFound) {
            System.out.println("Not found any record with : " + companyName);
        }
    }
}
