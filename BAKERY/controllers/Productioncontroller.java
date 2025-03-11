
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
@WebServlet("/Production")
public class Productioncontroller extends  HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id_product = request.getParameter("id_product");
        String id_parfum = request.getParameter("id_parfum");
        String qte = request.getParameter("qte");

        if (null != action && action.equals("insert") &&
            null != id_product && !id_product.isEmpty() &&
            null != qte && !qte.isEmpty() &&
            null != id_parfum && !id_parfum.isEmpty()  ) 
        {
            ///parse 
            int product = Integer.parseInt(id_product);
            double quantity = Double.parseDouble(qte);
            int parfum = Integer.parseInt(id_parfum);
            Connection connection=null;
            try {
                Production production = new Production(0, product, quantity, null);

                connection = Connexion.connectePostgres();
                connection.setAutoCommit(false);
                production.save(connection);

                ProductParfum productParfum = new ProductParfum();

                productParfum.getById(product);
                productParfum.setParfum(parfum);
                productParfum.setQuantity(quantity);
                if(!productParfum.used())
                productParfum.save(connection);

                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } finally {
                if ( null != connection ) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                response.sendRedirect("Production?action=listAll");

            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String date_min = request.getParameter("date_min");
        String date_max = request.getParameter("date_max");
        String id_ingredient = request.getParameter("id_ingredient");
        String name_product = request.getParameter("name_product");

        String qte = request.getParameter("qte");
        
        if(null != action && action.equals("listAll")){
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/listProduction.jsp");
            
            try {
                List<Production> productions = Production.getFiltre(date_min, date_max, id_ingredient, name_product, qte);    
                request.setAttribute("productions", productions);
                List<Ingredient> ingredients=Ingredient.getAll();
                request.setAttribute("ingredients", ingredients);
            } catch (Exception e) {
                e.printStackTrace();
            }
            dispat.forward(request, response);
        }
        else if(null != action && action.equals("pageInsert")){
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/insertProduction.jsp");
            try {
                List<Product> products=Product.getAll();
                request.setAttribute("products", products);
                List<Parfum> parfums = Parfum.getAll();
                request.setAttribute("parfums", parfums);

            } catch (Exception e) {
                e.printStackTrace();
            }
            dispat.forward(request, response);
        }

    }

}
