package client;

import com.google.gson.Gson;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UnsupportedLookAndFeelException;
import request.FileConverter;
import request.Request;
import request.RequestType;

public class FormMainClient extends javax.swing.JFrame {

    /**
     * Creates new form Form_Main_Client
     */
    private String id;
    public String user = null;
    private HashMap<String, FormChatPrivacy> hashMap = new HashMap<>();
    private PrintWriter os = null;
    private Gson gson = new Gson();
    private JList listFriend;
    DefaultListModel<String> listModel = new DefaultListModel<>();
    private Vector friends = new Vector();
    FormLogin formlogin;

    public FormMainClient() {
        initComponents();

        // create login form
        this.formlogin = new FormLogin(this);
        this.formlogin.setLocationRelativeTo(null);
        this.formlogin.setVisible(true);
        // end create login form

        this.friends.add(new FriendEntry("cuong", true));
        this.friends.add(new FriendEntry("duc", false));
        this.friends.add(new FriendEntry("hieu", false));
        this.friends.add(new FriendEntry("phuong", false));
        addListFriend();
        
        backgroundThread();
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        lblAvatar = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtAddFriend = new javax.swing.JTextField();
        btnAddFriend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAvatar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        lblUser.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblUser.setText("user");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtAddFriend.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtAddFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddFriendActionPerformed(evt);
            }
        });

        btnAddFriend.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnAddFriend.setText("Add");
        btnAddFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFriendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAddFriend)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btnAddFriend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtAddFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public PrintWriter getOS() {
        return this.os;
    }

// begin thread in background
    public void backgroundThread() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                InetAddress address = null;
                Socket sk = null;
                BufferedReader is = null;
                
                try {
                    address = InetAddress.getLocalHost();
                    System.out.println(address);
                    sk = new Socket(address, 6969);
                    is = new BufferedReader(new InputStreamReader(sk.getInputStream()));
                    os = new PrintWriter(sk.getOutputStream());
                    
                    UUID randomUUID = UUID.randomUUID();
                    id = randomUUID.toString();           
                    
                    // Ngay khi kết nối đến server, gửi luôn id
                    Request rqLogin = new Request();
                    rqLogin.id = id;
                    String jsonLogin = gson.toJson(rqLogin);
                    
                    os.println(jsonLogin);
                    os.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Client Address : " + address);

                try {
                    // Gửi tin nhắn: gửi tại form chat                    

                    // Nhận tin nhắn: nhận về json, trả về FormChat tương ứng với người gửi                    
                    while (true) {
                        String json = is.readLine();
                        Request rq = gson.fromJson(json, Request.class);

                        // Nếu là kiểu đăng nhập
                        if (rq.getType() == RequestType.LOGIN) {

                            // server báo login thất bại
                            if (!rq.isLogin()) {
                                formlogin.throwMessage();
                                System.out.println("login that bai: " + rq.isLogin());
                            } else {

                                // server báo login thành công
                                System.out.println("login thanh cong");
//                                lblAvatar.setIcon(FileConverter.stringToImage(rq.getAvatar()));
                                showForm();
                                formlogin.setVisible(false);
                            }
                            continue;
                        }

                        String userSend = rq.getFromUser();
                        Object content = rq.getContent();

                        // Nếu là kiểu massage
                        if (rq.getType() == RequestType.MESSAGE) {
                            hashMap.get(userSend).updateTxtContentReceive(rq);
                        } else if (rq.getType() == RequestType.ASKFRIEND) {
                            String newFriend = rq.getFromUser();
                            listModel.addElement(newFriend);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();

                } finally {
                    try {
                        is.close();
                        os.close();
                        sk.close();

                    } catch (IOException ex) {
                        Logger.getLogger(FormMainClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Connection Closed");
                }
            }
        }).start();
    }
    // end thread in background

//    private void askFriend() {
//        String newFriend = this.txtAddFriend.getText();
//        Request rqAskFriend = new Request(this.user, newFriend, null, RequestType.ASKFRIEND, null);
//        String jsonAskFriend = gson.toJson(rqAskFriend);
//        os.println(jsonAskFriend);
//        os.flush();
//        this.listModel.addElement(newFriend);
//    }

    private void txtAddFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddFriendActionPerformed
//        this.askFriend();
    }//GEN-LAST:event_txtAddFriendActionPerformed

    private void btnAddFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFriendActionPerformed
//        this.askFriend();
        this.friends.add(new FriendEntry("huong ly", true));
        addListFriend();
    }//GEN-LAST:event_btnAddFriendActionPerformed

    public void exit() {
        System.exit(0);
    }

    public void showForm() {
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void listFriendMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            FriendEntry entry = (FriendEntry) listFriend.getSelectedValue();
            String friend = entry.getName();

            entry.setOnline(true);

            if (this.hashMap.get(friend) == null) {
                this.hashMap.put(friend, new FormChatPrivacy(this.user, friend, this.os));
                this.hashMap.get(friend).setVisible(true);
            } else {
                this.hashMap.get(friend).setVisible(true);
            }
        }
    }

    public void addListFriend() {
        listFriend = new JList(friends);
        listFriend.setCellRenderer(new FriendCellRenderer());
        listFriend.setFixedCellHeight(35);
        listFriend.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 1));
        this.jScrollPane3.setViewportView(listFriend);

        listFriend.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listFriendMouseClicked(evt);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            javax.swing.UIManager.setLookAndFeel(new AluminiumLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormMainClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMainClient();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFriend;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextField txtAddFriend;
    // End of variables declaration//GEN-END:variables
}

class FriendEntry {

    private String name;
    private boolean online;

    private ImageIcon icon;

    private final ImageIcon onlineIcon = new ImageIcon("images/online-icon.png");
    private final ImageIcon offlineIcon = new ImageIcon("images/offline-icon.png");

    public FriendEntry(String name, boolean online) {
        this.name = name;
        this.online = online;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getIcon() {
        if (online) {
            return onlineIcon;
        }
        return offlineIcon;
    }

    public void setOnline(boolean online) {
        this.online = online;
        if (online) {
            this.icon = onlineIcon;
        } else {
            this.icon = offlineIcon;
        }
    }

    // Override standard toString method to give a useful result
    public String toString() {
        return name;
    }
}

class FriendCellRenderer extends JLabel implements ListCellRenderer {

    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

    public FriendCellRenderer() {
        setOpaque(true);
        setIconTextGap(20);
    }

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        FriendEntry entry = (FriendEntry) value;
        setText(entry.getName());
        setIcon(entry.getIcon());
        setFont(new Font("Dialog", 1, 16));

        if (isSelected) {
            setBackground(Color.GRAY);
            setForeground(Color.white);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }

        return this;
    }
}
