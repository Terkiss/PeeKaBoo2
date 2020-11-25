using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Numerics;
using System.Runtime.CompilerServices;
using System.Threading;

namespace Security
{
    class Data 
    {
        public BigInteger numver;
        public Boolean sosu;
    }
    class KeyData 
    {
        public BigInteger Ekey;
        public BigInteger Dkey;
    }
    class Program
    {
        static Boolean[] data = new Boolean[1000];
        static List<Data> items = new List<Data>();
        static long start = 3000000000;
        static long destration = 5;




        static List<KeyData> KeyValue = new List<KeyData>();

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

        private static void threadFindSosu()
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

            // 아리스토 의 체 


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
        //6000000047
        //6000000049
        static BigInteger left = 6000000047;
        static BigInteger right = 6000000049;
        static BigInteger divid = 100000;

        static void Main(string[] args)
        {


            //  36000000564000002208
            //  1500000023500000092
            BigInteger oilerValue = (left -1) * (right -1);
            BigInteger eValue = 0;
            BigInteger dValue = 0;


            //for (int i = 1; i < left * right; i++)
            //{
            //    BigInteger yaksu = FindGCD((left * right), i);
            //    if(yaksu == 1)
            //    {
            //        Console.WriteLine(i + "  |  "+ (left * right));
            //    }
                
            //}



            Console.WriteLine("최대 공약수 " + FindGCD(24, 7));

            Console.WriteLine("오일러 평선 실행결과 " + oilerValue);
            //eValue = findE(left, right);
            Console.WriteLine("E 값 찾기 : "+ eValue);

            //dValue = findD(eValue, oilerValue);
            Console.WriteLine("D 값 찿기 : "+ dValue);

            BigInteger a = 5;
            BigInteger c = 0;
            BigInteger d = 0;
            // 평문이 5일떄 암호화
            c = (IntPow(a,53)) % (left*right);
            Console.WriteLine((left * right) +"평문 5 가 다음과 같이 암호화 :: " + c);

            d = (IntPow(c, 197)) % (left * right);
            Console.WriteLine("암호문이 다음과 같이 복호화 :: " + a);


            Console.WriteLine("키를 생성 합니다");
            Thread[] thread = new Thread[24];

            // 20억 +240만A6
            for (int i = 0; i < thread.Length; i++)
            {
                thread[i] = new Thread(new ParameterizedThreadStart(findEthread));
                Console.WriteLine(i + "번 스레드 실행");
                thread[i].Start(i);
            }

            for (int i = 0; i < thread.Length; i++)
            {
                thread[i].Join();
            }

            KeyValue.Sort(delegate (KeyData A, KeyData B)
            {
                if (A.Ekey > B.Ekey)
                {
                    return 1;
                }
                else if (A.Ekey < B.Ekey)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            });



            FileInfo file = new FileInfo("KeyValue.txt");

            if (!file.Exists)
            {
                FileStream fs2 = file.Create();
                fs2.Close();
            }

            FileStream fs = file.OpenWrite();
            TextWriter tw = new StreamWriter(fs);

            tw.WriteLine("left=" + left);
            tw.WriteLine("right=" + right);

            for (int i = 0; i < KeyValue.Count; i++)
            {
             
                tw.WriteLine("Ekey =" + KeyValue[i].Ekey + ", Dkey =" + KeyValue[i].Dkey);
            }

            tw.Close();
            fs.Close();


        }
        static BigInteger IntPow(BigInteger x, BigInteger pow)
        {
            BigInteger ret = 1;
            while (pow != 0)
            {
             
                if ((pow & 1) == 1)
                {   // 일쪽에 숫자가 있다면 x를 곱함 
                   
                    ret *= x;
                }
                
                x *= x;
                pow >>= 1;

            }
            return ret;
        }
        //111 2 1 
        //011 4 2
        //001 16 8
        //000 32 16


