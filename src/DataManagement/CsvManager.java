/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataManagement;

import Objetos.ListaDoble;
import Objetos.SimulationData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Abraham Santana
 */
public class CsvManager {
    
    public ListaDoble ReadText() {
        
        ListaDoble list = new ListaDoble();

        File cvsFile = new File("test/simulationData.csv");
        BufferedReader lector;

        String line;
        String data_cvs = "";
        try {
            lector = new BufferedReader(new FileReader(cvsFile));
            while ((line = lector.readLine()) != null) {
                if (!line.isEmpty()) {
                    data_cvs += line + "\n";
                }
            }

            if (!"".equals(data_cvs)) {
                String[] data_split = data_cvs.split("\n");
                for (int i = 0; i < data_split.length; i++) {
                    String[] data = data_split[i].split("(,|, )");
                    if (!data_split[i].equals("secondsDuration, daysDeadline, motherboards, cpu, ram, powersupplys, graphicscards, assemblers")) {
                        SimulationData simulationData = new SimulationData(data[4], data[7], data[1], data[6], data[2], data[0], data[3], data[5]);
                        list.insertFinal(simulationData);
                    }
                }
            }

            lector.close();
        } catch (Exception e) {

        }
        return list;
    }

    public void WriteText(SimulationData newData, SimulationData newData1) {
        try {
            FileWriter w = new FileWriter("test/simulationData.csv");
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.append("secondsDuration, daysDeadline, motherboards, cpu, ram, powersupplys, graphicscards, assemblers \n");

            wr.append(String.valueOf(newData.getSimulationDuration()) + "," + String.valueOf(newData.getDeadline()) + ","
                    + String.valueOf(newData.getMotherboards()) + "," + String.valueOf(newData.getCpu()) + ","
                    + String.valueOf(newData.getRam()) + "," + String.valueOf(newData.getPowersupplys()) + ","
                    + String.valueOf(newData.getGraphicscards()) + "," + String.valueOf(newData.getAssemblers()) + "\n");
            
            wr.append(String.valueOf(newData1.getSimulationDuration()) + "," + String.valueOf(newData1.getDeadline()) + ","
                    + String.valueOf(newData1.getMotherboards()) + "," + String.valueOf(newData1.getCpu()) + ","
                    + String.valueOf(newData1.getRam()) + "," + String.valueOf(newData1.getPowersupplys()) + ","
                    + String.valueOf(newData1.getGraphicscards()) + "," + String.valueOf(newData1.getAssemblers()) + "\n");

            wr.close();
            bw.close();

        } catch (IOException e) {

        }
    }
}
