/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author Abraham Santana
 */
public class SimulationData {
    
     public Object getRam() {
        return ram;
    }

    public void setRam(Object ram) {
        this.ram = ram;
    }

    public Object getAssemblers() {
        return assemblers;
    }

    public void setAssemblers(Object assemblers) {
        this.assemblers = assemblers;
    }

    public Object getDeadline() {
        return deadline;
    }

    public void setDeadline(Object deadline) {
        this.deadline = deadline;
    }

    public Object getGraphicscards() {
        return graphicscards;
    }

    public void setGraphicscards(Object graphicscards) {
        this.graphicscards = graphicscards;
    }

    public Object getMotherboards() {
        return motherboards;
    }

    public void setMotherboards(Object motherboards) {
        this.motherboards = motherboards;
    }

    public Object getSimulationDuration() {
        return simulationDuration;
    }

    public void setSimulationDuration(Object simulationDuration) {
        this.simulationDuration = simulationDuration;
    }

    public Object getCpu() {
        return cpu;
    }

    public void setCpu(Object cpu) {
        this.cpu = cpu;
    }

    public Object getPowersupplys() {
        return powersupplys;
    }

    public void setPowersupplys(Object powersupplys) {
        this.powersupplys = powersupplys;
    }

    public SimulationData(Object ram, Object assemblers, Object deadline, Object graphicscards, Object motherboards, Object simulationDuration, Object cpu, Object powersupplys) {
        this.ram = ram;
        this.assemblers = assemblers;
        this.deadline = deadline;
        this.graphicscards = graphicscards;
        this.motherboards = motherboards;
        this.simulationDuration = simulationDuration;
        this.cpu = cpu;
        this.powersupplys = powersupplys;
    }
    private Object ram;
    private Object assemblers;
    private Object deadline;
    private Object graphicscards;
    private Object motherboards;
    private Object simulationDuration;
    private Object cpu;
    private Object powersupplys;
}

