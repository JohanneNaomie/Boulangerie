<%@ page import="java.util.List" %>
<%@ page import="material.*" %>
<%@ page import="vente.*" %>

<%

    List<ProductParfum> products = (List<ProductParfum>) request.getAttribute("pp");
    List<Client> clients = (List<Client>) request.getAttribute("clients");
    List<Seller> sellers = (List<Seller>) request.getAttribute("sellers");

%>



<%@ include file="sideBar.jsp" %>




<div class="main-panel">
    <div class="content">
        <div class="page-inner">
            <div class="page-header">
                <h4 class="page-title">Ventes</h4>
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
                               <h4 class="card-title">Insertion de vente</h4>
                           </div>
                       </div>
                       <div class="card-body">
                            <form action="Sale" method="post">
                                <input type="hidden" name="action" value="insert">
                                <div class="form-group">
                                    <label for="client">Client</label>
                                    <select name="id_client" id="client" class="form-control input-square">
                                        <%
                                            for (Client client : clients) {
                                                out.print(client.option());
                                            } 
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="seller">Vendeur</label>
                                    <select name="id_seller" id="seller" class="form-control input-square">
                                        <%
                                            for (Seller seller : sellers) {
                                                out.print(seller.option());
                                            } 
                                        %>
                                    </select>
                                </div>
                                
                                
                                <div class="form-group">

                                    <label for="productParfum">Produit Parfum </label>
                                    <select name="productParfum" id="productParfum" class="form-control input-square">
                                        <%
                                            for (ProductParfum productParfum : products) {
                                                out.print(productParfum.option());
                                            } 
                                        %>
                                    </select>
                                </div>
                                
                                <div class="form-control">
                                    <label for="qte">Quantite </label>
                                    <input type="number" step="1" class="form-control input-square" name="qte" id="qte">
                                </div>

                                <div class="form-control">
                                    <label for="date_sales">Date</label>
                                    <input type="date"  class="form-control input-square" name="date_sales" id="date_sales">
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

