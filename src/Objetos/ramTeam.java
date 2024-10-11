/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import lanzsantanaso.main;

/**
 *
 * @author santi
 */
public class ramTeam extends Thread {

    private company company;
    private int dayCicle;
    int salary = 40;

    public ramTeam(company company) {
        this.company = company;
        this.dayCicle = 0;

    }

    public int getEmployeeCount() {
        return company.getRamEmployeeCount();
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
        // System.out.println("El equipo de " + getEmployeeCount() + " animadores" + "
        // gana: " + salary*24*getEmployeeCount()+"$");
    }

    public Storage getRamStorage() {
        return company.getRamStorage();
    }

    public void operate() {
        setDayCicle(getDayCicle() + 1);
        if (getDayCicle() >= 1) {
            try {
                getRamSemaphore().acquire(); // wait
                int addedAmount = getRamStorage().add(getEmployeeCount() * 3); // Adds 3 for each employee in the
                                                                              // team the function .add() in drive class
                                                                              // returns the added amount to be reported
                                                                              // later
                System.out
                        .println("El Almacen de tarjetas RAM tiene " + getRamStorage().getResourse() + " RAM");
                getRamSemaphore().release(); // wait
                setDayCicle(0);

            } catch (InterruptedException ex) {
                Logger.getLogger(ramTeam.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ramTeam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Semaphore getRamSemaphore() {
        return company.getRamSemaphore();
    }
}
