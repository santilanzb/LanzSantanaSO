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
 * @author santi
 */
public class company extends Thread {
    private String companyName;
    
    //Time managing section
    private int deadlineRatio;
    private int daysLeftRelease;
    private int dayDuration;
    private int daysGoneBy;

    //Economy section
    private int profits;
    private int pmFaults;
    private int pmDiscountedAmount;
    private int salaryAccount;
    private int computersReleased;
    private int gcComputersReleased;
    private int commonComputerProfit;
    private int gcComputerProfit;
    
    //Semaphore section
    private Semaphore motherboardSemaphore;
    private Semaphore ramSemaphore;
    private Semaphore powersupplySemaphore;
    private Semaphore graphicscardSemaphore;
    private Semaphore cpuSemaphore;
    private Semaphore assemblerSemaphore;
    private Semaphore daysLeftSemaphore;
    private Semaphore salaryAccountSemaphore;

    //Storage section
    private Storage motherboardStorage;
    private Storage ramStorage;
    private Storage powersupplyStorage;
    private Storage graphicscardStorage;
    private Storage cpuStorage;
    private storageAssembler assemblerStorage;
    private storageAssembler gcAssemblerStorage;
 
    
    //Components requirements section
    private int motherboardReq;
    private int ramReq;
    private int powersupplyReq;
    private int cpuReq;
    private int gcComputersRatio;
    private int graphicscardAmount;
    
    //Employee type count section
    private int motherboardEmployeeCount;
    private int ramEmployeeCount;
    private int powersupplyEmployeeCount;
    private int graphicscardEmployeeCount;
    private int cpuEmployeeCount;
    private int assemblerEmployeeCount;
    
    //Employee type team Objects
    private motherboardTeam motherboardTeam;
    private ramTeam ramTeam;
    private powersupplyTeam powersupplyTeam;
    private graphicscardTeam graphicscardTeam;
    private cpuTeam cpuTeam;
    private director director;
    private proyectManager proyectManager;
    private assemblerTeam assemblerTeam;
   
    public company(String companyName,int commonComputerProfit,int gcComputerProfit, int deadlineRatio, int dayDuration, int motherboardReq, int ramReq, int powersupplyReq, int cpuReq, int gcComputersRatio, int graphicscardAmount, int motherboardEmployeeCount, int ramEmployeeCount, int powersupplyEmployeeCount, int graphicscardEmployeeCount, int cpuEmployeeCount, int assemblerEmployeeCount) {
        //Id
        this.companyName = companyName;
        //Economics
        this.commonComputerProfit = commonComputerProfit;
        this.gcComputerProfit = gcComputerProfit;
        this.profits = 0;
        this.pmFaults = 0;
        this.pmDiscountedAmount = 0;
        this.salaryAccount = 0;
        this.computersReleased = 0;
        this.gcComputersReleased = 0;
        //Time management
        this.deadlineRatio = deadlineRatio;
        this.daysLeftRelease = deadlineRatio;
        this.dayDuration = dayDuration;
        //Semaphores
        this.motherboardSemaphore = new Semaphore(1);
        this.ramSemaphore = new Semaphore(1);
        this.powersupplySemaphore = new Semaphore(1);
        this.graphicscardSemaphore = new Semaphore(1);
        this.cpuSemaphore = new Semaphore(1);
        this.assemblerSemaphore = new Semaphore(1);
        this.daysLeftSemaphore = new Semaphore(1); //This semaphore is going to be used to try to avoid concurrencies between the project manager and director changing days left
        this.salaryAccountSemaphore = new Semaphore(1);
        
        //Storages
        this.motherboardStorage = new Storage(25);
        this.ramStorage = new Storage(55);
        this.powersupplyStorage = new Storage(35);
        this.graphicscardStorage = new Storage(10);
        this.cpuStorage = new Storage(20);
        this.assemblerStorage = new storageAssembler(69);
        this.gcAssemblerStorage = new storageAssembler(69);
        
        //Requirements
        this.motherboardReq = motherboardReq;
        this.ramReq = ramReq;
        this.powersupplyReq = powersupplyReq;
        this.cpuReq = cpuReq;
        this.gcComputersRatio = gcComputersRatio;
        this.graphicscardAmount = graphicscardAmount;

        //Employee counts
        this.motherboardEmployeeCount = motherboardEmployeeCount;
        this.ramEmployeeCount = ramEmployeeCount;
        this.powersupplyEmployeeCount = powersupplyEmployeeCount;
        this.graphicscardEmployeeCount = graphicscardEmployeeCount;
        this.cpuEmployeeCount = cpuEmployeeCount;
        this.assemblerEmployeeCount = assemblerEmployeeCount;
     
        //Teams
        this.motherboardTeam = new motherboardTeam(this);
        this.ramTeam = new ramTeam(this);
        this.powersupplyTeam = new powersupplyTeam(this);
        this.graphicscardTeam = new graphicscardTeam(this);
        this.cpuTeam = new cpuTeam(this);
        this.director = new director(this);
        this.proyectManager = new proyectManager(this);
        this.assemblerTeam = new assemblerTeam(this);

    }
    @Override
    public void run() {                                         
                    getMotherboardTeam().start();
                    getRamTeam().start();
                    getPowersupplyTeam().start();
                    getGraphicscardTeam().start();
                    getCpuTeam().start();
                    getAssemblerTeam().start();
                    getDirector().start();
                    getProyectManager().start();
    }
    
    public String getCompanyName() {
        return companyName;
    }

    public int getDeadlineRatio() {
        return deadlineRatio;
    }

    public void setDeadlineRatio(int deadlineRatio) {
        this.deadlineRatio = deadlineRatio;
    }

    public int getDaysLeftRelease() {
        return daysLeftRelease;
    }

