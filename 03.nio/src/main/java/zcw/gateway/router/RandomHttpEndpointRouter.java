package zcw.gateway.router;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    private List<String> urls;

    private RandomHttpEndpointRouter() {
        super();
    }

    public RandomHttpEndpointRouter(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String route() {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));
    }
}
