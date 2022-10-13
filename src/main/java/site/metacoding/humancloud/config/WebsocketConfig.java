package site.metacoding.humancloud.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

//    // private final config.웹소켓핸들러 webSocketHandler;
//    private final TestHandler testHandler;
//
//    //    https://brunch.co.kr/@springboot/695
//    // 핸들러는 웹 소켓이 작동하는 곳이고,
//    // 컨피그는 웹소캣을 활성화하기 위한 설정 파일 같은 것
//    // 에나블웹소캣 어노테이션을 통해 웹소캣을 활성화 시키고
//    // 접속 주소(엔드포인트), 접속 유저CORS : setAllowedOrigins("*") => 모든 유저
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
////        registry.addHandler((WebSocketHandler) webSocketHandler, "/test").setAllowedOrigins("*");
//        registry.addHandler(testHandler, "/test")
//                .withSockJS();
//                //.setAllowedOrigins("*"); 이거 sockjs랑 같이 걸면 오류남
//    }

    private final TestHandler testHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/queue", "/topic");

        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(testHandler);
    }

}
