package edu.upc.dsa.dao.implementations;


import edu.upc.dsa.dao.QueryDAO;
import edu.upc.dsa.models.Query;
import org.apache.log4j.Logger;


public class QueryDAOImpl implements QueryDAO {

    private static QueryDAOImpl instance;
    final static Logger logger = Logger.getLogger(QueryDAOImpl.class);

    public static QueryDAO getInstance(){
        if(instance == null){
            instance = new QueryDAOImpl();
        }
        return instance;
    }

    @Override
    public Query showQuery(Query query) {
        logger.info(query.toString());
        return query;
    }
}
