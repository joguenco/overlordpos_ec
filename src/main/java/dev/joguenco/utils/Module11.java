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
package dev.joguenco.utils;

/**
 *
 * @author Jorge Luis
 */
public class Module11 {

    private String reverseString(String accessKey) {
        var reverseKey = "";
        for (int x = accessKey.length() - 1; x >= 0; x--) {
            reverseKey = reverseKey + accessKey.charAt(x);
        }
        return reverseKey;
    }

    private int getAdditionByDigits(String accessKey) {

        int pivot = 2;
        int stringLength = accessKey.length();
        int totalQuantity = 0;
        int b = 1;
        for (int i = 0; i < stringLength; i++) {
            if (pivot == 8) {
                pivot = 2;
            }
            int temp = Integer.parseInt("" + accessKey.substring(i, b));
            b++;
            temp = temp * pivot;
            pivot++;
            totalQuantity = totalQuantity + temp;
        }
        totalQuantity = 11 - totalQuantity % 11;
        if (totalQuantity == 10) {
            totalQuantity = 1;
        }
        if (totalQuantity == 11) {
            totalQuantity = 0;
        }
        return totalQuantity;
    }

    public int module11(String accessKey) {
        return getAdditionByDigits(reverseString(accessKey));
    }
}
