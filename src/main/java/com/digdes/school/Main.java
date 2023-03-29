package com.digdes.school;

import java.util.*;
/**Требования к реализации:

 1) Исходники необходимо выложить под своей учетной записью  на  GitHub и выдать права на чтение для digdesjavaschool@gmail.com.
 +  2) Все Ваши классы должны лежать в java пакете: com.digdes.school
 +  3) В этом пакете должен лежать класс JavaSchoolStarter, в котором есть метод execute на вход передается строка, а на выход List<Map<String,Object>>.
    Дефолтный конструктор без аргументов обязательно должен быть
 4) Скобки в условиях реализовывать не нужно, но если сделаете - будет плюсом.
 7) Валидацию запроса проверять не нужно, но если сделаете - будет плюсом
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

        try {
            /**INSERT*/
           List<Map<String,Object>> INSERT = javaSchoolStarter.execute("INSERT VALUES " +
                   "('lastName' = 'Федоров2' , 'id'=2, 'age'=20)" +
                   "('lastName' = 'Федоров23' , 'age'=20)" +
                   "('lastName' = 'gtdft' , 'id'=1, 'age'=60, 'active'=false)"
           );
           List<Map<String,Object>> result1_1 = javaSchoolStarter.execute("INSERT VALUES 'lastName' = 'Иванов' , 'age'=30 ");
          // List<Map<String,Object>> result1_2 = javaSchoolStarter.execute("INSERT VALUES 'lastName' = 'dsads' , 'id'=4, 'age'=30, 'active'=false");

            System.out.println("--------------------------");
            System.out.println("----INFO INSERT FROM MAIN " + INSERT);
            System.out.println("--------------------------");

            /**UPDATE*/
            // List<Map<String,Object>> UPDATE = javaSchoolStarter.execute("UPDATE VALUES 'active'=false, 'cost'=10.1");
           //  List<Map<String,Object>> UPDATE = javaSchoolStarter.execute("UPDATE VALUES 'active'=false, 'id'=null where 'id' = 2");
           // List<Map<String,Object>> UPDATE = javaSchoolStarter.execute("UPDATE VALUES ‘active’=true  where 'id'=2  " );
            // for(Map<String,Object> ob : UPDATE){System.out.println(ob);}

            System.out.println("--------------------------");
             //System.out.println("----INFO UPDATE FROM MAIN " + UPDATE);
            System.out.println("--------------------------");;
            /**DELETE*/
            //List<Map<String,Object>> DELETE = javaSchoolStarter.execute("delete WHERE 'age' = 20  or 'lastName' = 'Фед%'");

            //  for(Map<String,Object> ob : DELETE){System.out.println(ob);}

            System.out.println("--------------------------");
           // System.out.println("----INFO DELETE FROM MAIN " + DELETE);
            System.out.println("--------------------------");

            /**SELECT*/
           List<Map<String,Object>> SELECT = javaSchoolStarter.execute("SELECT ");
            //List<Map<String,Object>> SELECT = javaSchoolStarter.execute("SELECT *");
           // List<Map<String,Object>> SELECT = javaSchoolStarter.execute("SELECT WHERE  'id' != null");


            System.out.println("--------------------------");
            System.out.println("----INFO SELECT FROM MAIN " + SELECT);
            System.out.println("--------------------------");


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}