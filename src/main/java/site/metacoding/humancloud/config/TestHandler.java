package site.metacoding.humancloud.config;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TestHandler extends TextWebSocketHandler {
    //        private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>(); // 로그인 된 유저 담을 곳
    private Map<String, WebSocketSession> userSessionsMap = new HashMap<String, WebSocketSession>(); // 의문?

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception { //클라이언트와 서버 연결
//            sessions.add(session);
//            String senderId = currentUserName(session);
//            userSessionsMap.put(senderId,session);
//        }
//        private String currentUserName(WebSocketSession session) {
//            Map<String, Object> httpSession = session.getAttributes(); // principal user1, principal user2, ... 이런 식으로 맵 저장
////            MemberVO loginUser = (MemberVO)httpSession.get("login");
//
////        if(loginUser == null) {
//            String mid = session.getId();
//            return mid;
////        }
        String sessionId = session.getId();
        userSessionsMap.put(sessionId, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {// 메시지
        String msg = message.getPayload();//자바스크립트에서 넘어온 Msg

        if (msg != null || msg != "") {
            String[] str = msg.split(",");
            String cmd = str[0];
            String subscriber = str[1];
            String company = str[2];

            WebSocketSession subscriberSession = userSessionsMap.get(subscriber);
            WebSocketSession companySession = userSessionsMap.get(company);

            // 구독하기
            if ("subscribe".equals(cmd) && companySession != null) {
                TextMessage tmpMsg = new TextMessage(subscriber + "님이 " + "구독함!");
                companySession.sendMessage(tmpMsg);
            }
        }
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {//연결 해제
        userSessionsMap.remove(session);
    }
}
