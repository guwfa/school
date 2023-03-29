package com.digdes.school;

import java.util.*;

public class JavaSchoolStarter {

      List<Map<String,Object>> list = new ArrayList<>();


    public JavaSchoolStarter(){
    }

    public List<Map<String,Object>> execute(String request) throws Exception {

        List<Map<String,Object>> result = new ArrayList<>();
        List<Boolean> isEquals = new ArrayList<>();
        List<String> condition;
        Map<String,Object> map = new HashMap<>();
        String[] task = setSpace(request);

        Long id = null;
        String lastName = null;
        Long age = null;
        Double cost = null;
        Boolean active = null;

        Long idEq = null;
        String lastNameEq = null;
        Long ageEq = null;
        Double costEq = null;
        Boolean activeEq = null;

        switch (task[0].toUpperCase(Locale.ROOT)) {
            case "INSERT" -> {
                if(task[1].toUpperCase(Locale.ROOT).equals("VALUES")) {
                    condition = new ArrayList<>(List.copyOf(List.of(task)));
                    condition.remove(0);
                    condition.remove(0);
                    condition.removeIf(x -> x.equals("="));

                    /** Тут начало попытки сделать добавление пары строк */
                    condition.removeIf(x -> x.equals("("));
                    List<List<String>>  listKeyValue = new ArrayList<>();
                    List<String> keyValue2 = new ArrayList<>();

                    System.out.println("INFO INSERT: " +"condition = " + condition);

                    if(condition.contains(")") ) {
                        for (int i = 0 ; i < condition.size()-1; i++) {
                            System.out.println("INFO INSERT " + " condition.get(" + i + ") " + condition.get(i));
                            if(!condition.get(i).trim().contains(")")) {
                                String tmp = condition.get(i).replaceAll("’|‘|'","") + "=" + condition.get(i+1);
                                keyValue2.add(tmp);
                                i++;
                            }
                            if(condition.get(i+1).trim().contains(")") ) {
                                listKeyValue.add(keyValue2);
                                System.out.println("INFO INSERT  CREATE listKeyValue ( index = " + i + "): " +"listKeyValue = " + listKeyValue);
                                keyValue2 = new ArrayList<>();
                            }
                        }
                    } else  if(!condition.contains(")")) {
                        for (int i = 0 ; i < condition.size()-1; i++){
                            if(i % 2 == 0){
                                String tmp = condition.get(i).replaceAll("’|‘|'","") + "=" + condition.get(i+1);
                                keyValue2.add(tmp);
                            }
                        }
                        listKeyValue.add(keyValue2);
                    }

                    System.out.println("INFO INSERT: " +"KeyValue2 = " + keyValue2);
                    System.out.println("INFO INSERT: " +"ListKeyValue = " + listKeyValue);

                    for (List<String> list1 : listKeyValue) {
                         id = null;
                         lastName = null;
                         age = null;
                         cost = null;
                         active = null;
                        for (String str : list1) {
                            String[] tmp = str.replaceAll("[\\[\\]]","").trim().split("=");

                            System.out.println("INFO INSERT: " + "tmp = " + Arrays.stream(tmp).toList());

                            switch (tmp[0].trim().replaceAll("’|‘|'","")){
                                case "ID" -> {
                                    map.put("ID",Long.parseLong(tmp[1].trim()));
                                    id = Long.parseLong(tmp[1].trim());
                                    System.out.println("INFO INSERT: " + "ID = " + id + "( " + Long.parseLong(tmp[1].trim()) + " )" );
                                }
                                case "LASTNAME" -> {
                                    map.put("LASTNAME", tmp[1].trim().replaceAll("’|‘|'",""));
                                    lastName = tmp[1].trim().replaceAll("’|‘|'","");
                                    System.out.println("INFO INSERT: " + "LASTNAME = " + lastName + "( " + tmp[1].trim().replaceAll("’|‘|'","") + " )");
                                }
                                case "AGE" -> {
                                    map.put("AGE", Long.parseLong(tmp[1].trim()));
                                    age = Long.parseLong(tmp[1].trim());
                                    System.out.println("INFO INSERT: " + "AGE = " + age + "( " + Long.parseLong(tmp[1].trim()) + " )");
                                }
                                case "COST" -> {
                                    map.put("COST", Double.valueOf(tmp[1].trim()));
                                    cost = Double.valueOf(tmp[1].trim());
                                    System.out.println("INFO INSERT: " + "COST = " + cost + "( " + Double.valueOf(tmp[1].trim()) + " )");
                                }
                                case "ACTIVE" -> {
                                    map.put("ACTIVE", Boolean.valueOf(tmp[1].trim()));
                                    active = Boolean.valueOf(tmp[1].trim());
                                    System.out.println("INFO INSERT: " + "ACTIVE = " + active + "( " + Boolean.valueOf(tmp[1].trim()) + " )");
                                }
                                default -> throw new Exception("В запросе указаны колонки, которых нет в исходной таблице");
                            }
                        }

                        System.out.println("INFO INSERT: " + "map.size() = " + map.size() );
                        if(map.size() != 0) {
                            list.add(map);

                            map = new HashMap<>();
                            if (id != null) { map.put("ID", id); }
                            if (lastName != null) { map.put("LASTNAME", lastName); }
                            if (age != null) { map.put("AGE", age); }
                            if (cost != null) { map.put("COST", cost); }
                            if (active != null) { map.put("ACTIVE", active); }
                            result.add(map);

                            System.out.println("INFO INSERT: " + "result add map = " + map );
                        }

                        System.out.println("INFO INSERT: " + "list  = " + list );
                    }

                    return List.copyOf(result);


                    /** Тут конец попытки сделать добавление пары строк */

                    /*for (int i = 0 ; i < condition.size()-1; i++){
                        if(i % 2 == 0){
                            String tmp = condition.get(i).replaceAll("’|‘|'","") + "=" + condition.get(i+1);
                            keyValue.add(tmp);
                        }
                    }

                    for (String str : keyValue) {
                        String[] tmp = str.replaceAll("[\\[\\]]","").trim().split("=");

                        switch (tmp[0].trim().replaceAll("’|‘|'","")){
                            case "ID" -> {
                                map.put("ID",Long.parseLong(tmp[1].trim()));
                                id = Long.parseLong(tmp[1].trim());
                            }
                            case "LASTNAME" -> {
                                map.put("LASTNAME", tmp[1].trim().replaceAll("’|‘|'",""));
                                lastName = tmp[1].trim().replaceAll("’|‘|'","");
                            }
                            case "AGE" -> {
                                map.put("AGE", Long.parseLong(tmp[1].trim()));
                                age = Long.parseLong(tmp[1].trim());
                            }
                            case "COST" -> {
                                map.put("COST", Double.valueOf(tmp[1].trim()));
                                cost = Double.valueOf(tmp[1].trim());
                            }
                            case "ACTIVE" -> {
                                map.put("ACTIVE", Boolean.valueOf(tmp[1].trim()));
                                active = Boolean.valueOf(tmp[1].trim());
                            }
                            default -> throw new Exception("В запросе указаны колонки, которых нет в исходной таблице");
                        }
                    }

                    if(map.size() != 0) {
                        list.add(map);

                        map = new HashMap<>();
                        if (id != null) { map.put("ID", id); }
                        if (lastName != null) { map.put("LASTNAME", lastName); }
                        if (age != null) { map.put("AGE", age); }
                        if (cost != null) { map.put("COST", cost); }
                        if (active != null) { map.put("ACTIVE", active); }
                        result.add(map);
                    }*/
                }
                else { throw new Exception("Не верно составленный запрос"); }
            }
            case "UPDATE" -> {

                if(!request.toUpperCase().contains("WHERE")){

                    System.out.println("INFO UPDATE:  UPDATE ALL ");

                    if(task[1].toUpperCase(Locale.ROOT).equals("VALUES")) {
                        condition = new ArrayList<>(List.copyOf(List.of(task)));
                        condition.remove(0);
                        condition.remove(0);
                        condition.removeIf(x -> x.equals("="));

                        List<String> keyValue = new ArrayList<>();

                        for (int i = 0 ; i < condition.size()-1; i++){
                            if(i % 2 == 0){
                                String tmp = condition.get(i).replaceAll("’|‘|'","") + "=" + condition.get(i+1);
                                keyValue.add(tmp);
                            }
                        }
                        for (String str : keyValue) {
                            String[] tmp = str.replaceAll("[\\[\\]]","").trim().split("=");

                            System.out.println("INFO UPDATE: " + "tmp = " + Arrays.stream(tmp).toList());

                            switch (tmp[0].trim().replaceAll("’|‘|'","")){
                                case "ID" -> {
                                    map.put("ID",Long.parseLong(tmp[1].trim()));
                                    id = Long.parseLong(tmp[1].trim());
                                    System.out.println("INFO UPDATE: " + "ID = " + id + "( " + Long.parseLong(tmp[1].trim()) + " )" );
                                }
                                case "LASTNAME" -> {
                                    map.put("LASTNAME", tmp[1].trim().replaceAll("’|‘|'",""));
                                    lastName = tmp[1].trim().replaceAll("’|‘|'","");
                                    System.out.println("INFO UPDATE: " + "LASTNAME = " + lastName + "( " + tmp[1].trim().replaceAll("’|‘|'","") + " )");
                                }
                                case "AGE" -> {
                                    map.put("AGE", Long.parseLong(tmp[1].trim()));
                                    age = Long.parseLong(tmp[1].trim());
                                    System.out.println("INFO UPDATE: " + "AGE = " + age + "( " + Long.parseLong(tmp[1].trim()) + " )");
                                }
                                case "COST" -> {
                                    map.put("COST", Double.valueOf(tmp[1].trim()));
                                    cost = Double.valueOf(tmp[1].trim());
                                    System.out.println("INFO UPDATE: " + "COST = " + cost + "( " + Double.valueOf(tmp[1].trim()) + " )");
                                }
                                case "ACTIVE" -> {
                                    map.put("ACTIVE", Boolean.valueOf(tmp[1].trim()));
                                    active = Boolean.valueOf(tmp[1].trim());
                                    System.out.println("INFO UPDATE: " + "ACTIVE = " + active + "( " + Boolean.valueOf(tmp[1].trim()) + " )");
                                }
                                default -> throw new Exception("В запросе указаны колонки, которых нет в исходной таблице");
                            }
                        }


                        //Прогоняю цикл обновления значений в мапе каждой строки
                        for (Map<String,Object> objectMap : List.copyOf(list)){
                            if(request.toUpperCase().contains("ID")) { objectMap.put("id", id); }
                            if(request.toUpperCase().contains("LASTNAME")) { objectMap.put("lastName", lastName); }
                            if(request.toUpperCase().contains("AGE")) { objectMap.put("age", age); }
                            if(request.toUpperCase().contains("COST")) { objectMap.put("cost", cost); }
                            if(request.toUpperCase().contains("ACTIVE")) { objectMap.put("active", active); }

                            if(objectMap.size() != 0) { result.add(objectMap); }
                        }
                        System.out.println("INFO UPDATE:  UPDATE list =  " + list);
                        list = List.copyOf(result);
                        return result;
                    }
                    else { throw new Exception("Не верно составленный запрос"); }
                }
                else if(request.toUpperCase().contains("WHERE")) {
                    condition = new ArrayList<>(List.copyOf(List.of(task)));
                    condition.remove(0);
                    condition.remove(0);


                    List<String> conditionWhere = List.copyOf(condition);

                    condition.removeIf(x -> x.equals("="));
                    condition  =  condition.subList(0, condition.indexOf("WHERE"));
                    conditionWhere = conditionWhere.subList(conditionWhere.indexOf("WHERE") + 1 ,conditionWhere.size());

                    System.out.println("INFO UPDATE: " +"condition = " + condition);
                    System.out.println("INFO UPDATE: " +"conditionWhere = " + conditionWhere);

                    List<String> keyValueWhere = new ArrayList<>();
                    List<String> keyValue = new ArrayList<>();

                   /* for (int i = 0 ; i < conditionWhere.size()-1; i+=4){
                        if(!conditionWhere.get(i).equalsIgnoreCase("AND")) {
                            String tmp = conditionWhere.get(i).replaceAll("’|‘|'","") + " " + conditionWhere.get(i+1) + " " + conditionWhere.get(i+2);
                            keyValueWhere.add(tmp);
                        }
                    }*/

                    List<String> listAndOr = new ArrayList<>();

                    for (int i = 0 ; i < condition.size()-1; i+=4){
                        String tmp = condition.get(i).replaceAll("’|‘|'","") + " " + condition.get(i+1) + " " + condition.get(i+2) +
                                ( (condition.size() >= (i + 4)) ? (" |" + condition.get(i+3)) : ("") );
                        keyValueWhere.add(tmp);
                        if(condition.size() >= (i + 4)) listAndOr.add(condition.get(i+3));
                    }


                    for (int i = 0 ; i < condition.size()-1; i++){
                        if(i % 2 == 0){
                            String tmp = condition.get(i).replaceAll("’|‘|'","") + "=" + condition.get(i+1);
                            keyValue.add(tmp);
                        }
                    }

                    //
                    for(Map<String,Object> objectMap : list){
                        isEquals.clear();
                        //Прогоняю по условиям каждую строку
                        for(String str : keyValueWhere){
                            List<String> eqList = List.of(str.split(" "));

                           /* switch (eqList.get(0)){
                                case "ID" -> {
                                    if ( (isDigitLong(eqList.get(2))) || (eqList.get(2).equalsIgnoreCase("null") && eqList.get(1).startsWith("!=")) ) { idEq = Long.parseLong(eqList.get(2)); }
                                    else throw new NumberFormatException("Параметр не является числом");

                                    if(eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) {
                                        switch (eqList.get(1)) {
                                            case "<" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) < idEq); }
                                            case ">" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) > idEq); }
                                            case "<=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) <= idEq); }
                                            case ">=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) >= idEq); }
                                            default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                                        }
                                    } else if(eqList.get(1).startsWith("!=")) {
                                        isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) != idEq);
                                    } else if(eqList.get(1).startsWith("=")) {
                                        isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) == idEq);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "LASTNAME" -> {
                                    lastNameEq = eqList.get(2).replaceAll("’|‘|'","");

                                    if(eqList.get(1).startsWith("=")) {
                                        isEquals.add(objectMap.get("LASTNAME").equals(lastNameEq));
                                    } else if(eqList.get(1).startsWith("!=")) {
                                        if(objectMap.get("LASTNAME") != null) { isEquals.add(!objectMap.get("LASTNAME").equals(lastNameEq)); }
                                        else isEquals.add(false);
                                    } else if(eqList.get(1).startsWith("LIKE")) {
                                        if(lastNameEq.startsWith("%") && lastNameEq.endsWith("%")) {
                                            isEquals.add(objectMap.get("LASTNAME").toString().contains(lastNameEq.replaceAll("%","").trim()));
                                        } else if(lastNameEq.endsWith("%")) {
                                            isEquals.add(objectMap.get("LASTNAME").toString().startsWith(lastNameEq.replaceAll("%","").trim()));
                                        } else if(lastNameEq.startsWith("%")) {
                                            isEquals.add(objectMap.get("LASTNAME").toString().endsWith(lastNameEq.replaceAll("%","").trim()));
                                        } else {
                                            isEquals.add(objectMap.get("LASTNAME").toString().equals(lastNameEq.replaceAll("%","").trim()));
                                        }
                                    } else if(eqList.get(1).startsWith("ILIKE")) {
                                        if(lastNameEq.startsWith("%") && lastNameEq.endsWith("%")) {
                                            isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().contains(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                        } else if(lastNameEq.endsWith("%")) {
                                            isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().startsWith(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                        } else if(lastNameEq.startsWith("%")) {
                                            isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().endsWith(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                        } else {
                                            isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().equals(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                        }
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "AGE" -> {
                                    if ((isDigitLong(eqList.get(2)))  || (eqList.get(2).equalsIgnoreCase("null") && eqList.get(1).startsWith("!="))) { ageEq = Long.parseLong(eqList.get(2)); }
                                    else throw new NumberFormatException("Параметр не является числом");

                                    if(eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) {
                                        switch (eqList.get(1)) {
                                            case "<" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) < ageEq); }
                                            case ">" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) > ageEq); }
                                            case "<=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) <= ageEq); }
                                            case ">=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) >= ageEq); }
                                            default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                                        }
                                    } else if(eqList.get(1).startsWith("!=")) {
                                        isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) != ageEq);
                                    } else if(eqList.get(1).startsWith("=")) {
                                        isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) == ageEq);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "COST" -> {
                                    if ((isDigitDouble(eqList.get(2)))  || (eqList.get(2).equalsIgnoreCase("null") && eqList.get(1).startsWith("!="))) { costEq = Double.parseDouble(eqList.get(2)); }
                                    else throw new NumberFormatException("Параметр не является числом");

                                    if(eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) {
                                        switch (eqList.get(1)) {
                                            case "<" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) < costEq); }
                                            case ">" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) > costEq); }
                                            case "<=" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) <= costEq); }
                                            case ">=" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) >= costEq); }
                                            default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                                        }
                                    } else if(eqList.get(1).startsWith("!=")) {
                                        isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) != costEq);
                                    } else if(eqList.get(1).startsWith("=")) {
                                        if(objectMap.get("COST") != null) { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) == costEq); }
                                        else isEquals.add(false);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "ACTIVE" -> {
                                    if ((isBoolean(eqList.get(2)))  || (eqList.get(2).equalsIgnoreCase("null") && eqList.get(1).startsWith("!="))) { activeEq = Boolean.parseBoolean(eqList.get(2)); }
                                    else throw new NumberFormatException("Параметр не является булевым значением");

                                    if(eqList.get(1).startsWith("!=")) {
                                        isEquals.add(Boolean.parseBoolean(String.valueOf(objectMap.get("ACTIVE"))) != activeEq);
                                    } else if(eqList.get(1).startsWith("=")) {
                                        if(objectMap.get("ACTIVE") != null) { isEquals.add(Boolean.parseBoolean(String.valueOf(objectMap.get("ACTIVE"))) == activeEq); }
                                        else isEquals.add(false);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                default -> throw new Exception("В запросе указаны колонки, которых нет в исходной таблице");
                            }*/

                            switch (eqList.get(0)){
                                case "ID" -> {
                                    if ((isDigitLong(eqList.get(2)))) { idEq = Long.parseLong(eqList.get(2)); }
                                    else if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { idEq = null; }
                                    else throw new NumberFormatException("Параметр не является числом");

                                    System.out.println("INFO UPDATE: " +"costEq = " + costEq);
                                    System.out.println("INFO UPDATE: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO UPDATE: " +"objectMap.get(\"ID\") = " + objectMap.get("ID"));

                                    if((eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) && objectMap.get("ID") != null) {
                                        switch (eqList.get(1)) {
                                            case "<" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) < idEq); }
                                            case ">" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) > idEq); }
                                            case "<=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) <= idEq); }
                                            case ">=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) >= idEq); }
                                            default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                                        }
                                    } else if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("ID") != null && objectMap.get("ID") != idEq); }
                                        else { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) != idEq); }
                                    } else if(eqList.get(1).startsWith("=") && objectMap.get("ID") != null) {
                                        isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) == idEq);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "LASTNAME" -> {
                                    if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { lastNameEq = null; }
                                    else { lastNameEq = eqList.get(2).replaceAll("’|‘|'",""); }

                                    System.out.println("INFO UPDATE: " +"costEq = " + costEq);
                                    System.out.println("INFO UPDATE: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO UPDATE: " +"objectMap.get(\"LASTNAME\") = " + objectMap.get("LASTNAME"));

                                    if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("LASTNAME") != null && objectMap.get("LASTNAME") != lastNameEq); }
                                        else {  isEquals.add(!objectMap.get("LASTNAME").equals(lastNameEq)); }
                                    }
                                    if(objectMap.get("LASTNAME") != null) {
                                        if(eqList.get(1).startsWith("=")) {
                                            isEquals.add(objectMap.get("LASTNAME").equals(lastNameEq));
                                        } else if(eqList.get(1).startsWith("LIKE")) {
                                            if(lastNameEq.startsWith("%") && lastNameEq.endsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().contains(lastNameEq.replaceAll("%","").trim()));
                                            } else if(lastNameEq.endsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().startsWith(lastNameEq.replaceAll("%","").trim()));
                                            } else if(lastNameEq.startsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().endsWith(lastNameEq.replaceAll("%","").trim()));
                                            } else {
                                                isEquals.add(objectMap.get("LASTNAME").toString().equals(lastNameEq.replaceAll("%","").trim()));
                                            }
                                        } else if(eqList.get(1).startsWith("ILIKE")) {
                                            if(lastNameEq.startsWith("%") && lastNameEq.endsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().contains(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                            } else if(lastNameEq.endsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().startsWith(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                            } else if(lastNameEq.startsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().endsWith(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                            } else {
                                                isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().equals(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                            }
                                        }
                                    }
                                    else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "AGE" -> {
                                    if ((isDigitLong(eqList.get(2)))) { ageEq = Long.parseLong(eqList.get(2)); }
                                    else if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { ageEq = null; }
                                    else throw new NumberFormatException("Параметр не является числом");

                                    System.out.println("INFO UPDATE: " +"ageEq = " + costEq);
                                    System.out.println("INFO UPDATE: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO UPDATE: " +"objectMap.get(\"AGE\") = " + objectMap.get("AGE"));

                                    if((eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) && objectMap.get("ID") != null) {
                                        switch (eqList.get(1)) {
                                            case "<" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) < ageEq); }
                                            case ">" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) > ageEq); }
                                            case "<=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) <= ageEq); }
                                            case ">=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) >= ageEq); }
                                            default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                                        }
                                    } else if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("AGE") != null && objectMap.get("AGE") != ageEq); }
                                        else { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) != ageEq); }
                                    } else if(eqList.get(1).startsWith("=") && objectMap.get("ID") != null) {
                                        isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) == ageEq);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "COST" -> {
                                    if ((isDigitDouble(eqList.get(2)))) {
                                        costEq = Double.parseDouble(eqList.get(2));
                                    }
                                    else if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) {
                                        costEq = null;
                                    }
                                    else throw new NumberFormatException("Параметр не является числом");

                                    System.out.println("INFO UPDATE: " +"costEq = " + costEq);
                                    System.out.println("INFO UPDATE: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO UPDATE: " +"objectMap.get(\"COST\") = " + objectMap.get("COST"));

                                    if(!eqList.get(1).startsWith("!=") && objectMap.get("COST") != null) {
                                        if((eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) && objectMap.get("COST") != null) {
                                            switch (eqList.get(1)) {
                                                case "<" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) < costEq); }
                                                case ">" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) > costEq); }
                                                case "<=" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) <= costEq); }
                                                case ">=" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) >= costEq); }
                                                default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                                            }
                                        }
                                        else if(eqList.get(1).startsWith("=") && objectMap.get("COST") != null) {
                                            isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) == costEq);
                                        }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                    }
                                    else if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) {
                                            isEquals.add(objectMap.containsKey("COST") ? true : false);
                                            System.out.println("INFO UPDATE: " +"isEquals = " + isEquals);
                                        }
                                        else { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) != costEq); }
                                    } else if (objectMap.get("COST") == null && !eqList.get(1).startsWith("!=")) {
                                        isEquals.add(false);
                                    }
                                }
                                case "ACTIVE" -> {
                                    if ((isBoolean(eqList.get(2)))) { activeEq = Boolean.parseBoolean(eqList.get(2)); }
                                    else if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { activeEq = null; }
                                    else throw new NumberFormatException("Параметр не является булевым значением");

                                    System.out.println("INFO UPDATE: " +"costEq = " + costEq);
                                    System.out.println("INFO UPDATE: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO UPDATE: " +"objectMap.get(\"ACTIVE\") = " + objectMap.get("ACTIVE"));

                                    if(conditionWhere.get(1).startsWith("!=")) {
                                        if(conditionWhere.get(2).equals("NULL")) {
                                            isEquals.add(objectMap.containsKey("ACTIVE") ? true : false);
                                            System.out.println("INFO UPDATE: " +"isEquals = " + isEquals);
                                        }
                                        else { isEquals.add(Boolean.parseBoolean(String.valueOf(objectMap.get("ACTIVE"))) != activeEq); }
                                    } else if(conditionWhere.get(1).startsWith("=")  && objectMap.get("ID") != null) {
                                        if(objectMap.get("ACTIVE") != null) { isEquals.add(Boolean.parseBoolean(String.valueOf(objectMap.get("ACTIVE"))) == activeEq); }
                                        else isEquals.add(false);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                default -> throw new Exception("В запросе указаны колонки, которых нет в исходной таблице");
                            }
                        }

                        //Обновляю данные которые совпали по выборке
                        if (!isEquals.contains(false)) {
                            for (String str : keyValue) {
                                String[] tmp = str.replaceAll("[\\[\\]]","").trim().split("=");

                                switch (tmp[0].trim().replaceAll("’|‘|'","")){
                                    case "ID" -> {
                                        if(!tmp[1].trim().equalsIgnoreCase("null")) {
                                            objectMap.put("ID",Long.parseLong(tmp[1].trim()));
                                            System.out.println("INFO UPDATE: " + "ID = " + id + "( " + Long.parseLong(tmp[1].trim()) + " )" );
                                        } else objectMap.remove("ID");
                                    }
                                    case "LASTNAME" -> {
                                        if(!tmp[1].trim().equalsIgnoreCase("null")) {
                                            objectMap.put("LASTNAME", tmp[1].trim().replaceAll("’|‘|'",""));
                                            System.out.println("INFO UPDATE: " + "LASTNAME = " + lastName + "( " + tmp[1].trim().replaceAll("’|‘|'","") + " )");
                                        } else objectMap.remove("LASTNAME");
                                    }
                                    case "AGE" -> {
                                        if(!tmp[1].trim().equalsIgnoreCase("null")) {
                                            objectMap.put("AGE", Long.parseLong(tmp[1].trim()));
                                            System.out.println("INFO UPDATE: " + "AGE = " + age + "( " + Long.parseLong(tmp[1].trim()) + " )");
                                        } else objectMap.remove("AGE");
                                    }
                                    case "COST" -> {
                                        if(!tmp[1].trim().equalsIgnoreCase("null")) {
                                            objectMap.put("COST", Double.valueOf(tmp[1].trim()));
                                            System.out.println("INFO UPDATE: " + "COST = " + cost + "( " + Double.valueOf(tmp[1].trim()) + " )");
                                        } else objectMap.remove("COST");
                                    }
                                    case "ACTIVE" -> {
                                        if(!tmp[1].trim().equalsIgnoreCase("null")) {
                                            objectMap.put("ACTIVE", Boolean.valueOf(tmp[1].trim()));
                                            System.out.println("INFO UPDATE: " + "ACTIVE = " + active + "( " + Boolean.valueOf(tmp[1].trim()) + " )");
                                        } else objectMap.remove("ACTIVE");
                                    }
                                    default -> throw new Exception("В запросе указаны колонки, которых нет в исходной таблице");
                                }
                            }
                            if(objectMap.size() != 0) { result.add(objectMap); }
                        }
                    }
                }
                else { throw new Exception("Не верно составленный запрос"); }

            }
            case "DELETE" -> {
                if(task.length == 1 ) {
                    System.out.println("INFO DELETE:  DELETE ALL ");
                    result = list;
                    list.clear();
                    return result;
                }
                else if(task[1].toUpperCase(Locale.ROOT).equalsIgnoreCase("WHERE")) {
                    condition = new ArrayList<>(List.copyOf(List.of(task)));
                    condition.remove(0);
                    condition.remove(0);
                    List<String> keyValue = new ArrayList<>();

                    System.out.println("INFO DELETE: " +"condition = " + condition);

                    List<String> listAndOr = new ArrayList<>();

                    for (int i = 0 ; i < condition.size()-1; i+=4){
                        String tmp = condition.get(i).replaceAll("’|‘|'","") + " " + condition.get(i+1) + " " + condition.get(i+2) +
                                ( (condition.size() >= (i + 4)) ? (" |" + condition.get(i+3)) : ("") );
                        keyValue.add(tmp);
                        if(condition.size() >= (i + 4)) listAndOr.add(condition.get(i+3));
                    }


                    for(Map<String,Object> objectMap : list){
                        isEquals.clear();
                        for(String str : keyValue){
                            List<String> eqList = List.of(str.split(" "));
                            switch (eqList.get(0)){
                                case "ID" -> {
                                    if ((isDigitLong(eqList.get(2)))) { idEq = Long.parseLong(eqList.get(2)); }
                                    else if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { idEq = null; }
                                    else throw new NumberFormatException("Параметр не является числом");

                                    System.out.println("INFO DELETE: " +"costEq = " + costEq);
                                    System.out.println("INFO DELETE: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO DELETE: " +"objectMap.get(\"ID\") = " + objectMap.get("ID"));

                                    if((eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) && objectMap.get("ID") != null) {
                                        switch (eqList.get(1)) {
                                            case "<" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) < idEq); }
                                            case ">" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) > idEq); }
                                            case "<=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) <= idEq); }
                                            case ">=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) >= idEq); }
                                            default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                                        }
                                    } else if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("ID") != idEq); }
                                        else { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) != idEq); }
                                    } else if(eqList.get(1).startsWith("=") && objectMap.get("ID") != null) {
                                        isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) == idEq);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "LASTNAME" -> {
                                    if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { lastNameEq = null; }
                                    else { lastNameEq = eqList.get(2).replaceAll("’|‘|'",""); }

                                    System.out.println("INFO DELETE: " +"costEq = " + costEq);
                                    System.out.println("INFO DELETE: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO DELETE: " +"objectMap.get(\"LASTNAME\") = " + objectMap.get("LASTNAME"));

                                    if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("LASTNAME") != lastNameEq); }
                                        else {  isEquals.add(!objectMap.get("LASTNAME").equals(lastNameEq)); }
                                    }
                                    if(objectMap.get("LASTNAME") != null) {
                                        if(eqList.get(1).startsWith("=")) {
                                            isEquals.add(objectMap.get("LASTNAME").equals(lastNameEq));
                                        } else if(eqList.get(1).startsWith("LIKE")) {
                                            if(lastNameEq.startsWith("%") && lastNameEq.endsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().contains(lastNameEq.replaceAll("%","").trim()));
                                            } else if(lastNameEq.endsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().startsWith(lastNameEq.replaceAll("%","").trim()));
                                            } else if(lastNameEq.startsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().endsWith(lastNameEq.replaceAll("%","").trim()));
                                            } else {
                                                isEquals.add(objectMap.get("LASTNAME").toString().equals(lastNameEq.replaceAll("%","").trim()));
                                            }
                                        } else if(eqList.get(1).startsWith("ILIKE")) {
                                            if(lastNameEq.startsWith("%") && lastNameEq.endsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().contains(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                            } else if(lastNameEq.endsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().startsWith(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                            } else if(lastNameEq.startsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().endsWith(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                            } else {
                                                isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().equals(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                            }
                                        }
                                    }
                                    else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "AGE" -> {
                                    if ((isDigitLong(eqList.get(2)))) { ageEq = Long.parseLong(eqList.get(2)); }
                                    else if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { ageEq = null; }
                                    else throw new NumberFormatException("Параметр не является числом");

                                    System.out.println("INFO DELETE: " +"ageEq = " + costEq);
                                    System.out.println("INFO DELETE: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO DELETE: " +"objectMap.get(\"AGE\") = " + objectMap.get("AGE"));

                                    if((eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) && objectMap.get("ID") != null) {
                                        switch (eqList.get(1)) {
                                            case "<" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) < ageEq); }
                                            case ">" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) > ageEq); }
                                            case "<=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) <= ageEq); }
                                            case ">=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) >= ageEq); }
                                            default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                                        }
                                    } else if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("AGE") != ageEq); }
                                        else { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) != ageEq); }
                                    } else if(eqList.get(1).startsWith("=") && objectMap.get("ID") != null) {
                                        isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) == ageEq);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "COST" -> {
                                    if ((isDigitDouble(eqList.get(2)))) {
                                        costEq = Double.parseDouble(eqList.get(2));
                                    }
                                    else if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) {
                                        costEq = null;
                                    }
                                    else throw new NumberFormatException("Параметр не является числом");

                                    System.out.println("INFO DELETE: " +"costEq = " + costEq);
                                    System.out.println("INFO DELETE: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO DELETE: " +"objectMap.get(\"COST\") = " + objectMap.get("COST"));

                                    if(!eqList.get(1).startsWith("!=") && objectMap.get("COST") != null) {
                                        if((eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) && objectMap.get("COST") != null) {
                                            switch (eqList.get(1)) {
                                                case "<" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) < costEq); }
                                                case ">" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) > costEq); }
                                                case "<=" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) <= costEq); }
                                                case ">=" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) >= costEq); }
                                                default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                                            }
                                        }
                                        else if(eqList.get(1).startsWith("=") && objectMap.get("COST") != null) {
                                            isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) == costEq);
                                        }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                    }
                                    else if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("COST") != costEq); }
                                        else { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) != costEq); }
                                    } else if (objectMap.get("COST") == null && !eqList.get(1).startsWith("!=")) {
                                        isEquals.add(false);
                                    }
                                }
                                case "ACTIVE" -> {
                                    if ((isBoolean(eqList.get(2)))) { activeEq = Boolean.parseBoolean(eqList.get(2)); }
                                    else if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { activeEq = null; }
                                    else throw new NumberFormatException("Параметр не является булевым значением");

                                    System.out.println("INFO DELETE: " +"costEq = " + costEq);
                                    System.out.println("INFO DELETE: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO DELETE: " +"objectMap.get(\"ACTIVE\") = " + objectMap.get("ACTIVE"));

                                    if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("ACTIVE") != activeEq); }
                                        else { isEquals.add(Boolean.parseBoolean(String.valueOf(objectMap.get("ACTIVE"))) != activeEq); }
                                    } else if(eqList.get(1).startsWith("=")  && objectMap.get("ID") != null) {
                                        if(objectMap.get("ACTIVE") != null) { isEquals.add(Boolean.parseBoolean(String.valueOf(objectMap.get("ACTIVE"))) == activeEq); }
                                        else isEquals.add(false);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                default -> throw new Exception("В запросе указаны колонки, которых нет в исходной таблице");
                            }
                        }

                        idEq = null;
                        lastNameEq = null;
                        ageEq = null;
                        costEq = null;
                        activeEq = null;

                        int tmp = 0;
                        Boolean isDelete = null;

                        System.out.println("INFO DELETE: " +"isEquals = " + isEquals);

                        for(int i = 0; i <= isEquals.size()-1; i++){
                            if (listAndOr.size() == 0) { isDelete =  isEquals.get(i); }
                            else if(listAndOr.get( ( (tmp < listAndOr.size()) && (listAndOr.size() != 1 ) ) ? tmp : listAndOr.size()-1 ).equals("AND")) {
                                if(isDelete != null) { isDelete = isDelete & isEquals.get(i); }
                                else { isDelete = isEquals.get(i); }
                            }
                            else if(listAndOr.get( ( (tmp < listAndOr.size()) && (listAndOr.size() != 1 ) ) ? tmp : listAndOr.size()-1 ).equals("OR")) {
                                if(isDelete != null) { isDelete = isDelete | isEquals.get(i); }
                                else { isDelete = isEquals.get(i); }
                            }
                            tmp++;
                        }

                        if (isDelete) {
                            System.out.println("INFO DELETE: " +"isDelete = " + isDelete + " add objectMap = " + objectMap);
                            result.add(objectMap);
                        }else { System.out.println("INFO DELETE: " +"isDelete = " + isDelete); }
                    }

                    for(Map<String,Object> objectMap : result) { list.remove(objectMap); }
                    System.out.println("INFO DELETE: " +"result = " + result);
                    return result;
                }
                else { throw new Exception("Не верно составленный запрос"); }

            }
            case "SELECT" -> {
                if(task.length == 1 ) {
                    System.out.println("INFO SELECT:  SELECT ALL ");
                    return List.copyOf(list);
                }
                else if( task[1].trim().equals("*")) { return List.copyOf(list); }
                else if(task[1].toUpperCase(Locale.ROOT).equalsIgnoreCase("WHERE")) {
                    condition = new ArrayList<>(List.copyOf(List.of(task)));
                    condition.remove(0);
                    condition.remove(0);
                    List<String> keyValue = new ArrayList<>();

                    System.out.println("INFO SELECT: " +"condition = " + condition);

                    List<String> listAndOr = new ArrayList<>();

                    for (int i = 0 ; i < condition.size()-1; i+=4){
                        String tmp = condition.get(i).replaceAll("’|‘|'","") + " " + condition.get(i+1) + " " + condition.get(i+2) +
                                ( (condition.size() >= (i + 4)) ? (" |" + condition.get(i+3)) : ("") );
                        keyValue.add(tmp);
                        if(condition.size() >= (i + 4)) listAndOr.add(condition.get(i+3));
                    }

                    for(Map<String,Object> objectMap : list){
                        isEquals.clear();
                        for(String str : keyValue){
                            List<String> eqList = List.of(str.split(" "));
                            switch (eqList.get(0)){
                                case "ID" -> {
                                    if ((isDigitLong(eqList.get(2)))) { idEq = Long.parseLong(eqList.get(2)); }
                                    else if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { idEq = null; }
                                    else throw new NumberFormatException("Параметр не является числом");

                                    System.out.println("INFO SELECT: " +"costEq = " + costEq);
                                    System.out.println("INFO SELECT: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO SELECT: " +"objectMap.get(\"ID\") = " + objectMap.get("ID"));

                                    if((eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) && objectMap.get("ID") != null) {
                                        switch (eqList.get(1)) {
                                            case "<" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) < idEq); }
                                            case ">" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) > idEq); }
                                            case "<=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) <= idEq); }
                                            case ">=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) >= idEq); }
                                            default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                                        }
                                    } else if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("ID") != idEq); }
                                        else { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) != idEq); }
                                    } else if(eqList.get(1).startsWith("=") && objectMap.get("ID") != null) {
                                        isEquals.add(Long.parseLong(String.valueOf(objectMap.get("ID"))) == idEq);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "LASTNAME" -> {
                                    if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { lastNameEq = null; }
                                    else { lastNameEq = eqList.get(2).replaceAll("’|‘|'",""); }

                                    System.out.println("INFO SELECT: " +"costEq = " + costEq);
                                    System.out.println("INFO SELECT: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO SELECT: " +"objectMap.get(\"LASTNAME\") = " + objectMap.get("LASTNAME"));

                                    if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("LASTNAME") != lastNameEq); }
                                        else {  isEquals.add(!objectMap.get("LASTNAME").equals(lastNameEq)); }
                                    }
                                    if(objectMap.get("LASTNAME") != null) {
                                         if(eqList.get(1).startsWith("=")) {
                                            isEquals.add(objectMap.get("LASTNAME").equals(lastNameEq));
                                        } else if(eqList.get(1).startsWith("LIKE")) {
                                            if(lastNameEq.startsWith("%") && lastNameEq.endsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().contains(lastNameEq.replaceAll("%","").trim()));
                                            } else if(lastNameEq.endsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().startsWith(lastNameEq.replaceAll("%","").trim()));
                                            } else if(lastNameEq.startsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().endsWith(lastNameEq.replaceAll("%","").trim()));
                                            } else {
                                                isEquals.add(objectMap.get("LASTNAME").toString().equals(lastNameEq.replaceAll("%","").trim()));
                                            }
                                        } else if(eqList.get(1).startsWith("ILIKE")) {
                                            if(lastNameEq.startsWith("%") && lastNameEq.endsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().contains(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                            } else if(lastNameEq.endsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().startsWith(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                            } else if(lastNameEq.startsWith("%")) {
                                                isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().endsWith(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                            } else {
                                                isEquals.add(objectMap.get("LASTNAME").toString().toUpperCase().equals(lastNameEq.toUpperCase().replaceAll("%","").trim()));
                                            }
                                        }
                                    }
                                    else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "AGE" -> {
                                    if ((isDigitLong(eqList.get(2)))) { ageEq = Long.parseLong(eqList.get(2)); }
                                    else if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { ageEq = null; }
                                    else throw new NumberFormatException("Параметр не является числом");

                                    System.out.println("INFO SELECT: " +"ageEq = " + costEq);
                                    System.out.println("INFO SELECT: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO SELECT: " +"objectMap.get(\"AGE\") = " + objectMap.get("AGE"));

                                    if((eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) && objectMap.get("ID") != null) {
                                        switch (eqList.get(1)) {
                                            case "<" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) < ageEq); }
                                            case ">" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) > ageEq); }
                                            case "<=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) <= ageEq); }
                                            case ">=" -> { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) >= ageEq); }
                                            default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                                        }
                                    } else if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("AGE") != ageEq); }
                                        else { isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) != ageEq); }
                                    } else if(eqList.get(1).startsWith("=") && objectMap.get("ID") != null) {
                                        isEquals.add(Long.parseLong(String.valueOf(objectMap.get("AGE"))) == ageEq);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                case "COST" -> {
                                    if ((isDigitDouble(eqList.get(2)))) {
                                        costEq = Double.parseDouble(eqList.get(2));
                                    }
                                    else if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) {
                                        costEq = null;
                                    }
                                    else throw new NumberFormatException("Параметр не является числом");

                                    System.out.println("INFO SELECT: " +"costEq = " + costEq);
                                    System.out.println("INFO SELECT: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO SELECT: " +"objectMap.get(\"COST\") = " + objectMap.get("COST"));

                                    if(!eqList.get(1).startsWith("!=") && objectMap.get("COST") != null) {
                                        if((eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) && objectMap.get("COST") != null) {
                                            switch (eqList.get(1)) {
                                                case "<" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) < costEq); }
                                                case ">" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) > costEq); }
                                                case "<=" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) <= costEq); }
                                                case ">=" -> { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) >= costEq); }
                                                default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                                            }
                                        }
                                        else if(eqList.get(1).startsWith("=") && objectMap.get("COST") != null) {
                                            isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) == costEq);
                                        }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                    }
                                    else if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("COST") != null); }
                                        else { isEquals.add(Double.parseDouble(String.valueOf(objectMap.get("COST"))) != costEq); }
                                    } else if (objectMap.get("COST") == null && !eqList.get(1).startsWith("!=")) {
                                            isEquals.add(false);
                                    }
                                }
                                case "ACTIVE" -> {
                                    if ((isBoolean(eqList.get(2)))) { activeEq = Boolean.parseBoolean(eqList.get(2)); }
                                    else if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { activeEq = null; }
                                    else throw new NumberFormatException("Параметр не является булевым значением");

                                    System.out.println("INFO SELECT: " +"costEq = " + costEq);
                                    System.out.println("INFO SELECT: " +"eqList.get(1) = \"" + eqList.get(1) + "\"");
                                    System.out.println("INFO SELECT: " +"objectMap.get(\"ACTIVE\") = " + objectMap.get("ACTIVE"));

                                    if(eqList.get(1).startsWith("!=")) {
                                        if(eqList.get(2).equals("NULL")) { isEquals.add(objectMap.get("ACTIVE") != activeEq); }
                                        else { isEquals.add(Boolean.parseBoolean(String.valueOf(objectMap.get("ACTIVE"))) != activeEq); }
                                    } else if(eqList.get(1).startsWith("=")  && objectMap.get("ID") != null) {
                                        if(objectMap.get("ACTIVE") != null) { isEquals.add(Boolean.parseBoolean(String.valueOf(objectMap.get("ACTIVE"))) == activeEq); }
                                        else isEquals.add(false);
                                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                                }
                                default -> throw new Exception("В запросе указаны колонки, которых нет в исходной таблице");
                            }
                        }

                        idEq = null;
                        lastNameEq = null;
                        ageEq = null;
                        costEq = null;
                        activeEq = null;

                        int tmp = 0;
                        Boolean isView = null;

                        System.out.println("INFO SELECT: " +"isEquals = " + isEquals);

                        for(int i = 0; i <= isEquals.size()-1; i++){
                            if (listAndOr.size() == 0) { isView =  isEquals.get(i); }
                            else if(listAndOr.get( ( (tmp < listAndOr.size()) && (listAndOr.size() != 1 ) ) ? tmp : listAndOr.size()-1 ).equals("AND")) {
                                if(isView != null) { isView = isView & isEquals.get(i); }
                                else { isView = isEquals.get(i); }
                            }
                            else if(listAndOr.get( ( (tmp < listAndOr.size()) && (listAndOr.size() != 1 ) ) ? tmp : listAndOr.size()-1 ).equals("OR")) {
                                if(isView != null) { isView = isView | isEquals.get(i); }
                                else { isView = isEquals.get(i); }
                            }
                            tmp++;
                        }

                        if (isView) {
                            System.out.println("INFO SELECT: " +"isView = " + isView + " add objectMap = " + objectMap);
                            result.add(objectMap);
                        }else { System.out.println("INFO SELECT: " +"not add objectMap  isView = " + isView); }
                    }
                }
                else { throw new Exception("Не верно составленный запрос"); }
            }
        }
        return result;
    }

    private  String[] setSpace(String str) {
        List<String> result = new ArrayList<>(List.of(str
                .replaceAll("’=", "' = ")
                .replaceAll("’!=", "' != ")
                .replaceAll("\\(", "( ")
                .replaceAll("\\)", " ) ")
                .replaceAll("(?i)like", " LIKE ")
                .replaceAll("(?i) and", " AND ")
                .replaceAll("(?i) or", " OR ")
                .replaceAll("(?i) where", " WHERE ")
                .replaceAll("(?i) ilike | i LIKE", " ILIKE ")
                .replaceAll("=", " = ")
                .replaceAll("<", " < ")
                .replaceAll(">", " > ")
                .replaceAll(">= | >  =", " >= ")
                .replaceAll("<= | <  =", " <= ")
                .replaceAll("!= | ! = |! =", " != ")
                .replaceAll(",", " ")
                .replaceAll("(?i)null", " NULL ")
                .replaceAll("(?i)id", "ID")
                .replaceAll("(?i)lastName", "LASTNAME")
                .replaceAll("(?i)age", "AGE")
                .replaceAll("(?i)cost", "COST")
                .replaceAll("(?i)active", "ACTIVE")
                .split(" ")));

        result.removeIf(String::isEmpty);

        return result.toArray(new String[0]);
    }

    private  boolean isDigitLong(String s) throws NumberFormatException {
        try {
            Long.parseLong(s.trim());
            return true;
        } catch (NumberFormatException e) { return false; }
    }

    private  boolean isDigitDouble(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s.trim());
            return true;
        } catch (NumberFormatException e) { return false; }
    }

    private  boolean isBoolean(String s) {
        try {
            Boolean.parseBoolean(s.trim());
            return true;
        } catch (NumberFormatException e) { return false; }
    }
}
