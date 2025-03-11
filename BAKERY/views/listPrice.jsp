<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="material.Product" %>
<%

    List<Product> products = (List<Product>) request.getAttribute("products");
    String error = (String) request.getAttribute("error");
    List<Product> allProducts = (List<Product>) request.getAttribute("allProducts");

%>



<%@ include file="sideBar.jsp" %>




<div class="main-panel">
    <div class="content">
        <div class="page-inner">
            <div class="page-header">
                <h4 class="page-title">Liste Product Prices</h4>
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
                    <li class="separator">
                        <i class="flaticon-right-arrow"></i>
                    </li>
                    <li class="nav-item">
                        <a href="Product?action=pageInsert">Insertion</a>
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
                            <form action="Product" method="get">
                                <input type="hidden" name="action" value="price">
                                <div class="form-group">
                                    <label for="date_min">date min</label>
                                    <input class="form-control input-square" type="date" name="date_min" id="date_min">    
                                    <label for="date_max">date max</label>
                                    <input type="date" name="date_max" id="date_max" class="form-control input-square">
                                </div>
                                
                                <div class="form-group">

                                    <label for="product">Product</label>
                                    <select name="id_product" id="product" class="form-control input-square">
                                        <option value="">product</option>
                                        <%         for (Product i : allProducts){ %>
                                        <option value="<% out.print(i.getIdProduct()); %>"> <% out.print(i.getName()); %></option>
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
                                            <th>Name</th>
                                            <th>price</th>
                                            <th>Date</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Name</th>
                                            <th>price</th>
                                            <th>Date</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>

                                        <%
                                        for( Product product : products){
                                        %>
                                        <tr>
                                            <td><%=product.getName()%></td>
                                            <td><%=product.getPrice()%></td>
                                            <td><%=product.getDate()%></td>
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
