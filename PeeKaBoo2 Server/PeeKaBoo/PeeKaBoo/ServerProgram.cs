using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using PeeKaBoo.Network;
namespace PeeKaBoo
{

    class ServerProgram
    {

        bool run = true;

        byte[] receiveBytes = new byte[2000];
        byte[] sendBytes = new byte[6000];

        public ServerProgram()
        {

        }

        public void ServerStart()
        {
            run = true;
            Thread thread = new Thread(new ThreadStart(serverProcess));
            thread.Start();
        }

        public void ServerStop()
        {
            run = false;
        }

        private void serverProcess()
        {
            Socket serverSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.IP);

            serverSocket.Bind(new IPEndPoint(IPAddress.Any, 10000));
            while (run)
            {
                // 서버 최대 접속 인원
                serverSocket.Listen(100);
                Console.WriteLine(" 클라이언트의 클라이언트 연결 대기..............");
                Socket transferSock = serverSocket.Accept();
               
                Console.WriteLine(" 클라이언트의 연결이 성공..............");


                transferSock.BeginReceive(receiveBytes, 0, receiveBytes.Length, SocketFlags.None, new AsyncCallback(receiveStr), transferSock);

                //sendBytes = Encoding.Default.GetBytes("c# server에서 데이터를 보냇어!\r");

                //transferSock.BeginSend(sendBytes, 0, sendBytes.Length, SocketFlags.None, new AsyncCallback(sendStr), transferSock);



                // 테스트 시작
                //MultiThreadedClientReceiver multiThreadedClient = new MultiThreadedClientReceiver(transferSock);

                

            }
        }






