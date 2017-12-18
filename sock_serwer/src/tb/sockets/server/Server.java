package tb.sockets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Server extends javax.swing.JFrame {

    static ServerSocket ss;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dos;
    static boolean active;

    public Server() {
        initComponents();
    }
public void startServer()
{
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new Server().setVisible(true);
            active=true;
        }
    });
    
    String msgin = "";
    try {
        ss = new ServerSocket(1201);
        s = ss.accept();
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
        while (!msgin.equals("exit")) {
            msgin = dis.readUTF();
            msg_area.setText(msg_area.getText().trim() + "\n" + msgin);

        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public boolean active()
{
	return active;
}
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        msg_text = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        msg_area.setColumns(20);
        msg_area.setRows(5);
        jScrollPane1.setViewportView(msg_area);

        msg_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_textActionPerformed(evt);
            }
        });

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
                .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void msg_textActionPerformed(java.awt.event.ActionEvent evt) {
       
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
            if(msg_text.getText().equals(""))
            {
            	 JOptionPane.showMessageDialog(this,"Nie mo¿na wys³aæ pustej wiadomoœci!");
            }
       
            else{ 
            	dos.writeUTF(time +" Server: "+msgout); 
            	msg_area.setText( msg_area.getText().trim()+"\n"+ time+msg_text.getText());
            	}
        } catch (Exception e) {
        }
       
        msg_text.setText("");
    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
        String msgin = "";
        try {
            ss = new ServerSocket(1201);
            s = ss.accept();
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            while (!msgin.equals("exit")) {
                msgin = dis.readUTF();
                msg_area.setText(msg_area.getText().trim() + "\n" + msgin);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea msg_area;
    private javax.swing.JTextField msg_text;
}
