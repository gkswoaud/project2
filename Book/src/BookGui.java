
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class BookGui {
	static DefaultTableModel model;
	
	BookGui ()  {
		JFrame frame = new JFrame("도서대여 프로그램");
		frame.setLocation(130, 100);
		frame.setPreferredSize(new Dimension(800,500));
		Container contentPane = frame.getContentPane();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		JButton addBookButton = new JButton("도서등록");
		JButton deleteBookButton = new JButton("도서삭제");
		JButton addMemberButton = new JButton("회원등록");
		JButton deleteMemberButton = new JButton("회원삭제");
		JButton rentalBookButton = new JButton("대출");
		JButton returnBookButton = new JButton("반납");
		JButton rentalListButton = new JButton("대출목록");
		JButton allListButton = new JButton("전체목록");
		JButton memberIndexButton = new JButton("회원목록");
		JTextField title = new JTextField(20);
		JTextField writer = new JTextField(4);
		JTextField publisher = new JTextField(9);
		JTextField code = new JTextField(4);
		JButton indexButton = new JButton("검색");
		panel1.add(addBookButton);
		panel1.add(deleteBookButton);
		panel1.add(addMemberButton);
		panel1.add(deleteMemberButton);
		contentPane.add(panel1, BorderLayout.WEST);
		panel2.add(rentalBookButton);
		panel2.add(returnBookButton);
		panel1.add(rentalListButton);
		panel1.add(allListButton);
		panel1.add(memberIndexButton);
		contentPane.add(panel2, BorderLayout.SOUTH);
		panel3.add(new JLabel("제목"));
		panel3.add(title);
		panel3.add(indexButton);
		contentPane.add(panel3, BorderLayout.NORTH);
		String columNames[] = {"제목", "저자", "출판사", "도서번호", "대여상태"};
		DefaultTableModel model = new DefaultTableModel(columNames, 0);
		JTable table = new JTable(model);
		this.model = model;
		Book_MS_DB.printTable(table); 
		contentPane.add(new JScrollPane(table));
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				addBookButtonGui();
			}
		};
		addBookButton.addActionListener(obj1);
		
		ActionListener obj2 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				deleteBookButtonGui();
			}
		};
		deleteBookButton.addActionListener(obj2);
		
		ActionListener obj3 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				addMemberButtonGui();
			}
		};
		addMemberButton.addActionListener(obj3);
		
		ActionListener obj4 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				deleteMemberButtonGui();
			}
		};
		deleteMemberButton.addActionListener(obj4);
		
		ActionListener obj5 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				rentalBookButtonGui();
			}
		};
		rentalBookButton.addActionListener(obj5);
		
		ActionListener obj6 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				returnBookGui();
			}
		};
		returnBookButton.addActionListener(obj6);
		
		ActionListener obj7 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				Book_MS_DB.rentalListAddRow(model);
			}
		};
		rentalListButton.addActionListener(obj7);
		
		ActionListener obj8 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				Book_MS_DB.allBookDataAddRow(model);
			}
		};
		allListButton.addActionListener(obj8);
		
		ActionListener obj9 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				rentalMemberGui();
			}
		};
		memberIndexButton.addActionListener(obj9);
		
		ActionListener obj10 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String titleStr = title.getText();
				String writerStr = writer.getText();
				String publisherStr = publisher.getText();
				String codeStr = code.getText();
				if (titleStr.equals("") && writerStr.equals("") && publisherStr.equals(""))  {
					if (!codeStr.equals(""))  {
						Book_MS_DB.allRemoveRowData(model);
						Book_MS_DB.rentalBookIndex(codeStr, model);
						return;
					}	
					else  {
						System.out.println("검색조건을 입력해주세요.");
						return;
					}
				}
				Book_MS_DB.allRemoveRowData(model);
				Book_MS_DB.rentalBookIndex(titleStr, writerStr, publisherStr, table);
			}
		};
		indexButton.addActionListener(obj10);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void addBookButtonGui()  { 
		JFrame frame = new JFrame("도서등록");
		frame.setLocation(130,100);
		frame.setPreferredSize(new Dimension(400,200));
		Container contentPane = frame.getContentPane();
		JTextField bookName = new JTextField();
		JTextField writer = new JTextField();
		JTextField publisher = new JTextField();
		JTextField code = new JTextField();
		JButton button = new JButton("확인");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.setLayout(new GridLayout(4, 2));
		contentPane.add(panel2, BorderLayout.NORTH);
		panel2.add(new JLabel("도서등록"));
		panel1.add(new JLabel("제목"));
		panel1.add(bookName);
		panel1.add(new JLabel("저자"));
		panel1.add(writer);
		panel1.add(new JLabel("출판사"));
		panel1.add(publisher);
		panel1.add(new JLabel("도서번호"));
		panel1.add(code);
		contentPane.add(panel1);
		contentPane.add(button, BorderLayout.SOUTH);
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String str1 = bookName.getText();
				String str2 = writer.getText();
				String str3 = publisher.getText();
				String str4 = code.getText();
				Book_MS_DB.bookSave_DB(str1, str2, str3, str4);
				frame.setVisible(false);
			}
		};
		button.addActionListener(obj1);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void deleteBookButtonGui()  { 
		JFrame frame = new JFrame("도서삭제");
		frame.setLocation(130,100);
		frame.setPreferredSize(new Dimension(400,90));
		Container contentPane = frame.getContentPane();
		JTextField code = new JTextField();
		JButton button = new JButton("확인");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(new JLabel("도서번호"));
		panel.add(code);
		contentPane.add(panel);
		contentPane.add(button, BorderLayout.SOUTH);
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String codeStr = code.getText();
				Book_MS_DB.bookDelete_DB(codeStr);
				frame.setVisible(false);
			}
		};
		button.addActionListener(obj1);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void addMemberButtonGui()  { 
		JFrame frame = new JFrame("회원등록");
		frame.setLocation(130, 100);
		frame.setPreferredSize(new Dimension(400,170));
		Container contentPane = frame.getContentPane();
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		contentPane.add(panel2, BorderLayout.NORTH);
		JTextField memberName = new JTextField();
		JTextField address = new JTextField();
		JTextField phone = new JTextField();
		JButton button = new JButton("확인");
		panel2.add(new JLabel("회원등록"));
		panel.add(new JLabel("이름"));
		panel.add(memberName);
		panel.add(new JLabel("주소"));
		panel.add(address);
		panel.add(new JLabel("전화번호"));
		panel.add(phone);
		contentPane.add(panel);
		contentPane.add(button, BorderLayout.SOUTH);
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String memberNameStr = memberName.getText();
				String addressStr = address.getText();
				String phoneStr = phone.getText();
				Book_MS_DB.memberSave_DB(memberNameStr, addressStr, phoneStr);
				frame.setVisible(false);
			}
		};
		button.addActionListener(obj1);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void deleteMemberButtonGui()  { 
		JFrame frame = new JFrame("회원삭제");
		frame.setLocation(130, 100);
		frame.setPreferredSize(new Dimension(400,90));
		Container contentPane = frame.getContentPane();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		JButton button = new JButton("확인");
		JTextField phone = new JTextField();
		panel.add(new JLabel("전화번호"));
		panel.add(phone);
		contentPane.add(panel);
		contentPane.add(button, BorderLayout.SOUTH);
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String phoneStr = phone.getText();
				Book_MS_DB.memberDelete_DB(phoneStr);
				frame.setVisible(false);
			}
		};
		button.addActionListener(obj1);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void rentalBookButtonGui()  {
		JFrame frame = new JFrame("대출(도서)");
		frame.setLocation(130, 100);
		frame.setPreferredSize(new Dimension(700,200));
		Container contentPane = frame.getContentPane();
		JTextField title = new JTextField(15);
		JTextField writer = new JTextField(4);
		JTextField publisher = new JTextField(6);
		JButton indexButton = new JButton("검색");
		JButton button = new JButton("확인");
		JPanel panel = new JPanel();
		panel.add(new JLabel("제목"));
		panel.add(title);
		panel.add(indexButton);
		contentPane.add(panel, BorderLayout.NORTH);
		String colNames[] = {"제목", "저자", "출판사", "도서번호", "대여상태"};
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);
		contentPane.add(new JScrollPane(table));
		contentPane.add(button, BorderLayout.SOUTH);
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				int count = table.getRowCount();
				for (int cnt = 0; cnt < count; cnt++)  {
					model.removeRow(0);
				}
				Book_MS_DB.rentalBookIndex(title.getText(), writer.getText(), publisher.getText(), table);
			}
		};
		indexButton.addActionListener(obj1);
		
		ActionListener obj2 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String selectBook[] = Book_MS_DB.getTableData(table);
				if (selectBook == null)  {
					System.out.println("도서를 선택해주세요.");
					return;
				}
				else if (selectBook[4].equals("대여중"))  {
					System.out.println("이미 대여중인 도서입니다.");
					return;
				}
				frame.setVisible(false);
				rentalMemberGui(selectBook);
			}
		};
		button.addActionListener(obj2);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void rentalMemberGui(String selectBook[])  { 
		JFrame frame = new JFrame("대출(회원)");
		frame.setLocation(130, 100);
		frame.setPreferredSize(new Dimension(700,200));
		Container contentPane = frame.getContentPane();
		JTextField name = new JTextField(15);
		JButton indexButton = new JButton("검색");
		JButton button = new JButton("확인");
		JPanel panel = new JPanel();
		panel.add(new JLabel("이름"));
		panel.add(name);
		panel.add(indexButton);
		contentPane.add(panel, BorderLayout.NORTH);
		String colNames[] = {"이름", "주소", "전화번호"};
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);
		contentPane.add(new JScrollPane(table));
		contentPane.add(button, BorderLayout.SOUTH);
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				int count = table.getRowCount();
				for (int cnt = 0; cnt < count; cnt++)  {
					model.removeRow(0);
				}
				String nameStr = name.getText();
				Book_MS_DB.rentalMemberIndex(nameStr, table);
			}
		};
		indexButton.addActionListener(obj1);
		
		ActionListener obj2 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String selectMember[] = Book_MS_DB.getTableData(table);
				boolean b = Book_MS_DB.mrb_list(selectMember[2]);
				if (!b)  {
					System.out.println("대여 불가");
					return;
				}
				Book_MS_DB.rental_DB(selectBook, selectMember);
				frame.setVisible(false);
			}
		};
		button.addActionListener(obj2);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void returnBookGui()  {
		JFrame frame = new JFrame("반납");
		frame.setLocation(130,100);
		frame.setPreferredSize(new Dimension(300,90));
		Container contentPane = frame.getContentPane();
		JTextField code = new JTextField();
		JButton button = new JButton("확인");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(new JLabel("도서번호"));
		panel.add(code);
		contentPane.add(panel);
		contentPane.add(button, BorderLayout.SOUTH);
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String codeStr = code.getText();
				Book_MS_DB.returnBook_DB(codeStr);
				frame.setVisible(false);
			}
		};
		button.addActionListener(obj1);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void rentalMemberGui()  { 
		JFrame frame = new JFrame("회원목록");
		frame.setLocation(130, 100);
		frame.setPreferredSize(new Dimension(900,400));
		Container contentPane = frame.getContentPane();
		JTextField name = new JTextField(15);
		JButton indexButton = new JButton("검색");
		JButton button = new JButton("전체보기");
		JPanel panel = new JPanel();
		panel.add(new JLabel("이름"));
		panel.add(name);
		panel.add(indexButton);
		contentPane.add(panel, BorderLayout.NORTH);
		String colNames[] = {"이름", "주소", "전화번호", "대여도서1", "대여도서2", "대여도서3"};
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		Book_MS_DB.allMemberDataAddRow(model);
		JTable table = new JTable(model);
		contentPane.add(new JScrollPane(table));
		contentPane.add(button, BorderLayout.SOUTH);
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				int count = table.getRowCount();
				for (int cnt = 0; cnt < count; cnt++)  {
					model.removeRow(0);
				}
				String nameStr = name.getText();
				Book_MS_DB.rentalMemberIndex(nameStr, table);
			}
		};
		indexButton.addActionListener(obj1);
		
		ActionListener obj2 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				Book_MS_DB.allRemoveRowData(model);
				Book_MS_DB.allMemberDataAddRow(model);
			}
		};
		button.addActionListener(obj2);
		
		frame.pack();
		frame.setVisible(true);
	}
}
