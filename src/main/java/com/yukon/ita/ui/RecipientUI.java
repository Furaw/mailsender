package com.yukon.ita.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.yukon.ita.recipient.Recipient;
import com.yukon.ita.recipient.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Route("recipientsui")
public class RecipientUI extends VerticalLayout {
    private final RecipientRepository recipientRepository;

    private Grid<Recipient> grid = new Grid<>();

    @Autowired
    public RecipientUI(RecipientRepository recipientRepository) {
        add(new H1("Table Pecipients"));
        grid.addColumn(Recipient::getRecipientId).setHeader("RecipientId");
        grid.addColumn(Recipient::getRecipientEmail).setHeader("RecipientEmail");
        this.recipientRepository = recipientRepository;
        add(grid);
        grid.setItems(recipientRepository.findAll());
    }

}
