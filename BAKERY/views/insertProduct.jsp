

<%@ include file="sideBar.jsp" %>




<div class="main-panel">
    <div class="content">
        <div class="page-inner">
            <div class="page-header">
                <h4 class="page-title">Produit</h4>
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
                        <a href="#">Insertion</a>
                    </li>
                    
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                   <div class="card">
                       <div class="card-header">
                           <div class="d-flex align-items-center">
                               <h4 class="card-title">Insertion de Produit</h4>
                           </div>
                       </div>
                       <div class="card-body">
                            <form action="Product" method="post">
                                <input type="hidden" name="action" value="insert">
                                <div class="form-group">
                                    <label for="name">Nom</label>
                                    <input type="text" id="name" name="name" class="form-control input-square" required placeholder="name">
                                </div>
                                <div class="form-control">

                                    <label for="stock">Durree de Conservation: </label>
                                    <input type="number" step="0.01" class="form-control input-square" name="conservation_time_h" id="conservation_time_h">
                            
                                </div>
                                <div class="form-control">

                                    <label for="price">Prix </label>
                                    <input type="number" step="0.001" class="form-control input-square" name="price" id="price">
                                    
                                </div>
                                <div class="form-control">
                                    <label for="date_time">Date</label>
                                    <input type="date"  class="form-control input-square" name="date_time" id="date_time">
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

