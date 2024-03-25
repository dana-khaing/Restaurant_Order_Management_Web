package com.oaxaca.order_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.oaxaca.order_service.service.CustomerWebSocketService;
import com.oaxaca.order_service.service.KitchenStaffWebSocketService;
import com.oaxaca.order_service.service.WaiterWebSocketService;




@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    @NonNull
    private WaiterWebSocketService waiterWebSocketHandler; 

    @Autowired
    @NonNull
    private KitchenStaffWebSocketService kitchenStaffWebSocketHandler;

    @Autowired
    @NonNull
    private CustomerWebSocketService customerWebSocketHandler;


    

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        registry.addHandler(waiterWebSocketHandler, "/waiter-orders").setAllowedOrigins("localhost:3000, http://oaxaca.hopto.org/");
        registry.addHandler(kitchenStaffWebSocketHandler, "/kitchen-orders").setAllowedOrigins("localhost:3000, http://oaxaca.hopto.org/");
        registry.addHandler(customerWebSocketHandler, "/customer-orders").setAllowedOrigins("localhost:3000, http://oaxaca.hopto.org/");
    }
}