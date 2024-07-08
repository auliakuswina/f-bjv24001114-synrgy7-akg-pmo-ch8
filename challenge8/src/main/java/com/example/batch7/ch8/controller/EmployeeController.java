package com.example.batch7.ch8.controller;


import com.example.batch7.ch8.config.Config;
import com.example.batch7.ch8.entity.Employee;
import com.example.batch7.ch8.service.EmployeeService;
import com.example.batch7.ch8.repository.EmployeeRepository;
import com.example.batch7.ch8.sp.QuerySPEmployee;
import com.example.batch7.ch8.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    //CURD : method CURD ada dimana?
    @Autowired
    private EmployeeService employeeService;

    Config config = new Config(); // tampa DI -> Depedency injection
    @Autowired
    public Response response;

    //JIKa pake DI
    @Autowired
    private Config configWithDI;

    @GetMapping(value = "")
    public String hello(){
        return "Hello Word";

    }

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping(value = {"/list-employee", "/list-employee/"})
    public ResponseEntity<Map> getListEmpoyee() {

        return new ResponseEntity<Map>(response.sukses(employeeService.pagination(0,1)), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public Map hello(@RequestBody Employee request){
        Map map = new HashMap();
        try {
            logger.info("Request:",request);
            map =  employeeService.save(request);
            return map;
        }catch (Exception e){
            logger.info("Eror hello :",e.getMessage());
            return map;
        }
    }



    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public QuerySPEmployee querySPEmployee;

    @GetMapping(value = {"/list", "/list/"})
    public ResponseEntity<Map> getById() {
        Map map = new HashMap();
        map.put("list",employeeRepository.getListSP());
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
    @GetMapping(value = {"/inject", "/inject/"})
    public ResponseEntity<String> inject() {

        jdbcTemplate.execute(querySPEmployee.getData);
        jdbcTemplate.execute(querySPEmployee.getDataEmployeeLikeName);
        jdbcTemplate.execute(querySPEmployee.insertEmployee);
        jdbcTemplate.execute(querySPEmployee.updateEmployee);
        jdbcTemplate.execute(querySPEmployee.deletedEmployee);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

}
