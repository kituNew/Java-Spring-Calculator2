package com.example.demo.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demo.service.OperationExecutor;
import com.example.demo.service.OperationNavigator;
import com.example.demo.strategy.binary.OperationStrategy;

public class XmlContextBootstrap {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        OperationExecutor executor = context.getBean(OperationExecutor.class);
        OperationNavigator navigator = context.getBean(OperationNavigator.class);

        OperationStrategy strategy = navigator.getBinary("+");
        double result = executor.execute(strategy, 2, 3);

        System.out.println("2 + 3 = " + result);
    }
}