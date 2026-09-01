
package db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import university.Tools;

public class clientSocketTools {
    private static Socket socket;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    
    public static boolean setConnection(){
        try{
            socket = new Socket("localhost",4444);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            return true;
        }
        catch(IOException ex){
            Tools.msgBox(ex.getMessage());
            return false;
        }
    }
    
    public static boolean closeConnection(){
        try{
            
            dis.close();
            dos.close();
            socket.close();
            return true;
        }
        catch(IOException ex){
            Tools.msgBox(ex.getMessage());
            return false;
        }
    }
    
    public static boolean checkUserAndPass(String user,String pass){
        String strCheck = ";Check;"+user+";"+pass+";";
        try {
            //Tools.msgBox("userName = " + user +"\n pass = " + pass);
            dos.writeUTF(strCheck);
            boolean check = dis.readBoolean();
            return check;
        } catch (IOException ex) {
            Tools.msgBox(ex.getMessage());
            return false;
        }
        
    }
    
    public static boolean runNonQuery(String sqlStatement){
        try{
            dos.writeUTF(sqlStatement);
            boolean boolRead = dis.readBoolean();
            return boolRead;
        }
        catch(IOException ex){
            Tools.msgBox(ex.getMessage());
            return false;
        }
    }
    
   public static String getAutoNumber(String tableName,String columenName){
        String strAuto = ";getauto;"+tableName+";"+columenName+";";
        try{
            dos.writeUTF(strAuto);
            return dis.readUTF();
        }
        catch(IOException ex){
            Tools.msgBox(ex.getMessage());
            return "0";
        }
    }
   
   public static String getAutoNumber(String tableName,String columenName,String deptID){
        String strAuto = ";getsauto;"+tableName+";"+columenName+";" + deptID +";";
        try{
            dos.writeUTF(strAuto);
            return dis.readUTF();
        }
        catch(IOException ex){
            Tools.msgBox(ex.getMessage());
            return "0";
        }
    }
   
   
   public static void fillCompoBox(String tableName,String columenName,JComboBox combo){
       String strFill = ";fillcombo;"+tableName+ ";" +columenName + ";";
       try{
           dos.writeUTF(strFill);
           String strRead = dis.readUTF();
           Vector values = StringToVector(strRead, ';');
           combo.setModel(new DefaultComboBoxModel(values));
       }
       catch(IOException ex){
           Tools.msgBox(ex.getMessage());
       }
       //
   }
   
   
   public static void fillToJTable(String selectStatmentOrTableName,JTable table){
       try{
          if(selectStatmentOrTableName.startsWith("select"))
            dos.writeUTF(selectStatmentOrTableName);
          else
            dos.writeUTF(";tableName;"+selectStatmentOrTableName+";");
         int rows = dis.readInt();
         int columens = 0;
         DefaultTableModel model = (DefaultTableModel) table.getModel();
         for(int i=0;i<=rows;i++){
             String strRead = dis.readUTF();
             Vector vector = StringToVector(strRead, ';');
             model.addRow(vector);
             columens = getColumenNumber(strRead, ';');
         }
         if(table.getColumnCount() != columens){
                Tools.msgBox("JTable columens count not equal Query columens count\n"
                +"JTable columens count Is: "+ table.getColumnCount()+
                        "\nQuery columens count Is: "+ columens);
            }
         
          
       }
       catch(IOException ex){
           Tools.msgBox(ex.getMessage());
       }
       
       /*int columens = item.columns;
       Object [][] values = item.tableToArray();
       DefaultTableModel model = (DefaultTableModel) table.getModel();
       for(Object []items:values)
            model.addRow(items);
       if(table.getColumnCount() != columens){
                Tools.msgBox("JTable columens count not equal Query columens count\n"
                +"JTable columens count Is: "+ table.getColumnCount()+
                        "\nQuery columens count Is: "+ columens);
            }*/
   }
   
   
   private static int getColumenNumber(String str, char symbol){
       int columens = 0;
       for(int i =0;i<str.length();i++){
           if(str.charAt(i) == symbol)
               columens++;
       }
       columens--;
       return columens;
           
   }

   public static Vector StringToVector(String str , char symbol){
       String string = str;
       String item = "";
       int columens = getColumenNumber(string,symbol);
       Vector vector = new Vector(columens);
       boolean first = true;
       for(int Char = 0;Char<string.length()-1;Char++){
           if(string.charAt(Char) == symbol){
               if(!first)
                   vector.add(item);
               Char++;
               item = "";
               first = false;
           }
           item+=(string.charAt(Char));
           if(Char==string.length()-2)
               vector.add(item);
        }
        return vector;
    }
   
   public static Vector getTableData(String statment){
       String strSelect = ";TableData;"+statment +";";
       
       try{
           Vector vector = null;
           dos.writeUTF(strSelect);
           int rows = dis.readInt();
           for(int i=0;i<rows;i++){
                String strRead = dis.readUTF();
               vector = StringToVector(strRead, ';');
           }
           return vector;
       }
       catch(IOException ex){
           Tools.msgBox(ex.getMessage());
           return new Vector(0);
       }
   }
    
   
    public static int getFee(String regID){
       String strSelect = "select fee from "
               + "registeration where regID = " + regID;
       int fee = (int) getTableData(strSelect).get(0);
       return fee;   
   }
    public static int getDiscount(String pecID){
       String strSelect = "select discount from "
               + "peculiarity where pecID = " + pecID;
           int discount = (int) getTableData(strSelect).get(0);
       return discount;   
    }
    public static boolean objectIsNull(Object obj){
        if(obj.equals("null"))
        return false;
        else 
        return true;
    }
   
}
