package com.terukiss.peekaboo2.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.ByteBuffer;

public class CsharpServerCommunication {
    JeongLog jeongLog;
    public CsharpServerCommunication(String debugTag)
    {
        jeongLog = new JeongLog(debugTag);
    }

//    private void clickProcess(String data) {
//        BufferedReader in = null;
//        BufferedWriter out = null;
//        ObjectOutputStream oos = null;
//        Socket socket;
//        try {
//            socket = new Socket("hotlinedeahet.iptime.org", 10000);
//
//            // in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
//            jeongLog.logD("소켓을 테스트 합니다 "+data);
//            out.write(data + " \n");
//            out.flush();
//
//            byte[] buf = new byte[200];
//            socket.getInputStream().read(buf);
//            // String receiveStr = in.readLine();
//
//            String aa = "";
//            for (int i = 0; i < buf.length; i++)
//            {
//                if(buf[i]==0)
//                {
//                    byteArrayOutputStream.write(buf, 0, i-1);
//                    aa += byteArrayOutputStream.toString("UTF-8");
//
//                    break;
//                }
//            }
//            jeongLog.logD(aa) ;
//            textView.setText(aa);
//            //textView.setText("receiveStr : "+receiveStr);
//            //in.close();
//            out.close();
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
    public Socket serverConnect() throws IOException
    {
        int portNum =Integer.parseInt(ConnectionInfo.ServerPort);
        return new Socket(ConnectionInfo.ServerHostName, 10000);
    }


    public void sendCsharpServer(final String data)
    {
        final String sendData = data;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedWriter out = null;
                try{
                    Socket server = serverConnect();
                    out = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));

                    out.write(sendData + " \n");
                    out.flush();

                    out.close();
                    server.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        });

        thread.start();





    }



    public void receiveCsharpServer()
    {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                BufferedReader in = null;
                try {
                    Socket server = serverConnect();

                    byte[] buf = new byte[2000];
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2000);

                    server.getInputStream().read(buf);

                    String receiveData = "";
                    for (int i = 0; i < buf.length; i++)
                    {
                        jeongLog.logD(i+"어허"+receiveData);
                        jeongLog.logD("버퍼 데이터"+buf[i]);
                        if(buf[i]==0)
                        {
                            byteArrayOutputStream.write(buf, 0, i-1);
                            receiveData += byteArrayOutputStream.toString("UTF-8");
                            jeongLog.logD("임시적 데이터 결과 :  "+byteArrayOutputStream.toString("UTF-8"));
                            break;
                        }
                    }
                    jeongLog.logD("최종 받은 데이터 결과 : "+receiveData) ;

                    byteArrayOutputStream.close();
                    server.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        });

        thread.start();




    }


}
