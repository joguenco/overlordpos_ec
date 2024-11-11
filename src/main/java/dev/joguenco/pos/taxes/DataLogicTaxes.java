/*
 * Copyright (C) 2024 jorgeluis
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package dev.joguenco.pos.taxes;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.DataParams;
import com.unicenta.data.loader.DataRead;
import com.unicenta.data.loader.PreparedSentence;
import com.unicenta.data.loader.SerializerReadClass;
import com.unicenta.data.loader.SerializerWriteParams;
import com.unicenta.data.loader.SerializerWriteString;
import com.unicenta.data.loader.Session;
import com.unicenta.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Luis
 */
public class DataLogicTaxes extends BeanFactoryDataSingle {

    private Session s;

    @Override
    public void init(Session s) {
        this.s = s;
    }

    public PreparedSentence getTax() {
        return new PreparedSentence(s,
                "SELECT id, name, rate, category "
                + "from taxes "
                + "where id = ? ",
                SerializerWriteString.INSTANCE,
                (DataRead dr) -> {
                    TaxInfo taxInfo = new TaxInfo();

                    taxInfo.readValues(dr);

                    return taxInfo;
                }
        );
    }
    
    public List<Product> getProducts(Session s, String currentTax) {
        try {
            var sent = new PreparedSentence(
                    s,
                    "SELECT id, name, pricesell "
                    + "from products "
                    + "WHERE taxcat = ?",
                    SerializerWriteString.INSTANCE,
                    new SerializerReadClass(Product.class));

            return sent.list(currentTax);

        } catch (BasicException ex) {
            System.out.println("BasicException " + ex.getMessage());
            return new ArrayList<>();
        }
    }
    
    public final void updatePriceByTax(Session s, List<Product> products, Double newTax, Double currentTax) throws BasicException {

        for (Product p : products) {
            var newPrice = (p.getPriceSell() * ((currentTax) + 1)) / ((newTax) + 1);

            var r = new PreparedSentence(s,
                    "UPDATE products SET pricesell = ? WHERE id = ?",
                    SerializerWriteParams.INSTANCE
            );

            r.exec(new DataParams() {
                @Override
                public void writeValues() throws BasicException {
                    setDouble(1, newPrice);
                    setString(2, p.getId());
                }
            });
        }
    }
    
    public final int updateProductByTax(Session s, String newTax, String currentTax) throws BasicException {

        return new PreparedSentence(s,
                "UPDATE products SET taxcat = ? WHERE taxcat = ?",
                SerializerWriteParams.INSTANCE
        ).exec(new DataParams() {
            @Override
            public void writeValues() throws BasicException {
                setString(1, newTax);
                setString(2, currentTax);
            }
        });
    }
}
