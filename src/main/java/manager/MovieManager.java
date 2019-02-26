package manager;
import db.DBConnectionProvider;
import model.Movie;
import model.Genre;
import util.DateUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;



public class MovieManager {

    private Connection connection;

    public MovieManager() {
        connection= DBConnectionProvider.getInstance().getConnection();
    }


    public void  addMovie(Movie movie){
        try {
            String query="INSERT INTO movie(title,description,createdDate,picture,year,director) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,movie.getTitle());
            preparedStatement.setString(2,movie.getDescription());
            preparedStatement.setString(3,DateUtil.convertDateToString(movie.getCreatedDate()));
            preparedStatement.setString(4,movie.getPicture());
            preparedStatement.setString(5,DateUtil.convertDateToString(movie.getMovieYear()));
            preparedStatement.setString(6,movie.getDirector());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys=preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                movie.setId(generatedKeys.getInt(1));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public List<Movie> movieByGenre(int id){
        String query = "SELECT * FROM `movie` INNER JOIN `genremovie` ON movie.id=genremovie.`movie_id` \n" +
                "AND genremovie.`genre_id` IN (SELECT id FROM `genre`  INNER JOIN `genremovie` ON genre.id=genremovie.`genre_id` \n" +
                "WHERE genremovie.`genre_id`=" +id+ ")";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Movie> movies = new LinkedList<Movie>();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setDescription(resultSet.getString(3));
                movie.setCreatedDate(DateUtil.convertStringToDate(resultSet.getString(4)));
                movie.setPicture(resultSet.getString(5));
                movie.setMovieYear(DateUtil.convertStringToDate(resultSet.getString(6)));
                movie.setDirector(resultSet.getString(7));

                movies.add(movie);
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Movie> getAllMovie(){
        String query = "Select * from movie";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println(resultSet + " hhhhhhhhhhh");
            List<Movie> movies = new LinkedList<Movie>();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setDescription(resultSet.getString(3));
                movie.setCreatedDate(DateUtil.convertStringToDate(resultSet.getString(4)));
                movie.setPicture(resultSet.getString(5));
                movie.setMovieYear(DateUtil.convertStringToDate(resultSet.getString(6)));
                movie.setDirector(resultSet.getString(7));

                movies.add(movie);
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Movie> getMovieLimit(){
        String query = "Select * from movie limit 6";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println(resultSet + " hhhhhhhhhhh");
            List<Movie> movies = new LinkedList<Movie>();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setDescription(resultSet.getString(3));
                movie.setCreatedDate(DateUtil.convertStringToDate(resultSet.getString(4)));
                movie.setPicture(resultSet.getString(5));
                movie.setMovieYear(DateUtil.convertStringToDate(resultSet.getString(6)));
                movie.setDirector(resultSet.getString(7));

                movies.add(movie);
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Movie getMovieById(int id){
        String query = "SELECT * FROM movie WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setDescription(resultSet.getString(3));
                movie.setCreatedDate(DateUtil.convertStringToDate(resultSet.getString(4)));
                movie.setPicture(resultSet.getString(5));
                movie.setMovieYear(resultSet.getDate(6));
                movie.setDirector(resultSet.getString(7));
                return movie;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }



    public List<Movie> getAllMovieByYear(int year){
        String query = "Select * from movie where year ="+ year;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Movie> movies = new LinkedList<Movie>();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setDescription(resultSet.getString(3));
                movie.setCreatedDate(DateUtil.convertStringToDate(resultSet.getString(4)));
                movie.setPicture(resultSet.getString(5));
                movie.setMovieYear(DateUtil.convertStringToDate(resultSet.getString(6)));
                movie.setDirector(resultSet.getString(7));

                movies.add(movie);
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Movie> getAllMovieBySearch(String searchResult){
        String query = "Select * from movie where title LIKE  '" +"%"+ searchResult + "%"+ "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Movie> movies = new LinkedList<Movie>();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setDescription(resultSet.getString(3));
                movie.setCreatedDate(DateUtil.convertStringToDate(resultSet.getString(4)));
                movie.setPicture(resultSet.getString(5));
                movie.setMovieYear(DateUtil.convertStringToDate(resultSet.getString(6)));
                movie.setDirector(resultSet.getString(7));

                movies.add(movie);
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    public void addGenreMovieId(Movie movie, List<Genre> genre){
        String query =  "INSERT INTO genremovie(genre_id,movie_id) VALUES (?,?)";
        System.out.println(query +"query ");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (Genre genreList : genre) {
                int gId = genreList.getId();
                preparedStatement.setInt(1, gId);
                preparedStatement.setInt(2,movie.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
