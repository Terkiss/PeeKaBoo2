using PeeKaBoo.DataBase;
using System;
using System.Collections.Generic;
using System.Runtime.CompilerServices;
using System.Text;

namespace PeeKaBoo
{
    class Parser
    {
        
        public static string CommandParser(string str)
        {
            string[] data = str.Split("|");

            // 지정한

            string result = "";

            if (data.Length < 2)
            {
                Console.WriteLine("받은 데이터 오류");
            }
            else
            {
                // 데이터가 끝나는 마지막에 자바 소켓 은 \r\n 문자를 삽입 하므로 
                // 그 문자를 타이밍으로 서브 스트링을 실시 정제된 문자 데이터를 얻음 
                string uuid = data[data.Length - 1];
                int index = uuid.IndexOf(" \n");

                //Console.WriteLine("문자열 인덱스 값" + index);

                
                uuid = uuid.Substring(0,index);

                // Console.WriteLine(uuid + "uuid");

                data[data.Length - 1] = uuid;
                
               
                result = Pre_Processing_Command(data);
            }

            return result;
        }
        private static string roomDataDBInsert(string[] processed_Data)
        {
            string[] columns = DataBaseHelper._Instance.getColumnList(DataBaseInfo._TableRoom);
            string sql = DataBaseHelper._Instance.insertSqlGenerator(
                DataBaseInfo._TableRoom,
                columns, processed_Data
                );

            DataBaseHelper._Instance.sqlRun(sql);
            return PeeKaBooProtocol.ROOMCREATE + "|success";
        }
        private static string Pre_Processing_Command(string[] data)
        {
            int command = int.Parse(data[0]);
            string result = "";
            if (command == PeeKaBooProtocol.ROOMCREATE)
            {
                //*방 생성시
                //* 1 명령어
                //* 2 방이름
                //* 3 최대 접속 인원
                //* 4 방태그
                //* 5 방 입장 패스워드\
                //*6 방장 uuid
                string[] processed = new string[data.Length - 1];
                for (int i = 1; i < data.Length; i++)
                {

                    processed[i - 1] = data[i];

                    Console.WriteLine("처리한 데이터 " + processed[i - 1]);
                }

                result = roomDataDBInsert(processed);
                // DataBaseHelper._Instance.insertData("roomTBL", new string[] { "1", "2", "3" }, new string[] { "1-1", "11-2", "1-3" });

            }
            else if (command == PeeKaBooProtocol.ROOMDELETE)
            {

            }
            else if (command == PeeKaBooProtocol.MESSAGESEND)
            {

            }
            else if (command == PeeKaBooProtocol.ROOMREQUEST)
            {
                // 로그 데이터 베이스 기록
                LogWriter(data);
                // 룸 테이블의 내용을 조회 하여 데이터로 가공후 전송
                result = RoomRequest();
                //Console.WriteLine(RoomRequest());
                
            }

            return result;
        }
    
        private static string RoomRequest()
        {
            string roomlist = "";
    
            roomlist = PeeKaBooProtocol.commandGenerator(PeeKaBooProtocol.ROOMREQUESTRESPONSE);
               
            return roomlist;
        }
        private static void LogWriter(params string[] data)
        {
            String time = DateTime.Now.ToString("yyyyMMdd");

            String Message = "";

            for (int i = 0; i < data.Length; i++)
            {
                Message += data[i];
            }
            String[] columns = DataBaseHelper._Instance.getColumnList(DataBaseInfo._TableServerLog);
            String sql = DataBaseHelper._Instance.insertSqlGenerator(DataBaseInfo._TableServerLog,
                columns,
                new string[] {
                    time,
                    Message
                });

            DataBaseHelper._Instance.sqlRun(sql);
        }
    }
}
