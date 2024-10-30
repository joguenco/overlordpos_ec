package dev.joguenco.pos.ticketsnum;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.DataRead;
import com.unicenta.data.loader.SerializableRead;

/**
 *
 * @author jorgeluis
 */
public class TicketsNumInfo implements SerializableRead {

    private String code;
    private String serie;
    private int id;


    public TicketsNumInfo() { }

    public String getCode() {
        return code;
    }

    public String getSerie() {
        return serie;
    }

    public int getId() {
        return id;
    }

    @Override
    public void readValues(DataRead dr) throws BasicException {
        code = dr.getString(1);
        serie = dr.getString(2);
        id = dr.getInt(3);
    }
}