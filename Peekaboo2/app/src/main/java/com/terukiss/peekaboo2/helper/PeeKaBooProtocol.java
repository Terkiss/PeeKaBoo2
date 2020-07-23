package com.terukiss.peekaboo2.helper;

import java.io.IOException;



// todo 피카부 프로토콜에 나중에 parser  클래스와 통합이 필요하다 (최적화 )
public class PeeKaBooProtocol {
    public static final int RoomCreate = 0;
    public static final int MesaageSEND = 1;
    public static final int RoomDelete = 2;
    public static final int RoomRequst = 3;
    public static final int ROOMREQUESTRESPONSE = 4;
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
     *  방데이터 요청시
     *  1. 명령어
     *  2. uuid
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
    public static String commandGenerator(int commmand, String...data)
    {
        try
        {
            if(commmand == RoomCreate)
            {
                if(data.length != 5)
                {
                    throw new IOException();
                }
            }
            else if(commmand == MesaageSEND)
            {
                if(data.length != 2)
                {
                    throw new IOException();
                }
            }
            else if(commmand == RoomDelete)
            {
                if(data.length != 4)
                {
                    throw new IOException();
                }
            }
            else if(commmand == RoomRequst)
            {
                if(data.length != 1 )
                {
                    throw new IOException();
                }
            }
            StringBuilder tempCommand = new StringBuilder();
            tempCommand.append(commmand);
            for(int i  = 0 ; i < data.length; i++)
            {
                tempCommand.append("|").append(data[i]);
            }
            return tempCommand.toString();
        }
        catch (IOException E)
        {
            E.printStackTrace();
        }
        return "0";
    }
}