package com.example.kuri.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.example.kuri.myapplication.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;

    private EditText editText;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);


       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText.getText().toString();
                String password = editText2.getText().toString();
                final String serverPath = "http://127.0.0.1:3306/Se/login";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //使用GET方式请求服务器只能这样
                            URL url = new URL(serverPath + "?username=" + username + "&password=" + password);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("GET");
                            httpURLConnection.setConnectTimeout(5000);
                            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:22.0) Gecko/20100101 Firefox/22.0");
                            int responseCode = httpURLConnection.getResponseCode();
                            if (200 == responseCode) {
                                InputStream inputStream = httpURLConnection.getInputStream();
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                                final String responseMsg = bufferedReader.readLine();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (responseMsg.equals("true")){
                                            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(MainActivity.this, "登录失败！", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                                bufferedReader.close();
                                httpURLConnection.disconnect();
                            } else {

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }


        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText.getText().toString();
                String password = editText2.getText().toString();
                final String serverPath = "http://127.0.0.1:3306/Se/register";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //使用GET方式请求服务器只能这样
                            URL url = new URL(serverPath + "?username=" + username + "&password=" + password);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("GET");
                            httpURLConnection.setConnectTimeout(5000);
                            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:22.0) Gecko/20100101 Firefox/22.0");
                            final int responseCode = httpURLConnection.getResponseCode();
                            if (200 == responseCode) {
                                InputStream inputStream = httpURLConnection.getInputStream();
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                                final String responseMsg = bufferedReader.readLine();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (responseMsg.equals("equal")){
                                            Toast.makeText(MainActivity.this, "账号存在，请更改用户名！！", Toast.LENGTH_LONG).show();
                                        }else if(responseMsg.equals("true")){
                                            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(MainActivity.this, "注册失败！", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                                bufferedReader.close();
                                httpURLConnection.disconnect();
                            } else {

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });


    }
}
