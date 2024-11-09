//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2018 uniCenta & previous Openbravo POS works
//    https://unicenta.com
//
//    This file is part of uniCenta oPOS
//
//    uniCenta oPOS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   uniCenta oPOS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with uniCenta oPOS.  If not, see <http://www.gnu.org/licenses/>.

package com.unicenta.pos.customers;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.*;
import com.unicenta.format.Formats;
import com.unicenta.pos.forms.AppLocal;
import com.unicenta.pos.forms.BeanFactoryDataSingle;
import com.unicenta.pos.voucher.VoucherInfo;
import dev.joguenco.utils.ExtractNames;
import java.util.UUID;

/**
 * @author JG uniCenta
 * @author adrianromero
 */
public class DataLogicCustomers extends BeanFactoryDataSingle {
    
    /**
     * Main Method for customer object
     */
    protected Session s;
    private TableDefinition tcustomers;
    private static final Datas[] customerdatas = new Datas[] {
        Datas.STRING, 
        Datas.TIMESTAMP, 
        Datas.TIMESTAMP, 
        Datas.STRING, 
        Datas.STRING, 
        Datas.STRING, 
        Datas.STRING, 
        Datas.INT, 
        Datas.BOOLEAN, 
        Datas.STRING};
    
    /**
     *
     * @param s
     */
    @Override
    public void init(Session s){
// JG 03 Oct - Added Customer Image        
        this.s = s;
        tcustomers = new TableDefinition(s
            , "customers"
            , new String[] { 
                "ID", 
                "SEARCHKEY",                
                "TAXID",  
                "NAME", 
                "TAXCATEGORY",
                "CARD", 
                "MAXDEBT", 
                "ADDRESS",
                "ADDRESS2",
                "POSTAL",
                "CITY",
                "REGION",
                "COUNTRY",
                "FIRSTNAME",
                "LASTNAME",
                "EMAIL",
                "PHONE",
                "PHONE2",
                "FAX",
                "NOTES", 
                "VISIBLE", 
                "CURDATE", 
                "CURDEBT",
                "IMAGE",
                "ISVIP",
                "DISCOUNT",
                "MEMODATE",
                "TAXID_TYPE"
            }
            , new String[] { 
                "ID", 
                AppLocal.getIntString("label.searchkey"),
                AppLocal.getIntString("label.taxid"),
                AppLocal.getIntString("label.name"),
                "TAXCATEGORY",
                "CARD",
                AppLocal.getIntString("label.maxdebt"),
                AppLocal.getIntString("label.address"),
                AppLocal.getIntString("label.address2"),
                AppLocal.getIntString("label.postal"),
                AppLocal.getIntString("label.city"),
                AppLocal.getIntString("label.region"),
                AppLocal.getIntString("label.country"),
                AppLocal.getIntString("label.firstname"),
                AppLocal.getIntString("label.lastname"),
                AppLocal.getIntString("label.email"),
                AppLocal.getIntString("label.phone"),
                AppLocal.getIntString("label.phone2"),
                AppLocal.getIntString("label.fax"),
                AppLocal.getIntString("label.notes"),
                "VISIBLE",
                AppLocal.getIntString("label.curdate"),
                AppLocal.getIntString("label.curdebt"),
                "IMAGE",
                "ISVIP",
                "DISCOUNT",
                "MEMODATE",
                "TAXID_TYPE"
            }
            , new Datas[] { 
                Datas.STRING, //id
                Datas.STRING, //searchkey
                Datas.STRING, //taxid
                Datas.STRING, //name
                Datas.STRING, //taxcat
                Datas.STRING, //card
                Datas.DOUBLE, //maxdebt
                Datas.STRING, //add
                Datas.STRING, //add2
                Datas.STRING, //postal
                Datas.STRING, //city
                Datas.STRING, //region
                Datas.STRING, //cntry
                Datas.STRING, //fname
                Datas.STRING, //lname
                Datas.STRING, //email
                Datas.STRING, //phone 
                Datas.STRING, //phone2
                Datas.STRING, //fax
                Datas.STRING, //notes
                Datas.BOOLEAN, //visible
                Datas.TIMESTAMP, //curdate
                Datas.DOUBLE, //curdebt
                Datas.IMAGE, //image
                Datas.BOOLEAN, //isvip
                Datas.DOUBLE, //discount
                Datas.TIMESTAMP, //memodate                
                Datas.STRING //taxid_type
            }
            , new Formats[] {
                Formats.STRING, //id
                Formats.STRING, //searchkey
                Formats.STRING, //taxid
                Formats.STRING, //name
                Formats.STRING, //taxcat
                Formats.STRING, //card
                Formats.CURRENCY, //maxdebt
                Formats.STRING, //add
                Formats.STRING, //add2
                Formats.STRING, //postal
                Formats.STRING, //city
                Formats.STRING, //region
                Formats.STRING, //cntry
                Formats.STRING, //fname
                Formats.STRING, //lname
                Formats.STRING, //email
                Formats.STRING, //phone
                Formats.STRING, //phone2
                Formats.STRING, //fax
                Formats.STRING, //notes
                Formats.BOOLEAN, //visible
                Formats.TIMESTAMP, //curdate
                Formats.CURRENCY, //curdebt
                Formats.NULL, //image
                Formats.BOOLEAN, //isvip
                Formats.DOUBLE,  //discount
                Formats.TIMESTAMP, //memodate                
                Formats.STRING //taxid_type
            }
            , new int[] {0}
        );   
    }
    
