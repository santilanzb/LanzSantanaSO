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
 * @author Abraham Santana
 */
public class proyectManager extends Thread{
    
    int salary = 40;
    company company;
    private boolean WatchingAnime;
    private int hourlyCycle;

    public proyectManager(company company) {
        this.company = company;
        this.WatchingAnime = false;
        this.hourlyCycle = 0;
    }

    public void addDailySalary() {
        System.out.println("La cuenta antes de pagarle al PM: " + company.getSalaryAccount() );
        company.setSalaryAccount(
                company.getSalaryAccount() + salary * 24);
        
        System.out.println("La cuenta luego de pagarle al PM: " + company.getSalaryAccount() );
    }

    public void operate() {
        int timeSpent = 0;
        int halfHour = company.getDayDuration() / 48;
        int hour = company.getDayDuration() / 24;
        setHourlyCycle(0);
        while (hourlyCycle < 16) {
            try {
                // For the first 16 hours of the day, he alternates between watching porn and
                // working
                setWatchingAnime(true);

                // PRUEBA
                main.gui.getProjectManagerStatus().setText("Anime");
                main.gui.getProjectManagerStatusStar().setText("Anime");

                // System.out.println("El proyect Manager esta viendo anime");
                sleep(halfHour);
                timeSpent =+ halfHour;
                setWatchingAnime(false);

                // Prueba
                main.gui.getProjectManagerStatus().setText("Trabajando");
                main.gui.getProjectManagerStatusStar().setText("Trabajando");

                // System.out.println("El proyect Manager dejo de ver anime");
                sleep(halfHour);
                timeSpent += halfHour;
                setHourlyCycle(getHourlyCycle() + 1);
            } catch (InterruptedException ex) {
                Logger.getLogger(proyectManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // For the last 8 hours, the project manager works to change the days left for
          // release
        setWatchingAnime(false);

        // PRUEBA
        main.gui.getProjectManagerStatus().setText("Trabajando");
        main.gui.getProjectManagerStatusStar().setText("Trabajando");

        while (hourlyCycle < 24) {
            try {
                sleep(hour);
                timeSpent += hour;
            } catch (InterruptedException ex) {
                Logger.getLogger(proyectManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            setHourlyCycle(getHourlyCycle() + 1);

        } // At the right moment, the project Manager decreases the daysLeftRelease
          // counter
        try {
            sleep(company.getDayDuration() - timeSpent);
                
            company.getDaysLeftSemaphore().acquire(); // Hold the semaphore to change the daysLeftRelease counter
            company.setDaysLeftRelease(company.getDaysLeftRelease() - 1);
            if (company.getDaysLeftRelease() < 1) {
                company.setDaysLeftRelease(0);
            }
            company.getDaysLeftSemaphore().release(); // Releases the semaphore to change the daysLeftRelease counter
            System.out.println("Days left for release: " + company.getDaysLeftRelease());

        } catch (InterruptedException ex) {
            Logger.getLogger(proyectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        while (true) {
            operate();
            addDailySalary();
        }

    }

    public boolean isWatchingAnime() {
        return WatchingAnime;
    }

    public void setWatchingAnime(boolean WatchingAnime) {
        this.WatchingAnime = WatchingAnime;
    }

    public int getHourlyCycle() {
        return hourlyCycle;
    }

    public void setHourlyCycle(int hourlyCycle) {
        this.hourlyCycle = hourlyCycle;
    }
}

