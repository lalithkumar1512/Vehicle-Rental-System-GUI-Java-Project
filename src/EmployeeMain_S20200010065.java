import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Employee {
    int employeeId;
    String name;
    double basicPay;
    double allowancePay;
    Date joiningDate;

    public Employee(String name, double basicPay, double allowancePay) throws ParseException {
        this.name = name;
        this.basicPay = basicPay;
        this.allowancePay = allowancePay;
        //this.joiningDate = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(joiningDate);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasicPay() {
        return basicPay;
    }

    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
    }

    public double getAllowancePay() {
        return allowancePay;
    }

    public void setAllowancePay(double allowancePay) {
        this.allowancePay = allowancePay;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void calculateSalary() throws ParseException {
        System.out.println("Salary : " + this.allowancePay + this.basicPay);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", basicPay=" + basicPay +
                ", allowancePay=" + allowancePay +
                '}';
    }
}

class RegularEmployee extends Employee {
    int hikePercent;

    public RegularEmployee(String name, double basicPay, double allowancePay, int hikePercent) throws ParseException {
        super(name, basicPay, allowancePay);
        this.hikePercent = hikePercent;
    }

    public int getHikePercent() {
        return hikePercent;
    }

    public void setHikePercent(int hikePercent) {
        this.hikePercent = hikePercent;
    }

    @Override
    public void calculateSalary() throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        if (this.joiningDate.before((Date) sd.parse("2015/01/01"))){
            System.out.println("Salary : " + this.allowancePay * this.hikePercent + this.basicPay);
        } else {
            System.out.println("Salary : " + this.allowancePay + this.basicPay);
        }
    }
}

class partTimeEmployee extends Employee {
    int hoursWorked;

    public partTimeEmployee(String name, double basicPay, double allowancePay, int hoursWorked) throws ParseException {
        super(name, basicPay, allowancePay);
        this.hoursWorked = hoursWorked;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public void calculateSalary() {
        System.out.println("Salary : " + this.hoursWorked * this.basicPay);
    }
}

public class EmployeeMain_S20200010065{
    public static void main(String[] args) throws ParseException {
        Employee[] emp = new Employee[6];
        emp[0] =new Employee("thomas",40000,2000);
        emp[1]= new Employee("James", 20000, 2000);
        emp[2]= new RegularEmployee("Percy", 20000, 2000,10);
        emp[3] = new RegularEmployee("Toby", 50000, 5000,12);
        emp[4] = new partTimeEmployee("Gordon", 2000, 0,30);
        emp[5]  = new partTimeEmployee("Emily", 2000, 0,60);
        for(int i=0;i<6;i++)
        System.out.println(emp[i]);


    }
}