        // 오일러 파이 함수 는 1부터 n-1 까지 양의 정수 중에서 n과 서로소 의 관계에 있는
        // 정수들의 개수를 나타냅니다.
        // 두개의 정수가 서로소 라고 하는것은 두 숫자의 최대 공약수가 1인것을 말함
        private static BigInteger oilerPieFuntion(BigInteger left, BigInteger right)
        {
            BigInteger result = left * right;
            int oilerPieValue = 0;

            if (checkSosu(result))
            {
                result--;
            }
            else if (checkSosu(left) && checkSosu(right))
            {
                result = (left - 1) * (right - 1);
            }

            return result;

        }

        private static Boolean checkSosu(BigInteger check)
        {
            for (long j = 2; j < check; j++)
            {
                if (check % j == 0)
                {
                    return false;
                    break;
                }
            }

            return true;
        }

        private static void findEthread(Object index)
        {
            // 1500000023500000092
          
            BigInteger oilerNum = (left - 1) * (right - 1);
            BigInteger startPoint = 48000000+(divid * (int)index); ;
            BigInteger destPoint = 48000000 + (((int)index + 1) * divid);
        

            List<BigInteger> findValue = new List<BigInteger>();

            if (startPoint == 0)
            {
                startPoint = 1;
            }


            for (BigInteger i = startPoint; i < destPoint; i++)
            {
                BigInteger temp = FindGCD(oilerNum, i);
                if (temp < 2)
                {
                    Console.WriteLine("데이터 삽입 "+i);
                    findValue.Add(i);
                }
               

            }
            Console.WriteLine("findValue :: " + findValue.Count);
            for (int i = 0; i < findValue.Count; i++)
            {
                //BigInteger Dkey = findD(findValue[i], oilerNum);

                //if (Dkey > 0)
                //{
                    KeyData keyData = new KeyData();
                    keyData.Ekey = findValue[i];
                    keyData.Dkey = -1;

                    KeyValue.Add(keyData);

                    Console.WriteLine(i + " d값 연산중");
                //}
              

            }
        }
    

        // e 값을 정함
        // e는 1 < e < n 값 이며 p(n) 값 관느 서로소가 되어야 함
        private static BigInteger findE(BigInteger left, BigInteger right) 
        {

            List<BigInteger> findValue = new List<BigInteger>();
            BigInteger oilerNum = (left - 1) * (right - 1);


            for (int i = 1; i < oilerNum; i++)
            {
                BigInteger temp = FindGCD(oilerNum, i);
                if (temp < 2)
                {
                    Console.WriteLine("찾은 E 키 값 : " + i);
                    findValue.Add(i);
                }
                
            }






            FileInfo file = new FileInfo("KeyValue.txt");

            if (!file.Exists)
            {
                FileStream fs2 = file.Create();
                fs2.Close();
            }

            FileStream fs = file.OpenWrite();
            TextWriter tw = new StreamWriter(fs);

            tw.WriteLine("left=" + left);
            tw.WriteLine("right=" + right);

            for (int i = 0; i < findValue.Count; i++)
            {
                BigInteger dkey = findD(findValue[i], (left - 1) * (right - 1));
                tw.WriteLine("Ekey =" + findValue[i] + ", Dkey =" + dkey);
            }

            tw.Close();
            fs.Close();




            Console.WriteLine("인덱스 값 :: " + (int)MathF.Round(findValue.Count / 2));
            return findValue[(int)MathF.Round(findValue.Count / 2)];
        }
        private static BigInteger findD(BigInteger Evalue, BigInteger OilerPieFunctionValue)
        {
            for (int i = 1; i < OilerPieFunctionValue; i++)
            {
                BigInteger temp = (Evalue * i) % OilerPieFunctionValue;
                //Console.WriteLine("오일러 평선 값 : " + OilerPieFunctionValue +" E 값 :"+Evalue+ "  D 값 :" + i + "  temp:" +temp);
                if (temp == 1)
                {
                    return i;
                }
            }
            return -1;
        }


        // 최대 공약수 구함 
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
                //Console.WriteLine("a " + a + " gcd " + gcd + " b " + b);
                if ((a % gcd == 0) && (b % gcd == 0))
                {     
                    return gcd;
                }
            }
            return -1;
        }


    }


}
