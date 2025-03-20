package com.jhojan.curso.springboot.di.factura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope
@JsonIgnoreProperties({ "targetSource", "advisors" })
public class Invoice {

    @Autowired
    private Client client;

    @Value("${invoice.description.oficina}")
    private String description;

    @Autowired
    @Qualifier("itemsInvoiceOffice")
    private List<Item> items;

    public Invoice() {
        System.out.println("Invoice constructor:");
        System.out.println("DESCRIPTION: " + description);
    }

    @PostConstruct
    public void init() {
        System.out.println("Invoice init:");
        System.out.println("DESCRIPTION: " + description);
        client.setName(client.getName().concat(" Pepe"));
        description = description.concat(" del cliente: ").concat(client.getName()).concat(" ").concat(client.getLastName());
        // YA SE REALIZO LA INYECCIÓN DE DEPENDENCIAS Y DEMÁS
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destruyendo el bean o componente invoice!");
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public int getTotal() {
        return items.stream()
                .map(Item::getMatter)
                .reduce(0, Integer::sum);
    }
}
