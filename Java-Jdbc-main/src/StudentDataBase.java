import java.sql.*;
import java.util.Scanner;
public class StudentDataBase {
    static final String URL="jdbc:mysql://localhost:3306/jdbc_test";
    static final String USER="root";
    static final String PASSWORD="5007";
  public static void main(String[] args){
      try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn=DriverManager.getConnection(URL,USER,PASSWORD);
          System.out.println(" Connected to Database : student_db ");
          Scanner sc=new Scanner(System.in);
          int choice;
          do{
              System.out.println("\n**** Student Management Menu ****");
              System.out.println("1. Add Student");
              System.out.println("2. View All Students");
              System.out.println("3. Update Student ");
              System.out.println("4. Delete Student");
              System.out.println("5. Update newly  created columns in table");
              System.out.println("6. Search Student");
              System.out.println("7. Exit ");
              System.out.println("Enter your choice: ");
              choice =sc.nextInt();
              switch(choice){
                  case 1 -> addStudent(conn,sc);
                  case 2 -> viewStudents(conn);
                  case 3 -> updateStudent(conn,sc);
                  case 4 -> deleteStudent(conn,sc);
                  case 5 -> updateNewCols(conn,sc);
                  case 6 -> searchStudent(conn,sc);
                  case 7 ->{
                      System.out.println(" Exiting... ");
                      conn.close();
                  }
                  default -> System.out.println(" invalid choice, try again!");
              }
          } while(choice!=7);
      }catch(Exception e){
          e.printStackTrace();
      }
  }
                      // Add Student
    private static void addStudent(Connection conn,Scanner sc) throws SQLException{
      sc.nextLine();
      System.out.print("Enter name : ");
      String name=sc.nextLine();
      System.out.print(" Enter email : ");
      String email=sc.nextLine();
      System.out.print("Enter age : ");
      int age=sc.nextInt();
      sc.nextLine();
      System.out.print("Enter address : ");
      String address=sc.nextLine();
      System.out.print("Enter DOB (YYYY-MM-DD) : ");
      String dob=sc.nextLine();
      System.out.print("Enter College : ");
      String college=sc.nextLine();

      String sql="INSERT INTO student(name,email,age,address,dob,college) VALUES (?,?,?,?,?,?)";
      PreparedStatement st=conn.prepareStatement(sql);
      st.setString(1,name);
      st.setString(2,email);
      st.setInt(3,age);
      st.setString(4,address);
      st.setString(5,dob);
      st.setString(6,college);

      st.executeUpdate();
      System.out.println(" Student added successfully! ");
    }

                  // View Students

    private static void viewStudents(Connection conn) throws SQLException {
      String sql="SELECT * FROM student ";
      Statement st=conn.createStatement();
      ResultSet rs=st.executeQuery(sql);
      System.out.println("\n *** Student Records ***");
      while(rs.next()){
          printStudent(rs);
      }
    }

                 // Update Student(All Fields)

    private static void updateStudent(Connection conn,Scanner sc) throws SQLException{
      System.out.print("Enter student ID to update : ");
      long id=sc.nextLong();
      sc.nextLine();

      System.out.print("New name : ");
      String name=sc.nextLine();
      System.out.print("New email: ");
      String email=sc.nextLine();
      System.out.print("New age: ");
      int age=sc.nextInt();
      System.out.print("New address: ");
      String address=sc.nextLine();
      System.out.print("New DOB: ");
      String dob=sc.nextLine();
      System.out.print("New college: ");
      String college=sc.nextLine();

      String sql="UPDATE student SET name=?,email=?,age=?,address=?,dob=?,college=?,WHERE id=?";
      PreparedStatement st=conn.prepareStatement(sql);
      st.setString(1,name);
      st.setString(2,email);
      st.setInt(3,age);
      st.setString(4,address);
      st.setString(5,dob);
      st.setString(6,college);
      st.setLong(7,id);

      int rows=st.executeUpdate();
      if(rows >0){
          System.out.println("Student database updated successfully!");
      }else{
          System.out.println("Student not found ");
      }
    }

             // Update only new columns

    private static void updateNewCols(Connection conn,Scanner sc) throws SQLException{
      System.out.print("Enter student ID to update new columns: ");
      long id=sc.nextLong();
      sc.nextLine();

      System.out.print("Enter address: ");
      String address =sc.nextLine();
      System.out.print("Enter DOB (YYYY-MM-DD) : ");
      String dob=sc.nextLine();
      System.out.print("Enter college :");
      String college=sc.nextLine();

      String sql="UPDATE student SET address=?,dob=?,college=? WHERE id=?";
      PreparedStatement st=conn.prepareStatement(sql);
      st.setString(1,address);
      st.setString(2,dob);
      st.setString(3,college);
      st.setLong(4,id);

      int rows=st.executeUpdate();
      if(rows>0){
          System.out.println("Student extra details updated successfully!");
      }else{
          System.out.println("Student not found!");
      }
    }

               // Delete Student

    private static void deleteStudent(Connection conn,Scanner sc) throws SQLException{
      System.out.print("Enter student ID to delete : ");
      long id=sc.nextLong();

      String sql="DELETE FROM student WHERE id=?";
      PreparedStatement st=conn.prepareStatement(sql);
      st.setLong(1,id);

      int rows =st.executeUpdate();
      if (rows>0){
          System.out.println(" Student deleted successfully!");
      }else{
          System.out.println(" Student not found! ");
      }
    }

                // Search Student

    private static void searchStudent(Connection conn,Scanner sc) throws SQLException{
      System.out.println("\n Search by : ");
      System.out.println("1. ID : ");
      System.out.println("2. Name : ");
      System.out.println("3.Email : ");
      System.out.println("Enter your choice : ");
      int option =sc.nextInt();
      sc.nextLine();

      String sql =" ";
      PreparedStatement st=null;

      switch(option){
          case 1 -> {
              System.out.print("Enter student ID : ");
              long id=sc.nextLong();
              sql="SELECT * FROM student WHERE id=?";
              st=conn.prepareStatement(sql);
              st.setLong(1,id);
          }
          case 2 -> {
              System.out.print("Enter student name : ");
              String name=sc.nextLine();
              sql="SELECT * FROM student WHERE name LIKE ?";
              st=conn.prepareStatement(sql);
              st.setString(1,"%"+name+"%");
          }
          case 3 -> {
              System.out.print("Enter student email: ");
              String email=sc.nextLine();
              sql="SELECT * FROM student WHERE email=?";
              st=conn.prepareStatement(sql);
              st.setString(1,email);
          }
          default -> {
              System.out.println(" Invalid option !");
              return ;
          }
      }

      ResultSet rs=st.executeQuery();
      boolean found=false;
      while(rs.next()){
          printStudent(rs);
          found=true;
      }
      if(!found) {
          System.out.println("no student found");
      }
    }

          // Print student details

    private static void printStudent(ResultSet rs) throws SQLException {
      System.out.println(
              rs.getLong("id")+" | "+
              rs.getString("name")+   " | "+
              rs.getString("email")+  " | "+
              rs.getString("age") +   " | "+
              rs.getString("address")+" | "+
              rs.getString("dob")+    " | "+
              rs.getString("college"));
    }
}
