package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import rentcarTest.Dao.service.CarService;
import rentcarTest.dto.Car;
import rentcarTest.dto.Kind;
import rentcarTest.table.CarTable;

public class CarListPanel extends JPanel implements ActionListener, ItemListener {
	private JTextField tfSearch;
	private CarTable table;

	private JPanel pTable;
	private JScrollPane scrollPane;
	private JPanel pTitle;
	private JPanel pSearch;
	private JPanel pSearch_check;
	private JPanel pSearch_button;
	private JPanel pBtns;
	private JButton btnAdd;
	private JButton btnRent;

	private CarService service;
	private List<Car> lists;
	private JCheckBox chckbxRent;
	private JComboBox<String> cmbCate;
	private JButton btnSearch;

	public CarListPanel() {
		setPreferredSize(new Dimension(650, 661));
		service = new CarService();
		lists = service.showCars();

		initComponents();

		table.setItems(lists);

		CarPopupMenu popMenu = new CarPopupMenu(this);
		table.setComponentPopupMenu(popMenu);
		scrollPane.setViewportView(table);

	}

	private void initComponents() {

		setBackground(new Color(255, 255, 255));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		pTitle = new JPanel();
		pTitle.setPreferredSize(new Dimension(50, 70));
		pTitle.setBackground(new Color(255, 255, 255));
		add(pTitle);
		pTitle.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("차량 현황");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("굴림", Font.BOLD, 30));
		lblTitle.setBackground(new Color(255, 255, 255));
		pTitle.add(lblTitle);

		pSearch = new JPanel();
		add(pSearch);
		pSearch.setLayout(new BoxLayout(pSearch, BoxLayout.X_AXIS));

		pSearch_check = new JPanel();
		pSearch.add(pSearch_check);
		pSearch_check.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		chckbxRent = new JCheckBox("대여중");
		chckbxRent.addItemListener(this);
		pSearch_check.add(chckbxRent);

		pSearch_button = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pSearch_button.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pSearch.add(pSearch_button);

		cmbCate = new JComboBox<String>();
		setSearchCate();
		pSearch_button.add(cmbCate);

		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		pSearch_button.add(tfSearch);

		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		pSearch_button.add(btnSearch);

		pTable = new JPanel();
		pTable.setBackground(new Color(255, 255, 255));
		add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		pTable.add(scrollPane, BorderLayout.CENTER);

		table = new CarTable();

		pBtns = new JPanel();
		pBtns.setBackground(new Color(255, 255, 255));
		add(pBtns);

		btnAdd = new JButton("추가");
		pBtns.add(btnAdd);

		btnRent = new JButton("대여");
		pBtns.add(btnRent);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == chckbxRent) {
			selectSearchCheckedRent(e);
		}

	}

	// check_box - 대여중
	private void selectSearchCheckedRent(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			lists = service.showRentCars();
			table.setItems(lists);
		} else {
			lists = service.showCars();
			table.setItems(lists);
		}
	}

	public void insertCar(Car item) {
		service = new CarService();

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
		// 우클릭 메뉴
		if (e.getSource() instanceof JMenuItem) {
			if (e.getActionCommand().equals("수정")) {
				System.out.println("수정");
			}
			if (e.getActionCommand().equals("삭제")) {
				int selIdx = table.getSelectedRow();
				if (selIdx == -1) {
					JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
					return;
				}
				Car deleteCar = lists.get(selIdx);
				service.deleteCar(deleteCar);
				lists.remove(selIdx);
				table.removeRow(selIdx);
			}
			if (e.getActionCommand().equals("비고")) {
				System.out.println("수정");
			}
		}
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
		table.setItems(ctmList);
	}

	private class CarPopupMenu extends JPopupMenu {
		public CarPopupMenu(ActionListener listener) {
			JMenuItem updateMenu = new JMenuItem("수정");
			updateMenu.addActionListener(listener);
			add(updateMenu);
			JMenuItem deleteeMenu = new JMenuItem("삭제");
			deleteeMenu.addActionListener(listener);
			add(deleteeMenu);
			JMenuItem detailMenu = new JMenuItem("비고");
			detailMenu.addActionListener(listener);
			add(detailMenu);
		}
	}

}
