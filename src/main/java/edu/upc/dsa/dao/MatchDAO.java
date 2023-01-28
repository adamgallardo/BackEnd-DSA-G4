package edu.upc.dsa.dao;

import edu.upc.dsa.models.Ranking;

import java.util.List;

public interface MatchDAO {

    public List<Ranking> getRanking ();
    public Ranking addMatch(Ranking match);


}
