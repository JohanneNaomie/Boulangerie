<%@ page import="java.util.List" %>
<%@ page import="material.*" %>
<%
    Ingredient ingredient = (Ingredient) request.getAttribute("ingredient");
    List<Unit> units = (List<Unit>) request.getAttribute("units");
    
%>
<!-- 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Ingredient</title>
</head>
<body>
    <form action="Ingredient" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id_ingredient" value="<% out.print(ingredient.getIdIngredient()); %>">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="<% out.print(ingredient.getName()); %>" required><br><br>

        <label for="unit">unit: (<% out.print(ingredient.getUnit().getName()); %>)</label>
        <select name="unit" id="unit">
            <%
                for (Unit unit : units) {
                    out.print(unit.option());
                } 
            %>
        </select><br><br>
        <label for="price">price: </label>
        <input type="number" step="0.001" name="price" id="price" >
        
        <input type="hidden" step="0.01" name="stock" id="stock" value="<% out.print(ingredient.getStock()); %>">

        
        <button type="submit">Update Ingredient</button>
    </form>
</body>
</html> -->





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
                        <a href="Ingredient?action=pageInsert">Insertion</a>
                    </li>
                    
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                   <div class="card">
                       <div class="card-header">
                           <div class="d-flex align-items-center">
                               <h4 class="card-title">Update Ingredient</h4>
                           </div>
                       </div>
                       <div class="card-body">
                            <form action="Ingredient" method="post">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="id_ingredient" value="<% out.print(ingredient.getIdIngredient()); %>">

                                <div class="form-group">
                                    <label for="name">Name</label>
                                    <input type="text" id="name" name="name" class="form-control input-square" value="<% out.print(ingredient.getName()); %>" required placeholder="name">
                            
                                </div>
                                
                                <div class="form-group">

                                    <label for="unit">Unit (<% out.print(ingredient.getUnit().getName()); %>)</label>
                                    <select name="unit" id="unit" class="form-control input-square">
                                        <%
                                            for (Unit unit : units) {
                                                if(unit.getIdUnit() == ingredient.getIdUnit())
                                                out.print(unit.option());
                                            } 
                                            for (Unit unit : units) {
                                                if(unit.getIdUnit() != ingredient.getIdUnit())
                                                out.print(unit.option());
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-control">
                                    <label for="stock">Stock </label>
                                    <input type="number" step="0.01" readonly class="form-control input-square" name="stock" id="stock" value="<% out.print(ingredient.getStock()); %>">
                                </div>
                                <div class="form-control">

                                    <label for="price">Price </label>
                                    <input type="number" step="0.001" class="form-control input-square" name="price" id="price" value="<% out.print(ingredient.getPrice()); %>">
                                    
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

