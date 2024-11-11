package dev.joguenco.pos.taxes;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.DataRead;
import com.unicenta.data.loader.SerializableRead;
import lombok.Getter;

/**
 *
 * @author  Jorge Luis
 * @version 
 */
public class Product implements SerializableRead {

    @Getter
    private String id;
    @Getter
    private String name;
    @Getter
    private Double priceSell;

    public Product() {}

    @Override
    public void readValues(DataRead dr) throws BasicException {
        id = dr.getString(1);
        name = dr.getString(2);
        priceSell = dr.getDouble(3);
    }
}


