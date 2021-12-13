package com.example.NetflixWeb.Service;

import com.example.NetflixWeb.Model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ServiceUtil {
    @Autowired Reader r;
    List<Show> shows = r.readFromCSV("C:\\Users\\subhodghosh\\NetflixWeb\\src\\main\\java\\com\\example\\NetflixWeb\\Resource\\netflix_titles.csv");

    public List<Show> getByType(String type, Integer count) {
        List<Show>listShow = new ArrayList<>();
        if(count!= null){
            int counter = 1;
            for(Show s : shows){
                if(s.getType().equalsIgnoreCase(type) && counter<=count){
                    listShow.add(s);
                    counter++;
                }
            }
        }else{
            for(Show s : shows){
                    if(s.getType().equalsIgnoreCase(type)){
                    listShow.add(s);
                }
            }
        }
        return listShow;
    }

    public List<Show> getByTypeAndListed(String type, String listedItem) {
        List<Show>listShow = new ArrayList<>();
        if(listedItem!= null){
            for(Show s : shows){
                if(s.getType().equalsIgnoreCase(type) && s.getListedIn().contains(listedItem)){
                    listShow.add(s);
                }
            }
        }else{
            for(Show s : shows){
                if(s.getType().equalsIgnoreCase(type)){
                    listShow.add(s);
                }
            }
        }
        return listShow;
    }

    public List<Show> getByTypeAndCountry(String type, String country) {
        List<Show>listShow = new ArrayList<>();
        if(country!= null){
            for(Show s : shows){
                if(s.getType().equalsIgnoreCase(type) && s.getCountry().contains(country)){
                    listShow.add(s);
                }
            }
        }else{
            for(Show s : shows){
                if(s.getType().equalsIgnoreCase(type)){
                    listShow.add(s);
                }
            }
        }
        return listShow;
    }

    public List<Show> getByDate(String startDate, String endDate) throws ParseException {
        List<Show>listShow = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        if(null!=startDate && null!= endDate){
            Date startOfDate = formatter.parse(startDate);
            Date endOfDate = formatter.parse(endDate);
            if ((Date.parse(startDate) <= Date.parse(endDate))){
                System.out.println(("End date should be greater than Start date"));
            }else{
                for(Show show : shows) {
                    if (!show.getDateAdded().isEmpty()) {
                        String dateShow = show.getDateAdded();
                        if (dateShow.charAt(1) == ' ') {
                            StringBuilder sb = new StringBuilder(dateShow);
                            sb.deleteCharAt(1);
                            dateShow = sb.toString();
                        }
                        String[] dateSplit = dateShow.split(" ");
                        String month = null;
                        if (dateSplit[0].length() > 4) {
                            month = dateSplit[0].substring(1, 4);
                        } else {
                            month = "May";
                        }
                        String date = null;
                        if (dateSplit[1].length() > 2) {
                            date = dateSplit[1].substring(0, 2);
                        } else {
                            date = dateSplit[1].substring(0, 1);
                            date = "0".concat(date);
                        }
                        String wholeDate = date.concat("-").concat(month).concat("-").concat(dateSplit[2]);
                        Date dateOriginal = formatter.parse(wholeDate);
                        if(dateOriginal.after(startOfDate) && dateOriginal.before(endOfDate)) listShow.add(show);
                    }
                }
            }
        } else{
            System.out.println("The date cannot be NULL");
        }
        return listShow;
    }
}
