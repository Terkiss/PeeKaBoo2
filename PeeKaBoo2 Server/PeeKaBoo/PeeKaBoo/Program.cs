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

        static byte[] receiveBytes = new byte[2000];
        static byte[] sendBytes = new byte[200];
        static void Main(string[] args)
        {


            Console.WriteLine("---------------------------------------");
            Console.WriteLine(" 클라이언트의 연결을 기다립니다........ ");
            Console.WriteLine("---------------------------------------");


            Socket serverSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.IP);

            serverSocket.Bind(new IPEndPoint(IPAddress.Any, 10000));


            serverSocket.Listen(100);

            Socket transferSock = serverSocket.Accept();

            Console.WriteLine("---------------------------------------");
            Console.WriteLine(" 클라이언트의 연결이 성공..............");
            Console.WriteLine("---------------------------------------");
            

            transferSock.BeginReceive(receiveBytes, 0, receiveBytes.Length, SocketFlags.None, new AsyncCallback(receiveStr), transferSock);

            sendBytes = Encoding.Default.GetBytes("c# server에서 데이터를 보냇어!\r");
            transferSock.BeginSend(sendBytes, 0, sendBytes.Length, SocketFlags.None, new AsyncCallback(sendStr), transferSock);

            //transferSock.Receive(receiveBytes);





            //String strdata = Encoding.UTF8.GetString(receiveBytes);

            //Console.WriteLine(strdata);



            Console.WriteLine("어허");





            //  tcpServer();



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


        static void receiveStr(IAsyncResult ar)
        {
            Socket transferSock = (Socket)ar.AsyncState;
            int strLength = transferSock.EndReceive(ar);

            Console.WriteLine("---------------------------------------");
            Console.WriteLine(" 클라이언트의 데이터 수신이 성공.......");
            Console.WriteLine("---------------------------------------");
            Console.WriteLine(Encoding.Default.GetString(receiveBytes));
            
        }
        static void sendStr(IAsyncResult ar)
        {
            Socket transferSock = (Socket)ar.AsyncState;

            Console.WriteLine("---------------------------------------");
            Console.WriteLine(" 클라이언트의 데이터 전송이 성공.......");
            Console.WriteLine("---------------------------------------");
            int strLength = transferSock.EndSend(ar);
            //Console.WriteLine("Strlength {0}만큼 보내기 성공", strLength);
        }
    }
}
