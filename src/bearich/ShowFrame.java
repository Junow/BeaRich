package bearich;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

import com.sun.javafx.charts.Legend;

public class ShowFrame extends ApplicationFrame{
   static JFrame frame = new JFrame("BeARich"); // Main Frame
   JTextField textField = new JTextField(22);
   JTextArea messageArea = new JTextArea(45, 22);

   JPanel buttonPanel = new JPanel(); // Left Panel
   JPanel chartJPanel = new JPanel(); // Center Panel
   JPanel chatPanel = new JPanel(); // Right Panel

   JPanel wallet = new JPanel();
   
   //////// about chart////////
   TimeSeries BTCseries;
   TimeSeries ETHseries;
   TimeSeries DASHseries;
   TimeSeries LTCseries;
   TimeSeries ETCseries;
   TimeSeries XRPseries;
   TimeSeries BCHseries;
   TimeSeries XMRseries;
   TimeSeries ZECseries;
   TimeSeries QTUMseries;
   
   JTabbedPane JTP = new JTabbedPane();
   
   JFreeChart BTCchart;
   JFreeChart ETHchart;
   JFreeChart DASHchart;
   JFreeChart LTCchart;
   JFreeChart ETCchart;
   JFreeChart XRPchart;
   JFreeChart BCHchart;
   JFreeChart XMRchart;
   JFreeChart ZECchart;
   JFreeChart QTUMchart;
   
   ChartPanel BTCPanel;
   ChartPanel ETHPanel;
   ChartPanel DASHPanel;
   ChartPanel LTCPanel;
   ChartPanel ETCPanel;
   ChartPanel XRPPanel;
   ChartPanel BCHPanel;
   ChartPanel XMRPanel;
   ChartPanel ZECPanel;
   ChartPanel QTUMPanel;
   
//   * {currency} = BTC, ETH, DASH, LTC, ETC, XRP, BCH, XMR, ZEC, QTUM (기본값: BTC), ALL(전체)

   
   public ShowFrame(String title) {
      super(title);
      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      
      // Layout GUI
      frame.setPreferredSize(new Dimension(1400,800));
      
      chartJPanel.setPreferredSize(new Dimension(1000,780)); // center panel size
      chatPanel.setPreferredSize(new Dimension(290,780)); // right panel size
      
      messageArea.setSelectionColor(new Color(35,98,200));
      
//      frame.add(chartJPanel, "West"); 
      frame.add(JTP,"West");
      frame.add(chatPanel,"East"); 

      //////////    add chart /////////
      
      BTCseries = new TimeSeries("", Millisecond.class);
      ETHseries = new TimeSeries("", Millisecond.class);
      DASHseries = new TimeSeries("", Millisecond.class);
      LTCseries = new TimeSeries("", Millisecond.class);
      ETCseries = new TimeSeries("", Millisecond.class);
      XRPseries = new TimeSeries("", Millisecond.class);
      BCHseries = new TimeSeries("", Millisecond.class);
      XMRseries = new TimeSeries("", Millisecond.class);
      ZECseries = new TimeSeries("", Millisecond.class);
      QTUMseries = new TimeSeries("", Millisecond.class);
      
      final TimeSeriesCollection BTCdataset = new TimeSeriesCollection(BTCseries);
      final TimeSeriesCollection ETHdataset = new TimeSeriesCollection(ETHseries);
      final TimeSeriesCollection DASHdataset = new TimeSeriesCollection(DASHseries);
      final TimeSeriesCollection LTCdataset = new TimeSeriesCollection(LTCseries);
      final TimeSeriesCollection ETCdataset = new TimeSeriesCollection(ETCseries);
      final TimeSeriesCollection XRPdataset = new TimeSeriesCollection(XRPseries);
      final TimeSeriesCollection BCHdataset = new TimeSeriesCollection(BCHseries);
      final TimeSeriesCollection XMRdataset = new TimeSeriesCollection(XMRseries);
      final TimeSeriesCollection ZECdataset = new TimeSeriesCollection(ZECseries);
      final TimeSeriesCollection QTUMdataset = new TimeSeriesCollection(QTUMseries);
      
      BTCchart = createChart(BTCdataset);
      ETHchart = createChart(ETHdataset);
      DASHchart = createChart(DASHdataset);
      LTCchart = createChart(LTCdataset);
      ETCchart = createChart(ETCdataset);
      XRPchart = createChart(XRPdataset);
      BCHchart = createChart(BCHdataset);
      XMRchart = createChart(XMRdataset);
      ZECchart = createChart(ZECdataset);
      QTUMchart = createChart(QTUMdataset);
      
      BTCPanel = new ChartPanel(BTCchart); 
      ETHPanel = new ChartPanel(ETHchart);
      DASHPanel =new ChartPanel(DASHchart);
      LTCPanel = new ChartPanel(LTCchart);
      ETCPanel = new ChartPanel(ETCchart);
      XRPPanel = new ChartPanel(XRPchart);
      BCHPanel = new ChartPanel(BCHchart);
      XMRPanel = new ChartPanel(XMRchart);
      ZECPanel = new ChartPanel(ZECchart);
      QTUMPanel = new ChartPanel(QTUMchart);
      
//      chartJPanel.add(BTCPanel);
      JTP.add("BTC",  BTCPanel);
      JTP.add("ETH",  ETHPanel);
      JTP.add("DASH",  DASHPanel);
      JTP.add("LTC",  LTCPanel);
      JTP.add("ETC",  ETCPanel);
      JTP.add("XRP",  XRPPanel);
      JTP.add("XMR",  XMRPanel);
      JTP.add("BCH",  BCHPanel);
      JTP.add("ZEC",  ZECPanel);
      JTP.add("QTUM",  QTUMPanel);
      JTP.add("My Account", wallet);
      //BTCPanel.setPreferredSize(new Dimension(1000,765));
      ETHPanel.setPreferredSize(new Dimension(1000,765));
      DASHPanel.setPreferredSize(new Dimension(1000,765));
      LTCPanel.setPreferredSize(new Dimension(1000,765));
      ETCPanel.setPreferredSize(new Dimension(1000,765));
      XRPPanel.setPreferredSize(new Dimension(1000,765));
      XMRPanel.setPreferredSize(new Dimension(1000,765));
      BCHPanel.setPreferredSize(new Dimension(1000,765));
      ZECPanel.setPreferredSize(new Dimension(1000,765));
      QTUMPanel.setPreferredSize(new Dimension(1000,765));
      // set chart size
      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////
      BTCPanel.setMouseWheelEnabled(true);
      ETHPanel.setMouseWheelEnabled(true);
      DASHPanel.setMouseWheelEnabled(true);
      LTCPanel.setMouseWheelEnabled(true);
      ETCPanel.setMouseWheelEnabled(true);
      XRPPanel.setMouseWheelEnabled(true);
      XMRPanel.setMouseWheelEnabled(true);
      BCHPanel.setMouseWheelEnabled(true);
      ZECPanel.setMouseWheelEnabled(true);
      QTUMPanel.setMouseWheelEnabled(true);
      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////   
      //////////    add text field, message Area ////////////
      
      chatPanel.add(new JScrollPane(messageArea), "Center");
      chatPanel.add(textField, "South");
      
      chatPanel.setBackground(new Color(41,41,41));
//      chartJPanel.setBackground(new Color(41,41,41));
      buttonPanel.setBackground(new Color(41,41,41));
      BTCchart.setBackgroundPaint(new Color(41,41,41));
      ETHchart.setBackgroundPaint(new Color(41,41,41));
      DASHchart.setBackgroundPaint(new Color(41,41,41));
      LTCchart.setBackgroundPaint(new Color(41,41,41));
      ETCchart.setBackgroundPaint(new Color(41,41,41));
      XRPchart.setBackgroundPaint(new Color(41,41,41));
      XMRchart.setBackgroundPaint(new Color(41,41,41));
      BCHchart.setBackgroundPaint(new Color(41,41,41));
      ZECchart.setBackgroundPaint(new Color(41,41,41));
      QTUMchart.setBackgroundPaint(new Color(41,41,41));
      
      
      textField.setBackground(new Color(240,240,240));
      messageArea.setBackground(new Color(240,240,240));
      
      
      textField.setEditable(false);
      messageArea.setEditable(false);

      frame.pack();
   }   
   private JFreeChart createChart(final XYDataset dataset) {

      final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "",
            "",
            "",
            dataset,
            true,
            true,
            true
            );

