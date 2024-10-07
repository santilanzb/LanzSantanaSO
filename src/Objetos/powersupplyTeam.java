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
 * @author cdmar
 */
public class powersupplyTeam extends Thread {

    private company company;
    private int dayCicle;

    int salary = 16;

    public powersupplyTeam(company company) {
        this.company = company;
        this.dayCicle = 0;

    }

    public int getEmployeeCount() {
        return company.getPowersupplyEmployeeCount();
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
        // System.out.println("El equipo de " + getEmployeeCount() + " actore de
        // doblaje" + " gana: " + salary*24*getEmployeeCount()+"$");
    }

    public Storage getPowersupplyStorage() {
        return company.getPowersupplyStorage();
    }

    public void operate() {
        setDayCicle(getDayCicle() + 1);
        if (getDayCicle() >= 1) {
            try {
                getPowersupplySemaphore().acquire(); // wait
                int addedAmount = getPowersupplyStorage().add(getEmployeeCount() * 5); // Adds 5 voice translations for
                                                                                    // each employee in the team the
                                                                                    // function .add() in drive class
                                                                                    // returns the added amount to be
                                                                                    // reported later
                System.out.println("El almacen de fuente de alimentacion tiene " + getPowersupplyStorage().getResourse() + " fuentes de alimentacion");
                getPowersupplySemaphore().release(); // wait
                setDayCicle(0);

            } catch (InterruptedException ex) {
                Logger.getLogger(powersupplyTeam.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(powersupplyTeam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Semaphore getPowersupplySemaphore() {
        return company.getPowersupplySemaphore();
    }
}
