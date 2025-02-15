package com.market.page;

import javax.swing.*;
import com.market.bookitem.Book;
import com.market.bookitem.BookInIt;
import com.market.cart.Cart;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class CartAddItemPage extends JPanel {
	
	ImageIcon imageBook;
	int mSelectRow = 0;
	
	Cart mCart;
	
	public CartAddItemPage(JPanel panel, Cart cart) {
		Font ft;
		ft = new Font("함초롬돋움", Font.BOLD, 15);
		
		setLayout(null);
		
		Rectangle rect = panel.getBounds();
		System.out.println(rect);
		setPreferredSize(rect.getSize());
		
		mCart = cart;
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(20, 0, 300, 400);
		imageBook = new ImageIcon("./images/ISBN1234.jpg");
		imageBook.setImage(imageBook.getImage().getScaledInstance(250, 300, Image.SCALE_DEFAULT));
		JLabel label = new JLabel(imageBook);
		imagePanel.add(label);
		add(imagePanel);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(300, 0, 700, 400);
		add(tablePanel);
		
		ArrayList<Book> booklist = BookInIt.getmBookList();
		Object[] tableHeader = {"도서ID", "도서명", "가격", "저자", "설명", "분야", "출판일"};
		Object[][] content = new Object[booklist.size()][tableHeader.length];
		for (int i = 0; i<booklist.size(); i++) {
			Book bookitem = booklist.get(i);
			content[i][0] = bookitem.getBookId();
			content[i][1] = bookitem.getName();
			content[i][2] = bookitem.getUnitPrice();
			content[i][3] = bookitem.getAuthor();
			content[i][4] = bookitem.getDescription();
			content[i][5] = bookitem.getCategory();
			content[i][6] = bookitem.getReleaseDate();
		}
		
		JTable bookTable = new JTable(content, tableHeader);
		bookTable.setRowSelectionInterval(0, 0);
		bookTable.getSelectedColumn();
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setPreferredSize(new Dimension(600, 350));
		jScrollPane.setViewportView(bookTable);
		tablePanel.add(jScrollPane);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 400, 1000, 400);
		add(buttonPanel);
		
		JLabel buttonLabel = new JLabel("장바구니에 담기");
		buttonLabel.setFont(ft);
		JButton addButton = new JButton();
		addButton.add(buttonLabel);
		buttonPanel.add(addButton);
		
		bookTable.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int row = bookTable.getSelectedRow();
				int col = bookTable.getSelectedColumn();
				mSelectRow = row;
				Object value = bookTable.getValueAt(row, 0);
				String str = value + ".jpg";
				imageBook = new ImageIcon("./images/"+str);
					imageBook.setImage(imageBook.getImage().getScaledInstance(250,  300,  Image.SCALE_DEFAULT));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Book> booklist = BookInIt.getmBookList();
				int select = JOptionPane.showConfirmDialog(addButton, "장바구니에 추가하겠습니까?");
				if(select == 0) {
					int numId = mSelectRow;
					if (!isCartInBook(booklist.get(numId).getBookId())) {
						mCart.insertBook(booklist.get(numId));
					}
					JOptionPane.showMessageDialog(addButton,  "추가했습니다");
				}
			}
		});
	}
	public boolean isCartInBook(String bookId) {
		return mCart.isCartInBook(bookId);
	}

}
