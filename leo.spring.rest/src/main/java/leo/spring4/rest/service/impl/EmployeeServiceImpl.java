package leo.spring4.rest.service.impl;

import leo.spring4.rest.bean.Employee;
import leo.spring4.rest.bean.EmployeeList;
import leo.spring4.rest.ds.EmployeeDS;
import leo.spring4.rest.service.EmployeeService;

import java.util.List;

/**
 * @author YueLi
 * @date 16/10/30
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDS employeeDS;

    public void setEmployeeDS(EmployeeDS ds) {
        this.employeeDS = ds;
    }
    @Override
    public EmployeeList GetEmployee() {
        List<Employee> employees = employeeDS.getAll();
        return new EmployeeList(employees);
    }

    @Override
    public Employee GetEmployeeById(String id) {
        return employeeDS.get(Long.parseLong(id));
    }
}
