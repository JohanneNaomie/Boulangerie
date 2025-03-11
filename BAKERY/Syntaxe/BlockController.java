// package controllers;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.RequestDispatcher;


// import java.io.IOException;
// import java.sql.Connection;
// import java.sql.SQLException;

// // import materials.Block;
// import connexion.Connexion;

// @WebServlet("/block")
// public class BlockController extends HttpServlet {

//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         // Check if all parameters are provided and action is "insert"
//         String action = request.getParameter("action");
//         String name = request.getParameter("name");
//         String longueurStr = request.getParameter("longueur");
//         String largeurStr = request.getParameter("largeur");
//         String hauteurStr = request.getParameter("hauteur");
//         String prixRevientStr = request.getParameter("prixRevient");

//         if (action != null && action.equals("insert") &&
//             name != null && !name.isEmpty() &&
//             longueurStr != null && !longueurStr.isEmpty() &&
//             largeurStr != null && !largeurStr.isEmpty() &&
//             hauteurStr != null && !hauteurStr.isEmpty() &&
//             prixRevientStr != null && !prixRevientStr.isEmpty()) {

//             // Parse numerical values
//             double longueur = Double.parseDouble(longueurStr);
//             double largeur = Double.parseDouble(largeurStr);
//             double hauteur = Double.parseDouble(hauteurStr);
//             double prixRevient = Double.parseDouble(prixRevientStr);
            
//             // Create a new Block object
//             Block block = new Block(name, longueur, largeur, hauteur, prixRevient);
//             Connection connection=null;
//             Connexion conn=null;

//             // Save the block to the database
//             try {
//                 // Create a Connexion object with the necessary parameters for PostgreSQL
//                 conn = new Connexion();
                
//                 // Get the Connection object from Connexion
//                 connection = conn.connectePostgres();
                
//                 // Use the connection obtained from connectePostgres()
//                 block.save(connection);
                
//                 // Send success message to the response
//                 response.getWriter().print("<div id='message' class='typewriter'>Block successfully inserted!</div>");
                
//             } catch (SQLException | ClassNotFoundException e) {
//                 // Handle errors
//                 response.getWriter().print("<div id='message' class='typewriter'>Failed to insert block: " + e.getMessage() + "</div>");
//             } catch (Exception e) {
//                 // Handle other exceptions
//                 response.getWriter().print("<div id='message' class='typewriter'>Error: " + e.getMessage() + "</div>");
//             }finally {
//                 // Close the Connection after use, ensuring it's done even if an error occurs
//                 if (connection != null) {
//                     try {
//                         connection.close();
//                     } catch (SQLException e) {
//                         // Handle exception when closing the connection
//                         e.printStackTrace();
//                     }
//                 }
//             }
//         } else {
//             response.getWriter().print("<div id='message' class='typewriter'>Please fill in all fields.</div>");
//         }
//     }

//      @Override
//     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         String action = request.getParameter("action");
//         if (action != null && action.equals("pageInsert")) {
//             RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/insertBlock.jsp");
            
//             dispat.forward(request, response);
//         }
//     }
// }

