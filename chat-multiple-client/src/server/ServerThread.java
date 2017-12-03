package server;

import com.google.gson.Gson;
import database.DAO.LoginDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import request.FileConverter;
import request.Request;
import request.RequestType;

public class ServerThread extends Thread {

    String user = null;
    String json = null;
    BufferedReader is = null;
    PrintWriter os = null;
    public String id;
    public LoginDAO loginDAO = new LoginDAO();

    // thành phần chính
    Socket s = null;
    FormMainServer formMainServer = null;
    HashMap<String, ServerThread> hashMap = null;

    boolean loginStatus = false;

    // thư viện hỗ trợ
    Gson gson = new Gson();

    // Khởi tạo thread, tạo socket phục vụ cho client vừa kết nối
    public ServerThread(Socket s, FormMainServer formMainServer) {
        this.s = s;
        this.formMainServer = formMainServer;
        this.hashMap = formMainServer.getHashMap();

        try {
            is = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = new PrintWriter(s.getOutputStream());
            
            String json = is.readLine();
            Request rq = gson.fromJson(json, Request.class);
            this.id = rq.id;

        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }
    }

    public String getUser() {
        return user;
    }

    public BufferedReader getIs() {
        return is;
    }

    public PrintWriter getOs() {
        return os;
    }

    public boolean isLogin() {
        return this.loginStatus;
    }

    // Báo trạng thái đăng nhập cho client
    public void sendLoginResult() {
        Request rq = new Request();
        rq.setLogin(this.loginStatus);
        rq.setType(RequestType.LOGIN);
        
//        if (this.loginStatus) {
//            rq.setAvatar(FileConverter.fileToString("images/cuong.jpg"));
//        }
        
        String json = gson.toJson(rq);
        this.os.println(json);
        this.os.flush();
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Nhận json từ client gửi
                json = is.readLine();
                Request rq = gson.fromJson(json, Request.class);

                // Nếu là kiểu đăng nhập
                // Nhận thông tin đăng nhập từ FormLogin
                if (rq.getType() == RequestType.LOGIN) {
                    this.user = rq.getFromUser();
                    String pass = rq.getPassword();

                    // Kiểm tra tài khoản trong db
                    // loginStatus mặc định là false, thông tin đúng thì đổi sang true
                    if (loginDAO.checkLogin(user, pass) != null) {
                        this.loginStatus = true;

                        // Thay đổi trên FormMainServer
                        // Thông báo tài khoản đăng nhập                        
                        this.formMainServer.updateTextArea(this.user + " đăng nhập");
                        hashMap.put(user, this);
                        hashMap.remove(id);
                        
                        // tăng số connection                        
                        this.formMainServer.totalConnection++;
                        this.formMainServer.updateTotalConnection();
                    }

                    // Gửi kết quả đăng nhập về client
                    sendLoginResult();
                    continue;
                }

                // Đẩy json cho client đích 
                // Nếu ko phải kiểu đăng nhập thì đẩy đi
                if (rq.getType() != RequestType.LOGIN) {
                    String friend = rq.getToUser();
                    
                    System.out.println("server da nhan tn");

                    
                    if (this.hashMap.get(friend) != null) {
                        this.hashMap.get(friend).getOs().println(json);
                        this.hashMap.get(friend).getOs().flush();
                        System.out.println("server gui tn");
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("IO Error/ Client " + user + " terminated abruptly");

        } catch (NullPointerException e) {
            System.out.println("Client " + user + " Closed");

        } finally {
            try {
                if (is != null) {
                    is.close();
                }

                if (os != null) {
                    os.close();
                }

                if (s != null) {
                    s.close();
                }

                // Client thoát, xóa khỏi mảng hashMap
                this.hashMap.remove(this.user);

                // Nếu trước đó đã đăng nhập, thì ghi log đăng xuất
                if (isLogin()) {
                    this.formMainServer.updateTextArea(this.user + " đăng xuất");

                    // Khi client thoát, update lại total connection (giảm đi 1)
                    this.formMainServer.totalConnection--;
                    this.formMainServer.updateTotalConnection();
                }

            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }//end finally
    }// end void run

}