    /**
     * called from Find Customer
     * @return customer data
     */
        public SentenceList getCustomerList() {
        return new StaticSentence(s
            , new QBFBuilder("SELECT "
                    + "ID, TAXID, SEARCHKEY, NAME, "
                    + "POSTAL, EMAIL, PHONE, IMAGE "
                    + "FROM customers "
                    + "WHERE VISIBLE = " + s.DB.TRUE() + " AND ?(QBF_FILTER) ORDER BY NAME"
                , new String[] {"TAXID", "SEARCHKEY", "NAME", "POSTAL", "PHONE", "EMAIL"})
            , new SerializerWriteBasic(new Datas[] {
                Datas.OBJECT, Datas.STRING, 
                Datas.OBJECT, Datas.STRING, 
                Datas.OBJECT, Datas.STRING, 
                Datas.OBJECT, Datas.STRING, 
                Datas.OBJECT, Datas.STRING, 
                Datas.OBJECT, Datas.STRING})
            , (DataRead dr) -> {
                CustomerInfo c = new CustomerInfo(dr.getString(1));
                c.setTaxid(dr.getString(2));
                c.setSearchkey(dr.getString(3));
                c.setName(dr.getString(4));
                c.setPcode(dr.getString(5));
                c.setPhone1(dr.getString(6));
                c.setCemail(dr.getString(7));
                c.setImage(ImageUtils.readImage(dr.getBytes(8)));
//                c.setCurDebt(dr.getDouble(9));

            return c;                
        });
    }
        
    public final CustomerInfo getCustomerInfo(String id) throws BasicException {
	return (CustomerInfo) new PreparedSentence(s
            ,"SELECT " +
                "ID, TAXID, SEARCHKEY, NAME, POSTAL " +
             "FROM customers WHERE VISIBLE = " + s.DB.TRUE() + " " +
             "AND ID = ?"
		, SerializerWriteString.INSTANCE , (DataRead dr) -> {
                    CustomerInfo c = new CustomerInfo(dr.getString(1));
                    c.setTaxid(dr.getString(2));
                    c.setSearchkey(dr.getString(3));
                    c.setName(dr.getString(4));
                    c.setPcode(dr.getString(5));
//                            c.setisVip(dr.getBoolean(6));
//                            c.setDiscount(dr.getDouble(7));
            return c;
        }).find(id);
    }        
       
    /**
     *
     * @param customer
     * @return
     * @throws BasicException
     */
    public int updateCustomerExt(final CustomerInfoExt customer) throws BasicException {
     
        return new PreparedSentence(s
                , "UPDATE customers SET NOTES = ? WHERE ID = ?"
                , SerializerWriteParams.INSTANCE      
                ).exec(new DataParams() {@Override
        public void writeValues() throws BasicException {
                        setString(1, customer.getNotes());
                        setString(2, customer.getId());
                }});        
    }
    
    /**
     *
     * @return customer's existing reservation (restaurant mode)
     */
    public final SentenceList getReservationsList() {
        return new PreparedSentence(s
            , "SELECT R.ID, R.CREATED, R.DATENEW, C.CUSTOMER, customers.TAXID, customers.SEARCHKEY, COALESCE(customers.NAME, R.TITLE),  R.CHAIRS, R.ISDONE, R.DESCRIPTION " +
              "FROM reservations R LEFT OUTER JOIN reservation_customers C ON R.ID = C.ID LEFT OUTER JOIN customers ON C.CUSTOMER = customers.ID " +
              "WHERE R.DATENEW >= ? AND R.DATENEW < ?"
            , new SerializerWriteBasic(new Datas[] {Datas.TIMESTAMP, Datas.TIMESTAMP})
            , new SerializerReadBasic(customerdatas));             
    }
    
