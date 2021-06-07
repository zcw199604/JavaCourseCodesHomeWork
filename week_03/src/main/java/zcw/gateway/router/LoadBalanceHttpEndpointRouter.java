package zcw.gateway.router;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LoadBalanceHttpEndpointRouter implements HttpEndpointRouter {

    private LinkedList<String> urls;

    private LoadBalanceHttpEndpointRouter() {
        super();
    }

    public LoadBalanceHttpEndpointRouter(List<String> urls) {
        this.urls = urls.stream().collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public String route() {
        if (urls == null || urls.isEmpty())
            return "";
        String first = urls.removeFirst();
        urls.addLast(first);
        return first;
    }
}
