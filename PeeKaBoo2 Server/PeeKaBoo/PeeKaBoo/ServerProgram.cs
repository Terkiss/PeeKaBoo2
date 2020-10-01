using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using PeeKaBoo.Network;
namespace PeeKaBoo
{

    class ServerProgram
    {

        bool run = true;

        byte[] receiveBytes = new byte[2000];
        byte[] sendBytes = new byte[6000];

        public ServerProgram()
        {

        }

        public void ServerStart()
        {
            run = true;
            Thread thread = new Thread(new ThreadStart(serverProcess));
            thread.Start();
        }

        public void ServerStop()
        {
            run = false;
        }

        private void serverProcess()
        {
            Socket serverSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.IP);

            serverSocket.Bind(new IPEndPoint(IPAddress.Any, 10000));
            while (run)
            {
                // 서버 최대 접속 인원
                serverSocket.Listen(100);
                Console.WriteLine(" 클라이언트의 클라이언트 연결 대기..............");
                Socket transferSock = serverSocket.Accept();
               
                Console.WriteLine(" 클라이언트의 연결이 성공..............");


                transferSock.BeginReceive(receiveBytes, 0, receiveBytes.Length, SocketFlags.None, new AsyncCallback(receiveStr), transferSock);

                //sendBytes = Encoding.Default.GetBytes("c# server에서 데이터를 보냇어!\r");

                //transferSock.BeginSend(sendBytes, 0, sendBytes.Length, SocketFlags.None, new AsyncCallback(sendStr), transferSock);



                // 테스트 시작
                //MultiThreadedClientReceiver multiThreadedClient = new MultiThreadedClientReceiver(transferSock);

                

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

            // 2020 - 09 - 13 너무 긴 데이터는 꺠지는 문제 발생 
            // 1024바이트 이내로 전송시 일단은 안꺠지는거 90% 확인 
            string sendData = "안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안키시안||| \n";
            
            // 여기 임시 주석처리
            //sendData = Parser.CommandParser(ReceiceData);

            // 보내기전 초기화
            for (int i = 0; i < receiveBytes.Length; i++)
            {
                receiveBytes[i] = 0;
            }

            // 데이터가 보내는 바이트  보다 커지면 문제가 발생 할 소지가 있음 추후에 수정 
           // sendBytes = Encoding.Default.GetBytes(sendData);
            Byte[] send = Encoding.Default.GetBytes(sendData);
            String sss = Encoding.Default.GetString(send);
            sendBytes = send;
       
            Console.WriteLine(sendData.Length+"보내는 데이터의 길이 " +send.Length );
            Console.WriteLine(sss);
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
