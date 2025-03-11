package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import material.*;
import connexion.Connexion;

@WebServlet("/Ingredient")
public class IngredientController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id_ingredient = request.getParameter("id_ingredient");
        String name = request.getParameter("name");
        String unit = request.getParameter("unit");
        String stock = request.getParameter("stock");
        String price = request.getParameter("price");

        if (    
            null!=action && action.equals("insert") && 
            null!=name && !name.isEmpty() &&
            null!=unit && !unit.isEmpty() &&
            null!=stock && !stock.isEmpty() &&
            null!=price && !price.isEmpty() ){
                // parse 
                int id_unit=Integer.parseInt(unit);
                double stockage=Double.parseDouble(stock);
                double prix=Double.parseDouble(price);
                // creation de l' objet
                Ingredient ingredient=new Ingredient(0, name, id_unit,stockage,null,prix);
                Connection connection=null;
                Connexion conn=null;
                try {
                    conn = new Connexion();

                    connection = conn.connectePostgres();

                    ingredient.save(connection);

                    ingredient.savePrice(connection);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    
                } finally {
                    if ( null != connection ) {
                        try {
                            connection.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    response.sendRedirect("Ingredient?action=liste");
                }
        }
        else if (    
            null!=action && action.equals("update") && 
            null!=id_ingredient && !id_ingredient.isEmpty() &&
            null!=name && !name.isEmpty() &&
            null!=unit && !unit.isEmpty() &&
            null!=stock && !stock.isEmpty() &&
            null!=price && !price.isEmpty() ){
                // parse 
                int id_ing=Integer.parseInt(id_ingredient);
                int id_unit=Integer.parseInt(unit);
                double stockage=Double.parseDouble(stock);
                double prix=Double.parseDouble(price);
                // creation de l' objet
                Ingredient ingredient=new Ingredient(id_ing, name, id_unit,stockage,null,prix);
                Connection connection=null;
                Connexion conn=null;
                try {
                    conn = new Connexion();

                    connection = conn.connectePostgres();

                    ingredient.update(connection);

                    ingredient.savePrice(connection);
                } catch (Exception e) {
                    response.getWriter().print("<p>"+e.getMessage()+"</p>");
                    
                } finally {
                    if ( null != connection ) {
                        try {
                            connection.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    response.sendRedirect("Ingredient?action=liste");
                }
        }

    }    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id_ingredient = request.getParameter("id_ingredient");
        if(null != action && action.equals("pageInsert")){
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/insertIngredient.jsp");
            List<Unit> units = null;
            try {
                units=Unit.getAll();
                request.setAttribute("units", units);
                
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().print("<p>"+e.getMessage()+"</p>");

            }
            dispat.forward(request, response);
        }
        /// delete
        else if (null != action && action.equals("delete") && null !=id_ingredient && !id_ingredient.isEmpty()) {
            
            try {
                //  suppresion
                int id_ing=Integer.parseInt(id_ingredient);
                Ingredient ingredient = new Ingredient();
                ingredient.getById(id_ing);
                Connection connection = Connexion.connectePostgres();
                ingredient.deletePrice(connection);
                ingredient.delete(connection);

                connection.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            
            }
            response.sendRedirect("Ingredient?action=liste");
        }
        /// update
        else if (null != action && action.equals("update") && null !=id_ingredient && !id_ingredient.isEmpty()){
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/updateIngredient.jsp");
            List<Unit> units = null;
            
            try {
                int id_ing=Integer.parseInt(id_ingredient);
                Ingredient ingredient = new Ingredient();
                ingredient.getById(id_ing);
                request.setAttribute("ingredient", ingredient);
                units=Unit.getAll();
                request.setAttribute("units", units);
                
            } catch (Exception e) {
                e.printStackTrace();
                
            }
            dispat.forward(request, response);

        }
        /// liste des ingredients
        else if (null != action && action.equals("liste")) {
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/listIngredient.jsp");
            List<Ingredient> ingredients = null;
            String error = "";
            try {
                ingredients=Ingredient.getAll();
                request.setAttribute("ingredients", ingredients);
                
            } catch (Exception e) {
                e.printStackTrace();
                error=e.getMessage();
                
            }
            request.setAttribute("error", error);
            dispat.forward(request, response);
        }
        else {
            response.sendRedirect("/WEB-INF/views/errorPage.jsp");
        }
    }
}
