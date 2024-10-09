/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Interfaz.Vista;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import lanzsantanaso.main;

/**
 *
 * @author cdmar
 */
public class assemblerTeam extends Thread {

    private company company;
    private Semaphore assemblerSemaphore;
    private Semaphore ramSemaphore;
    private Semaphore graphicscardSemaphore;
    private Semaphore motherboardSemaphore;
    private Semaphore cpuSemaphore;
    private Semaphore powersupplySemaphore;
    private Storage ramStorage;
    private Storage graphicscardStorage;
    private Storage motherboardStorage;
    private Storage cpuStorage;
    private Storage powersupplyStorage;
    private int employeeCount;
    private int dayDuration;
    private int dayCicle;
    private int computerCicle;
    private int salaryAccount;
    private storageAssembler assemblerStorage;
    private storageAssembler gcAssemblerStorage;
    int salary = 50;
    private int motherboardReq; // This int represents the amount of motherboards required to assemble a computer
    private int cpuReq; // This int represents the amount of cpu required to assemble a computer
    private int ramReq; // This int represents the amount of ram required to assemble a computer
    private int powersupplyReq; // This int represents the amount of powersupply required to assemble a computer
    private int gcComputersRatio; // This int represents the ratio of common computers per graphics card computer
    private int graphicscardAmount; // This int represent the amount of graphics card per graphics card computer

