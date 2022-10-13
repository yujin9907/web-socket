package config;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class 웹소켓핸들러 extends TextWebSocketHandler{

    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<String, WebSocketSession>();

    //사용자(브라우저)가 웹 소켓에 붙게 되면 동작
    // 웹소캣세션이 생성되고, 스태틱 변수인 clients 객체에 넣어, 고유아이디를 키값으로 세션값을 넣음
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        CLIENTS.put(session.getId(), session);
    }

    // 접속이 끊어지면 clients에 등록된 아이디값을 제거함
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        CLIENTS.remove(session.getId());
    }

    // 사용자의 메세지를 받으면 동작
    // clients 객체에 접속한 키값을 통해, 자신 외 사용자에게 메시지를 전달하는 거임
    // 메시지를 전달하는 로직자체는 session객체의 센드메시지 코드를 통해 동작함(clients가 없다면 자신에게만 동작함)
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String id = session.getId();  //메시지를 보낸 아이디
        CLIENTS.entrySet().forEach( arg->{
            if(!arg.getKey().equals(id)) {  //같은 아이디가 아니면 메시지를 전달합니다.
                try {
                    arg.getValue().sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}