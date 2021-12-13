package com.example.NetflixWeb.Controller;

import com.example.NetflixWeb.Model.Show;
import com.example.NetflixWeb.Service.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.text.ParseException;
import java.util.List;

@RestController
public class NetflixController {
    @Autowired ServiceUtil service;

    @GetMapping("/netflixOne/{type}")
    public List<Show> findByType(@PathVariable String type, @QueryParam("count") Integer count ) {return service.getByType(type, count);}

    @GetMapping("/netflix/{type}")
    public List<Show> findByTypeAndListed(@PathVariable String type, @QueryParam("listedItem") String listedItem ) {return service.getByTypeAndListed(type, listedItem);}

    @GetMapping("/netflixSearch/{type}")
    public List<Show> findByTypeAndCountry(@PathVariable String type, @QueryParam("country") String country ) {return service.getByTypeAndCountry(type, country);}

    @GetMapping("/netflix")
    public List<Show> findByDate(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate ) throws ParseException {return service.getByDate(startDate, endDate);}
}