    /**
     *
     * @return create/update customer reservation  (restaurant mode)
     */
    public final SentenceExec getReservationsUpdate() {
        return new SentenceExecTransaction(s) {
            @Override
            public int execInTransaction(Object params) throws BasicException {  
    
                new PreparedSentence(s
                    , "DELETE FROM reservation_customers WHERE ID = ?"
                    , new SerializerWriteBasicExt(customerdatas, new int[]{0})).exec(params);
                if (((Object[]) params)[3] != null) {
                    new PreparedSentence(s
                        , "INSERT INTO reservation_customers (ID, CUSTOMER) VALUES (?, ?)"
                        , new SerializerWriteBasicExt(customerdatas, new int[]{0, 3})).exec(params);                
                }
                return new PreparedSentence(s
                    , "UPDATE reservations SET ID = ?, CREATED = ?, DATENEW = ?, TITLE = ?, CHAIRS = ?, ISDONE = ?, DESCRIPTION = ? WHERE ID = ?"
                    , new SerializerWriteBasicExt(customerdatas, new int[]{0, 1, 2, 6, 7, 8, 9, 0})).exec(params);
            }
        };
    }
    
    /**
     *
     * @return delete customer reservation (restaurant mode)
     */
    public final SentenceExec getReservationsDelete() {
        return new SentenceExecTransaction(s) {
            @Override
            public int execInTransaction(Object params) throws BasicException {  
    
                new PreparedSentence(s
                    , "DELETE FROM reservation_customers WHERE ID = ?"
                    , new SerializerWriteBasicExt(customerdatas, new int[]{0})).exec(params);
                return new PreparedSentence(s
                    , "DELETE FROM reservations WHERE ID = ?"
                    , new SerializerWriteBasicExt(customerdatas, new int[]{0})).exec(params);
            }
        };
    }
    
    /**
     *
     * @return insert a new customer reservation (restaurant mode)
     */
    public final SentenceExec getReservationsInsert() {
        return new SentenceExecTransaction(s) {
            @Override
            public int execInTransaction(Object params) throws BasicException {  
    
                int i = new PreparedSentence(s
                    , "INSERT INTO reservations (ID, CREATED, DATENEW, TITLE, CHAIRS, ISDONE, DESCRIPTION) VALUES (?, ?, ?, ?, ?, ?, ?)"
                    , new SerializerWriteBasicExt(customerdatas, new int[]{0, 1, 2, 6, 7, 8, 9})).exec(params);

                if (((Object[]) params)[3] != null) {
                    new PreparedSentence(s
                        , "INSERT INTO reservation_customers (ID, CUSTOMER) VALUES (?, ?)"
                        , new SerializerWriteBasicExt(customerdatas, new int[]{0, 3})).exec(params);                
                }
                return i;
            }
        };
    }
    
    /**
     *
     * @return customer table
     */
    public final TableDefinition getTableCustomers() {
        return tcustomers;
    }  
    
    public final PreparedSentence getVoucherNumber() {
        return new PreparedSentence(s,  
            "SELECT SUBSTRING(MAX(VOUCHER_NUMBER),10,3) AS LAST_NUMBER FROM vouchers " +
            "WHERE SUBSTRING(VOUCHER_NUMBER,1,8) = ?" 
            , SerializerWriteString.INSTANCE
            , (DataRead dr) -> dr.getString(1));
    }
      
      
/*        public final VoucherInfo getVoucherInfo(String id) throws BasicException {
            return (VoucherInfo) new PreparedSentence(s
                , "SELECT vouchers.ID, VOUCHER_NUMBER, CUSTOMER, " +
                        "customers.NAME, AMOUNT, STATUS " +
                  "FROM vouchers " +
                    "JOIN customers ON customers.id = vouchers.CUSTOMER " +
                  "WHERE STATUS='A' AND vouchers.ID=?" 
//                  "WHERE STATUS='A' "                         
		, SerializerWriteString.INSTANCE
		, VoucherInfo.getSerializerRead()).find(id);
    }
*/

