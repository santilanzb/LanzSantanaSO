/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Miguel
 */
public class Chart {

    public Chart() {
        XYSeries cartoonNetwork = new XYSeries("APPLE");
        cartoonNetwork.add(1, -6480);
        cartoonNetwork.add(2, -12960);
        cartoonNetwork.add(3, -19440);
        cartoonNetwork.add(4, -25820);
        cartoonNetwork.add(5, -32600);   
        cartoonNetwork.add(6, 917400);

        

        XYSeries starChannel = new XYSeries("MSI");
        starChannel.add(1, 2);
        starChannel.add(2, 3);
        starChannel.add(3, 4);
        starChannel.add(4, 5);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(starChannel);
        dataset.addSeries(cartoonNetwork);

        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Utilidades VS Tiempo",
                "Utilidades",
                "Tiempo",
                dataset,
                PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        plot.setRenderer(renderer);

        ChartPanel panel = new ChartPanel(xylineChart);

        // Creamos la ventana
        JFrame screen = new JFrame("Utilidad VS Tiempo");
        screen.setVisible(true);
        screen.setSize(800, 600);

        screen.add(panel);
    }

}
