/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Interfaz.Vista;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import lanzsantanaso.main;
/**
 *
 * @author Abraham Santana
 */
public class director extends Thread {

    int salary = 60;
    company company;
    private boolean checkingPM;
    private int hourlyCycle;

    public director(company company) {
        this.company = company;
        this.checkingPM = false;
        this.hourlyCycle = 0;
    }

    public void addDailySalary() {
        System.out.println("La cuenta antes de pagarle al Director: " + company.getSalaryAccount() );
        company.setSalaryAccount(
                company.getSalaryAccount() + salary * 24);
        System.out.println("El director gana " + salary*24+"$");
        System.out.println("La cuenta luego de pagarle al Director: " + company.getSalaryAccount() );
    }

    public void operate() {
        if (company.getDaysLeftRelease() == 0) { 
            try { 
                sleep(company.getDayDuration()); 

                company.getAssemblerSemaphore().acquire(); 
                int releasedCommonEpisodes = company.getAssemblerDrive().getResourse(); 
                company.getAssemblerDrive().setResourse(0); 
                int releasedPlotTwistEpisodes = company.getPlotAssemblerDrive().getResourse(); 
                
                company.getPlotAssemblerDrive().setResourse(0); 
                company.setEpisodesReleased(company.getEpisodesReleased() + releasedCommonEpisodes);
                company.setPlotEpisodesReleased(company.getPlotEpisodesReleased() + releasedPlotTwistEpisodes);
                int profitsToSum = releasedCommonEpisodes * company.getCommonEpisodeProfit()
                        + releasedPlotTwistEpisodes * company.getPlotEpisodeProfit(); 
                
                System.out.println("El director ha liberado " + releasedCommonEpisodes + " computadoras comunes y "
                        + releasedPlotTwistEpisodes + " computadoras con tarjeta grafica");
                System.out.println("Las ganancias totales de este ciclo, han sido " + profitsToSum + "$");
                company.setProfits(company.getProfits() + profitsToSum);
                company.getDaysLeftSemaphore().acquire();
                company.setDaysLeftRelease(company.getDeadlineRatio());
                company.getDaysLeftSemaphore().release();
                company.getAssemblerSemaphore().release(); // releases de assembler Semaphore
            } catch (InterruptedException ex) {
                Logger.getLogger(director.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else { // If there are more than 0 days left for release, the director will check on
                 // the project manajer for 35 minutes
                
                 int timeSpent = 0; //The timeSpent variable ensures that the time that the director's operate() method lasts exacly one day
                 
            int halfHour = company.getDayDuration() / 48;
            int hour = company.getDayDuration() / 24; // This variable represents 1 hour that will adapt based on the
                                                     // variable dayDuration
            Random random = new Random();
            int randomHour = random.nextInt(24); // Generate a random hour (between 0 and 23)
            hourlyCycle = 0;
            while (hourlyCycle < randomHour) {
                try {
                    setCheckingPM(false);

                    // PRUEBAAAA
                    if (company == main.CartoonNetwork) {
                        main.gui.getDirectorStatus().setText("Administrando");
                    } else {
                        main.gui.getDirectorStatusStar().setText("Administrando");
                    }

                    sleep(hour);
                    timeSpent += hour;
                } catch (InterruptedException ex) {
                    Logger.getLogger(director.class.getName()).log(Level.SEVERE, null, ex);
                }
                hourlyCycle++;
            }
            if (hourlyCycle == randomHour) { // The director will oberve the project manager for 35 minutes, and he will
                                             // keep working for the remaining of the day
                setCheckingPM(true);

                // PRUEBAAAA
                if (company == main.CartoonNetwork) {
                    main.gui.getDirectorStatus().setText("Supervisando PM");
                } else {
                    main.gui.getDirectorStatusStar().setText("Supervisando PM");
                }

                try {
                    sleep(halfHour); // Checks the project manajer for 35 minutes
                    timeSpent += halfHour;
                } catch (InterruptedException ex) {
                    Logger.getLogger(director.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (company.getProyectManager().isWatchingAnime()) { // Sees if he is watching anime, if he is, he will
                                                                    // punish him ;)
                    company.setPmFaults(company.getPmFaults() + 1); // Adds 1 unit to the project manager faults counter
                                                                  // in company

                    company.setPmDiscountedAmount(company.getPmDiscountedAmount() + 100); // Adds 100$ to the project
                                                                                        // manager discounted total in
                                                                                        // company

                    company.setSalaryAccount(company.getSalaryAccount() - 100); // Substracts 100$ to the salary account
                    System.out.println("El director atrapo al PM viendo anime");
                }

                try {
                    setCheckingPM(false);

                    // PRUEBAAAA
                    if (company == main.CartoonNetwork) {
                        main.gui.getDirectorStatus().setText("Administrando");
                    } else {
                        main.gui.getDirectorStatusStar().setText("Administrando");
                    }

                    sleep(halfHour);
                    timeSpent += halfHour;
                } catch (InterruptedException ex) {
                    Logger.getLogger(director.class.getName()).log(Level.SEVERE, null, ex);
                }
                hourlyCycle++; // By the time the event is over, 1 hour has gone by
                while (hourlyCycle < 24) {
                    try {
                        sleep(hour);
                        timeSpent += hour;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(director.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    hourlyCycle++;
                }

            }
            
            try {
                sleep(company.getDayDuration() - timeSpent);
            } catch (InterruptedException ex) {
                Logger.getLogger(director.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

    }

    @Override
    public void run() {

        while (true) {
            operate();
            addDailySalary();
        }

    }

    public int getHourlyCycle() {
        return hourlyCycle;
    }

    public void setHourlyCycle(int hourlyCycle) {
        this.hourlyCycle = hourlyCycle;
    }

    public boolean isCheckingPM() {
        return checkingPM;
    }

    public void setCheckingPM(boolean checkingPM) {
        this.checkingPM = checkingPM;
    }
}

