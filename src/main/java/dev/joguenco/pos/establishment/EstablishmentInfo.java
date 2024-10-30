package dev.joguenco.pos.establishment;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.DataRead;
import com.unicenta.data.loader.SerializableRead;

public class EstablishmentInfo implements SerializableRead {

    private String comercialName;
    private String address;
    private String phone;
    private String email;
    private String city;

    public EstablishmentInfo() {
        comercialName = "";
        address = "";
        phone = "";
        email = "";
        city = "";
    }

    public String getComercialName() {
        return comercialName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void readValues(DataRead dr) throws BasicException {
        comercialName = dr.getString(1);
        address = dr.getString(2);
        phone = dr.getString(3);
        email = dr.getString(4);
        city = dr.getString(5);
    }
}
