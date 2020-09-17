package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import rentcarTest.Dao.service.CarService;
import rentcarTest.Dao.service.RentService;
import rentcarTest.dto.Car;
import rentcarTest.dto.Kind;
import rentcarTest.dto.Rent;
import rentcarTest.dto.RentPerformance;
import rentcarTest.table.PerformanceTable;

public class PerformanceListPanel extends JPanel implements ActionListener {
	private JPanel pTitle;
	private JLabel lblTitle;
	private JPanel pSearch;
	private JPanel pTable;
	private JPanel pBtns;
	private JButton btnChart;
	private JScrollPane scrollPane;
	private PerformanceTable table;
	private JButton btnSearch;
	private JComboBox cmbOption;
	private JComboBox cmbKind;
	private JComboBox cmbCar;

	private RentService service;
	private CarService carService;
	private List<RentPerformance> lists;

	public PerformanceListPanel() {
		service = new RentService();
		carService = new CarService();
		lists = service.showPerformance(0,"0",0);

		initComponents();

		table.setItems(lists);
	}

	private void initComponents() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		pTitle = new JPanel();
		pTitle.setBackground(Color.WHITE);
		add(pTitle);
		pTitle.setLayout(new BorderLayout(0, 0));

		lblTitle = new JLabel("성과 현황");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 30));
		pTitle.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		pSearch = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pSearch.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(pSearch);

		cmbKind = new JComboBox();
		cmbKind.addActionListener(this);
		pSearch.add(cmbKind);

		cmbCar = new JComboBox();
		cmbCar.addActionListener(this);
		pSearch.add(cmbCar);

		cmbOption = new JComboBox();
		pSearch.add(cmbOption);

		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		pSearch.add(btnSearch);

		pTable = new JPanel();
		add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);

		table = new PerformanceTable();
		scrollPane.setViewportView(table);

		pBtns = new JPanel();
		add(pBtns);

		btnChart = new JButton("차트 보기");
		btnChart.addActionListener(this);
		pBtns.add(btnChart);

		setSearchCmb();
	}

	// 검색 분류
	private void setSearchCmb() {
		String[] kinds = { "차량분류", "소형", "중형", "승합차", "버스", "지프" };
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(kinds);
		cmbKind.setModel(model);

		String[] names = { "차종" };
		DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<String>(names);
		cmbCar.setModel(model2);

		String[] options = { "옵션", "월별", "연별", "금액별" };
		DefaultComboBoxModel<String> model3 = new DefaultComboBoxModel<String>(options);
		cmbOption.setModel(model3);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cmbKind) {
			actionPerformedCmbKind(e);
		}
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
		if (e.getSource() == btnChart) {
			actionPerformedBtnChart(e);
		}
	}

	protected void actionPerformedCmbKind(ActionEvent e) {
		if (cmbKind.getSelectedIndex() != 0) {
			String getkind = cmbKind.getSelectedItem().toString();
			Kind kind = new Kind();
			kind.setKind_name(getkind);

			List<Car> cars = carService.findCars(new Car(kind));
			String[] names = new String[10];
			names[0] = "차종";
			int size = 1;

			for (Car c : cars) {
				names[size++] = c.getCarName();
			}

			DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<String>(names);
			cmbCar.setModel(model2);
		}else {
			String[] names = { "차종" };
			DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<String>(names);
			cmbCar.setModel(model2);
		}
	}

	protected void actionPerformedBtnSearch(ActionEvent e) {
		int selKind = cmbKind.getSelectedIndex();
		String selName = cmbCar.getSelectedItem().toString();
		int selOption = cmbOption.getSelectedIndex();
		
		lists = service.showPerformance(selKind, selName, selOption);
		table.setItems(lists);
	}

	protected void actionPerformedBtnChart(ActionEvent e) {
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		dcd.setValue(service.lookup("H"), "Cars", "H");
		dcd.setValue(service.lookup("M"), "Cars", "M");
		dcd.setValue(service.lookup("B"), "Cars", "B");
		dcd.setValue(service.lookup("J"), "Cars", "J");
		dcd.setValue(service.lookup("S"), "Cars", "S");

		JFreeChart jChart = ChartFactory.createBarChart3D("How to use UTF-8?", null, null, dcd,
				PlotOrientation.VERTICAL, true, true, false);

		CategoryPlot plot = jChart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.black);

		ChartFrame chartFrm = new ChartFrame("Student Record", jChart, true);
		chartFrm.setVisible(true);
		chartFrm.setSize(800, 500);
	}
}
