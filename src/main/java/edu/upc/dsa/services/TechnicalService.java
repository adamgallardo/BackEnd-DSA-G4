package edu.upc.dsa.services;

import edu.upc.dsa.dao.IIssueDAO;
import edu.upc.dsa.dao.implementations.IssueDAOImpl;
import edu.upc.dsa.dao.QueryDAO;
import edu.upc.dsa.dao.implementations.QueryDAOImpl;
import edu.upc.dsa.models.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/technicalService", description = "Endpoint to Technical Service")
@Path("/technicalService")
public class TechnicalService {

    private QueryDAO queryDAO;

    private IIssueDAO issueManager;

    public TechnicalService(){
        this.queryDAO = QueryDAOImpl.getInstance();
        this.issueManager = IssueDAOImpl.getInstance();
    }

    //Query minim 2 Bernat
    @POST
    @ApiOperation(value = "Query", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Query.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/question")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response showQuery(Query query){
        Query q = new Query(query.getDate(), query.getTitle(), query.getMessage(), query.getSender());
        if (q.getDate().isEmpty() || q.getTitle().isEmpty() || q.getMessage().isEmpty() || q.getSender().isEmpty())
            return Response.status(500).build();

        else {
            this.queryDAO.showQuery(q);
            return Response.status(200).entity(q).build();
        }
    }

    //Issue minim 2 Tatiana
    @POST
    @ApiOperation(value = "Issue", notes = "date + user + message")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Issue.class),
            @ApiResponse(code = 500, message = "Information Error")
    })
    @Path("/report")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response reportIssue(Issue issueInfo) {
        if (issueInfo.getDate().isEmpty() || issueInfo.getInformer().isEmpty() || issueInfo.getMessage().isEmpty())
            return Response.status(500).build();

        else {
            this.issueManager.addIssue(
                    issueInfo.getDate(), issueInfo.getInformer(), issueInfo.getMessage());

            return Response.status(201).entity(issueInfo).build();
        }
    }
}
