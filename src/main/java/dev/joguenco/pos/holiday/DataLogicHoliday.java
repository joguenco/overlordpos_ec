/*
 * Copyright (C) 2023 Jorge Luis
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
package dev.joguenco.pos.holiday;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.DataRead;
import com.unicenta.data.loader.Session;
import com.unicenta.data.loader.TableDefinition;
import com.unicenta.pos.forms.BeanFactoryDataSingle;
import com.unicenta.data.loader.Datas;
import com.unicenta.data.loader.PreparedSentence;
import com.unicenta.data.loader.SerializerWriteString;
import com.unicenta.data.loader.StaticSentence;
import com.unicenta.format.Formats;
import java.util.List;

/**
 *
 * @author Jorge Luis
 */
public class DataLogicHoliday extends BeanFactoryDataSingle {

    private Session s;
    private TableDefinition tdHoliday;

    @Override
    public void init(Session s) {
        this.s = s;

        this.tdHoliday = new TableDefinition(s,
                "holidays",
                new String[]{"name", "date"},
                new String[]{"name", "date"},
                new Datas[]{Datas.STRING, Datas.TIMESTAMP},
                new Formats[]{Formats.STRING, Formats.DATE},
                new int[]{0});
    }

    public final TableDefinition getHoliday() {
        return tdHoliday;
    }

    public final List<HolidayInfo> listHolidays(String year, String month) throws BasicException {
        return new PreparedSentence(s,
                "SELECT id, name, date "
                + "from holidays "
                + "where concat(YEAR(date), MONTH(date)) = ?"
                + "order by date desc",
                SerializerWriteString.INSTANCE,
                HolidayInfo.getSerializerRead()).list(year + month);
    }
    
    public final List<HolidayInfo> listHolidays(String year) throws BasicException {
        return new PreparedSentence(s,
                "SELECT id, name, date "
                + "from holidays "
                + "where YEAR(date) = ?"
                + "order by date desc",
                SerializerWriteString.INSTANCE,
                HolidayInfo.getSerializerRead()).list(year);
    }

    public final void deleteHoliday(final String id) throws BasicException {
        new StaticSentence(s,
                "DELETE FROM holidays WHERE ID = ?",
                SerializerWriteString.INSTANCE).exec(id);
    }
    
    public final int countHoliday() throws BasicException {
        return (int) new PreparedSentence(s,
                "SELECT count(*) "
                + "FROM holidays "
                + "WHERE date = curdate()",
                null,
                (DataRead dr) -> {
                    int count = dr.getInt(1);
                    return count;
                }).find();
    }
}
