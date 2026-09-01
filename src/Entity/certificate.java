
package Entity;

import db.clientSocketTools;
import javax.swing.JTable;
import university.Tools;

public class certificate implements mainData{
   private String cerID;
   private String studentID;
   private String cerType;
   private String takeDate;
   private String degreeRate;
   private String cerLanguage;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCerID() {
        return cerID;
    }

    public void setCerID(String cerID) {
        this.cerID = cerID;
    }

    public String getCerType() {
        return cerType;
    }

    public void setCerType(String cerType) {
        this.cerType = cerType;
    }

    public String getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(String takeDate) {
        this.takeDate = takeDate;
    }

    public String getDegreeRate() {
        return degreeRate;
    }

    public void setDegreeRate(String degreeRate) {
        this.degreeRate = degreeRate;
    }

    public String getCerLanguage() {
        return cerLanguage;
    }

    public void setCerLanguage(String cerLanguage) {
        this.cerLanguage = cerLanguage;
    }

    @Override
    public void add() {
        String strInsert = "insert into certificate values ('"
                + cerID +"','"
                + studentID +"','"
                + cerType +"','"
                + takeDate + "','"
                + degreeRate + "','"
                + cerLanguage + "');";
        if(clientSocketTools.runNonQuery(strInsert))
            Tools.msgBox("تمت إضافة الشهادة بنجاح...");
        else
            Tools.msgBox("لم تتم إضافة الشهادة...\n"
                    + "الرجاء التأكد من البيانات...");
    }

    @Override
    public void update() {
        String strUpdate = "update certificate set "
                + "cerID = '" + cerID +"',"
                + "studentID ='" + studentID +"','"
                + "cerType ='" + cerType + "',"
                + "takeDate ='" + takeDate + "',"
                + "degreeRate = '" + degreeRate + "',"
                + "cerLanguage ='" + cerLanguage +"';";
        if(clientSocketTools.runNonQuery(strUpdate))
            Tools.msgBox("تم تحديث معلومات الشهادة بنجاح...");
        else
            Tools.msgBox("لم يتم تحديث معلومات الشهادة...\n"
                    + "الرجاء التحقق من البيانات...");
        
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
        String strSelect = "select * from certificate where studentID = '"
                + studentID + "';";
        clientSocketTools.fillToJTable(strSelect, table);
    }

    @Override
    public void getCustomRows(String statment, JTable table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValueByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