        private void receiveStr(IAsyncResult ar)
        {
            Socket transferSock = (Socket)ar.AsyncState;
            int strLength = transferSock.EndReceive(ar);

            Console.WriteLine("---------------------------------------");
            Console.WriteLine(" 클라이언트의 데이터 수신이 성공.......");
            Console.WriteLine("---------------------------------------");
            Console.WriteLine(Encoding.Default.GetString(receiveBytes));


            // 데이터 베이스 를 기록 합니다.
            // 혹은 데이터 를 이용할 클래스를 생성하는 
            // 생성자 호출 
            String ReceiceData = Encoding.Default.GetString(receiveBytes);

            // 2020 - 09 - 13 너무 긴 데이터는 꺠지는 문제 발생 
            // 1024바이트 이내로 전송시 일단은 안꺠지는거 90% 확인 
            // string sendData = "튼튼하고 치밀한 구성과[14] 줄거리, 연금술이라는 매력적인 소재, 적절한 완급 조절, 현실적이면서 독창적인 설정,[15] 뛰어난 연출과 액션, 과학과 사회에 대한 고찰, 철학적 담론, 줄거리를 관통하며 세련되게 어우러지는 주제의식과 그 주제의식에 잘 부합하면서도 매력있는 캐릭터, 그 캐릭터들의 적절한 활용, 그리고 모든 떡밥 회수에 성공하면서 작품 테마에 맞아떨어지는 시원하고 깔끔한 마무리로 모든 면에서 완성도가 매우 높다는 평을 받고 있는 작품이다. 이 문서만 봐도 알겠지만 일반 독자들과 평론가, 업계인 사이에서 원작과 실사영화를 제외한 미디어 믹스를 통틀어 호평이 끊이질 않는 작품이다. 결계사, 크로노 크루세이드 같이 모든 면에서 탄탄한 만화가 많이 있지만, 강철의 연금술사처럼 미디어 믹스까지 활발하게 만들어지고 성공한 작품들은 별로 없다.스토리는 소년만화의 정석인 모험 활극적인 면, 이해하기 쉬우면서도 튼튼한 구성, 무게 있는 소재를 사용하면서도 언제나 개그를 잃지 않아 작품 분위기가 암울해지지 않는다는 점이 장점으로 꼽힌다. 구성도 치밀해서 초반부의 사소한 장면들이 후반부에서 결정적인 작용을 하는가 하면 엑스트라에 불과한 줄 알았던 인물이 매우 큰 활약을 하는 전개가 매우 많다.최근 만화에서 보이는 캐릭터를 기반으로 미디어 믹스, 후속작, 외전 전개를 목표로 세부적인 섬세함을 내세운 작품들과는 달리, 단편에서 볼 수 있을 법한 결말을 염두에 둔 작품으로 전체의 완성도에 중점을 두고 완성한 작품이라고 볼 수 있다. 실제로 아메스트리스의 위치를 지도에서 보면 주변국과 전쟁, 외교전, 첩보전 등이 벌어지기 좋은 위치지만 주변국과의 일은 대충 이러한 일이 있었다고 설명만 하고 자세한 묘사는 거의 하지 않는다. 이런 면도 무대가 넓어지면서 스케일이 거대해지는 장편보다는, 무대가 계속 제한적인 스케일로 전개되는 단편과 비슷하다. 실제로 장기 연재 만화, 그것도 배틀 중심의 소년만화에서 이렇게 완성된 짜임새와 스토리적 재미를 보여주면서 동시에 인간에 대한 철학적인 담론까지 자연스럽게 엮어낸 작품은 매우 드물다.반전(反戰), 근현대 과학의 도덕성, 영혼의 존재, 정치, 종교, 민족 등에 대해 생각할 거리를 상당히 많이 던져주는 작품. 근대에 등장한 소설의 관점에서 강철의 연금술사를 바라본 글강철의 연금술사의 진정한 강점은 정점에 있던 수많은 소년만화들이 저지르는 대부분의 범실을 저지르지 않은 것.즉, 파워 인플레와 그에 따른 조연들의 공기화, 특정 캐릭터 편애, 가해자 미화[16], 캐릭터 붕괴, 끝도 없이 늘어나는 설정오류, 산화되는 주제, 반복되는 스토리 구조와 떨어지는 개연성, 그에 따른 용두사미식 진행이 일어나지 않았다는 것이다.[17]그리고 다른 소년만화들이 비교되어 비판을 받으면서 반작용으로 이 작품의 평가가 높아진 탓도 있다.가히리, 나루토, 블리치, 페어리 테일, 원피스, 귀멸의 칼날 등 수많은 소년만화들이 장기연재에 돌입하자 개연성 상실, 설정 충돌, 떡밥회수 실패, 캐릭터 붕괴, 주제의식 이탈 같은 문제점을 무시히 발생시켜 비판을 겪었는데 장편 만화들의 문제점이 새로 드러날 때마다 계속 재조명 되고 있다. 최고의 소년만화가 무엇인가에 대해서는 다양한 작품들이 거론되지만, 완벽한 소년만화가 무엇인가에 대해서는 대부분 이 작품을 꼽는 이유이기도 하다.팬들은 입을 모아 이 작품이 주간 연재지에서 연재되지 않은 게 다행이라고 말한다. 상대적으로 비주류인 잡지에 연재되어서 지나치게 상업성을 추구하다가 작품성이 떨어지는 일이나 무리한 장기 연재를 피해갈 수 있었다.[18] 그리고 주간 연재는 작가가 다음 페이지 메꾸는 데에도 힘이  \n들어서 전에 쓴 이야기를 기억해내거나 다시 읽어 확인하는 것도 힘들기 때문에 뛰어난 스토리 텔링이 장점인 강연금에겐 더욱 치명적이었을 것이다.그래서 소년 점프나 토에이 동화, 반다이같은 대형 기획사의 손이 타지 않은게 오히려 다행이었다고 평가된다.이런 대기업들과 작업했더라면 시작부터 관심과 주목을 받았거나 산업규모는 더 거대했을지 모르나[19] 작품의 질이 떨어지지 않고 유지되기는 힘들었을 것이다.굳이 단점을 꼽자면 캐릭터들의 성격이 너무 입체적이고, 스토리가 너무나 깔끔하게 끝나서 2차 창작이 어렵다보니 인기몰이 기간이 연재 당시의 인기나 명성에 비해 굉장히 짧았다는 점이다.보통 2차 창작에서는 원작의 떡밥, 불만족스러웠던 점, 자잘한 설정이나 뒷이야기를 가지고 썰을 푼다. 그런데 강철의  \n연금술사는 뿌려둔 떡밥을 전부 회수해 깔끔한 결말을 냈으며 스토리에서 주인공 형제와 관련된 큰 흐름에서 벗어난 자잘한 설정이 거의 없기 때문에[20], 1차 창작에 가깝게 창작하지 않는 이상 2차 창작 소재가 정말 없다.다만 이건 원작과 원작을 충실히 따라간 신강철 얘기고 구강철에서는 동인계에서의 2차 창작이 활발했다.[21]결말부에서 스카와 머스탱이 처벌을 안 받은 것에 대해서는 독자들 사이에서 설왕설래가 있다. 그러나 독자들의 생각 차이에서 호오가 갈릴 수 있어도, 개연성이나 핍진성 면에서 문제가 있는 것은 아니다.그리고 완성도에서 흠잡을 때가 없지만, 거꾸로 그 때문에 에피소드 별 임팩트는 상대적으로 덜하게 느껴진다.수많은 소년만화들이 순간의 극적 효과를 위해 앞에서 쌓아온 개연성과 캐릭터성을 무시하는 과오를 저지른 반면 본작은 임팩트를 위해 팩트를 무시하지 않았기 때문이다.전율이 이는 장면들은 앞에서 차곡차곡 쌓아올린 캐릭터성과 전개가 극대화하여 폭발하는 후반부에 집중되어 있으며 완독후 지금 이 전개가 어디서부터 왔는지 하나하나 되집어 가는 재미가 있다.완성도가 높은 작품인 만큼 만화 평론가, 일반 독자, 업계인들을 통틀어 만화 및 애니메이션 명작 이야기가 나올 때면 항상 거론된다.특히 서양에서 평가가 높은데 작품이 완결된지 10년이 흘렀지만 MyAnimeList, animenewsnetork, IMDB TVA부문을 비롯한 여러 만화 / 애니메이션 순위 선정 사이트에서 원작과 애니 모두 최상위에 랭크되고 있다.그런고로 10년이 지난 지금도 일본 올타임 만화/ 애니메이션을 꼽을 때 항상 1, 2위로 꼽힌다.[22]2020년 일본인 62만명이 뽑은 나를 구성하는 만화 랭킹에서 1위를 기록하기도 했다.#끝 테루키스||| \n";
            //string sendData = "1234|||\n";
            // 여기 임시 주석처리
            string sendData;
            sendData = Parser.CommandParser(ReceiceData);

            // 보내기전 초기화
            for (int i = 0; i < receiveBytes.Length; i++)
            {
                receiveBytes[i] = 0;
            }

            // 데이터가 보내는 바이트  보다 커지면 문제가 발생 할 소지가 있음 추후에 수정 
           // sendBytes = Encoding.Default.GetBytes(sendData);
            Byte[] send = Encoding.Default.GetBytes(sendData);
            String sendCommand = Encoding.Default.GetString(send);
            sendBytes = send;
       
            Console.WriteLine(sendData.Length+"보내는 데이터의 길이 " +send.Length );
            Console.WriteLine(sendCommand);
            transferSock.BeginSend(sendBytes, 0, sendBytes.Length, SocketFlags.None, new AsyncCallback(sendStr), transferSock);
            //transferSock.Close();
        }

        private void sendStr(IAsyncResult ar)
        {
            Socket transferSock = (Socket)ar.AsyncState;


            for (int i = 0; i < sendBytes.Length; i++)
            {
                sendBytes[i] = 0;
            }


            Console.WriteLine("---------------------------------------");
            Console.WriteLine(" 클라이언트의 데이터 전송이 성공.......");
            Console.WriteLine("---------------------------------------");
            int strLength = transferSock.EndSend(ar);
            //Console.WriteLine("Strlength {0}만큼 보내기 성공", strLength);
        }
    }
}
