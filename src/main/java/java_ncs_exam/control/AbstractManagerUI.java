package java_ncs_exam.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import java_ncs_exam.content.AbstractContentPanel;
import java_ncs_exam.content.AbstractCustomTablePanel;
import java_ncs_exam.exception.EmptyTfException;
import java_ncs_exam.exception.InValidationException;
import java_ncs_exam.exception.NotSelectedException;
import java_ncs_exam.exception.SqlConstraintException;

@SuppressWarnings("serial")
public abstract class AbstractManagerUI<T> extends JFrame implements ActionListener {

	private JPanel contentPane;
	protected JButton btnAdd;
	private JButton btnCancel;
	
	protected AbstractContentPanel<T> pContent; // AbstractContentPanel
	protected AbstractCustomTablePanel<T> pList; // AbstractCustomTablePanel
	protected JMenuItem empListByTitleItem;
	
	public AbstractManagerUI() {
		setService();		// service 연결
		initialize();	
		tableLoadData();	// component load data
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = createContentPanel();
		contentPane.add(pContent);

		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

		pList = createTablePanel();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	
	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("직책 수정");
		updateItem.addActionListener(this);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("직책 삭제");
		deleteItem.addActionListener(this);
		popMenu.add(deleteItem);
		
		return popMenu;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() instanceof JMenuItem) {
				if (e.getActionCommand().equals("직책 삭제")) {
					actionPerformedMenuDelete();
				}
				if (e.getActionCommand().equals("직책 수정")) {
					actionPerformedMenuUpdate();
				}
			} else {
				if (e.getSource() == btnCancel) {
					actionPerformedBtnCancel(e);
				}
				if (e.getSource() == btnAdd) {
					if (btnAdd.getText().equals("추가")) {
						actionPerformedBtnAdd(e);
					}
					if (btnAdd.getText().equals("수정")) {
						actionperformedBtnUpdate(e);
					}
				}
			}
		} catch (InValidationException | EmptyTfException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
		} catch (NotSelectedException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "선택 오류", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected abstract void setService();
	
	protected abstract void tableLoadData();
	
	protected abstract AbstractContentPanel<T> createContentPanel();
	
	protected abstract AbstractCustomTablePanel<T> createTablePanel();

	protected abstract void actionPerformedMenuUpdate();

	protected abstract void actionPerformedMenuDelete();
	
	protected abstract void actionperformedBtnUpdate(ActionEvent e);
	
	protected abstract void actionPerformedBtnAdd(ActionEvent e);
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pContent.clearTf();
		
		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
}
