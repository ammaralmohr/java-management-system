
package Entity;

import db.clientSocketTools;
import java.util.Vector;
import javax.swing.JTable;

public class peculiarity implements mainData{
    private int pecID;
    private String pecName;
    private int discount;

    public int getPecID() {
        return pecID;
    }

    public void setPecID(int pecID) {
        this.pecID = pecID;
    }

    public String getPecName() {
        return pecName;
    }

    public void setPecName(String pecName) {
        this.pecName = pecName;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public void add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAutoNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getAllRows(JTable table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getOneRow(JTable table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getCustomRows(String statment, JTable table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValueByName(String name) {
        String strSelect = "select pecID from peculiarity where pecName = '"
                + name +"';";
        Vector v = clientSocketTools.getTableData(strSelect);
        String value = (String) v.get(0);
        return value;
    }

    public String getDiscount(String value) {
        String strSelect = "select discount from peculiarity where pecID = '"
                + value +"';";
        Vector v = clientSocketTools.getTableData(strSelect);
        String val = (String) v.get(0);
        return val;
    }
    
}
