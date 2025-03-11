<%@ page import="java.util.List" %>
<%@ page import="vente.*" %>

<%
    List<Commission> commissions = (List<Commission>) request.getAttribute("commissions");
    List<Seller> sellers = (List<Seller>) request.getAttribute("sellers");
    double total=0;
%>


 <%@ include file="sideBar.jsp" %>




 <div class="main-panel">
     <div class="content">
         <div class="page-inner">
             <div class="page-header">
                 <h4 class="page-title">Liste des Commissions</h4>
                 <ul class="breadcrumbs">
                     <li class="nav-home">
                         <a href="#">
                             <i class="flaticon-home"></i>
                         </a>
                     </li>
                 </ul>
             </div>
             <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <div class="d-flex align-items-center">
                                <h4 class="card-title">Filter</h4>
                            </div>
                        </div>
                        <div class="card-body">
                            <form action="Commission" method="get">
                                <input type="hidden" name="action" value="listAll">
                                <div class="form-group">
                                    <label for="date_min">date min</label>
                                    <input class="form-control input-square" type="date" name="date_min" id="date_min">    
                                    <label for="date_max">date max</label>
                                    <input type="date" name="date_max" id="date_max" class="form-control input-square">
                                </div>
                                
                                <div class="form-group">

                                    <label for="Seller">Vendeur</label>
                                    <select name="id_seller" id="Seller" class="form-control input-square">
                                        <%         for (Seller i : sellers){ %>
                                        <%= i.option()%>
                                        <%  }    %>
                                    </select>
                                </div>
                        
                                <input type="submit" value="valider" class="btn btn-primary btn-round">
                            </form>         
                        </div>
                    </div>
                </div>
                 <div class="col-md-12">
                     <div class="card">
                         <div class="card-header">
                             <h4 class="card-title">Multi Filter Select</h4>
                         </div>
                         <div class="card-body">
                             <div class="table-responsive">
                                <%
                                if(null!=commissions){
                                %>  
                                 <table  class="display table table-striped table-hover" >
                                     <thead>
                                         <tr>
                                            <th>vendeur</th>
                                            <th>commission</th>
                                            <th>mois</th>
                                            <th>annee</th>
                                         </tr>
                                     </thead>
                                     <tbody>
                                        
                                         <%
                                         for (Commission p : commissions){
                                         %>
                                         <tr>
                                         <%
                                         out.print(p.line());
                                         total+=p.getCommission();
                                         %>
                                         </tr>
                                         <%
                                         }
                                         %>
                                         <tr>
                                            <td>total</td>
                                            <td><%=total%></td>
                                            <td></td>
                                            <td></td>
                                         </tr>
                                     </tbody>
                                 </table>
                                 <%
                                    }else {
                                %>
                                <h1>Pas de vendeur selectionner</h1>
                                <%
                                    }
                                 %>
                             </div>
                         </div>
                     </div>
                 </div>
 
                 
             </div>
         </div>
     </div>
     
 </div>
 <%@ include file="custom.jsp" %>

