<%@ page import="java.util.List" %>
<%@ page import="material.*" %>
<%@ page import="vente.*" %>

<%

    Client acheteur = (Client) request.getAttribute("acheteur");
    Client regulier = (Client) request.getAttribute("regulier");
    Double achat = (Double) request.getAttribute("achat");
    Integer nb = (Integer) request.getAttribute("nb");
    List<Object []> productQuantity = (List<Object []>) request.getAttribute("productQuantity") ;
    int i=0; 
%>



<%@ include file="sideBar.jsp" %>




<div class="main-panel">
    <div class="content">
        <div class="page-inner">
            <div class="page-header">
                <h4 class="page-title">Statistique</h4>
                <ul class="breadcrumbs">
                    <li class="nav-home">
                        <a href="#">
                            <i class="flaticon-home"></i>
                        </a>
                    </li>
                    
                </ul>
            </div>
            <div class="row">
                
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <div class="d-flex align-items-center">
                                <h4 class="card-title">Statistique de la clientele</h4>
                            </div>
                        </div>
                        <div class="card-body d-flex">
                            <div class="col-md-6">                                    
                                <h4 class="red">Client avec le plus d'achat</h4>
                                <h1><%=acheteur.getName()%></h1>
                                <h3>avec <b><%=achat%> </b>Produits Achetes</h3>
                            </div>
                            <div class="col-md-6">                      
                                <h4 class="blue">Client avec le plus de passage</h4>
                                <h1><%=regulier.getName()%></h1>
                                <h3>avec <b><%=nb%> </b>Passages</h3>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <div class="d-flex align-items-center">
                                <h4 class="card-title">Statistique de produit vendu</h4>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table  class="display table table-striped table-hover" >
                                    <thead>
                                        <tr>
                                            <th>Produit</th>
                                            <th>Quantite</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                        for(Object[] object : productQuantity){
                                        %>
                                        <tr>
                                            <td>
                                                <%
                                                    Product p = (Product) object [0];
                                                    out.print(p.getName());    
                                                %>
                                            </td>
                                            <td>
                                                <%
                                                    Integer qte = (Integer) object[1];
                                                    out.print(qte);    
                                                %>
                                            </td>
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
                
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <div class="d-flex align-items-center">
                                <h4 class="card-title">Vente de produits</h4>
                            </div>
                        </div>
                        <div class="card-body">
                            
                            <canvas id="myChart" width="400" height="400"></canvas>
	                        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
                                        
                            <script>
                                var ctx = document.getElementById('myChart').getContext('2d');
                                new Chart(ctx, {
                                    type: 'pie',
                                    data: {
                                        labels: [ <% i=0; 
                                        for(Object[] object : productQuantity){ 
                                            if(i!=0) {out.print(",");} 
                                            Product p = (Product) object [0]; 
                                            out.print("'"+p.getName()+"'"); 
                                            i++;  
                                        }
                                        %> ],
                                        datasets: [{
                                            label: '# of People',
                                            
                                            data: [ <% i=0; 
                                            for(Object[] object : productQuantity){ 
                                                if(i!=0) {out.print(",");} 
                                                Integer qte = (Integer) object[1];
                                                out.print(qte);
                                                i++;  
                                            }
                                            %> ],
                                            backgroundColor: [
                                            <% i=0; 
                                            for(Object[] object : productQuantity){ 
                                                if(i!=0) {out.print(",");} 
                                                Integer i1 = (Integer) object[2];
                                                Integer i2 = (Integer) object[3];
                                                Integer i3 = (Integer) object[4];
                                            %>
                                            'rgba(<%=i1%>,<%=i2%>,<%=i3%>, 0.8)'
                                            <%
                                                i++;  
                                            }
                                            %> 
                                            ],
                                            borderColor: [
                                            <% i=0; 
                                            for(Object[] object : productQuantity){ 
                                                if(i!=0) {out.print(",");} 
                                                Integer i1 = (Integer) object[2];
                                                Integer i2 = (Integer) object[3];
                                                Integer i3 = (Integer) object[4];
                                            %>
                                            'rgba(<%=i1%>,<%=i2%>,<%=i3%>, 1)'
                                            <%
                                                i++;  
                                            }
                                            %>
                                            ],
                                            borderWidth: 1
                                        }]
                                    },
                                    options: {
                                        responsive: false,
                                        plugins: {
                                            legend: {
                                                position: 'bottom',
                                            },
                                            title: {
                                                display: false,
                                            }
                                        },
                                        animation: {
                                            duration: 1000
                                        },
                                        tooltips: {
                                            callbacks: {
                                                label: function(tooltipItem, data) {
                                                    var dataset = data.datasets[tooltipItem.datasetIndex];
                                                    return dataset.label + ': ' + dataset.data[tooltipItem.index] + ' (' + 
                                                        Math.round((dataset.data[tooltipItem.index] / (6700 + 10300) * 100)) +
                                                        '%)';
                                                }
                                            }
                                        }
                                    }
                                });
                            </script>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</div>
<%@ include file="custom.jsp" %>

