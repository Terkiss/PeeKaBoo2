package com.terukiss.peekaboo2.helper.FileIO;

import android.graphics.Bitmap;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class FileSave {

    private Object files;
    public FileSave()
    {
    }

    public void saveForDevice(String path, String name, Object files)
    {
        this.files = files;
        CheckPath(new File(path), path, name);
    }

    private void CheckPath(File dir, String path, String fileName)
    {
        //디렉토리가 없으면 디렉토리를 만들고 그후에 파일을 카피
        // 디렉토리가 없으면~
        if (!dir.exists())
        {
            // 디렉토리 생성
            dir.mkdirs();
            fileSaves(path+fileName);
            //JeongLog.log.logD("복사 완료 ");
        }
        // 디렉토리가 있니?
        if(dir.exists())
        {
            String filepath = path + fileName ;

            File dataFile = new File(filepath);

            // 데이터 파일이 없으면~~
            if( !dataFile.exists() )
            {
                fileSaves(filepath);
                //JeongLog.log.logD("복사 완료 ");
            }
        }
    }

    public void fileSaves(String path)
    {
        // 복사 합니다 .
        try {
            String filepath = path;

            ObjectOutputStream Ooutstream = new ObjectOutputStream(new FileOutputStream(filepath));

            Ooutstream.writeObject(files);

            Ooutstream.flush();
            Ooutstream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readFile(String path)
    {
        Object objects= null;
        try
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));

            objects = objectInputStream.readObject();

            objectInputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return objects;
    }

    public void bitmapSave(String path, String fileName, Bitmap bmp)
    {
        String file_path = path;
        File dir = new File(file_path);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dir, fileName+".png");
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);

            bmp.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
