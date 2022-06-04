package bkmanagements;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import com.mysql.cj.protocol.Resultset;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class bkamange extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
	
		 public void Connect()
		    {
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            con = DriverManager.getConnection("jdbc:mysql://localhost/bookdb", "root","paulsonroot");
		        }
		        catch (ClassNotFoundException ex) 
		        {
		          ex.printStackTrace();
		        }
		        catch (SQLException ex) 
		        {
		        	   ex.printStackTrace();
		        }
	
		    }
		 
	public void table_load()
	{
		try 
		{
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/bookdb","root","paulsonroot");
			Connect();
			PreparedStatement  pst = con.prepareStatement("select * from booktbl");
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bkamange frame = new bkamange();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public bkamange() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel BookName = new JLabel("Book Name");
		BookName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		BookName.setBounds(50, 45, 91, 14);
		contentPane.add(BookName);

		textField = new JTextField();
		textField.setBounds(151, 43, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBounds(28, 22, 260, 155);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel Edition = new JLabel("Edition");
		Edition.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		Edition.setBounds(26, 71, 46, 14);
		panel.add(Edition);

		textField_1 = new JTextField();
		textField_1.setBounds(121, 69, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel Price = new JLabel("Price");
		Price.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		Price.setBounds(26, 129, 46, 14);
		panel.add(Price);

		textField_2 = new JTextField();
		textField_2.setBounds(121, 127, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JButton Save = new JButton("Save");
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookName=textField.getText();
				String edition=textField_1.getText();
				String price=textField_2.getText();
				try {
//					Class.forName("com.mysql.cj.jdbc.Driver");
//					Connection con=DriverManager.getConnection("jdbc:mysql://localhost/bookdb","root","paulsonroot");
					Connect();

					PreparedStatement pst=con.prepareStatement("insert into booktbl(name,edition,price)values(?,?,?)");
					pst.setString(1, bookName);
					pst.setString(2,edition);
					pst.setString(3,price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "inserted successfully");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");



				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		Save.setBounds(92, 189, 89, 23);
		contentPane.add(Save);

		JButton Clear = new JButton("Clear");
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");




			}
		});
		Clear.setBounds(213, 189, 89, 23);
		contentPane.add(Clear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(323, 22, 246, 149);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/bookdb","root","paulsonroot");
			Connect();
			PreparedStatement pst1=con.prepareStatement("select *from booktbl");
			ResultSet rs=pst1.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(28, 236, 283, 67);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel BookID = new JLabel("BookID");
		BookID.setBounds(23, 26, 46, 14);
		panel_1.add(BookID);

		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String id=textField_3.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost/bookdb","root","paulsonroot");
//					Connect();
					PreparedStatement	pst=con.prepareStatement("select name,edition,price from booktbl where id=?");
					pst.setString(1, id);
					ResultSet rs=pst.executeQuery();
					if(rs.next()==true) {
						String name=rs.getString(1);
						String edition=rs.getString(2);
						String price=rs.getString(3);
						textField.setText(name);
						textField_1.setText(edition);
						textField_2.setText(price);
					}else {
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
					}


				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		textField_3.setBounds(101, 23, 86, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);

		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con;
				try {
					String bookName=textField.getText();
					String edition=textField_1.getText();
					String price=textField_2.getText();
					String bid=textField_3 .getText();
//					Class.forName("com.mysql.cj.jdbc.Driver");
//
//					con = DriverManager.getConnection("jdbc:mysql://localhost/bookdb","root","paulsonroot");
					Connect();
//					PreparedStatement	pst=con.prepareStatement("update booktbl set name=?,edition=?,price=? where id=?");
					pst.setString(1, bookName);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Update!!!!!");
					table_load();
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(356, 242, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bid=textField_3.getText();
				try {
//					Class.forName("com.mysql.cj.jdbc.Driver");
//
//					Connection con=DriverManager.getConnection("jdbc:mysql://localhost/bookdb","root","paulsonroot");
					Connect();
					PreparedStatement	pst=con.prepareStatement("delete from booktbl where id=?");
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
					table_load();
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	

			}
		});
		btnNewButton_1.setBounds(464, 242, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(291, 0, 110, 14);
		contentPane.add(lblNewLabel);
	}
}
