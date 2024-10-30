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
import com.unicenta.data.loader.DataRead;
import com.unicenta.data.loader.SerializableRead;
import lombok.Getter;

/**
 *
 * @author Jorge Luis
 */
public class TaxInfo implements SerializableRead {

    @Getter
    private String id;
    @Getter
    private String name;
    @Getter
    private Double rate;
    @Getter
    private String taxcategoryid; 

    public TaxInfo() { }

    @Override
    public void readValues(DataRead dr) throws BasicException {
        id = dr.getString(1);
        name = dr.getString(2);
        rate = dr.getDouble(3);
        taxcategoryid = dr.getString(4);
    }
}
