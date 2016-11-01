package leo.spring4.rest.service;

import leo.spring4.rest.bean.EmployeeList;

/**
 * Created by YueLi on 16/10/30.
 *
 */
public interface EmployeeService {

    /**
     * 获取雇员信息
     * @return 雇员列表
     */
    EmployeeList GetEmployee();
}
