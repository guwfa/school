package com.digdes.school;

import java.util.*;
/**Требования к реализации:


 +  1) Исходники необходимо выложить под своей учетной записью  на  GitHub и выдать права на чтение для digdesjavaschool@gmail.com.
 +  2) Все Ваши классы должны лежать в java пакете: com.digdes.school
 +  3) В этом пакете должен лежать класс JavaSchoolStarter, в котором есть метод execute на вход передается строка, а на выход List<Map<String,Object>>.
    Дефолтный конструктор без аргументов обязательно должен быть
 4) Скобки в условиях реализовывать не нужно, но если сделаете - будет плюсом.
 +  7) Валидацию запроса проверять не нужно, но если сделаете - будет плюсом
 +  8) Если в запросе есть наименование колонки, которой нет в таблице - выдать Exception. Также если в сравнении участвует тип,
 который не поддерживается данным оператором выкинуть также Exception
 +  9) Лишние пробелы игнорируются, но в значении ячейки учитываются. Например ‘age’>4  равносильно ‘age’ > 4.
 + 10) Наименование колонок и строковые значения оборачиваются в одинарные кавычки.
 11) Значения, которые передаются на сравнение не могут быть null. А при записи значение колонки может быть null.
 Т.е. ‘age’>=null считаем, что такого не может быть. А UPDATE VALUES ‘age’=null - может быть, в этом  случае значение из ячейки удаляется.
 12) Если значение в ячейке например age пусто (null), а на вход передается условие типа ‘age’!=0 , а существующее значение age=null. То запрос считается корректным, 0!=null.
 +  13) Колонки и команды должны быть регистро независимыми.
 +  14) Код должен быть компилируемым и исполняем под OpenJDK 18.
 +  15) Сторонние библиотеки использовать нельзя. Реализация на чистом OpenJDK 18.
 +  16) Данные в коллекции должны сохраняться на время исполнения программы. Т.е. последовательно можно вызвать несколько команд
 */
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
            //INSERT = javaSchoolStarter.execute("INSERT VALUES 'lastName' = 'dsads' , 'id'=4, 'age'=30, 'active'=false");

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
            //DELETE = javaSchoolStarter.execute("delete WHERE 'age' = 20  or 'lastName' = 'Иван%'");

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