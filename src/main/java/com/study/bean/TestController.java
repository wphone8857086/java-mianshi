package com.study.bean;
 
import com.study.bean.test.CalculateService;
import com.study.bean.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * @description:
 * @author: Lxq
 * @date: 2020/7/10 15:17
 */
@RestController
public class TestController {
 
    @Autowired
    private TestService testService;
 
    @Autowired
    private CalculateService calculateService;
 
    @RequestMapping("/test")
    public String getHello() {
        String testList = testService.getList("code","name");
        String calculateResult = calculateService.getResult("测试");
        return (testList + "," +calculateResult);
    }
 
 
}