      final XYPlot plot = result.getXYPlot();

      plot.setBackgroundPaint(Color.white);
      
      plot.setDomainGridlinesVisible(false);
      plot.setRangeGridlinesVisible(false);
      plot.setBackgroundPaint(new Color(41,41,41));
      plot.getRenderer().setSeriesPaint(0, Color.white);
//      plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
//      plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
      ValueAxis xaxis = plot.getDomainAxis();
//      xaxis.setLabelAngle( 90 * (Math.PI / 180.0) ); // time rotate
      xaxis.setAutoRange(true);
      xaxis.setLabel("Time");
      //Domain axis would show data of 60 seconds for a time
//      xaxis.setFixedAutoRange(60000.0);  // size
      xaxis.setVerticalTickLabels(false); // vertical or horizon
      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////
      xaxis.setAxisLinePaint(Color.white);
      xaxis.setLabelPaint(Color.white);
      xaxis.setTickLabelPaint(Color.white);
      xaxis.setTickMarkPaint(Color.white);
      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////
      ValueAxis yaxis = plot.getRangeAxis();
      yaxis.setLabel("Price");
//      yaxis.setRange(8380000.0, 9010000.0); // y축 범위
      yaxis.setAutoRange(true);
      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////
      yaxis.setAxisLinePaint(Color.white);
      yaxis.setLabelPaint(Color.white);
      yaxis.setTickLabelPaint(Color.white);
      yaxis.setTickMarkPaint(Color.white);
      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////
//      setSize(new Dimension(800,400));
      

      return result;
   }

   
   public TimeSeries getBTCSeries() {
      return BTCseries;
   }

   public TimeSeries getETHSeries() {
      return ETHseries;
   }
   public TimeSeries getDASHSeries() {
      return DASHseries;
   }
   public TimeSeries getLTCSeries() {
      return LTCseries;
   }
   public TimeSeries getETCSeries() {
      return ETCseries;
   }
   public TimeSeries getXRPSeries() {
      return XRPseries;
   }
   public TimeSeries getBCHSeries() {
      return BCHseries;
   }
   public TimeSeries getXMRSeries() {
      return XMRseries;
   }
   public TimeSeries getZECSeries() {
      return ZECseries;
   }
   public TimeSeries getQTUMSeries() {
      return QTUMseries;
   }
   
   
   
   
}