using System;

namespace sqlecercise
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            SqlLite sqlLite = new SqlLite();
            
           
            while (true)
            {
                Console.WriteLine("1. db 생성");
                Console.WriteLine("2. table 생성");
                Console.WriteLine("3. table 삭제");
                Console.WriteLine("4. date 입력");
                Console.WriteLine("5. date 수정");
                Console.WriteLine("6. date 삭제");

                Console.WriteLine(" 명령을 입력해주세요");

                string select = Console.ReadLine();

                if (select.Equals("1"))
                {
                    sqlLite.CreateDB("koko.db"); // 생성은됨           
                }
                else if (select.Equals("2"))
                {
                    sqlLite.OpenDB("koko.db");
                    //sqlLite.createTable("Create table lastConnectTBL(hostNick varchar(60), hostAddress varchar(60), port varchar(60))");

                }

            }
        }
    }
}