    public assemblerTeam(company company) {
        this.company = company;
        this.assemblerSemaphore = company.getAssemblerSemaphore();
        this.employeeCount = company.getAssemblerEmployeeCount();
        this.dayDuration = company.getDayDuration();
        this.dayCicle = 0;
        this.computerCicle = 0;
        this.salaryAccount = company.getSalaryAccount();
        this.assemblerStorage = company.getAssemblerStorage();
        this.ramSemaphore = company.getRamSemaphore();
        this.ramStorage = company.getRamStorage();
        this.graphicscardSemaphore = company.getGraphicscardSemaphore();
        this.graphicscardStorage = company.getGraphicscardStorage();
        this.motherboardSemaphore = company.getMotherboardSemaphore();
        this.motherboardStorage = company.getMotherboardStorage();
        this.cpuSemaphore = company.getCpuSemaphore();
        this.cpuStorage = company.getCpuStorage();
        this.powersupplySemaphore = company.getPowersupplySemaphore();
        this.powersupplyStorage = company.getPowersupplyStorage();
        this.graphicscardAmount = company.getGraphicscardAmount();
        this.gcAssemblerStorage = company.getGcAssemblerStorage();
        this.gcComputersRatio = company.getGcComputersRatio();
        this.motherboardReq = company.getMotherboardReq();
        this.cpuReq = company.getCpuReq();
        this.ramReq = company.getRamReq();
        this.powersupplyReq = company.getPowersupplyReq();

    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    public int getDayCicle() {
        return dayCicle;
    }

    public void setDayCicle(int dayCicle) {
        this.dayCicle = dayCicle;
    }

    public int getSalaryAccount() {
        return salaryAccount;
    }

    public void setSalaryAccount(int salaryAccount) {
        this.salaryAccount = salaryAccount;
    }

    public void addDailySalary() {
        System.out.println("La cuenta antes de pagarle al assembler: " + company.getSalaryAccount() );
        company.setSalaryAccount(company.getSalaryAccount() + salary * 24 * getEmployeeCount());
        System.out.println("El equipo de " + getEmployeeCount() + " assembler" + " gana: " + salary*24*getEmployeeCount()+"$");
        System.out.println("La cuenta luego de pagarle al assembler: " + company.getSalaryAccount() );
    }

    public storageAssembler getAssemblerStorage() {
        return assemblerStorage;
    }

    public void operate() {
        setDayCicle(getDayCicle() + 1);
        if (getDayCicle() >= 2) {
            try {
                for (int i = 0; i < getEmployeeCount(); i++) { // Repeats the process of assembling a computer for each
                                                               // employee in the team

                    if (getComputerCicle() == getGcComputersRatio()) { // Graphics card computer assembly escenario
                        getMotherboardSemaphore().acquire();
                        getRamSemaphore().acquire();
                        getGraphicscardSemaphore().acquire();
                        getCpuSemaphore().acquire();
                        getPowersupplySemaphore().acquire();
                        if (getMotherboardStorage().getResourse() >= company.getMotherboardReq()
                                && // Verifies if all storage requirements are met for assembling the computer
                                getRamStorage().getResourse() >= company.getRamReq()
                                && getCpuStorage().getResourse() >= company.getCpuReq()
                                && getPowersupplyStorage().getResourse() >= company.getPowersupplyReq()
                                && getGraphicscardStorage().getResourse() >= company.getGraphicscardAmount()) {
                            getMotherboardStorage().substract(getMotherboardReq()); // If all requirements are met, substracts
                            getRamStorage().substract(getRamReq());
                            getCpuStorage().substract(getCpuReq());
                            getPowersupplyStorage().substract(getPowersupplyReq());
                            getGraphicscardStorage().substract(getGraphicscardAmount());

                            // This only happens if all requirements were met
                            getAssemblerSemaphore().acquire();
                            int addedAmount = getGcAssemblerStorage().add(1);
                            System.out.println("Un ensamblador agrego " + addedAmount
                                    + " computadora con tarjeta grafica al almacen de tarjetas graficas! ");
                            System.out.println(" /---Hay " + getGcAssemblerStorage().getResourse()
                                    + " computadoras con tarjetas graficas ensamblados---/");
                            getAssemblerSemaphore().release();
                            setComputerCicle(0); // Resets graphics card cicle

                        } else { // This happens if all requirements weren't met
                            System.out.println(
                                    "Un ensamblador no pudo ensamblar una computadora con tarjeta grafica por falta de requerimientos");

                        }
                        getMotherboardSemaphore().release(); // Releases every semaphore
                        getRamSemaphore().release();
                        getCpuSemaphore().release();
                        getPowersupplySemaphore().release();
                        getGraphicscardSemaphore().release();

                    } else { // Common computer assembly escenario
                        getMotherboardSemaphore().acquire();
                        getRamSemaphore().acquire();
                        getCpuSemaphore().acquire();
                        getPowersupplySemaphore().acquire();

                        if (getMotherboardStorage().getResourse() >= company.getMotherboardReq()
                                && // Verifies if all storage requirements are met for assembling the computer
                                getRamStorage().getResourse() >= company.getRamReq()
                                && getCpuStorage().getResourse() >= company.getCpuReq()
                                && getPowersupplyStorage().getResourse() >= company.getPowersupplyReq()) {
                            getMotherboardStorage().substract(getMotherboardReq()); // If all requirements are met, substracts
                            getRamStorage().substract(getRamReq());
                            getCpuStorage().substract(getCpuReq());
                            getPowersupplyStorage().substract(getPowersupplyReq());

                            // This only happens if all requirements were met
                            getAssemblerSemaphore().acquire();
                            int addedAmount = getAssemblerStorage().add(1); // Adds 1 computer to the storage

                            System.out.println("Un ensamblador agrego " + addedAmount + " computadoras a su almacen! ");
                            System.out.println(" /---Hay " + getAssemblerStorage().getResourse()
                                    + " computadoras comunes ensambladas---/");
                            getAssemblerSemaphore().release(); // Releases every semaphore
                            setComputerCicle(getComputerCicle() + 1); // Adds 1 count to the cicle for a graphics card computer

                        } else { // This happens if all requirements weren't met
                            System.out.println(
                                    "Un ensamblador no pudo ensamblar una computadora por falta de requerimientos");
                        }
                        getMotherboardSemaphore().release(); // Releases every semaphore
                        getRamSemaphore().release();
                        getCpuSemaphore().release();
                        getPowersupplySemaphore().release();
                    }
                }
                setDayCicle(0); // Resets the day cicle
            } catch (InterruptedException ex) {
                Logger.getLogger(assemblerTeam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {

        while (true) {

            try {
                operate();
                sleep(this.dayDuration);
                addDailySalary();

                

            } catch (InterruptedException ex) {
                Logger.getLogger(assemblerTeam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Semaphore getAssemblerSemaphore() {
        return assemblerSemaphore;
    }

    public int getComputerCicle() {
        return computerCicle;
    }

    public void setComputerCicle(int computerCicle) {
        this.computerCicle = computerCicle;
    }

    public Semaphore getRamSemaphore() {
        return ramSemaphore;
    }

    public Semaphore getGraphicscardSemaphore() {
        return graphicscardSemaphore;
    }

    public Semaphore getMotherboardSemaphore() {
        return motherboardSemaphore;
    }

    public Semaphore getCpuSemaphore() {
        return cpuSemaphore;
    }

    public Semaphore getPowersupplySemaphore() {
        return powersupplySemaphore;
    }

    public Storage getRamStorage() {
        return ramStorage;
    }

    public Storage getGraphicscardStorage() {
        return graphicscardStorage;
    }

    public Storage getMotherboardStorage() {
        return motherboardStorage;
    }

    public Storage getCpuStorage() {
        return cpuStorage;
    }

    public Storage getPowersupplyStorage() {
        return powersupplyStorage;
    }

    public int getMotherboardReq() {
        return motherboardReq;
    }

    public int getCpuReq() {
        return cpuReq;
    }

    public int getRamReq() {
        return ramReq;
    }

    public int getPowersupplyReq() {
        return powersupplyReq;
    }

    public int getGcComputersRatio() {
        return gcComputersRatio;
    }

    public int getGraphicscardAmount() {
        return graphicscardAmount;
    }

    public storageAssembler getGcAssemblerStorage() {
        return gcAssemblerStorage;
    }
}