        public final VoucherInfo getVoucherInfo(String id) throws BasicException {
            return (VoucherInfo) new PreparedSentence(s
                , "SELECT vouchers.ID, VOUCHER_NUMBER, CUSTOMER,  AMOUNT, STATUS " +
                  "FROM vouchers " +
                  "WHERE STATUS='A' AND vouchers.ID=?" 
		, SerializerWriteString.INSTANCE
		, VoucherInfo.getSerializerRead()).find(id);
    }
          
        public final VoucherInfo getVoucherInfoAll(String id) throws BasicException {
            return (VoucherInfo) new PreparedSentence(s
		, "SELECT vouchers.ID, VOUCHER_NUMBER, CUSTOMER, " +
                        "customers.NAME, AMOUNT, STATUS " +
                    "FROM vouchers " +
                        "JOIN customers ON customers.id = vouchers.CUSTOMER  " +
                    "WHERE vouchers.ID=?" 
		, SerializerWriteString.INSTANCE
		, VoucherInfo.getSerializerRead()).find(id);
    }    
          
    public final int countCustomerByTaxId(String taxId) throws BasicException {
        return (int) new PreparedSentence(s,
                "SELECT count(*) "
                + "FROM customers "
                + "WHERE TAXID = ?",
                SerializerWriteString.INSTANCE, (DataRead dr) -> {
                    int count = dr.getInt(1);
                    return count;
                }).find(taxId);
    }
    
    public final CustomerInfoBasic getCustomerByTaxId(String taxId) throws BasicException {
        return (CustomerInfoBasic) new PreparedSentence(s,
                "SELECT "
                + "ID, TAXID, SEARCHKEY, TAXID_TYPE ,NAME, ADDRESS, PHONE, EMAIL "
                + "FROM customers "
                + "WHERE TAXID = ?",
                SerializerWriteString.INSTANCE, (DataRead dr) -> {
                    CustomerInfoBasic c = new CustomerInfoBasic(dr.getString(1));
                    c.setTaxid(dr.getString(2));
                    c.setSearchkey(dr.getString(3));
                    c.setType(dr.getString(4));
                    c.setName(dr.getString(5));
                    c.setAddress(dr.getString(6));
                    c.setPhone(dr.getString(7));
                    c.setEmail(dr.getString(8));
                    return c;
                }).find(taxId);
    }
    
    public final int countCustomerByTaxIdAndTaxIdType(String taxId, String taxIdType) throws BasicException {
        return (int) new PreparedSentence(s,
                "SELECT count(*) "
                + "FROM customers "
                + "WHERE TAXID = ? "
                + "AND TAXID_TYPE = ?",
                SerializerWriteString.INSTANCE, (DataRead dr) -> {
                    int count = dr.getInt(1);
                    return count;
                }).find(taxId, taxIdType);
    }
    
    public final Double maxValueInSaleWhenIsFinalConsumer(String taxId) throws BasicException {
        return (Double) new PreparedSentence(s,
                "select maxdebt from customers "
                + "where taxid = ?",
                SerializerWriteString.INSTANCE, (DataRead dr) -> {
                    return dr.getDouble(1);
                }).find(taxId);
    }

    public int saveCustomer(final CustomerInfoBasic customer) throws BasicException {
        ExtractNames extract = new ExtractNames();
        String lastName = extract.extractLastName(customer.getName().trim());
        String firstName = extract.extractFirstName(customer.getName().trim());

        return new PreparedSentence(s,
                "INSERT INTO customers "
                + "(id,searchkey,taxid,name,maxdebt,address,taxid_type,firstname,lastname,notes,visible,isvip,discount,email,phone) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                SerializerWriteParams.INSTANCE
        ).exec(new DataParams() {
            @Override
            public void writeValues() throws BasicException {
                setString(1, UUID.randomUUID().toString());
                setString(2, customer.getTaxid());
                setString(3, customer.getTaxid());
                setString(4, customer.getName());
                setInt(5, 0);
                setString(6, customer.getAddress());
                setString(7, customer.getType());
                setString(8, firstName);
                setString(9, lastName);
                setString(10, "");
                setInt(11, 1);
                setInt(12, 0);
                setInt(13, 0);
                setString(14, customer.getEmail());
                setString(15, customer.getPhone());
            }
        });
    }

