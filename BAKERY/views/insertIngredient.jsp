<%@ page import="java.util.List" %>
<%@ page import="material.Unit" %>
<%

    List<Unit> units = (List<Unit>) request.getAttribute("units");
%>



<%@ include file="sideBar.jsp" %>




<div class="main-panel">
    <div class="content">
        <div class="page-inner">
            <div class="page-header">
                <h4 class="page-title">Ingredient</h4>
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
                        <a href="Ingredient?action=liste#multi-filter-select">Multi filter</a>
                    </li>
                    <li class="separator">
                        <i class="flaticon-right-arrow"></i>
                    </li>
                    <li class="nav-item">
                        <a href="Ingredient?action=liste#add-row">Update Delete</a>
                    </li>
                    <li class="separator">
                        <i class="flaticon-right-arrow"></i>
                    </li>
                    <li class="nav-item">
                        <a href="#">Insertion</a>
                    </li>
                    
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                   <div class="card">
                       <div class="card-header">
                           <div class="d-flex align-items-center">
                               <h4 class="card-title">Insertion Ingredient</h4>
                           </div>
                       </div>
                       <div class="card-body">
                            <form action="Ingredient" method="post">
                                <input type="hidden" name="action" value="insert">
                                <div class="form-group">
                                    <label for="name">Nom</label>
                                    <input type="text" id="name" name="name" class="form-control input-square" required placeholder="name">
                            
                                </div>
                                
                                <div class="form-group">

                                    <label for="unit">Unite </label>
                                    <select name="unit" id="unit" class="form-control input-square">
                                        <%
                                            for (Unit unit : units) {
                                                out.print(unit.option());
                                            } 
                                        %>
                                    </select>
                                </div>
                                    <input type="hidden" step="0.01" value="0" class="form-control input-square" name="stock" id="stock">
                                
                                <div class="form-control">

                                    <label for="price">Prix </label>
                                    <input type="number" step="0.001" class="form-control input-square" name="price" id="price">
                                    
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

