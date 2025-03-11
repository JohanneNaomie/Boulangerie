<%@ page import="java.util.List" %>
<%@ page import="material.Product" %>
<%@ page import="util.Month" %>

<%

    List<Product> products = (List<Product>) request.getAttribute("products");
    List<util.Month> months = (List<util.Month>) request.getAttribute("months");
    String error = (String) request.getAttribute("error");
%>


<%@ include file="sideBar.jsp" %>




<div class="main-panel">
    <div class="content">
        <div class="page-inner">
            <div class="page-header">
                <h4 class="page-title">Recommandations</h4>
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
                               <h4 class="card-title">Insertion de Recommandations</h4>
                           </div>
                       </div>
                       <div class="card-body">
                        <!-- changer -->
                            <form action="Recommended" method="post">
                                <input type="hidden" name="action" value="insert">
                                <div class="form-group">
                                    <label for="product">produit </label>
                                    <select name="id_product" id="product" class="form-control input-square" required>
                                        <%
                                        for( Product product : products){
                                        %>
                                        <option value="<% out.print(product.getIdProduct()); %>"><% out.print(product.getName()); %></option>
                                        
                                        <%
                                        }
                                        %>
                                    </select>
                                </div>
                                <div class="form-control">

                                    <label for="mois">mois </label>
                                    <select name="id_mois" id="mois" class="form-control input-square" required>
                                        <%
                                            for (util.Month month : months) {
                                                out.print(month.option());
                                            } 
                                        %>
                                    </select>
                                </div>
                                <div class="form-control">

                                    <label for="annee">Annee </label>
                                    <input type="number" step="1" class="form-control input-square" name="annee" id="annee" required>
                                    
                                </div>
                        
                                <input type="submit" value="valider" class="btn btn-primary btn-round">
                           </form>         
                       </div>
                   </div>
                </div>
                
            </div>
        </div>
    </div>
    
</div>
<%@ include file="custom.jsp" %>

