using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace PeeKaBoo
{
    class PeeKaBooProtocol
    {
        public static int ROOMCREATE    = 0;
        public static int MESSAGESEND      = 1;
        public static int ROOMDELETE    = 2;
        public static int ROOMREQUEST = 3;

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
     * @param commmand
     * @param data
     * @return
     */
        public static String commandGenerator(int commmand, params string[] data)
        {
            try
            {
                if (commmand == ROOMCREATE)
                {
                    if (data.Length != 5)
                    {
                        throw new IOException();
                    }
                }
                else if (commmand == MESSAGESEND)
                {
                    if (data.Length != 2)
                    {
                        throw new IOException();
                    }
                }
                else if (commmand == ROOMDELETE)
                {
                    if (data.Length != 4)
                    {
                        throw new IOException();
                    }
                }
                StringBuilder tempCommand = new StringBuilder();

                tempCommand.Append(commmand);
                for (int i = 0; i < data.Length; i++)
                {
                    tempCommand.Append("|").Append(data[i]);
                }
                return tempCommand.ToString();
            }
            catch (IOException E)
            {
                Console.WriteLine(E.StackTrace);
            }
            return "0";
        }



    }

}
