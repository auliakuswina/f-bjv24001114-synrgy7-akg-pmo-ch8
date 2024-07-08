package com.example.batch7.ch8.testing;

import com.example.batch7.ch8.entity.Employee;
import com.example.batch7.ch8.service.EmployeeService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestingEmployee {

    @Autowired
    public EmployeeService employeeService;

    @Test
    public void saveEmployee(){
        Employee save = new Employee();
        save.setName("Aldi");
        save.setAddress("Jakarta");
        save.setStatus("active");

       Map map =  employeeService.save(save);
        int responseCode = (Integer) map.get("status");
        Assert.assertEquals(200, responseCode);

    }


    @Test
    public void getPagination(){
        Map map =  employeeService.pagination(0,10);
        int responseCode = (Integer) map.get("status");
        Assert.assertEquals(200, responseCode);
    } 
}
