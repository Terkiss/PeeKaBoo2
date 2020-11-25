using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SQLite;
using System.IO;

namespace sqlecercise
{
    class SqlLite
    {
     
    
        String dbPath;
        public SqlLite()
        { 

        }
        public void CreateDB(string dbName)
        {
            FileStream fs = File.Create(dbName);
            fs.Close();
        }

        public void OpenDB(string dbName)
        {
            dbPath = string.Format("Data Source={0}", @"F:\Project\peekaboo2\PeeKaBoo2 Server\sqlecercise\sqlecercise\bin\Debug\netcoreapp3.1\koko.db");
            using var connection = new SQLiteConnection("Data Source=koko.db");
            Console.WriteLine("dbname :: " + dbName);
            Console.WriteLine("dbpath :: " + dbPath);

            if (connection == null)
            {
                Console.WriteLine("널입니다");

            }
          
            Console.WriteLine(connection.ConnectionString);
            try
            {
                connection.Open();
            }
  
            catch (System.NullReferenceException e)
            {
                Console.WriteLine("널에러");
            }

            String _CreateLastConnectTable = "Create table lastConnectTBL(hostNick varchar(60), hostAddress varchar(60), port varchar(60))";
            SQLiteCommand command = new SQLiteCommand(_CreateLastConnectTable, connection);
            command.ExecuteNonQuery();
            for (int i = 0; i < 10000; i++)
            {
                string data = "insert into lastConnectTBL(hostNick, hostAddress, port) values('2456', 'nick', '" + i + "')";
                SQLiteCommand command2 = new SQLiteCommand(data, connection);

                command2.ExecuteNonQuery();

            }


            //SQLiteCommand command = new SQLiteCommand(_CreateLastConnectTable, connection);

            //int result = command.ExecuteNonQuery();



        }

        public void createTable(string sql)
        {
            //SQLiteCommand command = new SQLiteCommand(sql, dbConn);
            //System.InvalidOperationException
            //try
            //{
            //    int result = command.ExecuteNonQuery();
            //    Console.WriteLine(result);
            //}
            //catch (InvalidOperationException e)
            //{
            //    Console.WriteLine(e.StackTrace.ToString());
            //}
         
        }

        public void SelectTable(string sql)
        {
            //SQLiteCommand command = new SQLiteCommand(sql, dbConn);
            //SQLiteDataReader reader = command.ExecuteReader();

            //while (reader.Read())
            //{
            //    // 연관 배열
            //    string print = "";

            //    for (int i = 0; i < reader.FieldCount; i++)
            //    {
            //        print += reader.GetString(i) + ", ";
            //    }

            //    Console.WriteLine(print);
            //}
        }

        static public void CreateFile(string databaseFileName)
        {
            FileStream fs = File.Create(databaseFileName);
            fs.Close();
        }


    }
}
