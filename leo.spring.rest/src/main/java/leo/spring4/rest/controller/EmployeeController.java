package leo.spring4.rest.controller;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import leo.spring4.rest.bean.Employee;
import leo.spring4.rest.bean.EmployeeList;
import leo.spring4.rest.ds.EmployeeDS;
import leo.spring4.rest.service.EmployeeService;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    private EmployeeDS employeeDS;

    public void setEmployeeDS(EmployeeDS ds) {
        this.employeeDS = ds;
    }

    private Jaxb2Marshaller jaxb2Mashaller;

    public void setJaxb2Mashaller(Jaxb2Marshaller jaxb2Mashaller) {
        this.jaxb2Mashaller = jaxb2Mashaller;
    }

    private static final String XML_VIEW_NAME = "employees";


    @RequestMapping(method=RequestMethod.GET, value="/employee/{id}")
    public ModelAndView getEmployee(@PathVariable String id) {
        Map<String,Object> map = new HashMap<String,Object>();

        Employee employee = employeeService.GetEmployeeById(id);
        map.put("id", employee.getId());
        map.put("email", employee.getEmail());
        map.put("pro", employee);
        return new ModelAndView(XML_VIEW_NAME, "object", map);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/employee/{id}")
    public ModelAndView updateEmployee(@RequestBody String body) {
        Source source = new StreamSource(new StringReader(body));
        Employee e = (Employee) jaxb2Mashaller.unmarshal(source);
        employeeDS.update(e);
        return new ModelAndView(XML_VIEW_NAME, "object", e);
    }

    @RequestMapping(method=RequestMethod.POST, value="/employee")
    public ModelAndView addEmployee(@RequestBody String body) {
        Source source = new StreamSource(new StringReader(body));
        Employee e = (Employee) jaxb2Mashaller.unmarshal(source);
        employeeDS.add(e);
        return new ModelAndView(XML_VIEW_NAME, "object", e);
    }
    @DeleteMapping("/employee/{id}")
    public ModelAndView removeEmployee(@PathVariable String id) {
        employeeDS.remove(Long.parseLong(id));
        List<Employee> employees = employeeDS.getAll();
        EmployeeList list = new EmployeeList(employees);
        return new ModelAndView(XML_VIEW_NAME, "object", list);
    }

    @GetMapping("/employees")
    public ModelAndView getEmployees() {
        return new ModelAndView(XML_VIEW_NAME, "employees", employeeService.GetEmployee());
    }

    @GetMapping("/content")
    public ModelAndView getContent() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content");
        mav.addObject("sampleContentList", employeeService.GetEmployee());
        return mav;
    }
//    @RequestMapping(method=RequestMethod.GET, value="/employees")
//    public ModelAndView getEmployees() {
//        List<Employee> employees = employeeDS.getAll();
//        EmployeeList list = new EmployeeList(employees);
//        return new ModelAndView(XML_VIEW_NAME, "employees", list);
//    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
