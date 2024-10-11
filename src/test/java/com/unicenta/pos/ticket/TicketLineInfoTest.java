package com.unicenta.pos.ticket;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class TicketLineInfoTest {

    @Test
    public void shouldSendOrderDiscountToPrinter(){

        String json = getText("src/test/resources/TicketLineInfo.json");
        assert json != null;
        TicketLineInfo ticketLineInfo = new Gson().fromJson(json, TicketLineInfo.class);
        assert ticketLineInfo != null;
        sendOrderScript(ticketLineInfo);
    }

    @Test
    public void shouldSendItemDiscountToPrinter() {
        String json = getText("src/test/resources/TicketLineInfoOrderDiscount.json");
        assert json != null;
        TicketLineInfo ticketLineInfo = new Gson().fromJson(json, TicketLineInfo.class);
        assert ticketLineInfo != null;
        sendOrderScript(ticketLineInfo);

    }

    private void sendOrderScript(TicketLineInfo line) {
        boolean printedP1 = false;
        boolean printedP2 = false;
        boolean printedP3 = false;
        boolean printedP4 = false;
        boolean printedP5 = false;
        boolean printedP6 = false;

        if (line.getProperty("product.printer")!=null && line.getProperty("ticket.updated")!=null) {

            if (line.getProperty("product.printer").equals("1")) {
                System.out.print("getting property: ");
                System.out.println(line.getProperty("ticket.updated").equals("true"));
                if((printedP1 == false)) {
                    //sales.printTicket("Printer.Ticket.P1");
                    printedP1 = true;
                }
            }

            if (line.getProperty("product.printer").equals("2")) {
                if((printedP2 == false) && line.getProperty("ticket.updated").equals("true")){
                    //sales.printTicket("Printer.Ticket.P2");
                    printedP2 = true;
                }
            }
            if (line.getProperty("product.printer").equals("3")) {
                if((printedP3 == false) && line.getProperty("ticket.updated").equals("true")){
                    //sales.printTicket("Printer.Ticket.P3");
                    printedP3 = true;
                }
            }

            if (line.getProperty("product.printer").equals("4")) {
                if((printedP4 == false) && line.getProperty("ticket.updated").equals("true")){
                    //sales.printTicket("Printer.Ticket.P4");
                    printedP4 = true;
                }
            }
            if (line.getProperty("product.printer").equals("5")) {
                if((printedP5 == false) && line.getProperty("ticket.updated").equals("true")){
                    //sales.printTicket("Printer.Ticket.P5");
                    printedP5 = true;
                }
            }

            if (line.getProperty("product.printer").equals("6")) {
                if((printedP6 == false) && line.getProperty("ticket.updated").equals("true")){
                    //sales.printTicket("Printer.Ticket.P6");
                    printedP6 = true;
                }
            }
        }
    }

    private String getText(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}