<%@ page import="material.*" %>
<%@ page import="java.sql.Date" %>

<%
    Product product = (Product) request.getAttribute("product");
    
%>




<%@ include file="sideBar.jsp" %>




<div class="main-panel">
    <div class="content">
        <div class="page-inner">
            <div class="page-header">
                <h4 class="page-title">Product</h4>
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
                        <a href="Product?action=liste#multi-filter-select">Multi filter</a>
                    </li>
                    <li class="separator">
                        <i class="flaticon-right-arrow"></i>
                    </li>
                    <li class="nav-item">
                        <a href="Product?action=liste#add-row">Update Delete</a>
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
                               <h4 class="card-title">Update Product</h4>
                           </div>
                       </div>
                       <div class="card-body">
                            <form action="Product" method="post">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="id_product" value="<%=product.getIdProduct()%>">
                                <div class="form-group">
                                    <label for="name">Name</label>
                                    <input type="text" id="name" name="name" class="form-control input-square" required placeholder="name" value="<% out.print(product.getName()); %>">
                                </div>
                                <div class="form-control">

                                    <label for="stock">Conservation Time H: </label>
                                    <input type="number" step="0.01" class="form-control input-square" name="conservation_time_h" id="conservation_time_h" value="<% out.print(product.getConservatonTimeH()); %>">
                            
                                </div>
                                <div class="form-control">

                                    <label for="price">Price </label>
                                    <input type="number" step="0.01" class="form-control input-square" name="price" id="price" value="<% out.print(product.getPrice());  %>">
                                    
                                </div>
                                <div class="form-control">
                                    <label for="date_time">Date time </label>
                                    <input type="date"  class="form-control input-square" name="date_time" id="date_time" value="<% out.print(product.getDate());%>">
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