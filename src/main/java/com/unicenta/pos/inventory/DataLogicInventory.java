/*
 * Copyright (C) 2024 <Jorge Luis from mestizos.dev>
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
package com.unicenta.pos.inventory;

import com.unicenta.data.loader.DataRead;
import com.unicenta.data.loader.PreparedSentence;
import com.unicenta.data.loader.Session;
import com.unicenta.pos.forms.BeanFactoryDataSingle;

/**
 *
 * @author <Jorge Luis from mestizos.dev>
 */
public class DataLogicInventory extends BeanFactoryDataSingle {

    protected Session s;

    @Override
    public void init(Session s) {
        this.s = s;
    }

    // Sequence for products table in reference column
    public PreparedSentence getProductSequence() {
        return new PreparedSentence(s,
                "select "
                + "cast(ifnull(max(cast(reference as unsigned) + 1), 1) as char(10)) as sequence "
                + "from products "
                + "where reference REGEXP '^[0-9]+$'",
                null,
                (DataRead dr) -> dr.getString(1));
    }
}
