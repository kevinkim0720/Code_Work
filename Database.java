// Kevin Kim 111378737
// CIS 331 Section 3
// PA 8
// This work was done in accordance to the JMU Honor Code
package assignment8;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import oracle.jdbc.pool.OracleDataSource;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Database extends Application {
    
    // Variables needed for this assignment
    Connection con;
    Statement stmt;

    ComboBox<String> JobTitle = new ComboBox<>();
    ComboBox<String> Department = new ComboBox<>();
    TextField empID = new TextField();
    TextField first = new TextField();
    TextField last = new TextField();
    TextField salary = new TextField();

    @Override
    public void start(Stage primaryStage) {
        try {
            // Creates a gridpane and adds labels and text boxes
            GridPane pane = new GridPane();
            pane.setAlignment(Pos.CENTER);
            pane.add(new Label("Empl ID: "), 0, 0);
            pane.add(empID, 1, 0);
            pane.add(new Label("First Name: "), 0, 1);
            pane.add(first, 1, 1);
            pane.add(new Label("Last Name: "), 0, 2);
            pane.add(last, 1, 2);
            pane.add(new Label("Salary: "), 0, 3);
            pane.add(salary, 1, 3);
            pane.add(new Label("JobTitle"), 0, 4);
            pane.add(JobTitle, 1, 4);
            pane.add(new Label("Department"), 0, 5);
            pane.add(Department, 1, 5);

            // Puts icons into each button
            Image save = new Image("images/save.gif");
            ImageView isave = new ImageView(save);
            Image right = new Image("images/right.gif");
            ImageView iright = new ImageView(right);
            Image left = new Image("images/left.gif");
            ImageView ileft = new ImageView(left);
            Image chart = new Image("images/chart.jpg");
            ImageView ichart = new ImageView(chart);
            
            // Creates new buttons
            Button btUpdateEmployee = new Button("Update Employee", isave);
            Button btPrevious = new Button("Previous", ileft);
            Button btNext = new Button("Next", iright);
            Button btShowChart = new Button("Show Chart", ichart);
            
            // Positions the buttons into the GUI
            pane.add(btUpdateEmployee, 0, 6);
            GridPane.setHalignment(btUpdateEmployee, HPos.LEFT);
            pane.add(btPrevious, 0, 7);
            GridPane.setHalignment(btPrevious, HPos.LEFT);
            pane.add(btNext, 1, 7);
            GridPane.setHalignment(btNext, HPos.CENTER);
            pane.add(btShowChart, 2, 7);
            GridPane.setHalignment(btShowChart, HPos.RIGHT);

            Scene scene = new Scene(pane, 550, 400);
            primaryStage.setTitle("Employee Database GUI");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Connects to SQL
            String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
            OracleDataSource ds = new OracleDataSource();
            ds.setURL(connectionString);
            con = ds.getConnection("emp", "emp");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
       
            // Do a query and popluate combobox titles
            ResultSet rsTitle = stmt.executeQuery("select distinct (title) from employees");
            while (rsTitle.next()) {
                JobTitle.getItems().add(rsTitle.getString(1));
            }
            
            // Do a query and populate combobox departments
            ResultSet rsDepart = stmt.executeQuery("select departmentname from departments");
            while (rsDepart.next()) {
                Department.getItems().add(rsDepart.getString(1));
            }
            
            // Creates first employee data and prints into the GUI
            ResultSet rsEmpl = stmt.executeQuery("select employeeid, firstname, lastname, salary, title, departmentname from employees e, departments d where e.departmentID = d.departmentID(+) order by lastname, firstname");
            rsEmpl.next();
            String id = rsEmpl.getString("employeeid");
            empID.setText(id);
            String f = rsEmpl.getString("firstname");
            first.setText(f);
            String l = rsEmpl.getString("lastname");
            last.setText(l);
            String s = rsEmpl.getString("salary");
            salary.setText(s);
            String title = rsEmpl.getString("title");
            JobTitle.setValue(title);
            String d = rsEmpl.getString("departmentname");
            Department.setValue(d);            

            // When a user clicks on a button, the button's action is performed
            btUpdateEmployee.setOnAction(e -> UpdateEmployee());
            btPrevious.setOnAction(e -> {
                try {
                    Previous(rsEmpl);
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            btNext.setOnAction(e -> {
                try {
                    Next(rsEmpl);
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            btShowChart.setOnAction(e -> ShowChart());

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

    }

    // Method to launch the GUI
    public static void main(String[] args) {
        launch(args);
    }

    // Button function to update an employee's data
    void UpdateEmployee() {
        try{
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String eempID = empID.getText();
            String eFirst = first.getText();
            String eLast = last.getText();
            String eSalary = salary.getText();
            String eJobTitle = JobTitle.getValue();
            String eDepartment = Department.getValue();
            String query = "update employees set firstname = '" + eFirst + "', lastname = '" + eLast + "', salary = " + eSalary + ", title = '" + eJobTitle + "', departmentID = (select departmentid from departments where departmentname = '" + eDepartment +"') where employeeID = " + eempID;
            int rsUpdate = stmt.executeUpdate(query);
            System.out.println(rsUpdate + " records affected");
        } catch (Exception e) {
            System.out.println("Cannot update");
    }

    }

    // Button function to get the previous employee's data
    void Previous(ResultSet rsEmpl) throws SQLException {
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        if (rsEmpl.previous()) {
            String id = rsEmpl.getString("employeeid");
            empID.setText(id);
            String f = rsEmpl.getString("firstname");
            first.setText(f);
            String l = rsEmpl.getString("lastname");
            last.setText(l);
            String s = rsEmpl.getString("salary");
            salary.setText(s);
            String title = rsEmpl.getString("title");
            JobTitle.setValue(title);
            String d = rsEmpl.getString("departmentname");
            Department.setValue(d); 
        }

    }

    // Button function to get the next employee's data
    void Next(ResultSet rsEmpl) throws SQLException {
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        if (rsEmpl.next()){
            String id = rsEmpl.getString("employeeid");
            empID.setText(id);
            String f = rsEmpl.getString("firstname");
            first.setText(f);
            String l = rsEmpl.getString("lastname");
            last.setText(l);
            String s = rsEmpl.getString("salary");
            salary.setText(s);
            String title = rsEmpl.getString("title");
            JobTitle.setValue(title);
            String d = rsEmpl.getString("departmentname");
            Department.setValue(d); 
        }
    }

    // Button function to create a chart of employee salaries
    void ShowChart() throws NullPointerException{
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rsSalary = stmt.executeQuery("select max(salary), min(salary), avg(salary), departmentname from employees e, departments d where e.departmentID = d.departmentID(+) group by departmentname order by departmentname");
            rsSalary.last();
            int rows = rsSalary.getRow();
            String []name = new String[rsSalary.getRow()];
            int []maxim = new int[rsSalary.getRow()];
            int []aver = new int[rsSalary.getRow()];
            int []mini = new int[rsSalary.getRow()];
            
            rsSalary.beforeFirst();

            //For loop to put data into each array
            for (int i = 0; i < rows; i++) {
                if (rsSalary.next()) {
                    String dp = rsSalary.getString("departmentname");
                    name[i] += dp;
                    int mm = rsSalary.getInt("max(salary)");
                    maxim[i] += mm;
                    int ae = rsSalary.getInt("avg(salary)");
                    aver[i] += ae;
                    int mnm = rsSalary.getInt("min(salary)");
                    mini[i] += mnm;
                }
            }

            // Re-naming the last value in name array to unassigned
            name[rows - 1] = "Unassigned";
            
            // Creates a second scene for barchart
            Stage stage = new Stage();
            stage.setTitle("Bar Chart");
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            BarChart<String,Number> bc = new BarChart<>(xAxis, yAxis);
            bc.setTitle("Salaries by Department");
            xAxis.setLabel("Departments");
            yAxis.setLabel("Salary");
            
            XYChart.Series max = new XYChart.Series();
            max.setName("Maximum");
            XYChart.Series avg = new XYChart.Series();
            avg.setName("Average");
            XYChart.Series min = new XYChart.Series();
            min.setName("Minimum");
            
            // Puts data into each series
            for(int i = 0; i < rsSalary.getRow(); i++) {
                max.getData().add(new XYChart.Data(name[i], maxim[i]));
                avg.getData().add(new XYChart.Data(name[i], aver[i]));
                min.getData().add(new XYChart.Data(name[i], mini[i]));
            }
            
            // Prints all data into one bar chart
            bc.getData().addAll(max, avg, min);
            stage.setScene(new Scene(bc, 800, 600));
            stage.show();
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
