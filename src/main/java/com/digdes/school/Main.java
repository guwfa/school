package com.digdes.school;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        JavaSchoolStarter javaSchoolStarter = new JavaSchoolStarter();

        List<Map<String,Object>> INSERT = null;
        List<Map<String,Object>> UPDATE = null;
        List<Map<String,Object>> DELETE = null;
        List<Map<String,Object>> SELECT = null;

        try {
            /**INSERT*/
            INSERT = javaSchoolStarter.execute("INSERT VALUES " +
                   "('lastName' = 'Иванов' , 'id'=1, 'age'=30)" +
                   "('lastName' = 'Петров' ,'id'=3, 'age'=20)" +
                   "('lastName' = 'Максимов' , 'id'=2, 'age'=60, 'active'=true)"
           );
            INSERT = javaSchoolStarter.execute("INSERT VALUES 'lastName' = 'dsads' , 'id'=4, 'age'=30, 'active'=false");

            System.out.println("----INFO INSERT FROM MAIN ");
            if(INSERT != null) {
                for (Map<String,Object> ob : INSERT) {
                    System.out.println("Данные = " + ob);
                }
            }

            /**UPDATE*/
           // UPDATE = javaSchoolStarter.execute("UPDATE VALUES 'id'=null ");
           // UPDATE = javaSchoolStarter.execute("UPDATE VALUES 'active'=false, 'id'=null where 'lastName' like 'Иван%' or 'age'=20");
            UPDATE = javaSchoolStarter.execute("UPDATE VALUES ‘active’=true  where 'lastName' like 'Иван%' " );

            System.out.println("----INFO UPDATE FROM MAIN ");
            if(UPDATE != null) {
                for (Map<String,Object> ob : UPDATE) {
                    System.out.println("Данные = " + ob);
                }
            }

            /**DELETE*/
            DELETE = javaSchoolStarter.execute("delete WHERE 'age' = 20  or 'lastName' = 'Иван%'");

            System.out.println("----INFO DELETE FROM MAIN ");
            if(DELETE != null) {
                for (Map<String,Object> ob : DELETE) {
                    System.out.println("Данные = " + ob);
                }
            }

            /**SELECT*/
            SELECT = javaSchoolStarter.execute("SELECT ");
            //SELECT = javaSchoolStarter.execute("SELECT *");
            //SELECT = javaSchoolStarter.execute("SELECT WHERE  'lastName' like 'Иван%' or 'age'=20 ");

            System.out.println("----INFO SELECT FROM MAIN ");
            if(SELECT != null) {
                for (Map<String,Object> ob : SELECT) {
                    System.out.println("Данные = " + ob);
                }
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
