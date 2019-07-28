package gv.bkap.timvieclam.model.server;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ServerInteractor implements IServerInteractor {

    public static final String DOMAIN = NotCommit.IP_SERVER;
    public static final String HOSTING = "http://" + DOMAIN + "/APITimViecLam/";
    public static final String HOSTING_IMAGES = HOSTING + "images/";
    public static final String HOSTING_API = HOSTING + "api/";
    public static final String PAGE_LOGIN = "login.php";
    public static final String PAGE_ADD_DEFAULT_CUSTOMER = "adddefaultcustomer.php";
    public static final String PAGE_GET_SIMPLE_ACCOUNT = "getsimpleaccount.php";
    public static final String PAGE_INSERT = "register.php";
    public static final String PAGE_CHANGE_INFO = "changeinfo.php";
    public static final String PAGE_LOADJOB = "loadjob.php";
    public static final String PAGE_INSERTJOB = "insertjob.php";
    public static final String PAGE_CHANGE_PASSWORD = "changepassword.php";
    public static final String PAGE_LOAD_CATEGORY = "loadcategory.php";

    public static final String DEFAULT_USER_IMG = HOSTING_IMAGES + "user.jpg";

    private final String TAG = "ServerInteractor";

    /**
     * Hàm gửi request tới máy chủ
     *
     * @param typeRequest Kiểu gửi: 1-GET | 2-POST
     * @param url         Liên kết tới máy chủ
     * @param param       Tham số
     * @return Dữ liệu text trả về từ máy chủ
     */
    @Override
    public String sendRequest(int typeRequest, String url, String param[][]) {
        String result = null;
        try {
            switch (typeRequest) {
                case 1:
                    result = sendRequestGET(url, param);
                    break;
                case 2:
                    result = sendRequestPOST(url, param);
                    break;
            }
        } catch (IOException ioe) {
            Log.e(this.getClass().getName() + "Lỗi nhập xuất", ioe.toString());
            ioe.printStackTrace();
        } catch (Exception ex) {
            Log.e(this.getClass().getName() + "Lỗi hệ thống", ex.toString());
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Thực thi gửi dữ liệu lấy từ form tới server (phương thức GET)
     */
    private String sendRequestGET(String url, String param[][]) throws IOException {
        // Chuẩn bị dữ liệu gửi dạng GET
        StringBuilder urlParams = new StringBuilder(url);
        if (param != null)
            for (int i = 0; i < param.length; i++) {
                if (i == 0) {
                    urlParams.append("?").append(param[i][0]).append("=").append(URLEncoder.encode(param[i][1], "UTF-8"));
                } else {
                    urlParams.append("&").append(param[i][0]).append("=").append(URLEncoder.encode(param[i][1], "UTF-8")); // Thêm &
                }
            }

        // Đường dẫn URL để gửi dữ liệu phương thức GET
        Log.e(TAG, "Gui du lieu di: " + urlParams.toString());
        URL link = new URL(urlParams.toString());

        // 1. Gửi dữ liệu (request) GET data
        HttpURLConnection conn = (HttpURLConnection) link.openConnection();
//        conn.setReadTimeout(10000);     // Read Timeout = 10s
//        conn.setConnectTimeout(15000);  // Connect Timeout = 15s
//        conn.setRequestMethod("POST");  // Phương thức gửi dữ liệu là POST
//        conn.setDoInput(true);          // Cho phép nhận dữ liệu
//        conn.setDoOutput(true);         // Cho phép gửi dữ liệu

        // Gửi
        conn.connect();
        Log.e(TAG, "Gửi dữ liệu GET thành công");

        // 2. Nhận phản hồi từ (response) server
        return getRespose(conn); // Trả về dữ liệu lấy từ server
    }


    /**
     * Thực thi gửi dữ liệu lấy từ form tới server (phương thức POST)
     */
    private String sendRequestPOST(String url, String param[][]) throws IOException {
        URL link = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) link.openConnection();
        conn.setReadTimeout(10000);     // Read Timeout = 10s
        conn.setConnectTimeout(15000);  // Connect Timeout = 15s
        conn.setRequestMethod("POST");  // Phương thức gửi dữ liệu là POST
        conn.setDoInput(true);          // Cho phép nhận dữ liệu
        conn.setDoOutput(true);         // Cho phép gửi dữ liệu

        // Chuẩn bị tham số
        HashMap<String, String> postDataParams = new HashMap<>();
        for (int i = 0; i < param.length; i++) {
            postDataParams.put(param[i][0], param[i][1]);
        }

        // Gửi dữ liệu POST
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(getPostDataString(postDataParams));
        writer.flush();
        writer.close();
        os.close();

        // Gửi
        conn.connect();

        // Nhận dữ liệu phản hồi
        return getRespose(conn);
    }

    /**
     * Đẩy dữ liệu lên server dạng POST
     *
     * @param params Collection chứa dữ liệu cần gửi
     * @return Khối dữ liệu đóng gói dạng chuỗi
     * @throws UnsupportedEncodingException
     */
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    /**
     * Lấy dữ liệu trả về (response) từ server
     *
     * @param conn đối tượng kết nối
     * @throws IOException
     */
    private String getRespose(HttpURLConnection conn) throws IOException {
        // Nhận phản hồi từ (response) server
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuilder sbResult = new StringBuilder();
        String line;
        // Đọc dữ liệu Response từ Server
        while ((line = reader.readLine()) != null) {
            // Đọc từng dòng dữ liệu trả về
            sbResult.append(line + "\n");
        }
        reader.close(); // Đóng luồng đọc
        return sbResult.toString();
    }
}
