package com.example.NetflixWeb.Service;

import com.example.NetflixWeb.Model.Show;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Service
public class Reader {
    public static List<Show> readFromCSV(String fileName){
        List<Show> shows = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try(BufferedReader br = Files.newBufferedReader(pathToFile)){
            br.readLine();
            String line = br.readLine();
            while (line != null){
                int start =0;
                List<String> attribute = new ArrayList<>();
                for(int i=0;i<line.length();i++){
                    if(line.charAt(i)=='\"'){
                        //int in = i;
                        i++;
                        while(i<line.length() && line.charAt(i)!='\"'){
                            i++;
                        }
                        continue;
                    }
                    if(line.charAt(i)==','){
                        attribute.add(line.substring(start, i));
                        start=i+1;
                    }
                }
                attribute.add(line.substring(start, line.length()));
                Show show = createShow(attribute);
                shows.add(show);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shows;
    }

    private static Show createShow(List<String> attribute) {
        String showId = attribute.get(0);
        String type = attribute.get(1);
        String title = attribute.get(2);
        String director = attribute.get(3);
        String cast = attribute.get(4);
        String country = attribute.get(5);
        String dateAdded = attribute.get(6);
        String releaseYear = attribute.get(7);
        String rating = attribute.get(8);
        String duration = attribute.get(9);
        String listedIn = attribute.get(10);
        String description = attribute.get(11);
        return new Show(showId, type, title, director, cast, country, dateAdded, releaseYear, rating, duration, listedIn, description);
    }
}
