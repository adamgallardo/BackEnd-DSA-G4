package edu.upc.dsa.services;

import edu.upc.dsa.dao.IUserDAO;
import edu.upc.dsa.dao.MatchDAO;
import edu.upc.dsa.dao.implementations.MatchDAOImpl;
import edu.upc.dsa.dao.implementations.UserDAOImpl;
import edu.upc.dsa.models.Ranking;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/match", description = "Endpoint to Match Service")
@Path("/match")


public class MatchService {
    private IUserDAO userManager;
    private MatchDAO matchManager;

    public MatchService() {
        this.userManager = UserDAOImpl.getInstance();
        this.matchManager = MatchDAOImpl.getInstance();
    }


    // devuelve el ranking.
    @GET
    @ApiOperation(value = "Get ranking!", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Ranking.class, responseContainer="List"),
    })
    @Path("/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRanking() {

        List<Ranking> ranking =this.matchManager.getRanking(); // new ArrayList<>(); //this.matchManager.getRanking();

            /*ranking.add(new Match( "maria", 150));
            ranking.add(new Match( "bjo", 120));
            ranking.add(new Match( "juan", 110));*/

        GenericEntity<List<Ranking>> entity = new GenericEntity<List<Ranking>>(ranking) {};
        return Response.status(200).entity(entity).build();
    } //añadir respuesta en caso de que vaya mal
}
