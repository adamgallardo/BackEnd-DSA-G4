package edu.upc.dsa.dao.implementations;

import edu.upc.dsa.dao.MatchDAO;
import edu.upc.dsa.models.Ranking;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MatchDAOImpl implements MatchDAO {


    private SessionImpl session;

    final static Logger logger = Logger.getLogger(ItemDAOImpl.class);

    private static MatchDAOImpl instance;

    private MatchDAOImpl(){
        this.session = SessionImpl.getInstance();
    }

    public static MatchDAO getInstance(){
        if(instance == null){
            instance = new MatchDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Ranking> getRanking() {
        List<Ranking> matchList = this.session.findAll(Ranking.class);
        List<Ranking> ranking = matchList.stream().sorted(Comparator.comparing(Ranking::getMaxPoints).reversed()).collect(Collectors.toList());
        return ranking;
    }

    @Override
    public Ranking addMatch(Ranking match) {
        this.session.save(match);
        return match;
    }
}
