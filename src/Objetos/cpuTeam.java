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
public class cpuTeam extends Thread {
    
    private company company;
    private int dayCicle;
    int salary = 26;

    public cpuTeam(company company) {
        this.company = company;
        this.dayCicle = 0;

    }

    public int getEmployeeCount() {
        return company.getCpuEmployeeCount();
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

    public Storage getCpuStorage() {
        return company.getCpuStorage();
    }

    public void operate() {
        setDayCicle(getDayCicle() + 1);
        if (getDayCicle() >= 2) {
            try {
                getCpuSemaphore().acquire(); // wait
                int addedAmount = getCpuStorage().add(getEmployeeCount()); // Adds 1 script for each employee in
                                                                                 // the team the function .add() in
                                                                                 // Storage class returns the added amount
                                                                                 // to be reported later
                System.out
                        .println("El Almacen de productores de cpu tiene " + getCpuStorage().getResourse() + " cpu's");
                getCpuSemaphore().release(); // wait
                setDayCicle(0);

            } catch (InterruptedException ex) {
                Logger.getLogger(cpuTeam.class.getName()).log(Level.SEVERE, null, ex);
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
               

            } catch (InterruptedException ex) {
                Logger.getLogger(cpuTeam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Semaphore getCpuSemaphore() {
        return company.getCpuSemaphore();
    }
}
