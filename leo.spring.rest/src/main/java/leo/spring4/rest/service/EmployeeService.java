package leo.spring4.rest.service;

import leo.spring4.rest.bean.EmployeeList;

/**
 * Created by plummoon on 16/10/30.
 */
public interface EmployeeService {

    /**
     * 获取雇员信息
     * @return
     */
    EmployeeList GetEmployee();
}
