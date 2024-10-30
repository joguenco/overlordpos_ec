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
import com.unicenta.data.loader.SerializableRead;
import com.unicenta.data.loader.SerializerRead;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jorge Luis
 */
public class HolidayInfo implements SerializableRead {

    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Date date;

    public HolidayInfo(Integer id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    @Override
    public void readValues(DataRead dr) throws BasicException {
        id = dr.getInt(1);
        name = dr.getString(2);
        date = dr.getTimestamp(3);
    }

    public static SerializerRead getSerializerRead() {
        return new SerializerRead() {
            @Override
            public Object readValues(DataRead dr) throws BasicException {
                return new HolidayInfo(
                        dr.getInt(1),
                        dr.getString(2),
                        dr.getTimestamp(3)
                );
            }
        };
    }
}
