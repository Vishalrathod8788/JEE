package com.pro.ProjectWithMaven;

import java.sql.Connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Project Started !" );
        
        Configuration cnf = new Configuration();
        cnf.configure("hibernate.cfg.xml");
        SessionFactory factory = cnf.buildSessionFactory();
        
        System.out.println(factory);
    }
}
