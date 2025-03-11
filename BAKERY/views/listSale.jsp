<%@ page import="java.util.List" %>
<%@ page import="material.*" %>
<%@ page import="vente.*" %>


<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    List<Parfum> parfums = (List<Parfum>) request.getAttribute("parfums");
    List<Sale> sales = (List<Sale>) request.getAttribute("sales");
%>


<%@ include file="sideBar.jsp" %>



<div class="main-panel">
    <div class="content">
        <div class="page-inner">
            <div class="page-header">
                <h4 class="page-title">Liste des Ventes</h4>
                <ul class="breadcrumbs">
                    <li class="nav-home">
                        <a href="#">
                            <i class="flaticon-home"></i>
                        </a>
                    </li>
                    <li class="separator">
                        <i class="flaticon-right-arrow"></i>
                    </li>
                    <li class="nav-item">
                        <a href="#multi-filter-select">Multi filter</a>
                    </li>
                    <li class="separator">
                        <i class="flaticon-right-arrow"></i>
                    </li>
                    <li class="nav-item">
                        <a href="#add-row">Update Delete</a>
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
                           <form action="Sale" method="get">
                               <input type="hidden" name="action" value="listeAll">
                                <div class="form-control">
                                    <label for="product">name product</label>
                                    <input type="text" name="name_product" id="product" class="form-control input-square" placeholder="product">
                                </div>
                                <div class="form-control">
                                    <label for="client">name client</label>
                                    <input type="text" name="client" id="client" class="form-control input-square" placeholder="product">
                                </div>

                               <div class="form-group">
                                   <label for="date_min">date min</label>
                                   <input class="form-control input-square" type="date" name="date_min" id="date_min">    
                                   <label for="date_max">date max</label>
                                   <input type="date" name="date_max" id="date_max" class="form-control input-square">
                                   <label for="date">date exact</label>
                                   <input type="date" name="date" id="date" class="form-control input-square">
                                    
                                </div>
                               
                                
                               <div class="form-group">

                                    <label for="category">category</label>
                                    <select name="id_category" id="category" class="form-control input-square">                                       
                                        <option value="">category</option>
                                       
                                        <%         for (Category i : categories){ %>
                                       <option value="<% out.print(i.getIdCategory()); %>"> <% out.print(i.getName()); %></option>
                                       <%  }    %>
                                   </select>
                               </div>
                               
                               <div class="form-group">

                                <label for="parfum">parfum</label>
                                <select name="id_parfum" id="parfum" class="form-control input-square">                                       
                                    <option value="">parfum</option>
                                   
                                    <%         for (Parfum i : parfums){ %>
                                   <option value="<% out.print(i.getIdParfum()); %>"> <% out.print(i.getName()); %></option>
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
                                <table id="multi-filter-select" class="display table table-striped table-hover" >
                                    <thead>
                                        <tr>
                                            <th>id </th>
                                            <th>client</th>
                                            <th>Nom du produit</th>
                                            <th>Categorie</th>
                                            <th>Parfum</th>
                                            <th>quantite</th>
                                            <th>date</th>
                                            <th>prix total</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>id </th>
                                            <th>client</th>
                                            <th>Nom du produit</th>
                                            <th>Categorie</th>
                                            <th>Parfum</th>
                                            <th>quantite</th>
                                            <th>date</th>
                                            <th>prix total</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>

                                        <%
                                        for (Sale p : sales){
                                        %>
                                        <tr>
                                        <%
                                        out.print(p.line());
                                        %>
                                        </tr>
                                        <%
                                        }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                
            </div>
        </div>
    </div>
    
</div>
<%@ include file="custom.jsp" %>

