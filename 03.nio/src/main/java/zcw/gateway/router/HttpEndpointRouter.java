package zcw.gateway.router;

public interface HttpEndpointRouter {

    String route();

    // Load Balance
    // Random
    // RoundRibbon 
    // Weight
    // - server01,20
    // - server02,30
    // - server03,50

}
