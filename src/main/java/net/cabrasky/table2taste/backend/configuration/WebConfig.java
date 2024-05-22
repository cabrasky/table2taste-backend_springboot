package net.cabrasky.table2taste.backend.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.cabrasky.table2taste.backend.websocket.TicketPrinterWebSocketHandler;



@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
    
    @Bean
    public TicketPrinterWebSocketHandler ticketPrinterWebSocketHandler() {
        return new TicketPrinterWebSocketHandler();
    }

}
