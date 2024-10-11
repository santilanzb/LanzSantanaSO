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
public class graphicscardTeam extends Thread {

    private company company;
    private int dayCicle;
    int salary = 34;

    public graphicscardTeam(company company) {
        this.company = company;
        this.dayCicle = 0;

    }

    public int getEmployeeCount() {
        return company.getGraphicscardEmployeeCount();
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
        // System.out.println("El equipo de " + getEmployeeCount() + " guionistas de
        // plotTwist" + " gana: " + salary*24*getEmployeeCount()+"$");
    }

    public Storage getGraphicscardStorage() {
        return company.getGraphicscardStorage();
    }

    public void operate() {
        setDayCicle(getDayCicle() + 1);
        if (getDayCicle() >= 3) {
            try {
                getGraphicscardSemaphore().acquire(); // wait
                int addedAmount = getGraphicscardStorage().add(getEmployeeCount()); // Adds 1 for each employee in
                                                                                 // the team the function .add() in
                                                                                 // storage class returns the added amount
                                                                                 // to be reported later
                System.out
                        .println("El almacen de tarjetas graficas tiene " + getGraphicscardStorage().getResourse() + " tarjetas graficas");
                getGraphicscardSemaphore().release(); // wait
                setDayCicle(0);

            } catch (InterruptedException ex) {
                Logger.getLogger(graphicscardTeam.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(graphicscardTeam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Semaphore getGraphicscardSemaphore() {
        return company.getGraphicscardSemaphore();
    }
}
