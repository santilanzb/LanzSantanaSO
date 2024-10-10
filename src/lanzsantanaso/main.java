/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lanzsantanaso;

import Interfaz.Vista;
import Objetos. *;
import DataManagement.CsvManager;
import Objetos.assemblerTeam. *;

/**
 *
 * @author santi
 */
public class main {
    
    public static CsvManager csvManager = new CsvManager();
    public static SimulationData simulationData = (SimulationData) csvManager.ReadText().getHead().getElement();
    public static SimulationData simulationData1 = (SimulationData) csvManager.ReadText().getTail().getElement();

    static int dayDuration = Integer.parseInt((String) simulationData.getSimulationDuration()) * 1000;
    static int deadline = Integer.parseInt((String) simulationData.getDeadline());
    
    static int dayDuration1 = Integer.parseInt((String) simulationData1.getSimulationDuration()) * 1000;
    static int deadline1 = Integer.parseInt((String) simulationData1.getDeadline());


    // Employee amount
    static int motherboard = Integer.parseInt((String) simulationData.getMotherboards());
    static int cpu = Integer.parseInt((String) simulationData.getCpu());
    static int ram = Integer.parseInt((String) simulationData.getRam());
    static int powersupplys = Integer.parseInt((String) simulationData.getPowersupplys());
    static int graphicscards = Integer.parseInt((String) simulationData.getGraphicscards());
    static int assembler = Integer.parseInt((String) simulationData.getAssemblers());

    static int motherboard1 = Integer.parseInt((String) simulationData1.getMotherboards());
    static int cpu1 = Integer.parseInt((String) simulationData1.getCpu());
    static int ram1 = Integer.parseInt((String) simulationData1.getRam());
    static int powersupplys1 = Integer.parseInt((String) simulationData1.getPowersupplys());
    static int graphicscards1 = Integer.parseInt((String) simulationData1.getGraphicscards());
    static int assembler1 = Integer.parseInt((String) simulationData1.getAssemblers());
    public static company Apple = new company("Apple", 300000, 650000, deadline, dayDuration, 1, 2, 6, 5, 3, 1, motherboard, cpu, ram, powersupplys, graphicscards, assembler);
    public static company MSI = new company("MSI", 350000, 800000, deadline1, dayDuration1, 2, 3, 4, 6, 3, 1, motherboard1, cpu1, ram1, powersupplys1, graphicscards1, assembler1);
    public static Vista gui = new Vista();
//    List fot the chart
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Apple.start();
        MSI.start();
        Apple.getDaysGoneBy();
        int counter = 1;

        while (true) {
            // FINANCE
            // APPLE
            gui.getCostsC().setText(String.valueOf(Apple.getSalaryAccount()));
            gui.getRevenueC().setText(String.valueOf(Apple.getProfits()));
            gui.getNetIncomeC().setText(String
                    .valueOf(main.Apple.getProfits() - Apple.getSalaryAccount()));
            // MSI
            gui.getCosts().setText(String.valueOf(MSI.getSalaryAccount()));
            gui.getRevenue().setText(String.valueOf(MSI.getProfits()));
            gui.getNetIncome().setText(
                    String.valueOf(MSI.getProfits() - MSI.getSalaryAccount()));

            // FAULTS & DEDUCTIONS
            // APPLE
            gui.getProjectManagerFaults().setText(String.valueOf(Apple.getPmFaults()));
            gui.getProjectManagerDeduction()
                    .setText(String.valueOf(Apple.getPmDiscountedAmount()));
            // MSI
            gui.getProjectManagerFaultsStar().setText(String.valueOf(MSI.getPmFaults()));
            gui.getProjectManagerDeductionStar()
                    .setText(String.valueOf(MSI.getPmDiscountedAmount()));

            // DRIVE AVAILABILITY
            // APPLE
            gui.getScriptAvailability()
                    .setText(String.valueOf(MSI.getMotherboardStorage().getMaxResourse()
                            - MSI.getMotherboardStorage().getResourse()));
            gui.getDubbingAvailability()
                    .setText(String.valueOf(MSI.getGraphicscardStorage().getMaxResourse()
                            - MSI.getGraphicscardStorage().getResourse()));
            gui.getStageAvailability()
                    .setText(String.valueOf(MSI.getRamStorage().getMaxResourse()
                            - MSI.getRamStorage().getResourse()));
            gui.getAnimationAvailability()
                    .setText(String.valueOf(MSI.getPowersupplyStorage().getMaxResourse()
                            - MSI.getPowersupplyStorage().getResourse()));
            gui.getPlotTwistAvailability()
                    .setText(String.valueOf(MSI.getCpuStorage().getMaxResourse()
                            - MSI.getCpuStorage().getResourse()));
            // MSI
            gui.getScriptAvailabilityC()
                    .setText(String.valueOf(Apple.getMotherboardStorage().getMaxResourse()
                            - Apple.getMotherboardStorage().getResourse()));
            gui.getDubbingAvailabilityC()
                    .setText(String.valueOf(Apple.getGraphicscardStorage().getMaxResourse()
                            - Apple.getGraphicscardStorage().getResourse()));
            gui.getAnimationAvailabilityC()
                    .setText(String.valueOf(Apple.getRamStorage().getMaxResourse()
                            - Apple.getRamStorage().getResourse()));
            gui.getPlotTwistAvailabilityC()
                    .setText(String.valueOf(Apple.getPowersupplyStorage().getMaxResourse()
                            - Apple.getPowersupplyStorage().getResourse()));
            gui.getStageAvailabilityC()
                    .setText(String.valueOf(Apple.getCpuStorage().getMaxResourse()
                            - Apple.getCpuStorage().getResourse()));

            // READY
            // APPLE
            gui.getReadyPlotTwist()
                    .setText(String.valueOf(Apple.getGcAssemblerStorage().getResourse())); 
            gui.getReadyStandard()
                    .setText(String.valueOf(Apple.getAssemblerStorage().getResourse()));
            // MSI
            gui.getReadyPlotTwistStar()
                    .setText(String.valueOf(MSI.getGcAssemblerStorage().getResourse()));
            gui.getReadyStandardStar()
                    .setText(String.valueOf(MSI.getAssemblerStorage().getResourse()));

            // DEADLINE COUNTER
            // APPLE
            gui.getDeadlineCounter().setText(String.valueOf(Apple.getDaysLeftRelease()));
            // MSI
            gui.getDeadlineCounterStar().setText(String.valueOf(MSI.getDaysLeftRelease()));

        }
    }

}
