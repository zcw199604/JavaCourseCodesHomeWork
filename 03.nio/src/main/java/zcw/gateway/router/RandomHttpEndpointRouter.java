package zcw.gateway.router;

import java.util.LinkedList;

public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    @Override
    public String route(LinkedList<String> urls) {
        String first = urls.removeFirst();
        urls.addLast(first);
        return first;
    }
}
