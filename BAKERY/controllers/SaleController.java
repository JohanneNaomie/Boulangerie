package controllers;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import material.*;
import vente.*;
import connexion.Connexion;
@WebServlet("/Sale")
public class SaleController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id_client = request.getParameter("id_client");
        String productParfum = request.getParameter("productParfum");
        String qte = request.getParameter("qte");
        String id_seller = request.getParameter("id_seller");
        String date_sales = request.getParameter("date_sales");

        if (null != action && action.equals("insert") &&
            null != id_client && !id_client.isEmpty() &&
            null != id_seller && !id_seller.isEmpty() &&
            null != productParfum && !productParfum.isEmpty() &&
            null != qte && !qte.isEmpty() && 
            null != date_sales && !date_sales.isEmpty() ) 
             
        {
            ///parse 
            int client = Integer.parseInt(id_client);
            int seller = Integer.parseInt(id_seller);
            int id_pp = Integer.parseInt(productParfum);
            double quantity = Double.parseDouble(qte);
            java.sql.Date date = java.sql.Date.valueOf(date_sales);
            // creation de l' objet
            Connection connection=null;
            
            try {
                Sale sale = new Sale(0, 0, id_pp, 0, quantity, 0, date, client, seller);
                sale.setPrice();
                connection = Connexion.connectePostgres();
                connection.setAutoCommit(false);
                sale.save(connection);
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
                response.sendRedirect("Sale?action=listAll");

            }

                          


        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String name_product = request.getParameter("name_product");
        String id_category = request.getParameter("id_category");
        String id_parfum = request.getParameter("id_parfum");
        String date_min = request.getParameter("date_min");
        String date_max = request.getParameter("date_max");
        String date = request.getParameter("date");
        String client = request.getParameter("client");

        
        
        if(null != action && action.equals("listAll")){
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/listSale.jsp");
            
            try {
                List<Sale> sales = Sale.getFiltre(name_product,id_category,id_parfum,date_min,date_max,date,client);    
                request.setAttribute("sales", sales);
                List<Category> categories=Category.getAll();
                request.setAttribute("categories", categories);
                
                List<Parfum> parfums = Parfum.getAll();
                request.setAttribute("parfums", parfums);
            } catch (Exception e) {
                e.printStackTrace();
            }
            dispat.forward(request, response);
        }else if (null != action && action.equals("pageInsert")){
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/insertSale.jsp");
            try {
                List<ProductParfum> pp=ProductParfum.getAlls();
                request.setAttribute("pp", pp);
                List<Client> clients = Client.getAll();
                request.setAttribute("clients", clients);   
                List<Seller> sellers = Seller.getAll();
                request.setAttribute("sellers", sellers);             

            } catch (Exception e) {
                // TODO: handle exception
            }
            dispat.forward(request, response);
        }
        
    }
}
