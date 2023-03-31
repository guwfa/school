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
                    
                    condition.removeIf(x -> x.equals("("));
                    List<List<String>>  listKeyValue = new ArrayList<>();
                    List<String> keyValue2 = new ArrayList<>();
                    HashMap<String,Object> map = new HashMap<>();

                    if(condition.contains(")") ) {
                        for (int i = 0 ; i < condition.size()-1; i++) {
                            if(!condition.get(i).trim().contains(")")) {
                                String tmp = condition.get(i).replaceAll("’|‘|'","") + "=" + condition.get(i+1);
                                keyValue2.add(tmp);
                                i++;
                            }
                            if(condition.get(i+1).trim().contains(")") ) {
                                listKeyValue.add(keyValue2);
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

                    for (List<String> list1 : listKeyValue) {
                        for (String str : list1) {
                            String[] tmp = str.replaceAll("[\\[\\]]","").trim().split("=");
                            switch (tmp[0].trim().replaceAll("’|‘|'","")){
                                case "ID" -> map.put("ID",Long.parseLong(tmp[1].trim()));
                                case "LASTNAME" -> map.put("LASTNAME", tmp[1].trim().replaceAll("’|‘|'",""));
                                case "AGE" -> map.put("AGE", Long.parseLong(tmp[1].trim()));
                                case "COST" -> map.put("COST", Double.valueOf(tmp[1].trim()));
                                case "ACTIVE" -> map.put("ACTIVE", Boolean.valueOf(tmp[1].trim()));
                                default -> throw new Exception("В запросе указаны колонки, которых нет в исходной таблице");
                            }
                        }

                        if(map.size() != 0) {
                            list.add(Map.copyOf(map));
                            result.add(Map.copyOf(map));
                        }
                    }
                }
                else { throw new Exception("Не верно составленный запрос"); }
            }
            case "UPDATE" -> {

                if(!request.toUpperCase().contains("WHERE")){
                    if(task[1].toUpperCase(Locale.ROOT).equals("VALUES")) {
                        condition = new ArrayList<>(List.copyOf(List.of(task)));
                        condition.remove(0);
                        condition.remove(0);
                        condition.removeIf(x -> x.equals("="));

                        result = List.copyOf(list);
                        list.clear();

                        List<String> keyValue = new ArrayList<>();

                        for (int i = 0 ; i < condition.size()-1; i++){
                            if(i % 2 == 0){
                                String tmp = condition.get(i).replaceAll("’|‘|'","") + "=" + condition.get(i+1);
                                keyValue.add(tmp);
                            }
                        }

                        for (String str : keyValue) {
                            String[] tmp = str.replaceAll("[\\[\\]]","").trim().split("=");

                            switch (tmp[0].trim().replaceAll("’|‘|'","")){
                                case "ID" -> {
                                    if(!tmp[1].trim().toUpperCase(Locale.ROOT).equals("NULL")) { id = Long.parseLong(tmp[1].trim()); }
                                    else idEq = (long) -1;
                                }
                                case "LASTNAME" -> {
                                    if(!tmp[1].trim().toUpperCase(Locale.ROOT).equals("NULL")) { lastName = tmp[1].trim().replaceAll("’|‘|'",""); }
                                    else lastNameEq = "-1";
                                }
                                case "AGE" -> {
                                    if(!tmp[1].trim().toUpperCase(Locale.ROOT).equals("NULL")) { age = Long.parseLong(tmp[1].trim()); }
                                    else ageEq = (long) -1;
                                }
                                case "COST" -> {
                                    if(!tmp[1].trim().toUpperCase(Locale.ROOT).equals("NULL")) { cost = Double.valueOf(tmp[1].trim()); }
                                    else costEq = (double) -1;
                                }
                                case "ACTIVE" -> {
                                    if(!tmp[1].trim().toUpperCase(Locale.ROOT).equals("NULL")) { active = Boolean.valueOf(tmp[1].trim()); }
                                    else activeEq = Boolean.FALSE;
                                }
                                default -> throw new Exception("В запросе указаны колонки, которых нет в исходной таблице");
                            }
                        }

                        //Прогоняю цикл обновления значений в мапе каждой строки
                        for (Map<String,Object> objectMap :  result) {

                            Map<String,Object> tmp =  new HashMap<>(objectMap);
                            if(id != null ) {
                                if( objectMap.containsKey("ID")) { tmp.put("ID", id); }
                            } else if(idEq != null && objectMap.containsKey("ID")) { tmp.remove("ID"); }
                            if(lastName != null ) {
                                if( objectMap.containsKey("LASTNAME")) { tmp.put("LASTNAME", lastName); }
                            } else if(lastNameEq != null && objectMap.containsKey("LASTNAME")) { tmp.remove("LASTNAME"); }
                            if(age != null ) {
                                if( objectMap.containsKey("AGE")) { tmp.put("AGE", age); }
                            } else if(ageEq != null && objectMap.containsKey("AGE")) { tmp.remove("AGE"); }
                            if(cost != null ) {
                                if( objectMap.containsKey("COST")) { tmp.put("COST", cost); }
                            } else if(costEq != null && objectMap.containsKey("COST")) { tmp.remove("COST"); }
                            if(active != null ) {
                                if( objectMap.containsKey("ACTIVE")) { tmp.put("ACTIVE", active); }
                            } else if(activeEq != null && objectMap.containsKey("ACTIVE")) { tmp.remove("ACTIVE"); }

                            list.add(tmp);
                        }

                        return List.copyOf(list);
                    }
                    else { throw new Exception("Не верно составленный запрос"); }
                }
                else if(request.toUpperCase().contains("WHERE")) {
                    condition = new ArrayList<>(List.copyOf(List.of(task)));
                    condition.remove(0);
                    condition.remove(0);

                    List<Map<String,Object>> noResult = new ArrayList<>();
                    List<String> conditionWhere = List.copyOf(condition);

                    condition  =  condition.subList(0, condition.indexOf("WHERE")); /** Данные для обновления */
                    conditionWhere = conditionWhere.subList(conditionWhere.indexOf("WHERE") + 1 ,conditionWhere.size()); /** Данные для сравнения (Where) */

                    //Начинаем добавлять в result  все нужные строки
                    List<String> keyValue = new ArrayList<>();
                    List<String> listAndOr = getSplitAndOr(conditionWhere,keyValue);

                    for(Map<String,Object> objectMap : list){
                        isEquals.clear();
                        for(String str : keyValue){
                            List<String> eqList = List.of(str.split(" "));
                            isEquals.add(isCondition(objectMap, eqList));
                        }
                        if (isView(isEquals,listAndOr)) { result.add(objectMap); }
                        else noResult.add(objectMap);
                    }
                    //Заканчиваем добавлять в result  все нужные строки

                    //Начинаем обновлять данные в нужных строках
                    list.clear();
                    keyValue = new ArrayList<>();
                    condition.removeIf(x -> x.equals("="));
                    for (int i = 0 ; i < condition.size()-1; i++){
                        if(i % 2 == 0){
                            String tmp = condition.get(i).replaceAll("’|‘|'","") + "="+ condition.get(i+1);
                            keyValue.add(tmp);
                        }
                    }

                    for (String str : keyValue) {
                        String[] tmp = str.replaceAll("[\\[\\]]","").trim().split("=");

                        switch (tmp[0].trim().replaceAll("’|‘|'","")){
                            case "ID" -> {
                                if(!tmp[1].trim().toUpperCase(Locale.ROOT).equals("NULL")) { id = Long.parseLong(tmp[1].trim()); }
                                else idEq = (long) -1;
                            }
                            case "LASTNAME" -> {
                                if(!tmp[1].trim().toUpperCase(Locale.ROOT).equals("NULL")) { lastName = tmp[1].trim().replaceAll("’|‘|'",""); }
                                else lastNameEq = "-1";
                            }
                            case "AGE" -> {
                                if(!tmp[1].trim().toUpperCase(Locale.ROOT).equals("NULL")) { age = Long.parseLong(tmp[1].trim()); }
                                else ageEq = (long) -1;
                            }
                            case "COST" -> {
                                if(!tmp[1].trim().toUpperCase(Locale.ROOT).equals("NULL")) { cost = Double.valueOf(tmp[1].trim()); }
                                else costEq = (double) -1;
                            }
                            case "ACTIVE" -> {
                                if(!tmp[1].trim().toUpperCase(Locale.ROOT).equals("NULL")) { active = Boolean.valueOf(tmp[1].trim()); }
                                else activeEq = Boolean.FALSE;
                            }
                            default -> throw new Exception("В запросе указаны колонки, которых нет в исходной таблице");
                        }
                    }

                    //Прогоняю цикл обновления значений в мапе каждой строки
                    for (Map<String,Object> objectMap :  result) {

                        Map<String,Object> tmp =  new HashMap<>(objectMap);
                        if(id != null ) {
                            tmp.put("ID", id);
                        } else if(idEq != null && objectMap.containsKey("ID")) { tmp.remove("ID"); }
                        if(lastName != null ) {
                            tmp.put("LASTNAME", lastName);
                        } else if(lastNameEq != null && objectMap.containsKey("LASTNAME")) { tmp.remove("LASTNAME"); }
                        if(age != null ) {
                            tmp.put("AGE", age);
                        } else if(ageEq != null && objectMap.containsKey("AGE")) { tmp.remove("AGE"); }
                        if(cost != null ) {
                             tmp.put("COST", cost);
                        } else if(costEq != null && objectMap.containsKey("COST")) { tmp.remove("COST"); }
                        if(active != null ) {
                            tmp.put("ACTIVE", active);
                        } else if(activeEq != null && objectMap.containsKey("ACTIVE")) { tmp.remove("ACTIVE"); }

                        list.add(tmp);
                    }
                    list.addAll(noResult);
                    return List.copyOf(list);
                    //Заканчиваем обновлять данные в нужных строках

                }
                else { throw new Exception("Не верно составленный запрос"); }
            }
            case "DELETE" -> {
                if(task.length == 1 ) {
                    result = list;
                    list.clear();
                    return result;
                }
                else if(task[1].toUpperCase(Locale.ROOT).equalsIgnoreCase("WHERE")) {
                    condition = new ArrayList<>(List.copyOf(List.of(task)));
                    condition.remove(0);
                    condition.remove(0);

                    List<String> keyValue = new ArrayList<>();
                    List<String> listAndOr = getSplitAndOr(condition,keyValue);

                    for(Map<String,Object> objectMap : list){
                        isEquals.clear();
                        for(String str : keyValue){
                            List<String> eqList = List.of(str.split(" "));
                            isEquals.add(isCondition(objectMap, eqList));
                        }
                        if (isView(isEquals,listAndOr)) { result.add(objectMap); }
                    }

                    for(Map<String,Object> objectMap : result) { list.remove(objectMap); }
                    return result;
                }
                else { throw new Exception("Не верно составленный запрос"); }

            }
            case "SELECT" -> {
                if(task.length == 1 ) { return List.copyOf(list); }
                else if( task[1].trim().equals("*")) { return List.copyOf(list); }
                else if(task[1].toUpperCase(Locale.ROOT).equalsIgnoreCase("WHERE")) {

                    condition = new ArrayList<>(List.copyOf(List.of(task)));
                    condition.remove(0);
                    condition.remove(0);

                    List<String> keyValue = new ArrayList<>();
                    List<String> listAndOr = getSplitAndOr(condition,keyValue);

                    for(Map<String,Object> objectMap : list){
                        isEquals.clear();
                        for(String str : keyValue){
                            List<String> eqList = List.of(str.split(" "));
                            isEquals.add(isCondition(objectMap, eqList));
                        }
                        if (isView(isEquals,listAndOr)) { result.add(objectMap); }
                    }
                }
                else { throw new Exception("Не верно составленный запрос"); }
            }
        }
        return result;
    }

    private Boolean isCondition( Map<String, Object> objectMap, List<String> eqList) throws Exception {
        long ageEq,idEq;
        String lastNameEq;
        double costEq;
        boolean activeEq, isResult = false;
        switch (eqList.get(0)){
            case "ID" -> {
                if ((isDigitLong(eqList.get(2)))) { idEq = Long.parseLong(eqList.get(2)); }
                else throw new NumberFormatException("Параметр не является числом");
                if((eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) && objectMap.get("ID") != null) {
                    switch (eqList.get(1)) {
                        case "<" ->  isResult = Long.parseLong(String.valueOf(objectMap.get("ID"))) < idEq;
                        case ">" ->  isResult = Long.parseLong(String.valueOf(objectMap.get("ID"))) > idEq;
                        case "<=" -> isResult = Long.parseLong(String.valueOf(objectMap.get("ID"))) <= idEq;
                        case ">=" -> isResult = Long.parseLong(String.valueOf(objectMap.get("ID"))) >= idEq;
                        default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                    }
                } else if(eqList.get(1).startsWith("!=")) {
                    if(objectMap.get("ID") == null) isResult = true;
                    else isResult = Long.parseLong(String.valueOf(objectMap.get("ID"))) != idEq;
                } else if(eqList.get(1).startsWith("=") && objectMap.get("ID") != null) {
                    isResult = Long.parseLong(String.valueOf(objectMap.get("ID"))) == idEq;
                }else throw new Exception("В запросе указаны не верные данные для сравнения");
            }
            case "LASTNAME" -> {
                if(eqList.get(2).equals("NULL") && eqList.get(1).startsWith("!=")) { lastNameEq = null; }
                else { lastNameEq = eqList.get(2).replaceAll("’|‘|'",""); }

                if(eqList.get(1).startsWith("!=")) {
                    if(objectMap.get("LASTNAME") == null) isResult = true;
                    else isResult = !String.valueOf(objectMap.get("LASTNAME")).equals(lastNameEq);
                }
                if(objectMap.get("LASTNAME") != null) {
                     if(eqList.get(1).startsWith("=")) {
                         isResult = objectMap.get("LASTNAME").equals(lastNameEq);
                    } else if(eqList.get(1).startsWith("LIKE")) {
                         assert lastNameEq != null;
                         if(lastNameEq.startsWith("%") && lastNameEq.endsWith("%")) {
                             lastNameEq = lastNameEq.replaceAll("%","").trim();
                            isResult = objectMap.get("LASTNAME").toString().contains(lastNameEq);
                        } else if(lastNameEq.endsWith("%")) {
                             lastNameEq = lastNameEq.replaceAll("%","").trim();
                            isResult = objectMap.get("LASTNAME").toString().startsWith(lastNameEq);
                        } else if(lastNameEq.startsWith("%")) {
                             lastNameEq = lastNameEq.replaceAll("%","").trim();
                            isResult = objectMap.get("LASTNAME").toString().endsWith(lastNameEq);
                        } else {
                            isResult = objectMap.get("LASTNAME").toString().equals(lastNameEq);
                        }
                    } else if(eqList.get(1).startsWith("ILIKE")) {
                         assert lastNameEq != null;

                         if(lastNameEq.startsWith("%") && lastNameEq.endsWith("%")) {
                             lastNameEq = lastNameEq.toUpperCase().replaceAll("%","").trim();
                            isResult = objectMap.get("LASTNAME").toString().toUpperCase().contains(lastNameEq);
                        } else if(lastNameEq.endsWith("%")) {
                             lastNameEq = lastNameEq.toUpperCase().replaceAll("%","").trim();
                            isResult = objectMap.get("LASTNAME").toString().toUpperCase().startsWith(lastNameEq);
                        } else if(lastNameEq.startsWith("%")) {
                             lastNameEq = lastNameEq.toUpperCase().replaceAll("%","").trim();
                            isResult = objectMap.get("LASTNAME").toString().toUpperCase().endsWith(lastNameEq);
                        } else {
                            isResult = objectMap.get("LASTNAME").toString().toUpperCase().equals(lastNameEq);
                        }
                    }
                }
                else throw new Exception("В запросе указаны не верные данные для сравнения");
            }
            case "AGE" -> {
                if ((isDigitLong(eqList.get(2)))) { ageEq = Long.parseLong(eqList.get(2)); }
                else throw new NumberFormatException("Параметр не является числом");

                if((eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) && objectMap.get("ID") != null) {
                    switch (eqList.get(1)) {
                        case "<" ->  isResult = Long.parseLong(String.valueOf(objectMap.get("AGE"))) < ageEq;
                        case ">" ->  isResult = Long.parseLong(String.valueOf(objectMap.get("AGE"))) > ageEq;
                        case "<=" -> isResult = Long.parseLong(String.valueOf(objectMap.get("AGE"))) <= ageEq;
                        case ">=" -> isResult = Long.parseLong(String.valueOf(objectMap.get("AGE"))) >= ageEq;
                        default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                    }
                } else if(eqList.get(1).startsWith("!=")) {
                    isResult = objectMap.get("AGE") != null ? (Long.parseLong(String.valueOf(objectMap.get("AGE"))) != ageEq) : true ;
                } else if(eqList.get(1).startsWith("=") && objectMap.get("AGE") != null) {
                    isResult = Long.parseLong(String.valueOf(objectMap.get("AGE"))) == ageEq;
                }else throw new Exception("В запросе указаны не верные данные для сравнения");
            }
            case "COST" -> {
                if ((isDigitDouble(eqList.get(2)))) { costEq = Double.parseDouble(eqList.get(2)); }
                else throw new NumberFormatException("Параметр не является числом");

                if(!eqList.get(1).startsWith("!=") && objectMap.get("COST") != null) {
                    if((eqList.get(1).startsWith(">") || eqList.get(1).startsWith("<")) && objectMap.get("COST") != null) {
                        switch (eqList.get(1)) {
                            case "<" ->  isResult = Double.parseDouble(String.valueOf(objectMap.get("COST"))) < costEq;
                            case ">" ->  isResult = Double.parseDouble(String.valueOf(objectMap.get("COST"))) > costEq;
                            case "<=" -> isResult = Double.parseDouble(String.valueOf(objectMap.get("COST"))) <= costEq;
                            case ">=" -> isResult = Double.parseDouble(String.valueOf(objectMap.get("COST"))) >= costEq;
                            default -> throw new Exception("В запросе указаны не верные данные для сравнения");
                        }
                    }
                    else if(eqList.get(1).startsWith("=") && objectMap.get("COST") != null) {
                        isResult = Double.parseDouble(String.valueOf(objectMap.get("COST"))) == costEq;
                    }else throw new Exception("В запросе указаны не верные данные для сравнения");
                }
                else if(eqList.get(1).startsWith("!=")) {
                    isResult = objectMap.get("COST") == null ? true : (Double.parseDouble(String.valueOf(objectMap.get("COST"))) != costEq);
                }  else if (objectMap.get("COST") == null && !eqList.get(1).startsWith("!=")) { isResult = false; }
            }
            case "ACTIVE" -> {
                if ((isBoolean(eqList.get(2)))) { activeEq = Boolean.parseBoolean(eqList.get(2)); }
                else throw new NumberFormatException("Параметр не является булевым значением");

                if(eqList.get(1).startsWith("!=")) {
                    isResult = objectMap.get("ACTIVE") == null ? true : (isResult = Boolean.parseBoolean(String.valueOf(objectMap.get("ACTIVE"))) != activeEq);
                } else if(eqList.get(1).startsWith("=")  && objectMap.get("ACTIVE") != null) {
                    if(objectMap.get("ACTIVE") != null) { isResult = Boolean.parseBoolean(String.valueOf(objectMap.get("ACTIVE"))) == activeEq; }
                    else isResult = false;
                }else throw new Exception("В запросе указаны не верные данные для сравнения");
            }
            default -> throw new Exception("В запросе указаны колонки, которых нет в исходной таблице");
        }
        return isResult;
    }

    private Boolean isView(List<Boolean> isEquals, List<String> listAndOr) {
        int tmp = 0;
        Boolean isView = null;

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
        return isView;
    }

    private List<String> getSplitAndOr(List<String> condition, List<String> keyValue) {
        List<String> listAndOr = new ArrayList<>();
        for (int i = 0 ; i < condition.size()-1; i+=4){
            String tmp = condition.get(i).replaceAll("’|‘|'","") + " " + condition.get(i+1) + " " + condition.get(i+2) +
                    ( (condition.size() >= (i + 4)) ? (" |" + condition.get(i+3)) : ("") );
            keyValue.add(tmp);
            if(condition.size() >= (i + 4)) listAndOr.add(condition.get(i+3));
        }
        return listAndOr;
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
