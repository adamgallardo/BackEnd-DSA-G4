package edu.upc.dsa.dao;

import edu.upc.dsa.models.Issue;

public interface IIssueDAO {
    Issue addIssue(String date, String informer, String message);

}