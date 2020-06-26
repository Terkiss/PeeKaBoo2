using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Numerics;
using System.Threading;

namespace Security
{
    class Data 
    {
        public BigInteger numver;
        public Boolean sosu;
    }
    class Program
    {
        static Boolean[] data = new Boolean[1000];
        static List<Data> items = new List<Data>();
        static long start = 3000000000;
        static long destration = 5;


        private static void findSosu(object dest)
        {
        
            long index = (int)dest;
            
            long startPoint =(start + (destration* index));
            long destPoint = (start + ((index + 1 ) * destration));
            Console.WriteLine("index : "+index+"StartPoint "+startPoint+",  destPoint "+destPoint);
            List<Data> item = new List<Data>();
            for (long i = startPoint; i < destPoint; i++)
            {
                Data data = new Data();
                data.sosu = true;
                data.numver = start + i;

                item.Add(data);
            }

            Console.WriteLine(item.Count);
            for (int i = 0; i < item.Count; i++)
            {
                for (long j = 2; j < item[i].numver; j++)
                {
                    if (item[i].numver % j == 0)
                    {
                        Console.WriteLine("소수가 아님 i 값 " + i + " j값 " + j);
                        item[i].sosu = false;
                        break;
                    }         
                }
                if (item[i].sosu)
                {

                    Console.WriteLine("소수찿기중 현재 발견한 소수 데이터 " + item[i].numver);
                }
            }

            for (int i = 0; i < item.Count; i++)
            {
                items.Add(item[i]);
            }
        }




        static void Main(string[] args)
        {

            Thread[] thread = new Thread[12];

            // 20억 +240만A6
            for (int i = 0; i < thread.Length; i++)
            {
                thread[i] = new Thread(new ParameterizedThreadStart(findSosu));

                thread[i].Start(i);       
            }

            for (int i = 0; i < thread.Length; i++)
            {
                thread[i].Join();
            }
   

            //Thread tread1 = new Thread(new ParameterizedThreadStart(findSosu));

            //Thread thread2 = new Thread(new ParameterizedThreadStart(findSosu));
            //Thread thread3 = new Thread(new ParameterizedThreadStart(findSosu));
            //Thread thread4= new Thread(new ParameterizedThreadStart(findSosu));
            //Thread thread5 = new Thread(new ParameterizedThreadStart(findSosu));
            //Thread thread6 = new Thread(new ParameterizedThreadStart(findSosu));



            //tread1.Start(0);
            //thread2.Start(1);
            //thread3.Start(2);
            //thread4.Start(3);
            //thread5.Start(4);
            //thread6.Start(5);

            //tread1.Join();
            //thread2.Join();
            //thread3.Join();
            //thread4.Join();
            //thread5.Join();
            //thread6.Join();

            items.Sort(delegate (Data A, Data B)
            {
                if (A.numver > B.numver)
                {
                    return 1;
                }
                else if (A.numver < B.numver)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            });
            for (int i = 0; i < items.Count; i++)
            {
                if (items[i].sosu)
                { 
                    Console.WriteLine(items[i].numver);
                }
                
            }

            FileInfo file = new FileInfo("Sosu.txt");

            if (!file.Exists)
            {
                FileStream fs2 = file.Create();
                fs2.Close();
            }

            FileStream fs = file.OpenWrite();
            TextWriter tw = new StreamWriter(fs);

            for (int i = 0; i < items.Count; i++)
            {
                if (items[i].sosu)
                { 
                    tw.WriteLine(items[i].numver);
                }
               
            }

            tw.Close();
            fs.Close();



            //for (int i = 2; i * i < data.Length; i++)
            //{
            //    String datas = "";

            //    if (data[i])
            //    {
            //        for (int j = i * i; j < data.Length - 1; j += i)
            //        {
            //            data[j] = false;
            //        }
            //    }

            //}

            //for (int i = data.Length - 1; i > data.Length - 100; i--)
            //{
            //    if (data[i])
            //    {
            //        Console.WriteLine(i);
            //    }
            //}

            //BigInteger sosu = 1999999973;
            //BigInteger sosu2 = 1999999943;

            //BigInteger nn = sosu * sosu2;

            //BigInteger oiler = (sosu -1 ) * (sosu2 -1);
            //Console.WriteLine(nn);

            //Console.WriteLine("최대 공약수 "+FindGCD(24,7));
            //for (int i = 1; i < 24; i++)
            //{
            //    BigInteger gcd = FindGCD(24, i);
            //    if (gcd < 2)
            //    {
            //        Console.WriteLine(i);
            //    }
            //}

            //FindD(7, 24);
        }
        private static BigInteger FindGCD(BigInteger a, BigInteger b)
        {
            BigInteger gcd;
            if (a > b)//a가 b보다 크면
            {
                //두 수를 교환
                BigInteger temp = a;
                a = b;
                b = temp;
            }
            for (gcd = a; ; gcd--)
            {
                if ((a % gcd == 0) && (b % gcd == 0))
                {     
                    return gcd;
                }
            }
            return -1;
        }


        private static BigInteger FindD(BigInteger e, BigInteger division)
        {

            for (BigInteger d = 2; d < division; d++)
            {
                BigInteger temp = (e * d) % division;
                Console.WriteLine("2d값 " + temp);
                if (temp == 1)
                {
                    Console.WriteLine("d값 " + d);
                    return d;
                }
            }
            return -1;
        }
    }


}
