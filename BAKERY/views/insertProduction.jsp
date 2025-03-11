<%@ page import="java.util.List" %>
<%@ page import="material.*" %>
<%

    List<Product> products = (List<Product>) request.getAttribute("products");
    List<Parfum> parfums = (List<Parfum>) request.getAttribute("parfums");
%>



<%@ include file="sideBar.jsp" %>




<div class="main-panel">
    <div class="content">
        <div class="page-inner">
            <div class="page-header">
                <h4 class="page-title">Production</h4>
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
                               <h4 class="card-title">Insertion Production</h4>
                           </div>
                       </div>
                       <div class="card-body">
                            <form action="Production" method="post">
                                <input type="hidden" name="action" value="insert">
                                <div class="form-group">

                                    <label for="id_product">Produit </label>
                                    <select name="id_product" id="id_product" class="form-control input-square">
                                        <%
                                            for (Product product : products) {
                                                out.print(product.option());
                                            } 
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">

                                    <label for="id_parfum">Parfum </label>
                                    <select name="id_parfum" id="id_parfum" class="form-control input-square">
                                        <%
                                            for (Parfum parfum : parfums) {
                                                out.print(parfum.option());
                                            } 
                                        %>
                                    </select>
                                </div>
                                <div class="form-control">
                                    <label for="qte">Quantity </label>
                                    <input type="number" step="1" class="form-control input-square" name="qte" id="qte">
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

