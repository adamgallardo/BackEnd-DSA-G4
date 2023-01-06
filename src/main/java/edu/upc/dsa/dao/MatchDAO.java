package edu.upc.dsa.dao;

import edu.upc.dsa.models.Match;

import java.util.List;

public interface MatchDAO {

    public List<Match> getRanking ();
    public Match addMatch(Match match);


}
