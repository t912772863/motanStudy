//package com.tian;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//public class Client {
//
//    /**
//     * 客户端调用
//     * @param args
//     */
//    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:motan-client.xml");
//        IDemoService service = (IDemoService) applicationContext.getBean("motanDemoServiceImpl");
//        String s = service.get("this is motan.");
//        System.out.println(s);
//    }
//}
