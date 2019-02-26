package servlet;

import manager.GenreManager;
import manager.MovieManager;
import model.Genre;
import model.Movie;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/addMovie")
public class AddMovieServlet extends HttpServlet {
private MovieManager movieManager=new MovieManager();
private GenreManager genreManager=new GenreManager();

        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                List<Genre> genre= new ArrayList<Genre>();

             if (ServletFileUpload.isMultipartContent(req)) {
                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setSizeThreshold(1024 * 1024);
                    factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

                    ServletFileUpload upload = new ServletFileUpload(factory);
                    upload.setFileSizeMax(1024 * 1024 * 50);
                    upload.setSizeMax(1024 * 1024 * 5 * 50);
                    String uploadPath = "/home/roza/Desktop/javaGitProjects/moviePortal/images";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }
                    try {
                        Movie movie = new Movie();
                        List<FileItem> formItems = upload.parseRequest(req);
                        if (formItems != null && formItems.size() > 0) {
                            for (FileItem item : formItems) {
                                if (!item.isFormField()) {
                                    String fileName = System.currentTimeMillis() + "_" + new File(item.getName()).getName();
                                    String filePath = uploadPath + File.separator + fileName;
                                    File storeFile = new File(filePath);
                                    item.write(storeFile);
                                    movie.setPicture(fileName);
                                } else {
                                    if (item.getFieldName().equals("title")) {
                                        movie.setTitle(item.getString());
                                    } else if (item.getFieldName().equals("description")) {
                                        movie.setDescription(item.getString());
                                    } else if (item.getFieldName().equals("director")) {
                                        movie.setDirector(item.getString());
                                    }else if(item.getFieldName().equals("gId")){
                                        Genre getGenreByName=genreManager.getCaGenreByName(item.getString());
                                        genre.add(getGenreByName);
                                        movie.setGenres(genre);
                                    }

                                }
                            }

                            movie.setCreatedDate(new Date());
                            movie.setMovieYear(new Date());
                            movieManager.addMovie(movie);
                            movieManager.addGenreMovieId(movie,genre);
                            resp.sendRedirect("/admin");
                        }
                    } catch (FileUploadException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        }



