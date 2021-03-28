package java_ncs_exam.content;

import java.awt.GridLayout;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import java_ncs_exam.dto.Title;
import java_ncs_exam.exception.EmptyTfException;
import java_ncs_exam.exception.InValidationException;

@SuppressWarnings("serial")
public class TitlePanel extends AbstractContentPanel<Title> {
	private JTextField tfTitleNo;
	private JTextField tfTitleName;
	
	public JTextField getTfTitleNo() {
		return tfTitleNo;
	}

	public JTextField getTfTitleName() {
		return tfTitleName;
	}

	public TitlePanel() {
		initialize();
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "직책 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblTitleNo = new JLabel("직책번호");
		lblTitleNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitleNo);
		
		tfTitleNo = new JTextField();
		add(tfTitleNo);
		tfTitleNo.setColumns(10);
		
		JLabel lblTitleName = new JLabel("직책명");
		lblTitleName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitleName);
		
		tfTitleName = new JTextField();
		tfTitleName.setColumns(10);
		add(tfTitleName);
		
	}

	@Override
	public void setItem(Title item) {
		tfTitleNo.setText(item.gettNo() + "");
		tfTitleName.setText(item.gettName() + "");
		
		tfTitleNo.setEditable(false);
		
	}

	@Override
	public Title getItem() {
		validCheck();
		typeCheck();
		
		int titleNo = Integer.parseInt(tfTitleNo.getText().trim());
		if(!(titleNo>0 && titleNo<1000)) {
			throw new EmptyTfException();
		}
		String titleName = tfTitleName.getText().trim();
		
		return new Title(titleNo, titleName);
	}

	public void typeCheck() {
		if(!Pattern.matches("^[0-9]*$", tfTitleNo.getText()) || !Pattern.matches("^[가-힣]*$", tfTitleName.getText())) {
			throw new EmptyTfException();
		}
	}

	@Override
	public void validCheck() {
		if (tfTitleNo.getText().contentEquals("")||tfTitleName.getText().contentEquals("")) {
			throw new InValidationException();
		}
		
	}

	@Override
	public void clearTf() {
		tfTitleNo.setText("");
		tfTitleName.setText("");
		
		if(!tfTitleNo.isEditable()) {
			tfTitleNo.setEditable(true);
		}
		
	}

}
