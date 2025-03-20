package com.jhojan.curso.springboot.di.factura.controllers;

import com.jhojan.curso.springboot.di.factura.models.Client;
import com.jhojan.curso.springboot.di.factura.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private Invoice invoice;

    @GetMapping("/show")
    public Invoice show() {
        Invoice i = new Invoice();

        Client c = new Client();
        c.setLastName(invoice.getClient().getLastName());
        c.setName(invoice.getClient().getName());

        i.setClient(c);
        i.setDescription(invoice.getDescription());
        i.setItems(invoice.getItems());
        return invoice;
    }

}
