
package Entity;

import db.clientSocketTools;
import javax.swing.JTable;
import university.Tools;

public class studentPhones implements mainData{
    private String studentID;
    private String phone;

    public String getNationalID() {
        return studentID;
    }

    public void setNationalID(String studentID) {
        this.studentID = studentID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void add() {
        String strAdd = "insert into studentPhones values ('"
                       + studentID + "','" + phone + "');";
        if(clientSocketTools.runNonQuery(strAdd))
            Tools.msgBox("تمت إضافة رقم الهاتف بنجاح...");
        else
            Tools.msgBox("لم تتم إضافة رقم الهاتف الرجاء التحقق من البيانات");
    }

    @Override
    public void update() {
        Tools.msgBox("method update is unavilable with phones");
    }
    
    @Override
    public void delete() {
        String strDelete = "delete from studentPhones where"
                        + "nationalID = '" + studentID +"';";
        if(clientSocketTools.runNonQuery(strDelete))
            Tools.msgBox("تم حذف رقم الهاتف بنجاح...");
        else
            Tools.msgBox("لم يتم حذف رقم الهاتف...");
    }
    
    public void deleteAllPhones(){
        String strDelete = "delete from studentPhones where nationalID = '"
                +getPhone() +"';";
        if(clientSocketTools.runNonQuery(strDelete))
            Tools.msgBox("تم حذف جميع أرقام الهواتف لهذا الطالب...");
    }

    @Override
    public String getAutoNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getAllRows(JTable table) {
        String strSelect = "select phone from studentPhones where studentID = '"
                + getNationalID()+ "';";
        clientSocketTools.fillToJTable(strSelect, table);
    }

    @Override
    public void getOneRow(JTable table) {
        
    }

    @Override
    public void getCustomRows(String statment, JTable table) {
        clientSocketTools.fillToJTable(statment, table);
    }

    @Override
    public String getValueByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
}
