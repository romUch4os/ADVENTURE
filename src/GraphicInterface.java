import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class GraphicInterface extends JFrame {

	private static GraphicInterface instance = null;
	private JPanel contentPane;
	private JTextArea textArea = new JTextArea();
	private JTextField textField;

	public static GraphicInterface getInstance() {
		
		if( instance == null ) {
			instance = new GraphicInterface();
			instance.setVisible(true);
		}
		
		return instance;
		
	}
	
	private GraphicInterface() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 25, 600, 725);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea.setFont(new Font("Consolas", Font.PLAIN, 16));
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.BLACK);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText("ddddjs");
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 564, 618);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Consolas", Font.PLAIN, 16));
		textField.setBounds(10, 640, 451, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAction = new JButton("Action!");
		btnAction.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnAction.setBounds(471, 640, 103, 35);
		contentPane.add(btnAction);
		
	}
	
	public void addText(String str) {
		
		textArea.append(str);
	}
	
}
