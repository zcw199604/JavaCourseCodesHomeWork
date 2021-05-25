package zcw.gateway.router;

import java.util.*;

public class WeightHttpEndpointRouter implements HttpEndpointRouter {

    private Map<String, Integer> serverMap;

    private List<String> servers = new ArrayList<String>();

    public WeightHttpEndpointRouter() {
        super();
    }

    public WeightHttpEndpointRouter(Map<String, Integer> serverMap) {
        this.serverMap = serverMap;
        Set<String> keySet = serverMap.keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
            String server = it.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                servers.add(server);
            }
        }
    }

    @Override
    public String route() {
        Random random = new Random();
        int idx = random.nextInt(servers.size());
        return servers.get(idx);
    }
}
