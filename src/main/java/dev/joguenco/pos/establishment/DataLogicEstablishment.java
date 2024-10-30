package dev.joguenco.pos.establishment;

import com.unicenta.data.loader.*;
import com.unicenta.format.Formats;
import com.unicenta.pos.forms.BeanFactoryDataSingle;

/**
 * @author jorgeluis
 */
public class DataLogicEstablishment extends BeanFactoryDataSingle {

    private Session s;
    private TableDefinition tdEstablishment;

    @Override
    public void init(Session s) {
        this.s = s;

        tdEstablishment = new TableDefinition(s,
                "establishments",
                new String[]{"id", "comercial_name", "city", "address",
                    "phone", "email", "principal", "status"},
                new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING,
                    Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN,},
                new Formats[]{Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING,
                    Formats.STRING, Formats.STRING, Formats.STRING, Formats.BOOLEAN,},
                new int[]{0});
    }

    public final TableDefinition getTableEstablishment() {
        return tdEstablishment;
    }

    public final PreparedSentence getEstablishmentInfo() {
        return new PreparedSentence(s,
                "select comercial_name, address, phone, email, city "
                + "from establishments "
                + "where id = ?",
                SerializerWriteString.INSTANCE,
                (DataRead dr) -> {
                    EstablishmentInfo establishmentInfo = new EstablishmentInfo();

                    establishmentInfo.readValues(dr);

                    return establishmentInfo;
                });
    }
}
