package edu.upc.dsa.util;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName());
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        for (String field: fields) {
            sb.append(field).append(", ");
        }
        //This part is for deleting the last coma and space
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(") VALUES (");
        for (String field: fields) {
            sb.append("?, ");
        }
        //This part is for deleting the last coma and space
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(")");
        return sb.toString();
    }
    public static String createQuerySELECTAll(Class theClass){
        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(theClass.getSimpleName());

        return sb.toString();
    }
    public static String createQuerySELECTByName(Class theClass, String name){
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE username = '").append(name).append("'");
        return sb.toString();
    }
}
