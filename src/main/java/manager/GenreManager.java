package manager;

import db.DBConnectionProvider;
import model.Genre;

import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GenreManager {

    private Connection connection;

    public GenreManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }


    public void addGenre(Genre genre) {
        try {
            String query = "INSERT INTO genre(name) VALUES(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, genre.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                genre.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<Genre> getAllgenres() {
        String query = "SELECT * FROM genre";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Genre> genres = new LinkedList<Genre>();
            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getInt(1));
                genre.setName(resultSet.getString(2));
                genres.add(genre);
            }
            return genres;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Genre getCaGenreByName(String name) {
        String query = "SELECT * FROM genre WHERE name = '" + name + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getInt(1));
                genre.setName(resultSet.getString(2));
                return genre;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
