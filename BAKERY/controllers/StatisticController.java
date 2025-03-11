package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import vente.*;
@WebServlet("/Statistic")
public class StatisticController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String date_min = request.getParameter("date_min");
        String date_max = request.getParameter("date_max");


        if (null != action && action.equals("stat")) {
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/statistic.jsp");

            try {
                Client acheteur = new Client();
                request.setAttribute("acheteur", acheteur);
                double achat = acheteur.lePlusAchateur(date_min, date_max);
                request.setAttribute("achat", achat);

                Client regulier = new Client();
                request.setAttribute("regulier", regulier);
                int nb = regulier.lePlusRegulier(date_min, date_max);
                request.setAttribute("nb", nb);

                List<Object []> productQuantity = Sale.productQuantity(date_min, date_max);
                request.setAttribute("productQuantity", productQuantity);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            dispat.forward(request, response);
        }
    }
}
