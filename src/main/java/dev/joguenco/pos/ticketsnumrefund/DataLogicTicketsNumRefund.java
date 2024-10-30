package dev.joguenco.pos.ticketsnumrefund;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.*;
import com.unicenta.data.model.Field;
import com.unicenta.data.model.Row;
import com.unicenta.format.Formats;
import com.unicenta.pos.forms.BeanFactoryDataSingle;
import dev.joguenco.pos.ticketsnum.TicketsNumInfo;

public class DataLogicTicketsNumRefund extends BeanFactoryDataSingle {

    private Session s;
    private Row ticketsNumRefundRow;
    private TableDefinition tableDefinitionTicketsNumRefund;

    public DataLogicTicketsNumRefund() {
        ticketsNumRefundRow = new Row(
                new Field("code", Datas.STRING, Formats.STRING, true, true, true),
                new Field("people_id", Datas.STRING, Formats.STRING),
                new Field("serie", Datas.STRING, Formats.STRING, true, true, true),
                new Field("id", Datas.INT, Formats.INT, true, true, true),
                new Field("priority", Datas.STRING, Formats.STRING),
                new Field("status", Datas.STRING, Formats.STRING)
        );
    }

    @Override
    public void init(Session s) {
        this.s = s;

        tableDefinitionTicketsNumRefund = new TableDefinition(s,
                "ticketsnum_refund",
                new String[]{"code", "people_id", "serie",
                        "id", "priority", "status"},
                new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING,
                        Datas.INT, Datas.STRING, Datas.STRING},
                new Formats[]{Formats.STRING, Formats.STRING, Formats.INT,
                        Formats.STRING, Formats.STRING, Formats.STRING},
                new int[]{0, 1});
    }

    public TableDefinition getTableDefinitionTicketsNumRefund() {
        return tableDefinitionTicketsNumRefund;
    }

    public Row getTicketsNumRefundRow() {
        return ticketsNumRefundRow;
    }

    public PreparedSentence getTicketsNumRefundList() {
        return new PreparedSentence(s,
                "SELECT "
                        + "t.code, t.people_id, t.serie, t.id, t.priority, t.status "
                        + "FROM ticketsnum_refund t "
                        + "LEFT OUTER JOIN people p "
                        + "ON t.people_id = p.id "
                        + "where t.people_id = ? "
                        + "order by t.code",
                new SerializerWriteBasicExt(new Datas[]{Datas.OBJECT, Datas.STRING},
                        new int[]{1}),
                new DataLogicTicketsNumRefund.TicketsNumRefundSerializerRead()
        );
    }

    // Count the active sequence filtered by user
    public PreparedSentence getCount() {
        return new PreparedSentence(s,
                "SELECT COUNT(*) as count "
                        + "FROM ticketsnum_refund "
                        + "WHERE people_id = ? "
                        + "AND code = ? "
                        + "AND status = 'Active'",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}),
                (DataRead dr) -> dr.getInt(1));
    }

    // Get code and serie active filtered by user
    public PreparedSentence getSerial() {
        return new PreparedSentence(s,
                "SELECT code, serie, id "
                        + "FROM ticketsnum_refund "
                        + "WHERE people_id = ? "
                        + "AND code = ? "
                        + "AND status = 'Active'",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}),
                (DataRead dr) -> {
                    TicketsNumInfo ticketNum = new TicketsNumInfo();

                    ticketNum.readValues(dr);

                    return ticketNum;
                });
    }

    private class TicketsNumRefundSerializerRead implements SerializerRead {

        @Override
        public Object readValues(DataRead dr) throws BasicException {
            return new Object[]{
                    dr.getString(1),
                    dr.getString(2),
                    dr.getString(3),
                    dr.getInt(4),
                    dr.getString(5),
                    dr.getString(6)
            };
        }
    }
}
