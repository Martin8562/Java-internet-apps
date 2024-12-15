import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.util.Date;

public class TimeSeriesGraphExample extends ApplicationFrame {

    public TimeSeriesGraphExample(String title) {
        super(title);
        TimeSeriesCollection dataset = createDataset();
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Time Series Graph Example",
            "Time",
            "Value",
            dataset,
            false,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new java.text.SimpleDateFormat("HH:mm:ss"));
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        setContentPane(chartPanel);
    }

    private TimeSeriesCollection createDataset() {
        TimeSeries series = new TimeSeries("Random Data");
        Second current = new Second(new Date());
        for (int i = 0; i < 10; i++) {
            series.add(current, Math.random());
            current = (Second) current.next();
        }
        return new TimeSeriesCollection(series);
    }

    public static void main(String[] args) {
        TimeSeriesGraphExample example = new TimeSeriesGraphExample("Time Series Graph Example");
        example.pack();
        RefineryUtilities.centerFrameOnScreen(example);
        example.setVisible(true);
    }
}
