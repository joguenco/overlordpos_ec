//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2018 uniCenta & previous Openbravo POS works
//    https://unicenta.com
//
//    This file is part of uniCenta oPOS
//
//    uniCenta oPOS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   uniCenta oPOS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with uniCenta oPOS.  If not, see <http://www.gnu.org/licenses/>.

package com.unicenta.pos.payment;

import com.unicenta.plugins.Application;
import com.unicenta.plugins.common.AppContext;
import com.unicenta.pos.util.RoundUtils;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.ArrayList;

@Slf4j
public class PaymentGatewayTeya implements PaymentGateway {

    /** Creates a new instance of PaymentGatewayExt */
    public PaymentGatewayTeya() {
    }

    Window getPaymentWindow(double amount) {
        if (amount > 0) {
            ArrayList<JPaymentSelectReceipt> paymentWindows = new ArrayList<>();
            for (Window window : Window.getWindows()) {
                if (window instanceof JPaymentSelectReceipt) {
                    paymentWindows.add((JPaymentSelectReceipt) window);
                }
            }
            return paymentWindows.get(paymentWindows.size()-1);
        }
        else {
            ArrayList<JPaymentSelectRefund> paymentWindows = new ArrayList<>();
            for (Window window : Window.getWindows()) {
                if (window instanceof JPaymentSelectRefund) {
                    paymentWindows.add((JPaymentSelectRefund) window);
                }
            }
            return paymentWindows.get(paymentWindows.size()-1);
        }

    }

    /**
     *
     * @param payinfo
     */
    @Override
    public void execute(PaymentInfoMagcard payinfo) {

        int timer = 0;
        int timeout = 150;

        double roundedValue = RoundUtils.round(payinfo.getTotal());

        new Application().teyaTransaction(roundedValue, getPaymentWindow(roundedValue), payinfo.getTransactionID());

        while (AppContext.getTeyaPaymentResult() == null) {
            try {
                log.info("uniCenta-oPos: waiting for payment to complete ....");
                Thread.sleep(1000);
                timer += 1;
                if (timer > timeout) break;
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }

        if (AppContext.getTeyaPaymentResult().getStatus().equals("SUCCESSFUL")) {
            payinfo.setCardName("Teya");
            payinfo.setVerification("Chip and Pin");
            payinfo.setChipAndPin(true);
            payinfo.paymentOK(
                    roundedValue > 0 ? AppContext.getTeyaPaymentResult().getReferenceId() : "",
                    roundedValue > 0 ? AppContext.getTeyaPaymentResult().getGatewayPaymentId() : "",
                    AppContext.getTeyaPaymentResult().getStatus()
            );
        }
        else {
            payinfo.paymentError("Transaction Error! Please try again", AppContext.getTeyaPaymentResult().getStatus());
        }



    }
}
