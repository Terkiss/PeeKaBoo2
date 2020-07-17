using PeeKaBoo.DataBase;
using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Security.Cryptography;
using System.Text;
using System.Text.RegularExpressions;

namespace PeeKaBoo
{
    class Program
    {

        //static byte[] receiveBytes = new byte[2000];
        //static byte[] sendBytes = new byte[2000];


        static void Main(string[] args)
        {

            ServerProgram serverProgram = new ServerProgram();

            DataBaseHelper dataBaseHelper = DataBaseHelper._Instance;

            dataBaseHelper.OpenDB(DataBaseInfo._DBName);

            dataBaseHelper.sqlRun(DataBaseInfo.CreateServerConfigData);

            while (true)
            {
                Console.WriteLine("   1.  설정 ");
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


                    var cusor = dataBaseHelper.sqlRunForResult("select * from " + DataBaseInfo._TableServerConfigData + ";");

                    // 데이터 베이스에 다음 레코드가 있으면 false 없으면 true
                    if (!cusor.HasRows)
                    {
                        Console.WriteLine("심플 데이터 설정");
                        Console.WriteLine(" ");

                        Console.Write(" 해당 서버의 이름 :: ");
                        String serverName = Console.ReadLine();
                        if (serverName.Length < 1)
                        {
                            Console.WriteLine("잘못 된 라인 ");

                            while (true)
                            {
                                Console.Write(" 해당 서버의 이름 :: ");
                                serverName = Console.ReadLine();

                                if (!(serverName.Length < 1))
                                {
                                    break;
                                }

                            }
                        }

                        Console.Write("\n");


                        Console.Write(" 포트 :: ");
                        String serverPort = Console.ReadLine();
                        Regex regex = new Regex(@"[0-9]");
                        if (!regex.IsMatch(serverPort))
                        {
                            while (true)
                            {
                                Console.Write(" 포트 :: ");
                                serverPort = Console.ReadLine();

                                if (regex.IsMatch(serverPort))
                                {
                                    break;
                                }
                            }
                        }

                        Console.Write("공개된 서버 (Y/N) :: ");
                        String serverOpen = Console.ReadLine();
                        while (true)
                        {
                            if (serverOpen.Equals("Y") || serverOpen.Equals("y") || serverOpen.Equals("N") || serverOpen.Equals("n"))
                            {
                                break;
                            }
                            Console.Write("공개된 서버 (Y/N) :: ");
                            serverOpen = Console.ReadLine();
                        }


                        Console.Write("간단한 서버 소개 \n");
                        String serverIntroduction = Console.ReadLine();



                        string[] columns = dataBaseHelper.getColumnList(DataBaseInfo._TableServerConfigData);
                        string sql = dataBaseHelper.insertSqlGenerator(DataBaseInfo._TableServerConfigData, columns, new string[]{
                            serverName,
                            serverPort,
                            serverOpen,
                            serverIntroduction
                        });

                        dataBaseHelper.sqlRun(sql);

                        // 테이블 생성   
                        dataBaseHelper.sqlRun(DataBaseInfo.CreateServerLogTable);

                        // 방테이블 생성
                        dataBaseHelper.sqlRun(DataBaseInfo.CreateRoomTable);
                        dataBaseHelper.sqlRun(DataBaseInfo.CreateRoomJoinUserTable);
                        dataBaseHelper.sqlRun(DataBaseInfo.CreateChatTable);
                     
                    }

                    string selectSQL = "select * from " + DataBaseInfo._TableServerConfigData;

                    cusor = dataBaseHelper.sqlRunForResult(selectSQL);

                    while (cusor.Read())
                    {
                        ServerConfiguration.ServerName = cusor.GetString(0);
                        ServerConfiguration.Serverport = cusor.GetString(1);
                        ServerConfiguration.DisclosureStatus = cusor.GetString(2);
                        ServerConfiguration.ServerIntroduction = cusor.GetString(3);
                    }


                    Console.WriteLine(ServerConfiguration.ServerName);
                    Console.WriteLine(ServerConfiguration.Serverport);
                    Console.WriteLine(ServerConfiguration.DisclosureStatus);
                    Console.WriteLine(ServerConfiguration.ServerIntroduction);



                    Console.Title = "Server Name : "+ServerConfiguration.ServerName + " 실행 중";



                    serverProgram.ServerStart();
                }
                else if (inputCommand.Equals("Stop"))
                {
                    Console.WriteLine("\n\n");
                    Console.WriteLine("서버 정지");
                    Console.WriteLine("\n\n");
                    serverProgram.ServerStop();
                    Console.Title = "Server Name : " + ServerConfiguration.ServerName + "";
                }
                else
                {
                    Console.WriteLine("\n\n");
                    Console.WriteLine(" 올바른 명령이 아닙니다. ");
                    Console.WriteLine("\n\n");
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
