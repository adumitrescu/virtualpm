/**
 * 
 */
package com.vm.component;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.Stacking;
import com.vaadin.addon.charts.model.Subtitle;
import com.vaadin.addon.charts.model.TickmarkPlacement;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;
import com.vaadin.addon.charts.model.Series;

/**
 * @author crpaslaru
 *
 */
public class StackedArea {

    public Component getChart(final String title, final String subtitle,final String[] xAxisCategories, final String yAxisTitle) {

        Chart chart = new Chart(ChartType.AREA);

        Configuration conf = chart.getConfiguration();

        conf.setTitle(new Title(title));
        conf.setSubTitle(new Subtitle(subtitle));

        XAxis xAxis = new XAxis();
        xAxis.setTickmarkPlacement(TickmarkPlacement.ON);
        xAxis.setCategories(xAxisCategories);
        conf.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setTitle(new AxisTitle(yAxisTitle));
        Labels labels = new Labels();
        labels.setFormatter("this.value / 1000");
        yAxis.setLabels(labels);
        conf.addyAxis(yAxis);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("this.x +': '+ Highcharts.numberFormat(this.y, 0, ',') +' millions'");
        conf.setTooltip(tooltip);

        PlotOptionsArea plotOptions = new PlotOptionsArea();
        plotOptions.setStacking(Stacking.NORMAL);
        conf.setPlotOptions(plotOptions);

        List<Series> series = new ArrayList<Series>();
        series.add(new ListSeries("Asia", 502, 635, 809, 947, 1402, 3634, 5268));
        series.add(new ListSeries("Africa", 106, 107, 111, 133, 221, 767, 1766));
        series.add(new ListSeries("Europe", 163, 203, 276, 408, 547, 729, 628));
        series.add(new ListSeries("America", 18, 31, 54, 156, 339, 818, 1201));
        series.add(new ListSeries("Ocenia", 2, 2, 2, 6, 13, 30, 46));
        conf.setSeries(series);

        chart.drawChart(conf);

        return chart;

    }
}
