package site.metacoding.humancloud.socket.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;



@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        // log.info("Stomp Hanler 실행");
        // jwt 인증 처리할 때 필요 : 컨트룰러 이전에 이 핸들러 먼저 실행됨
        return message;
    }
}
