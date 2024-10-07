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
public class MotherboardProducersTeam extends Thread {

    Company company;
    int salary = 20;
    private int dayCicle;

    public MotherboardProducersTeam(Company company) {
        this.company = company;
    }

    public int getEmployeeCount() {
        return company.getMotherboardProducersEmployeeCount();
    }

    public void setEmployeeCount(int employeeCount) {
        company.setMotherboardProducersEmployeeCount(employeeCount);
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

    public Drive getMotherboardProducersDrive() {
        return company.getMotherboardProducersDrive();
    }

    public void operate() {
        setDayCicle(getDayCicle() + 1);
        if (getDayCicle() >= 4) {
            try {

                getMotherboardProducersSemaphore().acquire(); // wait
                int addedAmount = getMotherboardProducersDrive().add(getEmployeeCount()); // Adds 1 script for each employee in
                                                                                  // the team the function .add() in
                                                                                  // drive class returns the added
                                                                                  // amount to be reported later
                System.out.println("El drive de  Productores de placa base tiene " + getMotherboardProducersDrive().getResourse() + " Placas Base");
                getMotherboardProducersSemaphore().release(); // wait
                setDayCicle(0);

            } catch (InterruptedException ex) {
                Logger.getLogger(MotherboardProducersTeam.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(MotherboardProducersTeam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Semaphore getMotherboardProducersSemaphore() {
        return company.getMotherboardProducersSemaphore();
    }
}
