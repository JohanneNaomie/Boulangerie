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

import vente.*;
import connexion.Connexion;
import java.util.HashMap;

@WebServlet("/Commission")

public class CommissionController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String date_min = request.getParameter("date_min");
        String date_max = request.getParameter("date_max");
        String id_seller = request.getParameter("id_seller");

        if(null != action && action.equals("listAll")){
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/listCommission.jsp");

            try {
                List<Commission> commissions = Commission.getFiltre(date_min,date_max,id_seller);
                request.setAttribute("commissions", commissions);

                List<Seller> sellers = Seller.getAll();
                request.setAttribute("sellers", sellers);

                
                
            } catch (Exception e) {

            }
            dispat.forward(request, response);
        }else if(null != action && action.equals("etat")){
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/etatCommission.jsp");
            try {
                HashMap<String, Double> nombre = Commission.getEtatCommissionBySexe(date_min, date_max);
                request.setAttribute("nombre", nombre);

                List<String> sexe = new ArrayList<String>();
                sexe.add("male");
                sexe.add("female");
                request.setAttribute("sexe",sexe);

            } catch (Exception e) {
                
            }
            dispat.forward(request, response);
        }
    }
}
