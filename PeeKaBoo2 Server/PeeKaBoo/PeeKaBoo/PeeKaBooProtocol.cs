using PeeKaBoo.DataBase;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace PeeKaBoo
{

    // todo 피카부 프로토콜에 parser 클래스의 통합 이 필요함 
    class PeeKaBooProtocol
    {
        public static int ROOMCREATE          = 0;
        public static int MESSAGESEND         = 1;
        public static int ROOMDELETE          = 2;
        public static int ROOMREQUEST         = 3;
        public static int ROOMREQUESTRESPONSE = 4;

        public PeeKaBooProtocol()
        { 
        
        }
        /**
     *  방 생성시
     *  1 명령어
     *  2 방이름
     *  3 최대 접속 인원
     *  4 방태그
     *  5 방 입장 패스워드\
     *  6 방장 uuid
     *
     *  방삭제시
     *  1. 명령어
     *  2. uuid
     *  3. 방이름
     *
     *
     *  메세지 전송시
     *  1. 명령어
     *  2. uuid
     *  3. 프로필 이름
     *  4. 방이름
     *  5. 메세지
     * @param command
     * @param data
     * @return
     */
        private static bool IsCommand(int command)
        {
            bool check = true;


            if (command == PeeKaBooProtocol.ROOMCREATE)
            {
                check = false;
            }
            else if (command == PeeKaBooProtocol.MESSAGESEND)
            {
                check = false;
            }
            else if (command == PeeKaBooProtocol.ROOMDELETE)
            {
                check = false;
            }
            else if (command == PeeKaBooProtocol.ROOMREQUEST)
            {
                check = false;
            }
            else if (command == PeeKaBooProtocol.ROOMREQUESTRESPONSE)
            {
                check = false;
            }

            return check;
        }
        public static String commandGenerator(int command)
        {
            if (IsCommand(command))
            {
                return "failed";
            }
            string sql = "";
            string commandStr = PeeKaBooProtocol.ROOMREQUESTRESPONSE + "&";
            DataBaseHelper dataBaseHelper = DataBaseHelper._Instance;

            if (command == PeeKaBooProtocol.ROOMREQUESTRESPONSE)
            {
                sql = "select * from " + DataBaseInfo._TableRoom;

                var cursor = DataBaseHelper._Instance.sqlRunForResult(sql);

                // 명령문&*방이름|맥스인원|태크|패스워드|방장
                //commandStr += PeeKaBooProtocol.ROOMREQUESTRESPONSE + "&";
                while (cursor.Read())
                {
                    string roomlist = "*";
                    roomlist += cursor.GetString(0) + "|";
                    roomlist += cursor.GetString(1) + "|";
                    roomlist += cursor.GetString(2) + "|";
                    roomlist += cursor.GetString(3) + "|";
                    roomlist += cursor.GetString(4);

                    commandStr += roomlist;
                }
            }

            return commandStr;
        }



    }

}
