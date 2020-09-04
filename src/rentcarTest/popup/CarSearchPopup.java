package rentcarTest.popup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rentcarTest.Dao.service.CarService;
import rentcarTest.dto.Car;
import rentcarTest.dto.Customer;
import rentcarTest.dto.Kind;
import rentcarTest.table.CarSearchTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarSearchPopup extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pTitle;
	private JPanel pSearch;
	private JPanel pTable;
	private JPanel pBtns;
	private JButton btnAdd;
	private JScrollPane scrollPane;
	private CarSearchTable table;
	private JComboBox<String> cmbCate;
	private JTextField tfSearch;
	private JButton btnSearch;
	private JLabel lblTitle;
	
	private CarService service = new CarService();
	private List<Car> lists = service.showCars();
	
	private CarRentPopup carList = new CarRentPopup();

	public void setCarList(CarRentPopup carList) {
		this.carList = carList;
	}
	
	public CarSearchPopup () {
		initComponents();
	}

	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pTitle = new JPanel();
		contentPane.add(pTitle);
		
		lblTitle = new JLabel("차량 검색");
		pTitle.add(lblTitle);
		
		pSearch = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pSearch.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(pSearch);
		
		cmbCate = new JComboBox<String>();
		setSearchCate();
		pSearch.add(cmbCate);
		
		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		pSearch.add(tfSearch);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		pSearch.add(btnSearch);
		
		pTable = new JPanel();
		contentPane.add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new CarSearchTable();
		table.setItems(lists);
		scrollPane.setViewportView(table);
		
		pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("선택");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
	}


	// 검색 분류
	private void setSearchCate() {
		String[] items = {"검색", "소형", "중형", "승합차", "버스", "지프" };
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(items);
		cmbCate.setModel(model);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	
	private void actionPerformedBtnAdd(ActionEvent e) {
		int selIdx = table.getSelectedRow();
		if (selIdx == -1) {
			JOptionPane.showMessageDialog(null, "해당 회원을 선택하세요.");
			return;
		}
		
		Car car = new Car();
		car = lists.get(selIdx);
		/*car.setCarKind(lists.get(selIdx).getCarKind());
		car.setCarName(lists.get(selIdx).getCarName());
		car.setSale(lists.get(selIdx).getSale());*/
		
		carList.setCar(car);
		this.dispose();
	}
	
	protected void actionPerformedBtnSearch(ActionEvent e) {
		List<Car> ctmList = null;
		String searchText = tfSearch.getText().trim();
		Car carListFind = new Car();

		Object cmbCateText = cmbCate.getSelectedItem();

		if (cmbCateText.equals("검색")) {
			ctmList = service.showCars();
		} else if (searchText.equals("")) {
			JOptionPane.showMessageDialog(null, "검색할 내용을 입력해주세요.");
		} else {
			if (cmbCateText.equals("소형")) {
				carListFind.setCarKind(new Kind("S", "소형"));
				carListFind.setCarName(searchText);
				System.out.println(carListFind);
				ctmList = service.findCars(carListFind);
			}
			if (cmbCateText.equals("중형")) {
				carListFind.setCarKind(new Kind("M", "중형"));
				carListFind.setCarName(searchText);
				ctmList = service.findCars(carListFind);
			}
			if (cmbCateText.equals("승합차")) {
				carListFind.setCarKind(new Kind("H", "승합차"));
				carListFind.setCarName(searchText);
				ctmList = service.findCars(carListFind);
			}
			if (cmbCateText.equals("버스")) {
				carListFind.setCarKind(new Kind("B", "버스"));
				carListFind.setCarName(searchText);
				ctmList = service.findCars(carListFind);
			}
			if (cmbCateText.equals("지프")) {
				carListFind.setCarKind(new Kind("J", "지프"));
				carListFind.setCarName(searchText);
				ctmList = service.findCars(carListFind);
			}
		}
		
		if (ctmList != null) {
			table.setItems(ctmList);
		} else {
			JOptionPane.showMessageDialog(null, "검색된 내용이 없습니다.");
		}
	}
}
