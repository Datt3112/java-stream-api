package com.java.stream.api;

import com.java.stream.api.entity.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoStreamApiApplication {

    static List<Employee> employeeList = new ArrayList<>();

    static {
        employeeList.add(new Employee(123, "VIvan", "satgonda", 90000.0, List.of("Project1", "Project2")));
        employeeList.add(new Employee(124, "Abhishek", "Tadpally", 100000.0, List.of("Project1", "Project2")));
        employeeList.add(new Employee(125, "Avinash", "Tadpally", 80000.0, List.of("Project1", "Project2")));
        employeeList.add(new Employee(128, "Krishank", "satgonda", 60000.0, List.of("Project1", "Project2")));
        employeeList.add(new Employee(130, "Avyukt", "satgonda", 30000.0, List.of("Project1", "Project2")));
    }


    public static void main(String[] args) {

        //SpringApplication.run(DemoStreamApiApplication.class, args);

        //Iterating the employee List
        employeeList.stream().forEach(emp -> System.out.println(emp)); //Terminal opration

        //Map- allows to map different type of object and returns the steam
        //collect -collect in list form
        List<Employee> incrementedemplist = employeeList.stream()
                .map(employee -> new Employee(employee.getEmpId(), employee.getFirstName(), employee.getLastName()
                        , employee.getSalary() * 2.0, employee.getProjects())).collect(Collectors.toList());
        System.out.println(incrementedemplist);

        //Filter for conditional checks...
        List<Employee> filteredEmp = employeeList.stream().filter(employee -> employee.getSalary() > 60000.0)
                .map(employee -> new Employee(employee.getEmpId(), employee.getFirstName(), employee.getLastName()
                        , employee.getSalary() * 2.0, employee.getProjects())).collect(Collectors.toList());
        System.out.println(filteredEmp);
        System.out.println("===================================");
        //FindFirst Operation
        Employee optEmployee = employeeList.stream().filter(employee -> employee.getSalary() > 60000.0).findFirst().orElse(null);
        System.out.println(optEmployee);

        System.out.println("===================================");

        //FlatMap : is used for flaten upp the the object you have : exa: if you have List<List<Object>> it will return List<Object>

        String project = employeeList.stream().map(employee -> employee.getProjects())
                .flatMap(String -> String.stream()).findFirst().orElse(null);

        System.out.println(project);

        System.out.println("===================================");
        //Short Circuit operations

        List<Employee> empOne = employeeList.stream().skip(1).limit(1).collect(Collectors.toList());
        System.out.println(empOne);

        System.out.println("===================================");

        //Finit Data
        List<Employee> sortedList = employeeList.stream().sorted(Comparator.comparing(Employee::getFirstName)).collect(Collectors.toList());
        System.out.println(sortedList);

        System.out.println("===================================");
        Employee emp=employeeList.stream().max(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new);
        System.out.println(emp);

        System.out.println("===================================");
        //Reduce..
        Double sum=employeeList.stream().map(employee -> employee.getSalary()).reduce(0.0,Double::sum);
        System.out.println(sum);


    }

}
