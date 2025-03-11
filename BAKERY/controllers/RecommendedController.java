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
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import material.*;
import util.*;
import vente.*;
import connexion.Connexion;
@WebServlet("/Recommended")
public class RecommendedController extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id_product = request.getParameter("id_product");
        String id_mois = request.getParameter("id_mois");
        String annee = request.getParameter("annee");
        if (    
            null!=action && action.equals("insert") && 
            null!=id_mois && !id_mois.isEmpty() &&
            null!=annee && !annee.isEmpty() ){
                // parse 
                int id_p = Integer.parseInt(id_product);
                int mois = Integer.parseInt(id_mois);
                int an = Integer.parseInt(annee);
                // creation de l' objet
                Connection connection=null;
                Connexion conn=null;
                try {
                    Recommended recommended = new Recommended();
                    recommended.getById(id_p);
                    util.Month month = new util.Month();
                    month.getById(mois);
                    recommended.setMonth(month);
                    recommended.setAnnee(an);
                    conn = new Connexion();

                    connection = conn.connectePostgres();

                    recommended.addRecommended(connection);
                } catch (Exception e) {

                    
                } finally {
                    if ( null != connection ) {
                        try {
                            connection.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    response.sendRedirect("Recommended?action=listAll");
                }
        }
      

    } 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id_mois = request.getParameter("id_mois");
        String annee = request.getParameter("annee");

        if(null != action && action.equals("pageInsert")){
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/insertRecommended.jsp");
            try {
                List<Product> products = Product.getAll();
                request.setAttribute("products", products);
                List<util.Month> months = util.Month.getAll();
                request.setAttribute("months", months);
            } catch (Exception e) {
                e.printStackTrace();
            }
            dispat.forward(request, response);
            
        }
                
        
        if(null != action && action.equals("listAll")){
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/listRecommended.jsp");
            
            try {
                List<Recommended> recommendeds = Recommended.getRecommendedProducts(id_mois,annee);
                request.setAttribute("recommendeds", recommendeds);
                List<util.Month> months = util.Month.getAll();
                request.setAttribute("months", months);
            } catch (Exception e) {
                e.printStackTrace();
            }
            dispat.forward(request, response);
        }
        
    }
}
