package BTL;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import java.util.logging.Level;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.Label;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class main_Frame implements ActionListener,MenuListener,KeyListener{

	private static JFrame frame;
	private JMenuBar mb = new JMenuBar();
	private String Filepath ="";
	private JPanel StudentList ;
	private JPanel StudentFilter ;
	private JPanel StudentIn4 ;
	public  static JTable table;
	private JPanel FuntionPanel;
	public static QuanlySV list = new QuanlySV();
	private int DeleteID ;
	private JTextPane IDText;
	private JTextPane NameText;
	private JTextPane BirthPlaceText;
	private JTextPane BirthText;
	private JTextPane MathText;
	private JTextPane PhysicsText;
	private JTextPane ChemistryText;
	private JTextPane TotalText;
	private JRadioButton Female_radiobtn;
	private JRadioButton Male_radiobtn ;
	private int Selectrow =-1;
	private String SavePath;
	private TableModel model;
	private TableRowSorter<TableModel> sorter;
	private JScrollPane scrollPane ;
	private int checkSave = 0,checkOpen=0;
	private Home tempQue = new Home();
	private JButton okbtn;
	private JButton btnCancel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run()  {
				try {
					@SuppressWarnings("unused")
					main_Frame window = new main_Frame();
					main_Frame.frame.setVisible(true);
					try {
			            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			            Logger.getLogger(main_Frame.class.getName()).log(Level.SEVERE, null, ex);
			        }
			        SwingUtilities.updateComponentTreeUI(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main_Frame() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	//Tao thanh Menu o tren
	public void Create_MenuBar()
	{
		JMenu file,about;
		JMenuItem open,save,exit;
		JMenuItem infor;
		//khoi tao nut tren thanh menu
		file = new JMenu("File");
		mb.add(file);
		about = new JMenu("About");
		mb.add(about);
		//Them cac lua chon vao nut
		//Nut File
		//Item Open
		open = new JMenuItem("Open");
		open.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Openicon.png")));
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int indexofextension;
				String extension = "";
				JFileChooser fc = new JFileChooser(".");
				fc.showOpenDialog(null); 	// mo cua so de chon file
				File f = fc.getSelectedFile();	// lay file dc chon
				Filepath =  f.getAbsolutePath();	// lay duong dan den file duoc chon
				indexofextension = Filepath.lastIndexOf(".");
				extension =  Filepath.substring(indexofextension+1);
				try {
					list.GetData(Filepath);
					if(extension.equals("txt"))
					{
						checkOpen =1;
						JOptionPane.showMessageDialog(null,"Chon File Thanh Cong ","Thong Bao",JOptionPane.PLAIN_MESSAGE);
						StudentFilter.setVisible(true);
						StudentIn4.setVisible(true);
						StudentList.setVisible(true);
						FuntionPanel.setVisible(true);
						updatetable();
					}
					else
						JOptionPane.showMessageDialog(null,"Chuong trinh chi chay voi file .txt ","Thong Bao",JOptionPane.WARNING_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Thong Bao",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		file.add(open);
		//Item Save
		save = new JMenuItem("Save");
		save.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Saveicon.png")));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SavePath = Filepath;
				try
				{
					if(list.size()<1)
					{
						JOptionPane.showMessageDialog(null,"Chua co du lieu Khong the luu ","Thong Bao",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						list.Save(SavePath);
						checkSave = 1;
						JOptionPane.showMessageDialog(null,"Luu File Thanh Cong ","Thong Bao",JOptionPane.PLAIN_MESSAGE);
					}
				}
				finally
				{
					
				}
			}
		});
		file.add(save);
		file.addSeparator();
		exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(checkSave == 0 && checkOpen ==1)
			        {
			        	int yes = JOptionPane.showConfirmDialog(null,"Ban chua luu lai ket qua ! Ban co muon luu roi thoat Khong ?");
			        	if(yes ==  JOptionPane.YES_OPTION)
			        	{
			        		list.Save(Filepath);
			        		frame.dispose();
			        		
			        	}
			        	else if(yes == JOptionPane.CLOSED_OPTION || yes == JOptionPane.CANCEL_OPTION) 
			        		 return;
			        	else frame.dispose();
			        	
			        }
			        else
			        {
			        	int PromptResult = JOptionPane.showConfirmDialog(null,"Ban Co Muon Thoat Khong?","Messege",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				        if(PromptResult==JOptionPane.YES_OPTION)
				            frame.dispose();
			        }
			}
		});
		file.add(exit);
		//Nut About
		infor = new JMenuItem("Click me For More");
		infor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Nhom 13 Java CNTT3 K59  \n"
						+ "- Nguyen Huu Thao\n- Dao Anh Khoa\n- Luong Thi Huong\n- Nguyen Duc Vinh\n- Pham Minh Tri","Cac Thanh vien trong Nhom\n==",JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		about.add(infor);	
	}
	//Tao bang FILTER  ===========================================================================================================================
	public void Create_Student_Filter()
	{
		StudentFilter = new JPanel();
		StudentFilter.setBounds(10, 10, 764, 97);
		frame.getContentPane().add(StudentFilter);
		Border s = BorderFactory.createTitledBorder("Student Filter");
		StudentFilter.setBorder(s);
		StudentFilter.setLayout(null);
		
		Label label = new Label("BirthPlace");
		label.setBackground(UIManager.getColor("Button.shadow"));
		label.setForeground(Color.BLACK);
		label.setAlignment(Label.CENTER);
		label.setBounds(35, 42, 73, 22);
		StudentFilter.add(label);
		
		JTextPane Birthtxt = new JTextPane();
		Birthtxt.setBounds(114, 42, 114, 22);
		StudentFilter.add(Birthtxt);
		Label label_1 = new Label("StudentID\r\n");
		label_1.setBackground(UIManager.getColor("Button.shadow"));
		label_1.setAlignment(Label.CENTER);
		label_1.setBounds(290, 42, 73, 22);
		StudentFilter.add(label_1);
		
		JTextPane IDtxt = new JTextPane();
		IDtxt.setBounds(386, 42, 114, 22);
		StudentFilter.add(IDtxt);
		//=================================== sorter tren table
		JButton filterbtn = new JButton("Filter");
		filterbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textFindID =  IDtxt.getText();
				String textFindBirth =  Birthtxt.getText().toUpperCase();
				textFindID.trim();
				textFindBirth.trim();
				textFindBirth.toLowerCase();
				
				table.setRowSorter(sorter);
				
				if(textFindID.length()==0 &&textFindBirth.length()==0) {
					return;
            }
			else {
               try {
            	   if(textFindID.length()==0)		//Tim theo Que quan
            		   sorter.setRowFilter(RowFilter.regexFilter(textFindBirth,3));
            	   else if(textFindBirth.length()==0) //Tim theo ID
            		   sorter.setRowFilter(RowFilter.regexFilter(textFindID,1));
            	   else
            	   {
            		   List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
       				   textFindBirth = String.format("(?i)%s", textFindBirth);
       				   filters.add(RowFilter.regexFilter(textFindBirth , 3));
       				   filters.add(RowFilter.regexFilter(textFindID , 1));
       				   sorter.setRowFilter(RowFilter.andFilter(filters));
            		  // sorter.setRowFilter(RowFilter.regexFilter(textFindBirth,3));
            		  // sorter.setRowFilter(RowFilter.regexFilter(textFindID,1));
            	   }
               } catch(PatternSyntaxException pse) {
            	   JOptionPane.showMessageDialog(null,"khong tim thay","Really Important Message",JOptionPane.PLAIN_MESSAGE);
               }
             }
			}
		});
		filterbtn.setBounds(530, 63, 89, 23);
		StudentFilter.add(filterbtn);
		
		JButton btnNewButton = new JButton("Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				IDtxt.setText("");
				Birthtxt.setText("");
				sorter.setRowFilter(null);	 
		}});
		btnNewButton.setBounds(651, 63, 89, 23);
		StudentFilter.add(btnNewButton);
	
	}
	// ================================== Tao Bang Thong Tin Thi Sinh ==============================================================================================
	public void Create_Student_In4()
	{
		StudentIn4 = new JPanel();
		StudentIn4.setBounds(10, 330, 764, 217);
		frame.getContentPane().add(StudentIn4);
		Border s = BorderFactory.createTitledBorder("Student Information");
		StudentIn4.setBorder(s);
		StudentIn4.setLayout(null);
		
		JLabel lblStuin4ID = new JLabel("ID");
		lblStuin4ID.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStuin4ID.setBackground(Color.LIGHT_GRAY);
		lblStuin4ID.setHorizontalAlignment(SwingConstants.CENTER);
		lblStuin4ID.setBounds(10, 22, 84, 21);
		StudentIn4.add(lblStuin4ID);
		
		JLabel Namelbl = new JLabel("Name");
		Namelbl.setHorizontalAlignment(SwingConstants.CENTER);
		Namelbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		Namelbl.setBackground(Color.LIGHT_GRAY);
		Namelbl.setBounds(10, 65, 84, 21);
		StudentIn4.add(Namelbl);
		
		JLabel BirthPlacelbl = new JLabel("Birth Place");
		BirthPlacelbl.setHorizontalAlignment(SwingConstants.CENTER);
		BirthPlacelbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		BirthPlacelbl.setBackground(Color.LIGHT_GRAY);
		BirthPlacelbl.setBounds(10, 108, 84, 21);
		StudentIn4.add(BirthPlacelbl);
		
		JLabel DateOfBirthlbl = new JLabel("Birth");
		DateOfBirthlbl.setHorizontalAlignment(SwingConstants.CENTER);
		DateOfBirthlbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		DateOfBirthlbl.setBackground(Color.LIGHT_GRAY);
		DateOfBirthlbl.setBounds(10, 151, 84, 21);
		StudentIn4.add(DateOfBirthlbl);
		
		IDText = new JTextPane();
		IDText.setEditable(false);
		IDText.setBounds(114, 22, 159, 21);
		StudentIn4.add(IDText);

		NameText = new JTextPane();
		NameText.setEditable(false);
		NameText.setBounds(114, 65, 159, 21);
		StudentIn4.add(NameText);
		
		BirthPlaceText = new JTextPane();
		BirthPlaceText.setEditable(false);
		BirthPlaceText.setBounds(114, 108, 159, 21);
		StudentIn4.add(BirthPlaceText);
		
		BirthText = new JTextPane();
		BirthText.setEditable(false);
		BirthText.setBounds(114, 151, 159, 21);
		StudentIn4.add(BirthText);
		
		JLabel lblMath = new JLabel("Math");
		lblMath.setHorizontalAlignment(SwingConstants.CENTER);
		lblMath.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMath.setBackground(Color.LIGHT_GRAY);
		lblMath.setBounds(283, 22, 84, 21);
		StudentIn4.add(lblMath);
		
		MathText = new JTextPane();
		MathText.setEditable(false);
		MathText.setBounds(377, 22, 159, 21);
		StudentIn4.add(MathText);
		
		JLabel Physics = new JLabel("Physics");
		Physics.setHorizontalAlignment(SwingConstants.CENTER);
		Physics.setFont(new Font("Tahoma", Font.BOLD, 13));
		Physics.setBackground(Color.LIGHT_GRAY);
		Physics.setBounds(283, 65, 84, 21);
		StudentIn4.add(Physics);
		
		PhysicsText = new JTextPane();
		PhysicsText.setEditable(false);
		PhysicsText.setBounds(377, 65, 159, 21);
		StudentIn4.add(PhysicsText);
		
		JLabel Chemistrylbl = new JLabel("Chemistry");
		Chemistrylbl.setHorizontalAlignment(SwingConstants.CENTER);
		Chemistrylbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		Chemistrylbl.setBackground(Color.LIGHT_GRAY);
		Chemistrylbl.setBounds(283, 108, 84, 21);
		StudentIn4.add(Chemistrylbl);
		
		ChemistryText = new JTextPane();
		ChemistryText.setEditable(false);
		ChemistryText.setBounds(377, 108, 159, 21);
		StudentIn4.add(ChemistryText);
		
		JLabel Total = new JLabel("Total");
		Total.setHorizontalAlignment(SwingConstants.CENTER);
		Total.setFont(new Font("Tahoma", Font.BOLD, 13));
		Total.setBackground(Color.LIGHT_GRAY);
		Total.setBounds(283, 151, 84, 21);
		StudentIn4.add(Total);
		
		TotalText = new JTextPane();
		TotalText.setEditable(false);
		TotalText.setBounds(377, 151, 159, 21);
		StudentIn4.add(TotalText);

		JLabel lblSex = new JLabel("SEX");
		lblSex.setHorizontalAlignment(SwingConstants.CENTER);
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSex.setBackground(Color.LIGHT_GRAY);
		lblSex.setBounds(546, 22, 84, 21);
		StudentIn4.add(lblSex);
		
		Male_radiobtn = new JRadioButton("Male");
		Male_radiobtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Female_radiobtn.setSelected(false);
			}
		});
		Male_radiobtn.setBounds(575, 50, 55, 23);
		StudentIn4.add(Male_radiobtn);

		Female_radiobtn = new JRadioButton("FeMale");
		Female_radiobtn.setBounds(575, 78, 69, 23);
		Female_radiobtn.addMouseListener(new MouseAdapter()
		{
				public void mouseClicked(MouseEvent e) {
					Male_radiobtn.setSelected(false);
				}
		});
		StudentIn4.add(Female_radiobtn);
	}
	//Tao Bang danh sach Thi Sinh=============================================================================================================
	public void Create_List_Student()
	{
		StudentList = new JPanel();
		StudentList.setBounds(10, 118, 764, 205);
		frame.getContentPane().add(StudentList);
		Border s = BorderFactory.createTitledBorder("List Student");
		StudentList.setBorder(s);
		StudentList.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 22, 744, 172);
		StudentList.add(scrollPane);
		table();
		model();
		//them sorter vao table ========================================================================================================================================
		sorter = new TableRowSorter<TableModel>(model);
		sorter.setSortable(0, false);	//tat sort o cot 0 va 4 
		sorter.setSortable(4, false);
		table.setModel(model);
		table.setRowSorter(sorter);
		sorter.addRowSorterListener(new RowSorterListener() {
		    @Override
		    public void sorterChanged(RowSorterEvent evt) {
		        int indexOfNoColumn = 0;
		        for (int i = 0; i < table.getRowCount(); i++) {
		            table.setValueAt(i + 1, i, indexOfNoColumn);
		        }
		    }
		});
		tablesetsize();
		scrollPane.setViewportView(table);
	}
	//tao PANEL chuc nang =======================================================================================================================
	public void Create_Funtion()

	{
		FuntionPanel = new JPanel();
		FuntionPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		FuntionPanel.setBounds(10, 552, 764, 31);
		frame.getContentPane().add(FuntionPanel);
		
		JButton EditBtn = new JButton("EDIT");
		EditBtn.setBounds(184, 6, 91, 23);
		EditBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Selectrow==-1)
					JOptionPane.showMessageDialog(null, "Can phai chon 1 doi tuong tren bang truoc ");
				else
				{
					JOptionPane.showMessageDialog(null, "Edit on ");
					IDText.setEditable(false);
					NameText.setEditable(true);
					BirthPlaceText.setEditable(true);
					BirthText.setEditable(true);
					MathText.setEditable(true);
					PhysicsText.setEditable(true);
					ChemistryText.setEditable(true);
				
				}

			}
		});
		FuntionPanel.setLayout(null);
		FuntionPanel.add(EditBtn);
		
		JButton DeleteBtn = new JButton("DELETE");
		DeleteBtn.setBounds(315, 6, 91, 23);
		//EVENT DELETE BUTTON ========================================================================================
		DeleteBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	

			
					try {
						if(Selectrow ==-1)
							JOptionPane.showMessageDialog(null, "Can phai chon 1 doi tuong tren bang truoc ");
						else
						{
							int PromptResult = JOptionPane.showConfirmDialog(null,"Xoa sinh vien?","Messege",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
					        if(PromptResult==JOptionPane.YES_OPTION)
					        {
							IDText.setText("");
							NameText.setText("");
							BirthPlaceText.setText("");
							BirthText.setText("");
							MathText.setText("");
							PhysicsText.setText("");
							ChemistryText.setText("");
							TotalText.setText("");
							if(Male_radiobtn.isSelected())
								Male_radiobtn.setSelected(false);
							if(Female_radiobtn.isSelected())
								Female_radiobtn.setSelected(false);
							Selectrow = -1;
							list.Delete(DeleteID);
							updatetable();
					        }
					        else return;
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,  e1.getMessage(), "Message ", JOptionPane.WARNING_MESSAGE);

				}
			}
		});
		
		JButton AddBtn = new JButton("INSERT");
		AddBtn.setBounds(55, 6, 91, 23);
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addStudentform window = new addStudentform();
					window.frame.setVisible(true);	
					window.frame.setLocationRelativeTo(null);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally
				{
					try {
						updatetable();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		FuntionPanel.add(AddBtn);
		FuntionPanel.add(DeleteBtn);
		okbtn = new JButton("OK");
		okbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sex;
				String text =  BirthPlaceText.getText().trim().toUpperCase();
				int indextable = table.getSelectedRow();		//lay vi tri Hang duoc chon
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Student temp = list.getStudent(indextable); 	//lay du lieu tu hang duoc chon cho vao bien temp
				int index = list.getIndex(temp);
				tempQue = new Home(text);	// lay vi tri cua bien temp trong list du lieu
				tempQue.setHome_ID(temp.getHID());				//mot bien HOME tam thoi de chinh sua
				tempQue.setHome_name(temp.getHname());
				if(temp.checkque(BirthPlaceText.getText()) == false)
				{
					tempQue.setHome_name(BirthPlaceText.getText().toUpperCase());
					Student.qlhome.add(tempQue);
				}
				else
				{
					int id = Student.qlhome.getID(BirthPlaceText.getText().toUpperCase());
					tempQue.setHome_ID(id);
					
				}
				if(IDText.getText().equals("") ||NameText.getText().equals("") ||BirthPlaceText.getText().equals("") || BirthText.getText().equals("") || MathText.getText().equals("")
						||PhysicsText.getText().equals("")||ChemistryText.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Can phai dien day du cac thong tin ", "Message ", JOptionPane.WARNING_MESSAGE);
				else if(temp.checkbirth(BirthText.getText()) == false)
					JOptionPane.showMessageDialog(null, "Ngay sinh phai dung dinh dang dd/mm/yyyy", "Message ", JOptionPane.WARNING_MESSAGE);
				else if(BirthPlaceText.getText().matches("\\d"))
					JOptionPane.showMessageDialog(null, "Que quan khong hop le ! , khong chua chu so ", "Message ", JOptionPane.WARNING_MESSAGE);
				else if(!Male_radiobtn.isSelected()&&!Female_radiobtn.isSelected())
					JOptionPane.showMessageDialog(null, "Can Phai xac dinh gioi tinh  ", "Message ", JOptionPane.WARNING_MESSAGE);
				else if(temp.checkvalidMark(Float.parseFloat(MathText.getText())) == false)
					JOptionPane.showMessageDialog(null, "Diem Toan khong hop le", "Message ", JOptionPane.WARNING_MESSAGE);
				else if(temp.checkvalidMark(Float.parseFloat(PhysicsText.getText())) == false)
					JOptionPane.showMessageDialog(null, "Diem Ly khong hop le", "Message ", JOptionPane.WARNING_MESSAGE);
				else if(temp.checkvalidMark(Float.parseFloat(ChemistryText.getText())) == false)
					JOptionPane.showMessageDialog(null, "Diem Hoa khong hop le", "Message ", JOptionPane.WARNING_MESSAGE);
				else
				{
					temp.setID(Integer.parseInt(IDText.getText()));
					temp.setName(NameText.getText());	
					temp.SetHOME(tempQue);
					temp.setDbirth(BirthText.getText());
					if(Male_radiobtn.isSelected())
						temp.setGender(Boolean.parseBoolean("true"));
					else
						temp.setGender(Boolean.parseBoolean("false"));
					temp.setMath(Float.parseFloat(MathText.getText()));
					temp.setPhysics(Float.parseFloat(PhysicsText.getText()));	
					temp.setChemistry(Float.parseFloat(ChemistryText.getText()));
					list.changeStudent(temp, index);
				
					sex = "";
					model.setValueAt(Integer.parseInt(IDText.getText()), Selectrow, 1);
					model.setValueAt(NameText.getText(), Selectrow, 2);
					model.setValueAt(BirthPlaceText.getText(), Selectrow, 3);
					model.setValueAt(BirthText.getText(), Selectrow, 4);
					model.setValueAt(Float.parseFloat(MathText.getText()), Selectrow, 6);
					model.setValueAt(Float.parseFloat(PhysicsText.getText()), Selectrow, 7);
					model.setValueAt(Float.parseFloat(ChemistryText.getText()), Selectrow, 8);
					if(Female_radiobtn.isSelected())
						sex ="Nu";
					else
						sex ="Nam";
					model.setValueAt(sex, Selectrow, 5);
					table.setModel(model);
			}					
			}
		});
		okbtn.setBounds(451, 6, 91, 23);
		FuntionPanel.add(okbtn);
		
		btnCancel = new JButton("CANCEL");
		btnCancel.setBounds(591, 6, 91, 23);
		FuntionPanel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener ()
				{
			public void actionPerformed(ActionEvent e) {
					IDText.setText("");
					NameText.setText("");
					BirthPlaceText.setText("");
					BirthText.setText("");
					MathText.setText("");
					PhysicsText.setText("");
					ChemistryText.setText("");
					TotalText.setText("");
					ChemistryText.setEditable(false);
					if(Male_radiobtn.isSelected())
						Male_radiobtn.setSelected(false);
					if(Female_radiobtn.isSelected())
						Female_radiobtn.setSelected(false);
					NameText.setEditable(false);
					BirthPlaceText.setEditable(false);
					BirthText.setEditable(false);
					MathText.setEditable(false);
					PhysicsText.setEditable(false);
					ChemistryText.setEditable(false);
				}
			});	
	}
	//Khai bao do rong cua tung cot trong Bang
	public void tablesetsize()
	{
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(34);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(65);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(115);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(7).setPreferredWidth(70);
		table.getColumnModel().getColumn(8).setResizable(false);
		table.getColumnModel().getColumn(8).setPreferredWidth(70);
		table.getTableHeader().setReorderingAllowed(false);
	}
	//Tao Bang + event trong bang
	public void table()
	{
		table = new JTable();
		//ENVENT CLICK TABLEs
		table.addMouseListener(new MouseAdapter() {	
		//GET THong tin tu tABLE ========================================================================================================== REAL
			public void mouseClicked(MouseEvent e) {	
				
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Selectrow =  table.getSelectedRow();								//lay hang duoc chon	
				Selectrow =  table.convertRowIndexToModel(Selectrow);				// chuyen hang duoc chon theo hien thi cua bang
				DeleteID = (int) model.getValueAt(Selectrow, 1);
				IDText.setText(model.getValueAt(Selectrow, 1).toString());
				NameText.setText(model.getValueAt(Selectrow, 2).toString());
				BirthPlaceText.setText(model.getValueAt(Selectrow, 3).toString());
				BirthText.setText(model.getValueAt(Selectrow, 4).toString());
				MathText.setText(model.getValueAt(Selectrow, 6).toString());
				PhysicsText.setText(model.getValueAt(Selectrow, 7).toString());
				ChemistryText.setText(model.getValueAt(Selectrow,8).toString());
				String sex = model.getValueAt(Selectrow, 5).toString();
				if(sex.equals("Nam"))
				{
					if(Female_radiobtn.isSelected())
						Female_radiobtn.setSelected(false);
					Male_radiobtn.setSelected(true);
				}
				else
				{
					if(Male_radiobtn.isSelected())
						Male_radiobtn.setSelected(false);
					Female_radiobtn.setSelected(true);
				}
				float math = Float.parseFloat(MathText.getText());
				float phy  = Float.parseFloat(PhysicsText.getText());
				float chem  = Float.parseFloat(ChemistryText.getText());
				float total =  math + phy +chem;
				TotalText.setText(Float.toString(total));
			}
		});
	}
	@SuppressWarnings("serial")
	//Tao Model cho Bang 
	public void model()
	{
		model = new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"Stt", "Ma Thi Sinh", "Ho va Ten", "Que Quan", "Ngay Sinh", "Gioi Tinh", "Diem Toan", "Diem Ly", "Diem Hoa"
				}
				
				
			) {
				public boolean isCellEditable(int row, int column)
				{
					return false;//This causes all cells to be not editable
				}

				 Class[] columnTypes = new Class[] {
							Integer.class, Integer.class, String.class, String.class, String.class, String.class, Float.class, Float.class, Float.class
						};
				};
	}
	//Update la thong tin cua Bang
	public static void updatetable() throws IOException
	{
		 DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		    /**
		    * additional code.
		    **/
		    tableModel.setRowCount(0);
		    /**/
		    Student a =  new Student();
		   
		    for (int i = 0; i < list.size(); i++) {
		    	 a = list.getStudent(i);
		    	 Object data[] = {
		    			 i+1,a.getID(),a.getName(),a.getHname(),a.getDbirth(),a.getGender(),a.getMath(),a.getPhysics(),a.getChemistry()
		    	 };
		        tableModel.addRow(data);
		    }
		    table.setModel(tableModel);
		    //		    /**
//		    * additional code.
//		    **/
//		    tableModel.fireTableDataChanged();
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			        if(checkSave == 0 && checkOpen ==1)
			        {
			        	int yes = JOptionPane.showConfirmDialog(null,"Ban chua luu lai ket qua ! Ban co muon luu roi thoat Khong ?");
			        	if(yes ==  JOptionPane.YES_OPTION)
			        	{
			        		list.Save(Filepath);
			        		e.getWindow().dispose();
			        		
			        	}
			        	else if(yes == JOptionPane.CLOSED_OPTION || yes == JOptionPane.CANCEL_OPTION) 
			        		 return;
			        	else e.getWindow().dispose();
			        	
			        }
			        else
			        {
			        	int PromptResult = JOptionPane.showConfirmDialog(null,"Ban Co Muon Thoat Khong?","Messege",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				        if(PromptResult==JOptionPane.YES_OPTION)
				            e.getWindow().dispose();
			        }	
			}
		});
		frame.setResizable(false);
		frame.setTitle("Quan ly v1.2");
		frame.setSize(783, 658);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
		frame.setJMenuBar(mb);
		frame.getContentPane().setLayout(null);
		Create_MenuBar();		// cai thanh o tren
		Create_Student_Filter();
		Create_Student_In4();
		Create_List_Student();
		Create_Funtion();
		if(Filepath.length() <1)
		{
			StudentFilter.setVisible(false);
			StudentIn4.setVisible(false);
			StudentList.setVisible(false);
			FuntionPanel.setVisible(false);
		}
		frame.setLocationRelativeTo(null);
	}

	@Override
	// okbtn btnCancel
		public void actionPerformed(ActionEvent e) {

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
}
