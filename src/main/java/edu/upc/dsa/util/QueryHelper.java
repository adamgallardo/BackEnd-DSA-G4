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
    public static String createQuerySELECTByName(Class theClass, String username){
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE username = '").append(username).append("'");
        return sb.toString();
    }
    public static String createQueryDELETE(Object entity){
        StringBuffer sb = new StringBuffer("");
        String id = (ObjectHelper.getter(entity, "id").toString());
        sb.append("DELETE FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE id = '").append(id).append("'");

        return sb.toString();
    }
    public static String createQuerySELECTById(Class theClass, String id){
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE id = '").append(id).append("'");

        return sb.toString();
    }
    public static String createQueryUPDATE(Object object){
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(object.getClass().getSimpleName());
        sb.append(" SET ");
        String id = (ObjectHelper.getter(object,"id").toString());
        String [] fields = ObjectHelper.getFields(object);
        for(String field: fields){
            sb.append(field).append(" = ?,");
        }
        //This part is for deleting the last coma
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE id = '").append(id).append("'");
        return sb.toString();
    }
}
