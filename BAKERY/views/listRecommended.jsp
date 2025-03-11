<%@ page import="java.util.List" %>
<%@ page import="material.*" %>
<%@ page import="vente.*" %>
<%@ page import="util.*" %>
<%

    List<util.Month> months = (List<util.Month>) request.getAttribute("months");
    List<Recommended> recommendeds = (List<Recommended>) request.getAttribute("recommendeds");
    String error = (String) request.getAttribute("error");
%>



<%@ include file="sideBar.jsp" %>




<div class="main-panel">
    <div class="content">
        <div class="page-inner">
            <div class="page-header">
                <h4 class="page-title">Liste des Produits Recommendes</h4>
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
                            <form action="Recommended" method="get">
                                <input type="hidden" name="action" value="listAll">
                                <div class="form-control">
                                    <label for="mois">mois</label>
                                    <select name="id_mois" id="mois" class="form-control input-square">
                                        <option value="">mois</option>
                                        <%
                                            for (util.Month month : months) {
                                                out.print(month.option());
                                            } 
                                        %>
                                    </select>
                                </div>
                                <div class="form-control">

                                    <label for="annee">Annee</label>
                                    <input type="number" name="annee" step="1" id="annee" class="form-control input-square" placeholder="annee">
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
                                <!-- id="multi-filter-select" -->
                                <table  class="display table table-striped table-hover" >
                                    <thead>
                                        <tr>
                                            <th>Nom</th>
                                            <th>categorie</th>
                                            <th>mois</th>
                                            <th>annee</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Nom</th>
                                            <th>categorie</th>
                                            <th>mois</th>
                                            <th>annee</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>

                                        <%
                                        for( Recommended recommended : recommendeds){
                                        %>
                                        <tr>
                                        <%
                                            out.print(recommended.line());
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
