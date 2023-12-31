package com.project.two;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.project.two.server.ProjectEnums.ConnectionType;
import com.project.two.server.ProjectEnums.ServiceKeys;
import com.project.two.server.Server;
import com.project.two.server.ServerConfig;
import com.project.two.utills.BusinessException;

public class ServerRun {

	public static void run(String[] args) throws Exception {
		ConnectionType type = Enum.valueOf(ConnectionType.class, args[0]);
		ServerConfig tcpSConfig = new ServerConfig();
//		String clientHost = "localhost";
//		int clientUDPPort = -1;
		int tcpPort = -1, udpPort = -1, rpcPort = -1;
		ServiceKeys rpcKey = null;
		String serverHost = (args.length >= 2) ? args[1] : "localhost";
		if (type == ConnectionType.TCP) {
			tcpPort = (args.length >= 3) ? Integer.parseInt(args[2]) : -1;
		} else if (type == ConnectionType.UDP) {
			udpPort = (args.length >= 3) ? Integer.parseInt(args[2]) : -1;
//			clientHost = (args.length >= 4) ? args[3] : "localhost";
//			clientUDPPort = (args.length >= 5) ? Integer.parseInt(args[4]) : -1;
		} else if (type == ConnectionType.RPC) {
			rpcPort = (args.length >= 3) ? Integer.parseInt(args[2]) : -1;
			if (args.length >= 4) {
				rpcKey = ServiceKeys.valueOf(args[3]);
			} else {
				throw new BusinessException("Please provide Service key");
			}
		} else if (type == ConnectionType.ALL) {
			tcpPort = (args.length >= 3) ? Integer.parseInt(args[2]) : -1;
			udpPort = (args.length >= 4) ? Integer.parseInt(args[3]) : -1;
//			clientHost = (args.length >= 5) ? args[4] : "localhost";
//			clientUDPPort = (args.length >= 6) ? Integer.parseInt(args[5]) : -1;
			rpcPort = (args.length >= 5) ? Integer.parseInt(args[4]) : -1;
			if (args.length >= 6) {
				rpcKey = ServiceKeys.valueOf(args[5]);
			} else {
				throw new BusinessException("Please provide Service key");
			}
		}
		ExecutorService executor = Executors.newCachedThreadPool();
		tcpSConfig.setTcpPort(tcpPort);
		tcpSConfig.setUdpPort(udpPort);
		tcpSConfig.setRpcPort(rpcPort);
		tcpSConfig.setExecutor(executor);
		tcpSConfig.setType(type);
//		tcpSConfig.setClientHost(clientHost);
		tcpSConfig.setServerHost(serverHost);
//		tcpSConfig.setClientUDPPort(clientUDPPort);
		tcpSConfig.setServiceKey(rpcKey);
		tcpSConfig.setLogs(false);
		Server server = new Server(tcpSConfig);
		server.startServer();
//        Thread.sleep(60000);
//        server.stopServer();
	}

}
