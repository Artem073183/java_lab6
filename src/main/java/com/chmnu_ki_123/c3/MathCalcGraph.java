package com.chmnu_ki_123.c3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;
import java.awt.*;
import static java.lang.Math.*;

public class MathCalcGraph extends JFrame {

    public MathCalcGraph(String title) {
        super(title);
        XYSeries series = new XYSeries("f(x)");

        // Змінні a, b, c, як у вихідному коді
        double a = 2.0, b = -1.0, c = 5.1;

        double minX = Double.NaN, minF = Double.NaN, maxX = Double.NaN, maxF = Double.NaN;
        boolean first = true;

        System.out.printf("%-10s %-10s\n", "x", "f(x)");
        System.out.println("--------------------------");

        // Побудова графіку для діапазону значень x
        for (double xVal = -10; xVal <= 5; xVal += 0.1) {
            double f;
            try {
                // Обчислення функції: f(x) = exp(a * x - b) / log10(abs(x - c))
                f = exp(a * xVal - b) / log10(abs(xVal - c));

                // Перевірка на NaN або безмежність
                if (Double.isNaN(f) || Double.isInfinite(f)) {
                    continue;
                }

                series.add(xVal, f);
                System.out.printf("%-10.2f %-10.5f\n", xVal, f);

                if (first || f < minF) {
                    minX = xVal;
                    minF = f;
                }
                if (first || f > maxF) {
                    maxX = xVal;
                    maxF = f;
                }

                first = false;
            } catch (Exception e) {
                System.err.println("Помилка обчислення значення функції при x = " + xVal + ": " + e.getMessage());
            }
        }

        System.out.println("--------------------------");
        System.out.printf("Знайдені екстремуми:%nМінімум: x = %.2f, f(x) = %.5f%nМаксимум: x = %.2f, f(x) = %.5f%n", minX, minF, maxX, maxF);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Графік функції з екстремумами",
                "x", "f(x)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MathCalcGraph chart = new MathCalcGraph("Побудова графіку та екстремуми");
        });
    }
}
