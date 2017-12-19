package tb.sockets.client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import java.awt.Color;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	Socket sSock;
	static JFormattedTextField frmtdtxtfldXxxx = new JFormattedTextField();
	static JFormattedTextField frmtdtxtfldXxxx2 = new JFormattedTextField();
	JLabel lblNotConnected = new JLabel("Nie po³¹czono"); 
	static JFormattedTextField frmtdtxtfldIp=new JFormattedTextField();
	String client_IP;
	static boolean started=true;
	
	 	static Socket s;
	    static DataInputStream dis;
	    static DataOutputStream dos;
	    
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
		setBounds(100, 100, 750, 600);
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

		
		JButton btnConnect = new JButton("Po³¹cz");
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
		
		lblNotConnected.setForeground(new Color(255, 255, 255));
		lblNotConnected.setOpaque(true);
		lblNotConnected.setBounds(10, 115, 123, 23);
		lblNotConnected.setBackground(Color.RED);
		contentPane.add(lblNotConnected);
		
		  jScrollPane1 = new javax.swing.JScrollPane();
	        msg_area = new javax.swing.JTextArea();
	        msg_text = new javax.swing.JTextField();
	        btnSend = new javax.swing.JButton();


	        msg_area.setColumns(20);
	        msg_area.setRows(5);
	        jScrollPane1.setViewportView(msg_area);
	        jScrollPane1.setBounds(145, 14, 487, 448);
	        contentPane.add(jScrollPane1);

	        btnSend.setText("Wyœlij");
	        btnSend.setBounds(560,465, 70, 30);
	        contentPane.add(btnSend);
	       
	        btnSend.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                btnSendActionPerformed(evt);
	            }
	        });
	        
	        msg_text.setBounds(145,465, 400, 30);
	        contentPane.add(msg_text);
}
	public void actionPerformed(ActionEvent event) {

          //   new Client().startClient();
	                
	                String set="U¿ytkownik: "+MainFrame.frmtdtxtfldXxxx2.getText();
	                
	                setTitle(set);
					MainFrame.startChat();
					
					if(started==true)
						{
		                lblNotConnected.setText("Po³¹czono");
		                lblNotConnected.setBackground(Color.GREEN);
		                JOptionPane.showMessageDialog(this,"Po³¹czono z serwerem");
						}
					else
					{
						lblNotConnected.setText("NIe po³¹czono");
		                lblNotConnected.setBackground(Color.RED);
					}
	};
	
    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {
        String msgout="";
        LocalDateTime now = LocalDateTime.now();
        Integer hour = now.getHour();
        Integer minute = now.getMinute();
        Integer second = now.getSecond();
        String time=hour.toString()+":"+minute.toString()+":"+second.toString()+" ";
       try {
           msgout=msg_text.getText().trim();
           if (msg_text.getText().equals(""))
           {
           	 JOptionPane.showMessageDialog(this,"Nie mo¿na wys³aæ pustej wiadomoœci!");
           }
           else{
           	dos.writeUTF(time +" Client: "+msgout);  
           	msg_area.setText(msg_area.getText().trim()+"\n"+time + msg_text.getText());
           	
           	}
           
       } catch (Exception e) {
       }
       
       msg_text.setText("");
   }
	
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea msg_area;
    private javax.swing.JTextField msg_text;
    
    static void startChat()
    {String msgin="";
        try {             	
            s=new Socket(getIp(),getPort());//1201-port localhost-server
            dis=new DataInputStream(s.getInputStream());
            dos=new DataOutputStream(s.getOutputStream());
            while(msgin.equals("exit")){
            	msgin=dis.readUTF();
                msg_area.setText(msg_area.getText().trim()+"\n"+msgin);
            }
            
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(msg_area,"NIE WYKRYTO SERWERA!");
        	started=false;
        }
    }
    
    static String getIp()
    {
    	String ip;
 		if(frmtdtxtfldIp.getText().equals(""))
     	{
     		ip="localhost";
     	}
     	else{ip=frmtdtxtfldIp.getText();}
 		return ip;
    }
    
    static int getPort()
    {
 	   int intport;
    	
    	if(frmtdtxtfldXxxx.getText().equals(""))
    	{
    		intport=1201;
    	}
    	else{
    		String port=frmtdtxtfldXxxx.getText();
        	intport=Integer.parseInt(port);
    	}  
 	return intport;
    }
}
