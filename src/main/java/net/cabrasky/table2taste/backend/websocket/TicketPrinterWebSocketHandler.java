package net.cabrasky.table2taste.backend.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class TicketPrinterWebSocketHandler extends TextWebSocketHandler {
	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private static final String SECRET_KEY = "your_secret_key";

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String query = session.getUri().getQuery();
        if (query != null && query.contains("key=" + SECRET_KEY)) {
        	System.out.println(String.format("Printer connected from: %s", session.getLocalAddress().toString()));
            sessions.put(session.getId(), session);
        } else {
            session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Unauthorized"));
        }
    }

	
	@Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(String.format("Printer disconnected from: %s", session.getLocalAddress().toString()));
        sessions.remove(session.getId());
    }



	public void broadcast(String message) {
		System.out.println(sessions.values().size());
		
		for (WebSocketSession session : sessions.values()) {
            if (session.isOpen()) {
            	System.out.println("tst");
            	try {
                session.sendMessage(new TextMessage(message));
            	} catch (IOException ex) {
            		ex.printStackTrace();
            	}
            }
        }
	}
}
