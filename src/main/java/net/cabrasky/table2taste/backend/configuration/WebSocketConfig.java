package net.cabrasky.table2taste.backend.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import net.cabrasky.table2taste.backend.websocket.TableCodeWebSocketHandler;
import net.cabrasky.table2taste.backend.websocket.TicketPrinterWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private TicketPrinterWebSocketHandler ticketPrinterWebSocketHandler;
	
	@Autowired
	private TableCodeWebSocketHandler codeWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(ticketPrinterWebSocketHandler, "/ws/orderPrinter")
        .setAllowedOrigins("*");
        registry.addHandler(codeWebSocketHandler, "/ws/tableCodes")
        .setAllowedOrigins("*");
    }
}
