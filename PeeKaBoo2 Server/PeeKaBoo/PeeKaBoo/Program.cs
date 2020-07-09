using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Security.Cryptography;
using System.Text;

namespace PeeKaBoo
{
    class Program
    {

        //static byte[] receiveBytes = new byte[2000];
        //static byte[] sendBytes = new byte[2000];


        static void Main(string[] args)
        {

            ServerProgram serverProgram = new ServerProgram();


            while (true)
            { 
                Console.WriteLine("   1.  서버 설정 ");
                Console.WriteLine("   2.  방삭제 ");
                Console.WriteLine("   3.  방인원 조회 ");
                Console.WriteLine("   4.  방세부 설정 수정 ");
                Console.WriteLine("Start. 서버 시작 ");
                Console.WriteLine("Stop.  서버 중지 ");

                Console.Write("명령어 :: ");
                String inputCommand = Console.ReadLine();


                if (inputCommand.Equals("1"))
                {
                    Console.WriteLine("\n\n");
                    Console.WriteLine("방생성 합니다 . ");
                    Console.WriteLine("\n\n");

                }
                else if (inputCommand.Equals("2"))
                {
                    Console.WriteLine("\n\n");
                    Console.WriteLine("방삭제");
                    Console.WriteLine("\n\n");

                }
                else if (inputCommand.Equals("3"))
                {
                    Console.WriteLine("\n\n");
                    Console.WriteLine("방 인원 조회");
                    Console.WriteLine("\n\n");
                }
                else if (inputCommand.Equals("4"))
                {
                    Console.WriteLine("\n\n");
                    Console.WriteLine("방세부 설정 수정");
                    Console.WriteLine("\n\n");
                }
                else if (inputCommand.Equals("Start"))
                {
                    Console.WriteLine("\n\n");
                    Console.WriteLine("어허");
                    Console.WriteLine("\n\n");
                    serverProgram.ServerStart();
                }
                else if (inputCommand.Equals("Stop"))
                {
                    Console.WriteLine("\n\n");
                    Console.WriteLine("서버 정지");
                    Console.WriteLine("\n\n");
                    serverProgram.ServerStop();
                }
                else
                {
                    Console.WriteLine("\n\n");
                    Console.WriteLine(" 올바른 명령이 아닙니다. ");
                    Console.WriteLine("\n\n");
                }
            }

            
            

            //Console.WriteLine("---------------------------------------");
            //Console.WriteLine(" 클라이언트의 연결을 기다립니다........ ");
            //Console.WriteLine("---------------------------------------");


            //Socket serverSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.IP);

            //serverSocket.Bind(new IPEndPoint(IPAddress.Any, 10000));

            //// 서버 최대 접속 인원
            //serverSocket.Listen(100);

            //Socket transferSock = serverSocket.Accept();

            //Console.WriteLine("---------------------------------------");
            //Console.WriteLine(" 클라이언트의 연결이 성공..............");
            //Console.WriteLine("---------------------------------------");
            

            //transferSock.BeginReceive(receiveBytes, 0, receiveBytes.Length, SocketFlags.None, new AsyncCallback(receiveStr), transferSock);

            //sendBytes = Encoding.Default.GetBytes("c# server에서 데이터를 보냇어!\r");

            //transferSock.BeginSend(sendBytes, 0, sendBytes.Length, SocketFlags.None, new AsyncCallback(sendStr), transferSock);

          


            //Console.WriteLine("어허");

        }




        public static void tcpServer()
        {

            Socket Server, Client;
            IPAddress serverIP = IPAddress.Parse("127.0.0.1");

            Server = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);

            Server.Bind(new IPEndPoint(IPAddress.Any, 10000));

            Server.Listen(10);

            Console.WriteLine("---------------------------------------");
            Console.WriteLine(" 클라이언트의 연결을 기다립니다........ ");
            Console.WriteLine("---------------------------------------");

            Client = Server.Accept();

            Console.WriteLine("---------------------------------------");
            Console.WriteLine(" 클라이언트의 연결이 성공.  ............");
            Console.WriteLine("---------------------------------------");
            NetworkStream ns = new NetworkStream(Client);

            StreamReader sr = new StreamReader(ns);
            Console.WriteLine(sr.ReadLine());

            StreamWriter sw = new StreamWriter(ns);
            sw.WriteLine("c#:보냈다");
            sw.Flush();

        }


        //static void receiveStr(IAsyncResult ar)
        //{
        //    Socket transferSock = (Socket)ar.AsyncState;
        //    int strLength = transferSock.EndReceive(ar);

        //    Console.WriteLine("---------------------------------------");
        //    Console.WriteLine(" 클라이언트의 데이터 수신이 성공.......");
        //    Console.WriteLine("---------------------------------------");
        //    Console.WriteLine(Encoding.Default.GetString(receiveBytes));
            
        //}
        //static void sendStr(IAsyncResult ar)
        //{
        //    Socket transferSock = (Socket)ar.AsyncState;

        //    Console.WriteLine("---------------------------------------");
        //    Console.WriteLine(" 클라이언트의 데이터 전송이 성공.......");
        //    Console.WriteLine("---------------------------------------");
        //    int strLength = transferSock.EndSend(ar);
        //    //Console.WriteLine("Strlength {0}만큼 보내기 성공", strLength);
        //}
    }
}
