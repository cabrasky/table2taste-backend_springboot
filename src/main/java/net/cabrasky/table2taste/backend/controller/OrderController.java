package net.cabrasky.table2taste.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.cabrasky.table2taste.backend.model.MenuItem;
import net.cabrasky.table2taste.backend.modelDto.OrderDTO;
import net.cabrasky.table2taste.backend.repository.MenuItemRepository;
import net.cabrasky.table2taste.backend.websocket.TicketPrinterWebSocketHandler;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
    private TicketPrinterWebSocketHandler ticketPrinterWebSocketHandler;


	@Autowired
    private MenuItemRepository menuItemRepository;
	
    @PostMapping
    public ResponseEntity<String> sendMessageToWebSocketClients(@RequestBody OrderDTO order) {
    	JsonObject ticketData = new JsonObject();
        
        // Add the current_date field
        ticketData.addProperty("current_date", "2024-04-03");
        
        // Create the items array
        JsonArray items = new JsonArray();
        
        for(OrderDTO.OrderItem orderItem: order.orderItems) {
        	JsonObject item = new JsonObject();
        	MenuItem mI = menuItemRepository.findById(orderItem.id).get();
        	item.addProperty("name", mI.getTranslations().stream().filter(t -> t.getLanguage().getId().equals("en") && t.getTranslationKey().equals("name")).findFirst().get().getValue());
        	item.addProperty("quantity", orderItem.quantity);
        	item.addProperty("price", mI.getPrice());
            items.add(item);
        }
        
        ticketData.add("items", items);
        
        // Add the total_amount field
        ticketData.addProperty("total_amount", 27.97);
        ticketPrinterWebSocketHandler.broadcast(ticketData.toString());
        return ResponseEntity.ok("Message sent to all WebSocket clients");
    }
}