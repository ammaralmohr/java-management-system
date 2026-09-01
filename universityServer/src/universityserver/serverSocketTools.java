
package universityserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class serverSocketTools {
    private static ServerSocket server;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    private static String url ="";
    private static final String dbName = "university";
    private static Connection connection;
    
    
    private static void setURL(){
        url = "jdbc:mysql://localhost:3306/"+ dbName // acsess to data base
               + "?useUnicode=true&characterEncoding=UTF-8";
    // make data base accept arabic 
    }
    
    private static void setConnection(){
        try {
            setURL();
            connection = DriverManager.getConnection(url,"root","");
        } catch (SQLException ex) {
           Tools.msgBox(ex.getMessage()+"\n setconnection error"); 
        }
    }
    public static void setServerConnection(){
        try{
            server = new ServerSocket(4444);
            Socket socket = server.accept();
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        }
        catch(IOException ex){
            Tools.msgBox(ex.getMessage() + "setServerConnection error...");
        }
    }
    
    public static void closeConnection(){
        try{
            dis.close();
            dos.close();
            server.close();
            //Tools.msgBox("server connection is closed correctly...");
            
        }
        catch(IOException ex){
            Tools.msgBox(ex.getMessage() + "connection not stoped");
        }
    }
    
    
    public static boolean checkUserAndPass(String user,String pass){
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            String strCheck = "select * from users where "+
                    "username='" + user +"' and "+
                    "pass='" + pass +"';";
            stmt.executeQuery(strCheck);
            while(stmt.getResultSet().next()){
                connection.close();
                return true;
            }
        connection.close();
        }
        catch (SQLException ex ){
            Tools.msgBox(ex.getMessage()+ "\n checkUserAndPass error");
        }
        
        return false;
    }
    // running insert & update & delete from data base
    public static boolean runNonQuery(String sqlStatement) throws SQLException{
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(sqlStatement);
            connection.close();
            return true;
        }
        catch(SQLException ex){
            Tools.msgBox(ex.getMessage() + "\n run non query rerror"); 
                connection.close();
            return false;
        }  
        
    }
        public static String getAutoNumber(String tableName,String columenName){
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            String strAuto = "select max(" + columenName + ")+1 as autoNum"+
                    " from "+ tableName +"where dept";
            stmt.executeQuery(strAuto);
            String num = "";
            while(stmt.getResultSet().next()){
                num = stmt.getResultSet().getString("autoNum");
            }
            connection.close();
            if(num.equals(null))return "1";
            else return num;
        }
        catch(SQLException ex){
            Tools.msgBox(ex.getMessage() + "get aouto number error");
            return "0";
        }
    }
    public static String getAutoNumber(String tableName,String columenName,String deptID){
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            String strAuto = "select max(" + columenName + ")+1 as autoNum"+
                    " from "+ tableName +" where deptID ="+ deptID ;
            stmt.executeQuery(strAuto);
            String num = "";
            while(stmt.getResultSet().next()){
                num = stmt.getResultSet().getString("autoNum");
            }
            connection.close();
            if(num == null)return "1";
            else return num;
        }
        catch(SQLException ex){
            Tools.msgBox(ex.getMessage() + "get aouto number error");
            return "0";
        }
    }
    
    public static void getTableData(String statement) throws IOException{
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(statement);
            ResultSetMetaData rsmd =rs.getMetaData();
            
            int columen = rsmd.getColumnCount();
            
            Table table = new Table(columen);
            while(rs.next()){
                Object row [] = new Object [columen];
                for(int x =0 ; x<columen;x++){
                    row[x]= rs.getString(x+1);
                }
                table.addNewRow(row);
                
            }
            int rows = table.getRowsCount(table);
            dos.writeInt(rows);
            for(int i =0;i<rows;i++)
                dos.writeUTF(table.getRowAsString(i));
            connection.close();
            //return table;
              
        }
        catch(SQLException |IOException ex){
            Tools.msgBox(ex.getMessage() + "get table data error");
            dos.writeInt(0);
            //return new Table(0);
        }
    }
    
    public static void fillCombo(String tableName, String columenName){
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String strSelect = "select "+columenName + " from "+ tableName;
            rs = stmt.executeQuery(strSelect);
            String strFill = ";";
            while(rs.next()){
               strFill += rs.getString(1)+";";
            }
            dos.writeUTF(strFill);
            connection.close();
        }
        catch(SQLException | IOException ex){
            Tools.msgBox(ex.getMessage() +"fill combo error");
                   
        }
    }
    
    // fill JTable witch take select statement <<<OR>>> table name
    
    public static void fillToJTable (String tableNameOrSelectStatement){
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String SPart = tableNameOrSelectStatement.substring(0, 7).toLowerCase();
            String strSelect ;
            if("select ".equals(SPart) ){
                strSelect = tableNameOrSelectStatement;
            }
            else{
                strSelect = "select * from "+ tableNameOrSelectStatement;                
            }
            getTableData(strSelect);
            /*int rows = table.getRowsCount(table);
                    dos.writeInt(rows);
                    for(int i =0;i<rows;i++)
                    dos.writeUTF(table.getRowAsString(i));*/
            connection.close();
        }
        catch(SQLException |IOException ex){
            Tools.msgBox(ex.getMessage() + "fill table error");
        }
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
    //private static String [] getContent(String )
    
    public static void CheckReceive(String strReceive) throws NullPointerException,IOException, SQLException{
       if(strReceive.startsWith(";Check;")){
            Vector check =StringToVector(strReceive, ';');
            String userName =(String) check.get(1);
            String pass = (String) check.get(2);
            boolean isUser = checkUserAndPass(userName,pass);
            dos.writeBoolean(isUser);
        }
        else if(strReceive.startsWith(";getauto;")){
            Vector getAouto = StringToVector(strReceive, ';');
            String tableName =(String) getAouto.get(1);
            String columenName = (String) getAouto.get(2);
            String aouto = getAutoNumber(tableName, columenName);
            dos.writeUTF(aouto);
            
        }
        else if(strReceive.startsWith(";getsauto;")){
            Vector getAouto = StringToVector(strReceive, ';');
            String tableName =(String) getAouto.get(1);
            String columenName = (String) getAouto.get(2);
            String deptID = (String)getAouto.get(3);
            String aouto = getAutoNumber(tableName, columenName,deptID);
            dos.writeUTF(aouto);
            
        }
        else if(strReceive.startsWith(";fillcombo;")){
            Vector fillCombo = StringToVector(strReceive, ';');
            String tableName =(String) fillCombo.get(1);
            String columenName = (String) fillCombo.get(2);
            fillCombo(tableName, columenName);
        }
        else if(strReceive.startsWith(";tableName;")){
           Vector fillJTable = StringToVector(strReceive,';');
           String str = (String) fillJTable.get(1);
           fillToJTable(str); 
        }
        else if(strReceive.startsWith("insert")|
                strReceive.startsWith("update")|
                strReceive.startsWith("delete")){
            boolean query = runNonQuery(strReceive);
            dos.writeBoolean(query);
        }
        else if(strReceive.startsWith(";TableData;")){
           Vector tableData = StringToVector(strReceive, ';');
           //Tools.msgBox(strReceive);
           String strData =(String) tableData.get(1);
           
           getTableData(strData);
        }
    }
    
    public static String receiveString(){
         String strReceive = "";
        try{
            strReceive = dis.readUTF();
            return strReceive;
        }
        catch(IOException ex){
            Tools.msgBox(ex.getMessage());
            return null;
        }
    }
    

    
}
