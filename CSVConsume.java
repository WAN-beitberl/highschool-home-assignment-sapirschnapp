import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.* ;
// for standard JDBC programs

import static java.lang.Integer.parseInt;

public class CSVConsume
{
    public static void main(String[] args)
    {
        String jdbcUrl="jdbc:mysql://localhost:3306/sapir";
        String filePath="C:\\Users\\sint1\\Desktop\\highschool.csv";
        String username ="root";
        String password="aq123eds";
        int batchSize=20;
        Connection connection= null;
        try {
         connection= DriverManager.getConnection(jdbcUrl,username,password);
         connection.setAutoCommit(false);
         String sql ="insert into employee(Id,First_name,Last_name,Email,Gender,Ip_address,Height,Age,Has_car,Car_color,Grade,Grade_avg,Identification_card) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
         PreparedStatement statement=connection.prepareStatement(sql);
         BufferedReader lineReader=new BufferedReader(new FileReader(filePath));
         String lineText=null;
         int count=0;
         lineReader.readLine();
         while ((lineText= lineReader.readLine()) !=null)
         {
             String [] data= lineText.split(",");
             String id=data[0];
             String First_name=data[1];
             String Last_name=data[2];
             String Email=data[3];
             String  Gender=data[4];
             String Ip_address=data[5];
             String Height=data[6];
             String Age=data[7];
             String Has_car=data[8];
             String Car_color =data[9];
             String Grade =data[10];
             String Grade_avg =data[11];
             String Identification_card =data[12];

             statement.setInt(1,parseInt(id));
             statement.setString(1,First_name);
             statement.setString(1,Last_name);
             statement.setString(1,Email);
             statement.setString(1,Gender);
             statement.setString(1,Ip_address);
             statement.setInt(1,parseInt(Height));
             statement.setInt(1,parseInt(Age));
             statement.setString(1,Has_car);
             statement.setString(1,Car_color);
             statement.setInt(1,parseInt(Grade));
             statement.setFloat(1, (float) Double.parseDouble(Grade_avg));
             statement.setString(1,Identification_card);
             statement.addBatch();
             if (count%batchSize==0)
             {
                 statement.executeBatch();
             }

         }
lineReader.close();
         statement.executeBatch();
         connection.commit();
         connection.close();
         System.out.println("sucees");
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

    }
}