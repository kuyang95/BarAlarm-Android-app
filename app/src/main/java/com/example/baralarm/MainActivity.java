package com.example.baralarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import java.util.zip.Inflater;
import android.os.Bundle;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClicked(View view){

        // 알림(Notification) 을 관리하는 관리자 객체를 운영체제(Context)로부터 소환하기
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Notification 객체를 생성해주는 건축가객체 생성(AlertDialog 와 비슷)
        NotificationCompat.Builder builder = null;

        // Oreo 버전(API26 버전) 이상에서는 알림시에 NotificationChannel 이라는 개념이 필수 구성요소가 됨
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            String channelID = "channel_01"; // 알림채널 식별자
            String channelName = "MyChannel01"; // 알림채널의 이름(별명)

            // 알림채널 객체 만들기
            NotificationChannel channel = new NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_DEFAULT);

            // 알림매니저에게 채널 객체의 생성을 요청
            notificationManager.createNotificationChannel(channel);

            // 알림건축가 객체 생성
            builder = new NotificationCompat.Builder(this, channelID);
        }

        else{
            // 알림건축가 객체 생성
            builder = new NotificationCompat.Builder(this, null);
        }

        // 건축가에게 원하는 알림의 설정작업
        builder.setSmallIcon(android.R.drawable.ic_menu_view);

        // 상태바를 드래그하여 아래로 내리면 보이는
        // 알림창(확장 상태바) 의 설정
        builder.setContentTitle("Title"); // 알림창 제목
        builder.setContentText("Messages..."); // 알림창 내용

        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.activity_notipage);
        builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout);



        // 알림창의 큰 이미지
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.introimage);
        builder.setLargeIcon(bm); // 매개변수가 Bitmap 을 줘야한다.

        // 건축가에게 알림 객체 생성하도록
        Notification notification = builder.build();

        // 알림매니저에게 알림(Notify) 요
        notificationManager.notify(1, notification);
    }
}