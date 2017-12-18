package tb.sockets.client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import tb.sockets.server.Server;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import java.awt.Color;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	Socket sSock;
	static JFormattedTextField frmtdtxtfldXxxx = new JFormattedTextField();
	static JFormattedTextField frmtdtxtfldXxxx2 = new JFormattedTextField();
	JLabel lblNotConnected = new JLabel("Not Connected");
	static JFormattedTextField frmtdtxtfldIp=new JFormattedTextField();
	String client_IP;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHost = new JLabel("Host:");
		lblHost.setBounds(10, 14, 46, 14);
		contentPane.add(lblHost);
		
		try{
		InetAddress iAddress;
		iAddress = InetAddress.getLocalHost();
		client_IP = iAddress.getHostAddress();
		}
		catch(Exception ex){}
		
			frmtdtxtfldIp.setText(client_IP);
			frmtdtxtfldIp.setBounds(43, 11, 90, 20);        
			contentPane.add(frmtdtxtfldIp);

		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setBounds(10, 90, 95, 23);
		contentPane.add(btnConnect);
		
		btnConnect.addActionListener(this);
		
		frmtdtxtfldXxxx.setText("1201");
		frmtdtxtfldXxxx.setBounds(43, 39, 90, 20);
		contentPane.add(frmtdtxtfldXxxx);
		
		
		frmtdtxtfldXxxx2.setText("Twoja nazwa");
		frmtdtxtfldXxxx2.setBounds(43, 65, 90, 20);
		contentPane.add(frmtdtxtfldXxxx2);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(10, 42, 46, 14);
		contentPane.add(lblPort);
		
		JLabel lblNick = new JLabel("Nick:");
		lblNick.setBounds(10, 68, 46, 14);
		contentPane.add(lblNick);
		
		OrderPane panel = new OrderPane();
		panel.setBounds(145, 14, 487, 448);
		contentPane.add(panel);
		
		lblNotConnected.setForeground(new Color(255, 255, 255));
		lblNotConnected.setBackground(new Color(128, 128, 128));
		lblNotConnected.setOpaque(true);
		lblNotConnected.setBounds(10, 115, 123, 23);
		contentPane.add(lblNotConnected);
		
	}
	public void actionPerformed(ActionEvent event) {
		//String s="U¿ytkownik:"+" "+frmtdtxtfldXxxx2.getText();
		// new Client().setVisible(true);
		
               /* new Server().startServer();
                try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
             new Client().startClient();

	                lblNotConnected.setText("Connected");
	                lblNotConnected.setBackground(Color.GREEN);
	                
	                
	};
	
}