    public void setDaysLeftRelease(int daysLeftRelease) {
        this.daysLeftRelease = daysLeftRelease;
    }

    public int getProfits() {
        return profits;
    }

    public void setProfits(int profits) {
        this.profits = profits;
    }

    public int getPmFaults() {
        return pmFaults;
    }

    public void setPmFaults(int pmFaults) {
        this.pmFaults = pmFaults;
    }

    public int getPmDiscountedAmount() {
        return pmDiscountedAmount;
    }

    public void setPmDiscountedAmount(int pmDiscountedAmount) {
        this.pmDiscountedAmount = pmDiscountedAmount;
    }

    public int getSalaryAccount() {
        return salaryAccount;
    }

    public void setSalaryAccount(int salaryAccount) {
        try {
            getSalaryAccountSemaphore().acquire();
            this.salaryAccount = salaryAccount;
            getSalaryAccountSemaphore().release();
        } catch (InterruptedException ex) {
            Logger.getLogger(company.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Semaphore getMotherboardSemaphore() {
        return motherboardSemaphore;
    }

    public Semaphore getRamSemaphore(){
        return ramSemaphore;
    }
    
    public Semaphore getPowersupplySemaphore(){
        return powersupplySemaphore;
    }
    
    public Semaphore getGraphicscardSemaphore(){
        return graphicscardSemaphore;
    }
    
    public Semaphore getCpuSemaphore(){
        return cpuSemaphore;
    }
    
    public Semaphore getAssemblerSemaphore() {
        return assemblerSemaphore;
    }
    
    public Storage getMotherboardStorage() {
        return motherboardStorage;
    }
    
    public Storage getRamStorage(){
        return ramStorage;
    }
    
    public Storage getPowersupplyStorage(){
        return powersupplyStorage;
    }
    
    public Storage getGraphicscardStorage(){
        return graphicscardStorage;
    }
    
    public Storage getCpuStorage(){
        return cpuStorage;
    }
    
    public storageAssembler getAssemblerStorage() {
        return assemblerStorage;
    }

    public storageAssembler getGcAssemblerStorage() {
        return gcAssemblerStorage;
    }
    
    public int getMotherboardReq() {
        return motherboardReq;
    }
    
    public int getRamReq(){
        return ramReq;
    }

    public int getPowersupplyReq(){
        return powersupplyReq;
    }
    
    public int getCpuReq(){
        return cpuReq;
    }
   
    public int getGcComputersRatio() {
        return gcComputersRatio;
    }

    public int getGraphicscardAmount() {
        return graphicscardAmount;
    }
    
    public int getMotherboardEmployeeCount() {
        return motherboardEmployeeCount;
    }
    
    public int getRamEmployeeCount(){
        return ramEmployeeCount;
    }
    
    public int getPowersupplyEmployeeCount(){
        return powersupplyEmployeeCount;
    }
    
    public int getGraphicscardEmployeeCount(){
        return graphicscardEmployeeCount;
    }
    
    public int getCpuEmployeeCount(){
        return cpuEmployeeCount;
    }
    
    public int getAssemblerEmployeeCount() {
        return assemblerEmployeeCount;
    }

    public void setMotherboardEmployeeCount(int motherboardEmployeeCount) {
        this.motherboardEmployeeCount = motherboardEmployeeCount;
    }

    public void setRamEmployeeCount(int ramEmployeeCount){
        this.ramEmployeeCount = ramEmployeeCount;
    }
    
    public void setPowersupplyEmployeeCount(int powersupplyEmployeeCount){
        this.powersupplyEmployeeCount = powersupplyEmployeeCount;
    }
    
    public void setGraphicscardEmployeeCount(int graphicscardEmployeeCount){
        this.graphicscardEmployeeCount = graphicscardEmployeeCount;
    }
    
    public void setCpuEmployeeCount(int cpuEmployeeCount){
        this.cpuEmployeeCount = cpuEmployeeCount;
    }

    public void setAssemblerEmployeeCount(int assemblerEmployeeCount) {
        this.assemblerEmployeeCount = assemblerEmployeeCount;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    public motherboardTeam getMotherboardTeam() {
        return motherboardTeam;
    }
    
    public ramTeam getRamTeam(){
        return ramTeam;
    }
    
    public powersupplyTeam getPowersupplyTeam(){
        return powersupplyTeam;
    }
    
    public graphicscardTeam getGraphicscardTeam(){
        return graphicscardTeam;
    }
    
    public cpuTeam getCpuTeam(){
        return cpuTeam;
    }
    
    public assemblerTeam getAssemblerTeam() {
        return assemblerTeam;
    }
    
    public void setAssemblerTeam(assemblerTeam assemblerTeam) {
        this.assemblerTeam = assemblerTeam;
    }

    public int getComputersReleased() {
        return computersReleased;
    }

    public void setComputersReleased(int computersReleased) {
        this.computersReleased = computersReleased;
    }

    public int getGcComputersReleased() {
        return gcComputersReleased;
    }

    public void setGcComputersReleased(int gcComputersReleased) {
        this.gcComputersReleased = gcComputersReleased;
    }

    public int getCommonComputerProfit() {
        return commonComputerProfit;
    }

    public int getGcComputerProfit() {
        return gcComputerProfit;
    }
    
    public director getDirector() {
        return director;
    }

    public proyectManager getProyectManager() {
        return proyectManager;
    }
        
    public Semaphore getDaysLeftSemaphore() {
        return daysLeftSemaphore;
    }

    public Semaphore getSalaryAccountSemaphore() {
        return salaryAccountSemaphore;
    }

    public int getDaysGoneBy() {
        return daysGoneBy;
    }

    public void setDaysGoneBy(int daysGoneBy) {
        this.daysGoneBy = daysGoneBy;
    }
}

