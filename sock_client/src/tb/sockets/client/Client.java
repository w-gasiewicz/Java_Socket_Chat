package tb.sockets.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Client extends javax.swing.JFrame {

    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dos;
    BufferedReader reader;
    
    public Client() {
        initComponents();
    }
    

    
   static String getIp()
   {
   	String ip;
		if(MainFrame.frmtdtxtfldIp.getText().equals(""))
    	{
    		ip="localhost";
    	}
    	else{ip=MainFrame.frmtdtxtfldIp.getText();}
		return ip;
   }
   
   static int getPort()
   {
	   int intport;
   	
   	if(MainFrame.frmtdtxtfldXxxx.getText().equals(""))
   	{
   		intport=1201;
   	}
   	else{
   		String port=MainFrame.frmtdtxtfldXxxx.getText();
       	intport=Integer.parseInt(port);
   	}  
	return intport;
   }
    
    private void initComponents() {
String set="U¿ytkownik: "+MainFrame.frmtdtxtfldXxxx2.getText();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        msg_text = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(set);

        msg_area.setColumns(20);
        msg_area.setRows(5);
        jScrollPane1.setViewportView(msg_area);

        btnSend.setText("Wyœlij");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }

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

   public static void main(String args[]) {     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
       // String msgin="";
        try {             	
            s=new Socket(getIp(),getPort());//1201-port localhost-server
            dis=new DataInputStream(s.getInputStream());
            dos=new DataOutputStream(s.getOutputStream());
            while(true){
            	String msgin=dis.readUTF();
                msg_area.setText(msg_area.getText().trim()+"\n"+msgin);
            }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(msg_area,"NIE WYKRYTO SERWERA!");
        }
    }

    
    void startClient()
    {
 
                new Client().setVisible(true);   
        
        try {             	
            s=new Socket(getIp(),getPort());//1201-port localhost-server
            
            InputStreamReader streamreader = new InputStreamReader(s.getInputStream());
            reader = new BufferedReader(streamreader);
            
            dis=new DataInputStream(s.getInputStream());
            dos=new DataOutputStream(s.getOutputStream());
            String msgin;
            while((msgin= reader.readLine()) != null){
            	msgin=dis.readUTF();
                msg_area.setText(msg_area.getText().trim()+"\n"+msgin);
            }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(msg_area,"NIE WYKRYTO SERWERA!");
        }
    }
    
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea msg_area;
    private javax.swing.JTextField msg_text;
}
