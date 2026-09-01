package universityserver;

public class Table {
    public int columns;
    public Object [][] Items;
    
    // constractor table
    public Table (int columns){
       this.columns = columns;
       Items = new Object [0][columns];
    }
    
    public void addNewRow(Object row[]){
        //الإحتفاظ بالعناصر القديمة داخل متغير مؤقت
        Object temp [][] = Items;
        // إضافة سطر جديد للجدول
        Items = new Object [Items.length+1][columns];
        // تعبئة العناصر القديمة في العنصر الأساسي
        for (int x=0;x<temp.length;x++){
            Items[x]=temp[x];
        }
        //إضافة السطر الجديد للجدول 
        Items[Items.length-1]= row;
    }
    
    public void printTable(){
        for(Object [] item:Items){
            for (Object itm:item){
                System.out.print(itm + " ; ");
            }
            System.out.println();
        }
    }
    
    public void editRow(int rowIndex,int columnIndex,Object newData){
        Items[rowIndex -1][columnIndex -1]= newData;
    }
    
    public void deleteRow(int rowIndex){
        Object temp [][]=Items;
        Items = new Object [Items.length-1][columns];
        int y=0;
       /* this code is correct but it is longer than the next
        for(int x =0; x<Items.length;x++){
            if(x>=rowIndex-1){
                Items[x]=temp[x+1];
            }
            else{
                Items[x]=temp[x];
            }
        }*/
       for(int x =0; x<temp.length;x++){
           if(x!=rowIndex-1){
               Items[y]=temp[x];
               y++;
           }   
       }
    }
    
    public Object getValue(int rowindex,int columnIndex){
       return Items[rowindex-1][columnIndex-1];
    }
    
    public Object[] getRow(int rowIndex){
        return Items[rowIndex];
    }
    public Object[][] tableToArray(){
        Object [][]array = new Object[Items.length][columns];
        for (int row = 0;row<Items.length;row++){
            for(int col = 0;col<columns;col++)
                array[row][col] = Items[row][col];
        }
        return array;
    }
    public int getRowsCount (Table table){
        return table.Items.length;
    }
    
    // get row as string
    public String getRowAsString(int row){
        Object[] table = getRow(row);
        String str = "";
        for(Object item:table)
            str += ";" + item;
        str += ";";
        return str;
    }
}
