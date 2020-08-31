package rentcarTest.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import rentcarTest.Dao.service.RentService;

public class PerformanceChartPopup extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
//	private BarChart chart;
	private JPanel pBtns;
	private JButton btnCancel;
	private JButton btnChart;
	private RentService service = new RentService();

	public static void main(String[] args) {
		try {
			PerformanceChartPopup dialog = new PerformanceChartPopup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PerformanceChartPopup() {
		initComponents();
		
	}
	private void initComponents() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		pBtns = new JPanel();
		getContentPane().add(pBtns, BorderLayout.SOUTH);
		
		btnCancel = new JButton("닫기");
		btnCancel.addActionListener(this);
		
		btnChart = new JButton("Show Chart");
		btnChart.addActionListener(this);
		pBtns.add(btnChart);
		pBtns.add(btnCancel);
		
		
	}

	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChart) {
			actionPerformedBtnChart(e);
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
	}
	private void actionPerformedBtnChart(ActionEvent e) {
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		dcd.setValue(service.lookup("H"), "Cars", "H");
		dcd.setValue(service.lookup("M"), "Cars", "M");
		dcd.setValue(service.lookup("B"), "Cars", "B");
		dcd.setValue(service.lookup("J"), "Cars", "J");
		dcd.setValue(service.lookup("S"), "Cars", "S");
		
		
		JFreeChart jChart = ChartFactory.createBarChart3D("How to use UTF-8?", null, null, dcd, PlotOrientation.VERTICAL, true, true, false);
		
		CategoryPlot plot = jChart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.black);
		
		ChartFrame chartFrm = new ChartFrame("Student Record", jChart, true);
		chartFrm.setVisible(true);
		chartFrm.setSize(1000, 1000);
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		PerformanceChartPopup.this.dispose();
	}
}
