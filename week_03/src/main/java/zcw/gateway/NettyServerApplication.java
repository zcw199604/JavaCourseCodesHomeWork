package zcw.gateway;


import zcw.gateway.inbound.HttpInboundServer;
import zcw.gateway.router.HttpEndpointRouter;
import zcw.gateway.router.LoadBalanceHttpEndpointRouter;
import zcw.gateway.router.RandomHttpEndpointRouter;
import zcw.gateway.router.WeightHttpEndpointRouter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NettyServerApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";

    public static void main(String[] args) {

        // 默认端口8888
        String proxyPort = System.getProperty("proxyPort", "8888");

        // 这是多个后端url走随机路由的例子
        String proxyServers = System.getProperty("proxyServers", "http://localhost:8801,http://localhost:8802");
        String proxyServers_ = System.getProperty("proxyServers", "http://localhost:8803,http://localhost:8804");
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");


        Map<String, HttpEndpointRouter> proxy = new HashMap<>();
        proxy.put("/aaa", new LoadBalanceHttpEndpointRouter(Arrays.asList(proxyServers.split(","))));
        proxy.put("/bbb", new RandomHttpEndpointRouter(Arrays.asList(proxyServers_.split(","))));

        Map<String, Integer> serverMap = new HashMap<>();
        serverMap.put("http://localhost:8805", 1);
        serverMap.put("http://localhost:8806", 5);

        proxy.put("/ccc", new WeightHttpEndpointRouter(serverMap));

        HttpInboundServer server = new HttpInboundServer(port, proxy);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
