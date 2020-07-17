using System;
using System.Collections.Generic;
using System.Text;

namespace PeeKaBoo
{
    class ServerConfiguration
    {
        private static string serverName;
        private static string serverport;
        private static string disclosureStatus;
        private static string serverIntroduction;
        private ServerConfiguration()
        { 
        
        }

        public static string ServerName { get => serverName; set => serverName = value; }
        public static string Serverport { get => serverport; set => serverport = value; }
        public static string DisclosureStatus { get => disclosureStatus; set => disclosureStatus = value; }
        public static string ServerIntroduction { get => serverIntroduction; set => serverIntroduction = value; }
    }
}
