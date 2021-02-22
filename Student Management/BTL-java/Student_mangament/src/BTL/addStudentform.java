package BTL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class addStudentform  {

	public JFrame frame;
	private JTextField Id;
	private JTextField Name;
	private JTextField Home;
	private JTextField Birth;
	private JTextField Toan;
	private JTextField Ly;
	private JTextField Hoa;
	private JRadioButton Nu; 
	private JRadioButton Nam; 
	public Student a = new Student();
	private JButton XacNhan;
	private JButton Huy;
	private JLabel lblNewLabel;
	private JLabel lblHoVaTen;
	private JLabel lblMaQue;
	private JLabel lblNgaySinh;
	private JLabel lblToan;
	private JLabel lblLy;
	private JLabel lblHoa;
	private Home temp;
	private int txt =   (int) main_Frame.table.getValueAt(main_Frame.table.getRowCount()-1,1);
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					addStudentform window = new addStudentform();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public addStudentform() throws NumberFormatException, IOException {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	private void initialize() throws NumberFormatException, IOException {
		frame = new JFrame();
		frame.setTitle("Them thi sinh ");
		frame.setResizable(false);
		frame.toFront();
		frame.requestFocus();
		frame.setBounds(100, 100, 310, 376);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Id = new JTextField();
		txt+=1;
		Id.setText(String.valueOf(txt));
		Id.setBounds(103, 24, 150, 20);
		frame.getContentPane().add(Id);
		Id.setColumns(10);
		
		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(103, 55, 150, 20);
		frame.getContentPane().add(Name);
		
		Home = new JTextField();
		Home.setColumns(10);
		Home.setBounds(103, 86, 150, 20);
		frame.getContentPane().add(Home);
		
		Birth = new JTextField();
		Birth.setColumns(10);
		Birth.setBounds(103, 117, 150, 20);
		frame.getContentPane().add(Birth);
		
		Toan = new JTextField();
		Toan.setColumns(10);
		Toan.setBounds(103, 148, 150, 20);
		frame.getContentPane().add(Toan);
		
		Ly = new JTextField();
		Ly.setColumns(10);
		Ly.setBounds(103, 179, 150, 20);
		frame.getContentPane().add(Ly);
		
		Hoa = new JTextField();
		Hoa.setColumns(10);
		Hoa.setBounds(103, 210, 150, 20);
		frame.getContentPane().add(Hoa);
		Nu = new JRadioButton("Nu");
		Nam = new JRadioButton("Nam");
		Nam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Nu.setSelected(false);
				a.setGender(true);
			}
		});
		Nam.setBounds(79, 237, 47, 23);
		frame.getContentPane().add(Nam);
		
		 Nu = new JRadioButton("Nu");
		 Nu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		 		Nam.setSelected(false);
		 		a.setGender(false);
			}
		 });
		Nu.setBounds(136, 237, 47, 23);
		frame.getContentPane().add(Nu);
		
		XacNhan = new JButton("Xac Nhan");
		XacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text =  Home.getText().toUpperCase();
				temp = new Home(text);
				if(e.getSource().equals(XacNhan))
				{
					if(a.checkque(Home.getText().toUpperCase()) == false)
						Student.qlhome.add(temp);
					else
					{
						int id = Student.qlhome.getID(text);
						temp.setHome_ID(id);
					}
					if(Id.getText().equals("") ||Name.getText().equals("") ||Home.getText().equals("") || Birth.getText().equals("") || Toan.getText().equals("")
							||Ly.getText().equals("")||Hoa.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Can phai dien day du cac thong tin ", "Message ", JOptionPane.WARNING_MESSAGE);
					else if(Integer.parseInt(Id.getText()) <10000)
						JOptionPane.showMessageDialog(null, "ID >9999", "Message ", JOptionPane.WARNING_MESSAGE);
					else if(Home.getText().matches("\\d*\\w*\\d*"))
						JOptionPane.showMessageDialog(null, "Que quan khong hop le ! , khong chua chu so ", "Message ", JOptionPane.WARNING_MESSAGE);
					else if(a.checkbirth(Birth.getText()) == false)
						JOptionPane.showMessageDialog(null, "Ngay sinh phai dung dinh dang dd/mm/yyyy", "Message ", JOptionPane.WARNING_MESSAGE);
					else if(!Nam.isSelected()&&!Nu.isSelected())
						JOptionPane.showMessageDialog(null, "Can Phai xac dinh gioi tinh  ", "Message ", JOptionPane.WARNING_MESSAGE);
					else if(a.checkvalidMark(Float.parseFloat(Toan.getText())) == false)
						JOptionPane.showMessageDialog(null, "Diem Toan khong hop le", "Message ", JOptionPane.WARNING_MESSAGE);
					else if(a.checkvalidMark(Float.parseFloat(Ly.getText())) == false)
						JOptionPane.showMessageDialog(null, "Diem Ly khong hop le", "Message ", JOptionPane.WARNING_MESSAGE);
					else if(a.checkvalidMark(Float.parseFloat(Hoa.getText())) == false)
						JOptionPane.showMessageDialog(null, "Diem Hoa khong hop le", "Message ", JOptionPane.WARNING_MESSAGE);
					else if(main_Frame.list.check(Integer.parseInt(Id.getText())) == true)
						JOptionPane.showMessageDialog(null, "Da co ID nay trong danh sach ", "Message ", JOptionPane.WARNING_MESSAGE);	
					else
					{
						a.setID(Integer.parseInt(Id.getText()));
						a.setName(Name.getText());	
						a.SetHOME(temp);
						a.setDbirth(Birth.getText());
						if(Nam.isSelected())
							a.setGender(Boolean.parseBoolean("true"));
						else
							a.setGender(Boolean.parseBoolean("false"));
						a.setMath(Float.parseFloat(Toan.getText()));
						a.setPhysics(Float.parseFloat(Ly.getText()));	
						a.setChemistry(Float.parseFloat(Hoa.getText()));
						main_Frame.list.add(a);
						try {
							main_Frame.updatetable();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Them Thanh Cong", "Message ", JOptionPane.PLAIN_MESSAGE);	
						frame.dispose();
					}
				}

				
			}
		});
		XacNhan.setBounds(37, 287, 89, 23);
		frame.getContentPane().add(XacNhan);
		
		Huy = new JButton("Huy");
		Huy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		Huy.setBounds(140, 287, 89, 23);
		frame.getContentPane().add(Huy);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(20, 27, 59, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblHoVaTen = new JLabel("Ho va ten");
		lblHoVaTen.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoVaTen.setBounds(20, 58, 59, 14);
		frame.getContentPane().add(lblHoVaTen);
		
		lblMaQue = new JLabel("Que Quan");
		lblMaQue.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaQue.setBounds(10, 89, 75, 14);
		frame.getContentPane().add(lblMaQue);
		
		lblNgaySinh = new JLabel("Ngay Sinh");
		lblNgaySinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgaySinh.setBounds(20, 120, 59, 14);
		frame.getContentPane().add(lblNgaySinh);
		
		lblToan = new JLabel("Toan");
		lblToan.setHorizontalAlignment(SwingConstants.CENTER);
		lblToan.setBounds(20, 151, 59, 14);
		frame.getContentPane().add(lblToan);
		
		lblLy = new JLabel("Ly");
		lblLy.setHorizontalAlignment(SwingConstants.CENTER);
		lblLy.setBounds(20, 182, 59, 14);
		frame.getContentPane().add(lblLy);
		
		lblHoa = new JLabel("Hoa");
		lblHoa.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoa.setBounds(20, 213, 59, 14);
		frame.getContentPane().add(lblHoa);

	}
}
