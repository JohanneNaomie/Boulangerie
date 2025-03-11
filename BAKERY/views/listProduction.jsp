<%@ page import="java.util.List" %>
<%@ page import="material.*" %>

<%
    List<Production> productions = (List<Production>) request.getAttribute("productions");
    List<Ingredient> ingredients = (List<Ingredient>) request.getAttribute("ingredients");
%>


 <%@ include file="sideBar.jsp" %>




 <div class="main-panel">
     <div class="content">
         <div class="page-inner">
             <div class="page-header">
                 <h4 class="page-title">Liste des Productions</h4>
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
                            <form action="Production" method="get">
                                <input type="hidden" name="action" value="listeAll">
                                <div class="form-group">
                                    <label for="date_min">date min</label>
                                    <input class="form-control input-square" type="date" name="date_min" id="date_min">    
                                    <label for="date_max">date max</label>
                                    <input type="date" name="date_max" id="date_max" class="form-control input-square">
                                </div>
                                
                                <div class="form-group">

                                    <label for="ingredient">Ingredient</label>
                                    <select name="id_ingredient" id="ingredient" class="form-control input-square">
                                        <option value="">ingredient</option>
                                        <%         for (Ingredient i : ingredients){ %>
                                        <option value="<% out.print(i.getIdIngredient()); %>"> <% out.print(i.getName()); %></option>
                                        <%  }    %>
                                    </select>
                                </div>
                                <div class="form-control">
                                    <label for="product">name product</label>
                                    <input type="text" name="name_product" id="product" class="form-control input-square" placeholder="product">
                                </div>
                                <div class="form-control">

                                    <label for="qte">quantity min</label>
                                    <input type="number" name="qte" step="0.001" id="qte" class="form-control input-square" placeholder="quantity">
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
                                            <th>Nom</th>
                                            <th>qte</th>
                                            <th>date</th>
                                         </tr>
                                     </thead>
                                     <tfoot>
                                         <tr>
                                            <th>id </th>
                                            <th>Nom</th>
                                            <th>qte</th>
                                            <th>date</th>
             
                                         </tr>
                                     </tfoot>
                                     <tbody>
 
                                         <%
                                         for (Production p : productions){
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

