/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abraham Santana
 */
public class motherboardTeam extends Thread {

    company company;
    int salary = 20;
    private int dayCicle;

    public motherboardTeam(company company) {
        this.company = company;
    }

    public int getEmployeeCount() {
        return company.getMotherboardEmployeeCount();
    }

    public void setEmployeeCount(int employeeCount) {
        company.setMotherboardEmployeeCount(employeeCount);
    }

    public int getDayDuration() {
        return company.getDayDuration();
    }

    public int getDayCicle() {
        return dayCicle;
    }

    public void setDayCicle(int dayCicle) {
        this.dayCicle = dayCicle;
    }

    public int getSalaryAccount() {
        return company.getSalaryAccount();
    }

    public void setSalaryAccount(int salaryAccount) {
        company.setSalaryAccount(salaryAccount);
    }

    public void addDailySalary() {
        setSalaryAccount(
                getSalaryAccount() + salary * 24 * getEmployeeCount());
        
    }

    public Storage getMotherboardStorage() {
        return company.getMotherboardStorage();
    }

    public void operate() {
        setDayCicle(getDayCicle() + 1);
        if (getDayCicle() >= 2) {
            try {

                getMotherboardSemaphore().acquire(); // wait
                int addedAmount = getMotherboardStorage().add(getEmployeeCount()); // Adds 1 script for each employee in
                                                                                  // the team the function .add() in
                                                                                  // storage class returns the added
                                                                                  // amount to be reported later
                System.out.println("El drive de  Productores de placa base tiene " + getMotherboardStorage().getResourse() + " Placas Base");
                getMotherboardSemaphore().release(); // wait
                setDayCicle(0);

            } catch (InterruptedException ex) {
                Logger.getLogger(motherboardTeam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {

        while (true) {

            try {

                operate();
                sleep(company.getDayDuration());
                addDailySalary();
                company.setDaysGoneBy(company.getDaysGoneBy() + 1);

            } catch (InterruptedException ex) {
                Logger.getLogger(motherboardTeam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//
    }

    public Semaphore getMotherboardSemaphore() {
        return company.getMotherboardSemaphore();
    }
}
