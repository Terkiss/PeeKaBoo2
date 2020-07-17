using System;
using System.Collections;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Text;

namespace PeeKaBoo.DataBase
{

    class DataBaseHelper
    {
        public static DataBaseHelper _Instance = new DataBaseHelper();

        private SQLiteConnection connection = null;
        private DataBaseHelper()
        {

        }
        ~DataBaseHelper()
        {
            connection.Close();
        }

        public void OpenDB(string dbName)
        {
            if (connection == null)
            {
                connection = new SQLiteConnection("Data Source=koko.db");
            }

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

            //String _CreateLastConnectTable = "Create table lastConnectTBL(hostNick varchar(60), hostAddress varchar(60), port varchar(60))";
            //SQLiteCommand command = new SQLiteCommand(_CreateLastConnectTable, connection);
            //command.ExecuteNonQuery();
            //for (int i = 0; i < 10000; i++)
            //{
            //    string data = "insert into lastConnectTBL(hostNick, hostAddress, port) values('2456', 'nick', '" + i + "')";
            //    SQLiteCommand command2 = new SQLiteCommand(data, connection);

            //    command2.ExecuteNonQuery();

            //}


            //SQLiteCommand command = new SQLiteCommand(_CreateLastConnectTable, connection);

            //int result = command.ExecuteNonQuery();



        }
        public void sqlRun(string sql)
        {
            if (connection == null)
            {
                Console.WriteLine("디비가 비엇음");
            }
            else
            {
                SQLiteCommand command = new SQLiteCommand(sql, connection);
                command.ExecuteNonQuery();

            }

        }
        public SQLiteDataReader sqlRunForResult(string sql)
        {
            SQLiteCommand command = new SQLiteCommand(sql, connection);
            SQLiteDataReader rdr = command.ExecuteReader();

            return rdr;
        }
        public string insertSqlGenerator(String _TableName, String[] filedName, String[] data)
        {
            String filed = "";
            String Riddle = "('";

            for (int i = 0; i < filedName.Length; i++)
            {
                if (i == filedName.Length - 1)
                {
                    filed += filedName[i];
                    Riddle += data[i] + "');";
                    break;
                }
                filed += filedName[i] + ", ";
                Riddle += data[i] + "', '";
            }

            String sql = "insert into " + _TableName + "(" + filed + ") values" + Riddle;

            return sql;
        }


        public String[] getColumnList(String tblName)
        {
            String sql = "PRAGMA table_info(" + tblName + ");";

            SQLiteCommand command = new SQLiteCommand(sql, connection);
            SQLiteDataReader reader = command.ExecuteReader();

            ArrayList columns = new ArrayList();

            while (reader.Read())
            {
                if (!reader.GetString(1).Equals("_id"))
                {
                    columns.Add(reader.GetString(1));
                }

            }

            string[] columnArray = new string[columns.Count];

            for (int i = 0; i < columnArray.Length; i++)
            {
                columnArray[i] = (string)columns[i];
            }

            return columnArray;


        }






    }
   
}
