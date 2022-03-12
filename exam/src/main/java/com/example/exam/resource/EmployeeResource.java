package com.example.exam.resource;

import com.example.exam.entity.Employee;
import com.example.exam.model.EmployeeModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/employees")
public class EmployeeResource {
    private EmployeeModel employeeModel;

    public EmployeeResource(){
        employeeModel = new EmployeeModel();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        try{
            return Response.status(Response.Status.OK).entity(employeeModel.findAll()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.OK).entity(new ArrayList<>()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Employee employee){
        try {
            Employee savedEmployee = employeeModel.add(employee);
            return Response.status(Response.Status.CREATED).entity(savedEmployee).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id")int id, Employee employee){
        try{
            Employee foundEmployee = employeeModel.findById(id);
            if (foundEmployee == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            Employee updatedEmployee = employeeModel.update(id, employee);
            return Response.status(Response.Status.OK).entity(updatedEmployee).build();
        } catch (SQLException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
