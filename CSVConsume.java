
    import java.sql.*;
    import java.util.*;
    public class CSVConsume
    {
        public static void main(String args[])
        {


            try {
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sapir", "root", "aq123eds");
                Statement stmt = con.createStatement();
                //      ResultSet rs=stmt.executeQuery("select * from highschool");
                //ResultSet rs = stmt.executeQuery("select * from highs4chool");
                //ResultSet rs=stmt.executeQuery("select * from ids_and_avgs");


                //highschool table
         /*       while (rs.next())1
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3)
                            + " " + rs.getString(4) + "  " + rs.getString(5) + "  " + rs.getString(6) + "  " +
                            rs.getInt(7) + "  " + rs.getInt(8) + "  " + rs.getString(9) + "  "
                            + rs.getString(10) + "  " + rs.getInt(11) + " " + rs.getDouble(12) +
                            " " + rs.getInt(13));
*/
              //  highschool_friends table
                // while(rs1.next())
                // System.out.println(rs.getInt(1)+"  "+rs.getInt(2) + " " + rs.getInt(3));

                //ids_and_avgs view
                //  while(rs.next())
                //    System.out.println(rs.getInt(1)+"  "+rs.getDouble(2));
                con.close();
            }
            catch (Exception e) {
                System.out.println(e);
            }

            System.out.println("enter input");
            Scanner sc = new Scanner(System.in);
            int x = sc.nextInt();

            while (x!=8)
            {
                switch (x)
                {
                    case 1:

                        try {
                            Connection con = DriverManager.getConnection(
                                    "jdbc:mysql://localhost:3306/sapir", "root", "aq123eds");
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("select AVG(Grade_avg) from view_avg");
                            while (rs.next())
                                System.out.println("the avg of the school is=" + rs.getDouble(1));

                            con.close();
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }
                        break;


                    case 2:
                        try {
                            Connection con = DriverManager.getConnection(
                                    "jdbc:mysql://localhost:3306/sapir", "root", "aq123eds");
                            Statement stmt = con.createStatement();

                            ResultSet rs = stmt.executeQuery("select AVG(Grade_avg) as 'girls avg' from highschool\n" +
                                    "where gender LIKE 'Female';");
                            while (rs.next())
                                System.out.println("the avg of the girls is=" + rs.getDouble(1));
                            con.close();
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }
                        break;

                    case 3:
                        try {
                            Connection con = DriverManager.getConnection(
                                    "jdbc:mysql://localhost:3306/sapir", "root", "aq123eds");
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("select AVG(Grade_avg) as 'boys avg' from highschool\n" +
                                    "where gender LIKE 'Male';");
                            while (rs.next())
                                System.out.println("the avg of the boys is=" + rs.getDouble(1));
                            con.close();
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }
                        break;

                    case 4:  try {
                        Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/sapir", "root", "aq123eds");
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("select AVG(height)  from highschool\n" +
                                "where Height >= 200 AND Car_color LIKE 'purple';");
                        while (rs.next())
                            System.out.println("the avg of height=" + rs.getDouble(1));
                        con.close();
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                        break;
                    case 5:
                    {
                        try {
                        System.out.println(" ID");
                        int Id = sc.nextInt();
                        String friends []= new String[10];
                        int counter =0;

                        Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/sapir", "root", "aq123eds");
                        Statement stmt = con.createStatement();

                        ResultSet rs=stmt.executeQuery("select * from friendships where friend_id = "+Id +" and other_friend_id != 0");
                        System.out.println("his friends ID:");
                        while (rs.next()) {
                            friends[counter++]=rs.getString("other_friend_id");
                            System.out.println(rs.getInt("other_friend_id"));
                        }
                        rs = stmt.executeQuery("select * from friendships where other_friend_id = "+Id+" and friend_id != 0");
                        while (rs.next()) {
                            friends[counter++]=rs.getString("friend_id");
                            System.out.println(rs.getInt("friend_id"));
                        }
                        System.out.println("friends of friends ID:");
                        for(int i=0;i<counter;i++)
                        {
                            ResultSet rs1 = stmt.executeQuery("select * from friendships where friend_id = "+friends[i]+" and other_friend_id != 0 and other_friend_id <> "+Id);
                            while (rs1.next()) {
                                System.out.println(rs1.getInt("other_friend_id"));
                            }
                            ResultSet rs2= stmt.executeQuery("select * from friendships where other_friend_id = "+friends[i]+" and friend_id != 0 and friend_id <> "+Id);
                            while (rs2.next()) {
                                System.out.println(rs2.getInt("friend_id"));
                            }
                        }
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    }
                    case 6:
                        try {
                            Connection con = DriverManager.getConnection(
                                    "jdbc:mysql://localhost:3306/sapir", "root", "aq123eds");
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("WITH friend_counts AS (\n" + "  SELECT \n" + "    Id, \n" + "    (SELECT COUNT(*) FROM friendships WHERE (friend_id = id OR other_friend_id = id) and (id != 0)) as num_friends\n" + "  FROM \n" + "    (SELECT DISTINCT friend_id as idd FROM friendships\n" + "    UNION\n" +
                                "    SELECT DISTINCT other_friend_id FROM friendships) as id\n" + ")\n"  + "SELECT \n" +
                                "  number_of_friends, \n" +
                                "  COUNT(*) as total, \n" +
                                "  ROUND(COUNT()/(SELECT COUNT() FROM friend_counts)*100, 2) as percentage\n" +
                                "FROM friend_counts\n" +
                                "GROUP BY number_of_friends\n" +
                                "ORDER BY number_of_friends;");

                            double sum1=0;
                        double sum=0;

                        int counter = 0;
                        while (rs.next()) {
                            if(rs.getInt("num_friends")==1)
                            {
                                sum1=rs.getDouble("percentage");
                            }
                            else
                            {
                                if(rs.getInt("num_friends")!=0)
                                {
                                    sum += rs.getDouble("percentage");
                                }
                            }
                        }
                            System.out.println("one friend is: "+sum1);
                            System.out.println("two and above friends is: "+sum);
                        System.out.println(" have no friends is: "+(100-sum-sum1));
                            con.close();
                    }
                        catch (Exception e) {
                        System.out.println(e);
                    }
                        break;

                    case 7:
                        try {
                            System.out.println("enter ID");
                            int Id =sc.nextInt();
                            Connection con = DriverManager.getConnection(
                                    "jdbc:mysql://localhost:3306/sapir", "root", "aq123eds");
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("select Grade_avg from view_avg where id=="+Id+" ");
                            while (rs.next())
                                System.out.println("the avg of the student is=" + rs.getDouble(1));

                            con.close();
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                        default :
                        // something if anything not match
                        System.out.println(" enter input again");
                }
                x = sc.nextInt();
            }
        }
    }
