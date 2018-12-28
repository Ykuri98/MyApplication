package com.example.kuri.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kuri.myapplication.R;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class DashboardActivity extends AppCompatActivity {

    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        button = (Button) findViewById(R.id.button6);
        button2 = (Button) findViewById(R.id.button7);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,TrainingActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EmailSender sender = new EmailSender();
                            //设置服务器地址和端口，可以查询网络
                            sender.setProperties("smtp.163.com", "25");
                            //分别设置发件人，邮件标题和文本内容
                            sender.setMessage("hg11yzw@163.com", "title" , "hello!");
                            //设置收件人
                            sender.setReceiver(new String[]{"×××××××@163.com"});
                            //添加附件换成你手机里正确的路径
                            // sender.addAttachment("/sdcard/emil/emil.txt");
                            //发送邮件
                            //sender.setMessage("你的163邮箱账号", "EmailS//ender", "Java Mail ！");这里面两个邮箱账号要一致
                            sender.sendEmail("smtp.163.com", "hg11yzw@163.com", "981014");
                        } catch (AddressException e) {
                            e.printStackTrace();
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                Intent intent = new Intent(DashboardActivity.this,TrainerActivity.class);
                startActivity(intent);
            }
        });

    }
}
