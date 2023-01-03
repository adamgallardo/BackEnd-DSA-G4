package edu.upc.dsa.dao.implementations;

import edu.upc.dsa.dao.SessionDAO;
import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
public class SessionImpl<E> implements SessionDAO<E> {

    private final Connection conn;
    private static SessionImpl instance;

    SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public static SessionImpl getInstance(){
        if(instance==null){
            Connection conn = FactorySession.getConnection();
            instance = new SessionImpl(conn);
        }
        return instance;
    }

    @Override
    public void save(Object entity) {
        String insertQuery = QueryHelper.createQueryINSERT(entity);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(insertQuery);

            int i = 0;
            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(++i, ObjectHelper.getter(entity,field));
            }
            pstm.executeQuery();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Object getByName(Class theClass, String username) {
        String query = QueryHelper.createQuerySELECTByName(theClass, username);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(query);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();

            Object entity = theClass.newInstance();
            while(rs.next()){
                for(int i=1; i<rsmd.getColumnCount()+1; i++){
                    ObjectHelper.setter(entity, rsmd.getColumnName(i),rs.getObject(i));
                }
            }
            return entity;
        }
        catch(SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        try {
            this.conn.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<E> findAll(Class theClass) {
        String selectAllQuery = QueryHelper.createQuerySELECTAll(theClass);
        PreparedStatement pstm = null;
        List<E> result = new LinkedList<E>();

        try {
            pstm = conn.prepareStatement(selectAllQuery);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();

            while(rs.next()) {
                Object entity = theClass.newInstance();
                for (int i = 1; i<rsmd.getColumnCount() + 1; i++) {
                    ObjectHelper.setter(entity,rsmd.getColumnName(i),rs.getObject(i));
                }
                result.add((E) entity);
            }
            return result;
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Object entity) {
        String deleteQuery = QueryHelper.createQueryDELETE(entity);
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(deleteQuery);
            pstm.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Object getById(Class theClass, String id) {
        String selectByIdQuery = QueryHelper.createQuerySELECTById(theClass, id);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectByIdQuery);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();

            Object entity = theClass.newInstance();
            while(rs.next()){
                for(int i=1; i<rsmd.getColumnCount()+1; i++){
                    ObjectHelper.setter(entity, rsmd.getColumnName(i),rs.getObject(i));
                }
            }
            return entity;
        }
        catch(SQLException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Object object) {
        String updateQuery = QueryHelper.createQueryUPDATE(object);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(updateQuery);
            int i = 1;
            for (String field: ObjectHelper.getFields(object)) {
                pstm.setObject(i++,ObjectHelper.getter(object, field));
            }
            pstm.executeQuery();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
