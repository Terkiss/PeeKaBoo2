package com.terukiss.peekaboo2.helper;

import java.io.IOException;

public class peeKaBooProtocol {
    public static final int CREATE  = 0;
    public static final int SEND    = 1;
    public static final int DELETE  = 2;


    public peeKaBooProtocol()
    {

    }

    /**
     *  방 생성시
     *  1 명령어
     *  2 최대 접속 인원
     *  3 방태그
     *  4 방 입장 패스워드\
     *  5 uuid
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
    public String commandGenerator(int commmand, String...data) throws IOException
    {
        try {
            if(commmand == CREATE)
            {
                if(data.length != 5)
                {
                    throw new IOException();
                }
            }
            else if(commmand == SEND)
            {
                if(data.length != 3)
                {
                    throw new IOException();
                }
            }
            else if(commmand == DELETE)
            {
                if(data.length != 5)
                {
                    throw new IOException();
                }
            }
            StringBuilder tempCommand = new StringBuilder();
            for(int i  = 0 ; i < data.length; i++)
            {
                tempCommand.append(data[i]).append("|");
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