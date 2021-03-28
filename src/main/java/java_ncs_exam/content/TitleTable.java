package java_ncs_exam.content;

import javax.swing.SwingConstants;

import java_ncs_exam.dto.Title;
import java_ncs_exam.exception.NotSelectedException;
import java_ncs_exam.service.TitleService;

@SuppressWarnings("serial")
public class TitleTable extends AbstractCustomTablePanel<Title> {
	private TitleService service;

	@Override
	public Title getItem() {
		int row = table.getSelectedRow();
		if (row == -1) {
			throw new NotSelectedException();
		}
		String title = (String) table.getValueAt(row, 0);
		String subTitle = title.replace("T", "");
		int t = Integer.parseInt(subTitle);

		return list.get(list.indexOf(new Title(t)));
	}

	@Override
	public void initList() {
		list = service.showTitles();
	}

	public void setService(TitleService service) {
		this.service = service;
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1);
		setTableCellWidth(100, 250);

	}

	@Override
	public Object[] toArray(Title t) {
		String tNo = String.format("T%03d", t.gettNo());
		
		return new Object[] { tNo, t.gettName() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "직책번호", "직책명" };
	}

}
