package zcw.gateway.router;

import java.util.LinkedList;
import java.util.List;

public interface HttpEndpointRouter {
    
    String route(LinkedList<String> endpoints);
    
    // Load Balance
    // Random
    // RoundRibbon 
    // Weight
    // - server01,20
    // - server02,30
    // - server03,50
    
}
