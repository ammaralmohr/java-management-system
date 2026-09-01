package university;


import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Tools {
    //صندوق عرض الرسائل
    public static void msgBox(String messag){
        JOptionPane.showMessageDialog(null,messag);
    }
    //صندوق ادخال 
     public static Object InputBox(String title){
        Object obj = JOptionPane.showInputDialog(title);
        return obj;
    }
    //صندوق التحقق من العملية
    public static boolean confirmMsg(String message){
        int confirm = JOptionPane.showConfirmDialog(null, message);
        return confirm == JOptionPane.YES_OPTION;
    } 
    //انشاء ملف
    public static void CreateFolder(String FolderName, String path){
        File f = new File(path+"/"+FolderName);
        f.mkdir();
    }
    //انشاء ملف
    public static void CreateFolder(String FolderName){
        File f = new File(FolderName);
        f.mkdir();
    }
    // تهيئة شاشة البرنامج
    public static void openForm(JFrame form,String IconName){
        try {
            form.setLocationRelativeTo(null);
            Image img = ImageIO.read(Tools.class.getResource(IconName));
            form.setIconImage(img);
            form.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void openForm(JFrame form){
        try {
            form.setLocationRelativeTo(null);
            Image img = ImageIO.read(Tools.class.getResource("emp.png"));
            form.setIconImage(img);
            form.setDefaultCloseOperation(2);
            form.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //مسح محتوى جميع الحقول النصية في المشروع
    public static void ClearText(Container form){
       for(Component c: form.getComponents()){
           if(c instanceof JTextField){
               JTextField J= (JTextField) c;
               J.setText("");
           }
           else if(c instanceof Container){
               ClearText((Container)c);
           }
       } 
    }
    // انشاء ملف فارغ
    public static void createEptyFile(String fileName){
        try {
            File f = new File(fileName+".txt");
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void createEptyFiles(String fileName[]){
      for(String str : fileName){
          createEptyFile(str);
      }  
    }
    // انشاء ملف ببيانات
    public static void createFile(String fileName, Object Data[]){
        try {
            PrintWriter p = new PrintWriter(fileName+".txt");
            for(Object obj : Data){
                p.println(obj);
            }
            p.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void creatFiles(String fileName[],Object allData[][]){
            for(int x=0;x<fileName.length;x++){
               createFile(fileName[x],allData[x]); 
            
        }
    }
   
    //استخراج الارقام من نص
    public static String getNumber(String txt){
        String val ="";
        for(char c:txt.toCharArray()){
            if(c=='0'|| c=='1'|| c=='2'|| c=='3'|| c=='4'|| c=='5'|| c=='6'|| c=='7'|| c=='8'|| c=='9')
             val +=c;   
        }
        return val;
    }
    public static int getNumberToInteger(String txt){
        String val ="";
        for(char c:txt.toCharArray()){
            if(c=='0'|| c=='1'|| c=='2'|| c=='3'|| c=='4'|| c=='5'|| c=='6'|| c=='7'|| c=='8'|| c=='9')
             val +=c;   
        }
        return Integer.parseInt(val);
    }
     public static String removeNumber(String txt){
        String val ="";
        for(char c:txt.toCharArray()){
            if(!(c=='0'|| c=='1'|| c=='2'|| c=='3'|| c=='4'|| c=='5'|| c=='6'|| c=='7'|| c=='8'|| c=='9'))
             val +=c;   
        }
        return val;
    }
    public static void printScreen(String imageName,JFrame form){
        try {
            form.setState(1);
            Robot r = new Robot();
            Rectangle rec =new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage img = r.createScreenCapture(rec);
            ImageIO.write(img, "jpg",new File( imageName+".jpg"));
            form.setState(0);
        } catch (Exception ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
