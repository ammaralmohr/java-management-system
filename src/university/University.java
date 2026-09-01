
package university;

import Entity.student;
import frames.frmCheck;
import frames.frmLogIn;
import frames.frmRegisteration;
import frames.frmStudentCertaficate;
import frames.frmStudentInfo;


public class University {
    static student std = new student();

    public static void main(String[] args) {
        Tools.openForm(new frmLogIn());
        //Tools.openForm(new frmCheck());
    }
    
}
