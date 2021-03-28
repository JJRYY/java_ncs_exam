package java_ncs_exam.control;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import java_ncs_exam.content.AbstractContentPanel;
import java_ncs_exam.content.AbstractCustomTablePanel;
import java_ncs_exam.content.TitlePanel;
import java_ncs_exam.content.TitleTable;
import java_ncs_exam.dto.Title;
import java_ncs_exam.service.TitleService;

@SuppressWarnings("serial")
public class Management extends AbstractManagerUI<Title> {
	
	private TitleService service;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Management frame = new Management();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Management() {
		setTitle("직책 관리");
	}
	
	@Override
	protected void setService() {
		service = new TitleService();
	}

	@Override
	protected void tableLoadData() {
		((TitleTable)pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<Title> createContentPanel() {
		return new TitlePanel();
	}

	@Override
	protected AbstractCustomTablePanel<Title> createTablePanel() {
		return new TitleTable();
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		Title updateTitle = pList.getItem();
		pContent.setItem(updateTitle);
		btnAdd.setText("수정");
		
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Title delTitle = pList.getItem();
		service.removeTitle(delTitle);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delTitle + "이(가) 삭제 되었습니다.");
		
	}

	@Override
	protected void actionperformedBtnUpdate(ActionEvent e) {
		Title beforeTitle = pList.getItem();
		Title updateTitle = pContent.getItem();
		service.updateTitle(updateTitle);
		pList.loadData();
		btnAdd.setText("추가");
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, beforeTitle + "이(가) "+ updateTitle + "로 변경 되었습니다.");
		
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title title = pContent.getItem();
		service.addTitle(title);
		JOptionPane.showMessageDialog(null, title + "이(가) 추가 되었습니다.");
		pList.loadData();
		pContent.clearTf();
		
	}

}
