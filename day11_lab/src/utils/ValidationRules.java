package utils;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import com.app.core.Department;
import com.app.core.Employee;

import custom_exceptions.EmpHandlingException;
import static com.app.core.Employee.sdf;
import static com.app.core.Department.valueOf;

public class ValidationRules {
	public static final int MIN_LENGTH;
	public static final int MAX_LENGTH;
	public static Date thresholdDate;
	static {
		MIN_LENGTH = 4;
		MAX_LENGTH = 15;
		try {
			thresholdDate = sdf.parse("1/4/2021");
		} catch (ParseException e) {
			System.out.println("err in static init block " + e);
		}
	}

	// add public static method for validating all inputs
	public static Employee validateAllInputs(int empId, Employee[] empData, String firstName, String lastName,
			String email, String deptId, String joinDate, double salary) throws EmpHandlingException, ParseException {
		validateEmpId(empId, empData);
		validateName(firstName, "First");
		validateName(lastName, "Last");
		validateEmail(email);
		Department dept = validateDept(deptId);
		Date date = parseValidateJoinDate(joinDate);
		// =>all i/ps are valid --encapsulate all these details in emp class instance ,
		// ret it's ref to the caller
		return new Employee(empId, firstName, lastName, email, dept, date, salary);
	}

//add a public static method : to validate email (invoked by tester) 
	// rule : must contain : "@" --contains , must end with .com --endsWith
	// return validated email , in case of no validation errs
	// otherwise throw custom exc.
	public static String validateEmail(String email) throws EmpHandlingException {
		if (email.contains("@") && email.endsWith(".com"))
			return email;
		// => invalid email --throw custom exc
		throw new EmpHandlingException("Invalid Email!!!!");
	}

	// add static method to validate first/last name : in case of success ret
	// first/last name
	// , o.w throw cust exc with proper mesg
	public static String validateName(String name, String mesg) throws EmpHandlingException {
		if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH)
			throw new EmpHandlingException("Invalid " + mesg + " Name : Must be within range 4-15");
		// => valid name
		return name;
	}

	// add a static method to validate dept
	public static Department validateDept(String dept) throws EmpHandlingException {
		try {
			// parse string --> Enum
			return valueOf(dept.toUpperCase());
		} catch (IllegalArgumentException e) {
			// create StringBuilder object inited with err mesg
			StringBuilder sb = new StringBuilder("Invalid Department\n");
			sb.append("Valid Departments : ");
			// System.out.println(sb);//
			sb.append(Arrays.toString(Department.values()));
			// System.out.println(sb);
			// throw custom exc with suitable mesg
			throw new EmpHandlingException(sb.toString());
		}
	}

	// add a static method , for parsing n validation of join date
	public static Date parseValidateJoinDate(String joinDate) throws ParseException, EmpHandlingException {
		// parsing (string ---> Date)
		Date d1 = sdf.parse(joinDate);
		// => parsing is successful --continue to validation
		if (d1.after(thresholdDate))
			return d1;// parsed n validated date
		// => validation failure
		throw new EmpHandlingException("Invalid join date");

	}

	// add a static method , to check for dup emp id.
	// in case of dup id : throw custom exc , o.w return validated(non dup) emp id
	// to the caller
	public static int validateEmpId(int empId, Employee[] empData) throws EmpHandlingException
	// empId=sc.nextInt() , empData=emps
	{
		// empData : {e1(10),e2(1),e3(100),e4(20),e5(50),null......}
		Employee newEmp = new Employee(empId);// empId 101
		// how are u going to chk for dups ? : Employee's equals
		for (Employee e : empData)
			if (e != null)
				if (e.equals(newEmp))
					throw new EmpHandlingException("Dup Emp ID!!!!!");
		// => no dup id
		return empId;

	}

	// add a static method for validating emp id
	// Method should throw custom exc : in case of invaid emp id
	// rets Employee ref , encapsulating complete emp details
	public static Employee getEmpDetails(int empId, Employee[] emps) throws EmpHandlingException{
		// empData : {e1(10),e2(1),e3(100),e4(20),e5(50),null......}
		Employee newEmp = new Employee(empId); //200
		for (Employee e : emps)
			if (e != null)
				if (e.equals(newEmp))
					return e;
		// => emp id not found
		throw new EmpHandlingException("Emp not found : Invalid Emp Id");

	}

}
