package leo.spring4.rest.controller;

/**
 * Created by plumm on 2016-10-10.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@Controller
public class EmployeeController {
    @RequestMapping(method = RequestMethod.GET, value = "/employee/{id}")
    public ModelAndView getEmployee(@PathVariable String id) {
        Employee e = employeeDS.get(Long.parseLong(id));
        return new ModelAndView(XML_VIEW_NAME, "object", e);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/employee")
    public ModelAndView addEmployee(@RequestBody String body) {
        Source source = new StreamSource(new StringReader(body));
        Employee e = (Employee) jaxb2Mashaller.unmarshal(source);
        employeeDS.add(e);
        return new ModelAndView(XML_VIEW_NAME, "object", e);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/employee/{id}")
    public ModelAndView updateEmployee(@RequestBody String body) {
        Source source = new StreamSource(new StringReader(body));
        Employee e = (Employee) jaxb2Mashaller.unmarshal(source);
        employeeDS.update(e);
        return new ModelAndView(XML_VIEW_NAME, "object", e);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/employee/{id}")
    public ModelAndView removeEmployee(@PathVariable String id) {
        employeeDS.remove(Long.parseLong(id));
        List<Employee> employees = employeeDS.getAll();
        EmployeeList list = new EmployeeList(employees);
        return new ModelAndView(XML_VIEW_NAME, "employees", list);
    }
}