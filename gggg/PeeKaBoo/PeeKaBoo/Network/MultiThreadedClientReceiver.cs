using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace PeeKaBoo.Network
{
    public class MultiThreadedClientReceiver
    {
        Socket clientTransock;
        Thread clientThread;


        byte[] receiveBytes = new byte[2000];
        byte[] sendBytes = new byte[2000];

        public MultiThreadedClientReceiver(Socket transock)
        {
            clientTransock = transock;
            clientThread = new Thread(Run);
            clientThread.Start();
         
        }

        private void Run()
        {
            while (true)
            {
                clientTransock.BeginReceive(receiveBytes, 0, receiveBytes.Length, SocketFlags.None, new AsyncCallback(receiveStr), clientTransock);
            }
        }

        private void receiveStr(IAsyncResult ar)
        {
            Socket transferSock = (Socket)ar.AsyncState;
            int strLength = transferSock.EndReceive(ar);

            Console.WriteLine("---------------------------------------");
            Console.WriteLine(" 클라이언트의 데이터 수신이 성공.......");
            Console.WriteLine("---------------------------------------");
            Console.WriteLine(Encoding.Default.GetString(receiveBytes));


            // 데이터 베이스 를 기록 합니다.
            // 혹은 데이터 를 이용할 클래스를 생성하는 
            // 생성자 호출 
            String ReceiceData = Encoding.Default.GetString(receiveBytes);



            string sendData = "";


            sendData = Parser.CommandParser(ReceiceData);

            for (int i = 0; i < receiveBytes.Length; i++)
            {
                receiveBytes[i] = 0;
            }

            // 데이터가 보내는 바이트  보다 커지면 문제가 발생 할 소지가 있음 추후에 수정 
            // sendBytes = Encoding.Default.GetBytes(sendData);
            Byte[] send = Encoding.Default.GetBytes(sendData);
            sendBytes = send;
            Console.WriteLine("보내는 데이터의 길이 " + send.Length);
            transferSock.BeginSend(sendBytes, 0, sendBytes.Length, SocketFlags.None, new AsyncCallback(sendStr), transferSock);
            //transferSock.Close();
        }
        private void sendStr(IAsyncResult ar)
        {
            Socket transferSock = (Socket)ar.AsyncState;


            for (int i = 0; i < sendBytes.Length; i++)
            {
                sendBytes[i] = 0;
            }


            Console.WriteLine("---------------------------------------");
            Console.WriteLine(" 클라이언트의 데이터 전송이 성공.......");
            Console.WriteLine("---------------------------------------");
            int strLength = transferSock.EndSend(ar);
            //Console.WriteLine("Strlength {0}만큼 보내기 성공", strLength);
        }
    }
}
