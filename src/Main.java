package src;

import java.io.*; 

class Main 
{
    
    static void askInfo (String which, Employee newEmployee) {

        Console cnsl = System.console();

        if (cnsl == null) {
            System.err.println("Console not available.");
            return;
        }
        
        String strSalary;
        boolean salaryValid = false;
        
        switch (which) {
            case "name":
                newEmployee.name = cnsl.readLine(String.format("Insert the employee's %s: ", which));

                while (newEmployee.name == null || newEmployee.name.length() == 0) {
                    newEmployee.name = cnsl.readLine(String.format("Insert the employee's %s: ", which));
                }
                break;
            
            case "role":
                newEmployee.role = cnsl.readLine(String.format("Insert the employee's %s: ", which));

                while (newEmployee.role == null || newEmployee.role.length() == 0) {
                    newEmployee.role = cnsl.readLine(String.format("Insert the employee's %s: ", which));
                }
                break;
            
            case "depto":
                newEmployee.dept = cnsl.readLine(String.format("Insert the employee's %s: ", which));

                while (newEmployee.dept == null || newEmployee.dept.length() == 0) {
                    newEmployee.dept = cnsl.readLine(String.format("Insert the employee's %s: ", which));
                }
                break;
            
            case "salary":
                do {
            
                    try {
                        strSalary = cnsl.readLine("Insert the employee's salary: ");
                        if (strSalary == null || strSalary.trim().isEmpty()) {
                            System.out.println("Salary cannot be empty. Please try again.");
                        } else if (Integer.parseInt(strSalary) < 0) {
                            System.out.println("Salary can not be a negative value.");
                        } else {
                            newEmployee.salary = Integer.parseInt(strSalary);
                            salaryValid = true;
                        }
                    } catch (NumberFormatException e){
                        System.out.println("Invalid input for salary. Please enter a numeric value.");
                        salaryValid = false;
                    }
        
                } while (!salaryValid);
                break;            
        }

    }

    public static void main(String a[])
    {

        Employee newEmployee = new Employee();

        Console cnsl = System.console();
    
        if (cnsl == null) {
            System.out.println("error");
            return;
        }

        System.out.println("Welcome!");
        System.out.println("To register an employee, you need the: Name, role, department and salary.");
        System.out.println("----------------------------------------");

        askInfo("name", newEmployee);
        askInfo("role", newEmployee);
        askInfo("depto", newEmployee);
        askInfo("salary", newEmployee);

        Employee.registerEmp(newEmployee);
        
    }
}