    public int updateCustomer(final CustomerInfoBasic customer) throws BasicException {
        ExtractNames extract = new ExtractNames();
        String lastName = extract.extractLastName(customer.getName().trim());
        String firstName = extract.extractFirstName(customer.getName().trim());

        return new PreparedSentence(s,
                "update customers "
                + "set name = ?, address = ?, firstname = ?, lastname =? ,email = ?, phone =? "
                + "where id = ?",
                SerializerWriteParams.INSTANCE
        ).exec(new DataParams() {
            @Override
            public void writeValues() throws BasicException {
                setString(1, customer.getName());
                setString(2, customer.getAddress());
                setString(3, firstName);
                setString(4, lastName);
                setString(5, customer.getEmail());
                setString(6, customer.getPhone());
                setString(7, customer.getId());
            }
        });
    }
    
    public CustomerInfoExt loadCustomerExtTaxByIdAndTaxIdType(String taxId, String taxIdType) throws BasicException {
        return (CustomerInfoExt) new PreparedSentence(s,
                "SELECT "
                + "ID, "
                + "SEARCHKEY, "
                + "TAXID, "
                + "NAME, "
                + "TAXCATEGORY, "
                + "CARD, "
                + "MAXDEBT, "
                + "ADDRESS, "
                + "ADDRESS2, "
                + "POSTAL, "
                + "CITY, "
                + "REGION, "
                + "COUNTRY, "
                + "FIRSTNAME, "
                + "LASTNAME, "
                + "EMAIL, "
                + "PHONE, "
                + "PHONE2, "
                + "FAX, "
                + "NOTES, "
                + "VISIBLE, "
                + "CURDATE, "
                + "CURDEBT, "
                + "IMAGE, "
                + "ISVIP, "
                + "DISCOUNT, "
                + "MEMODATE, "
                + "TAXID_TYPE "
                + "FROM customers "
                + "WHERE TAXID = ? "
                + "AND TAXID_TYPE = ?",
                SerializerWriteString.INSTANCE,
                (DataRead dr) -> {
                    CustomerInfoExt c = new CustomerInfoExt(dr.getString(1));
                    c.setSearchkey(dr.getString(2));
                    c.setTaxid(dr.getString(3));
                    c.setTaxCustomerID(dr.getString(3));
                    c.setName(dr.getString(4));
                    c.setTaxCustCategoryID(dr.getString(5));
                    c.setCard(dr.getString(6));
                    c.setMaxdebt(dr.getDouble(7));
                    c.setAddress(dr.getString(8));
                    c.setAddress2(dr.getString(9));
                    c.setPcode(dr.getString(10));
                    c.setCity(dr.getString(11));
                    c.setRegion(dr.getString(12));
                    c.setCountry(dr.getString(13));
                    c.setFirstname(dr.getString(14));
                    c.setLastname(dr.getString(15));
                    c.setCemail(dr.getString(16));
                    c.setPhone1(dr.getString(17));
                    c.setPhone2(dr.getString(18));
                    c.setFax(dr.getString(19));
                    c.setNotes(dr.getString(20));
                    c.setVisible(dr.getBoolean(21));
                    c.setCurdate(dr.getTimestamp(22));
                    c.setAccdebt(dr.getDouble(23));
                    c.setImage(ImageUtils.readImage(dr.getString(24)));
                    c.setisVIP(dr.getBoolean(25));
                    c.setDiscount(dr.getDouble(26));
                    c.setMemoDate(dr.getString(27));
                    c.setType(dr.getString(28));

                    return c;
                }).find(taxId, taxIdType);
    }
    
    /**
     * Return customer data basic
     *
     * @param id customes
     * @return CustomerInfoBasic
     * @throws BasicException
     */
    public final CustomerInfoBasic getCustomerById(String customerId) throws BasicException {
        return (CustomerInfoBasic) new PreparedSentence(s,
                "SELECT "
                + "ID, TAXID, SEARCHKEY, TAXID_TYPE ,NAME, ADDRESS, PHONE, EMAIL "
                + "FROM customers "
                + "WHERE ID = ?",
                SerializerWriteString.INSTANCE, (DataRead dr) -> {
                    CustomerInfoBasic c = new CustomerInfoBasic(dr.getString(1));
                    c.setTaxid(dr.getString(2));
                    c.setSearchkey(dr.getString(3));
                    c.setType(dr.getString(4));
                    c.setName(dr.getString(5));
                    c.setAddress(dr.getString(6));
                    c.setPhone(dr.getString(7));
                    c.setEmail(dr.getString(8));
                    return c;
                }).find(customerId);
    }
}