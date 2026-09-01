
package Entity;

import db.clientSocketTools;
import javax.swing.JTable;
import university.Tools;

public class student implements mainData{
    private int fee = 0;
    private String univresityID = "";
    private String studentID = "";
    private String FirstName = "";
    private String MiddelName = "";
    private String LastName = "";
    private String motherName = "";
    private String address = "";
    private String hiringDate = "";
    private String deptID = "";
    private String regID = "";
    private String pecID = "";
    

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getMiddelName() {
        return MiddelName;
    }

    public void setMiddelName(String MiddelName) {
        this.MiddelName = MiddelName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }


    public String getUnivresityID() {
        return univresityID;
    }

    public void setUnivresityID(String univresityID) {
        this.univresityID = univresityID;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        //int discount;
        //discount = clientSocketTools.getDiscount(pecID);
        this.fee = fee; // - discount;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public String getRegID() {
        return regID;
    }

    public void setRegID(String regID) {
        this.regID = regID;
    }

    public String getPecID() {
        return pecID;
    }

    public void setPecID(String pecID) {
        this.pecID = pecID;
    }
    
    
    public void printStudent(){
        Tools.msgBox("NationalID: " + getStudentID()
                    +"\nFirstName: " + getFirstName()
                    +"\nMiddelName: " + getMiddelName()
                    +"\nLastName: " + getLastName()
                    +"\nMotherName: " + getMotherName()
                    +"\nAddress: " + getAddress());
    }
//insert into employee values (2,'ali',1250,2);
    @Override
    public void add() {
        String strAdd = "insert into student values ("
                        +"'"+getStudentID() + "',"
                        +getUnivresityID() + ","
                        +"'"+getFirstName() + "',"
                        +"'"+getMiddelName() + "',"
                        +"'"+getLastName() + "',"
                        +"'"+getMotherName() + "',"
                        +"'"+getAddress() + "',"
                        +"'"+getHiringDate() + "',"
                        +"'"+getFee() + "',"
                        +"'"+getDeptID() + "',"
                        +"'"+getRegID() + "',"
                        +"'"+getPecID() + "');";
        
        if(clientSocketTools.runNonQuery(strAdd)){
            Tools.msgBox("تمت إضافة الطالب بنجاح");
        }
        else
            Tools.msgBox("خطأ في إضافة الطالب الرجاء التحقق من البيانات");
    }
//update employee set emp_name='ahmed' where emp_no=1;
    @Override
    public void update() {
        String strUpdate ="update student set "
                            +"studentID='" + getStudentID() + "',"
                            +"firstName ='" + getFirstName() + "',"
                            +"middelName ='" + getMiddelName() + "',"
                            +"lastName ='" + getLastName() + "',"
                            +"motherName ='" + getMotherName() + "',"
                            +"address ='" + getAddress() + "',"
                            +"hiringDate ='" + getHiringDate() + "',"
                            +"fee =" + getFee() + ","
                            +"regID ='" + getRegID() + "',"
                            +"pecID'" + getPecID() +"'"
                            +" where universityID =" + getUnivresityID()
                            +" and deptID = '" + getDeptID() + "';";
        if(clientSocketTools.runNonQuery(strUpdate))
            Tools.msgBox("تم تحديث بيانات الطالب بنجاح");
        else
            Tools.msgBox("لم يتم تحديث بيانات الطالب الرجاء التحقق من البيانات");
    }

    @Override
    public String getAutoNumber() {
        String autoNumber = clientSocketTools.getAutoNumber("student","universityID",getDeptID());
        return autoNumber;
    }

    @Override
    public void getAllRows(JTable table) {
        clientSocketTools.fillToJTable("student", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String str = "select * from student where nationalID = " +getStudentID();
        clientSocketTools.fillToJTable(str, table);
    }

    @Override
    public void getCustomRows(String statment, JTable table) {
        clientSocketTools.fillToJTable(statment, table);
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValueByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    




    
}
