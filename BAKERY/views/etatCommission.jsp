<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>

<%
    HashMap<String, Double> nombre = (HashMap<String, Double>) request.getAttribute("nombre");
    List<String> sexe = (List<String>) request.getAttribute("sexe");
        double somme = 0;
        for (String s : sexe){
            somme+=nombre.get(s);
        }
%>


 <%@ include file="sideBar.jsp" %>




 <div class="main-panel">
     <div class="content">
         <div class="page-inner">
             <div class="page-header">
                 <h4 class="page-title">Etat des commissions</h4>
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
                                <h4 class="card-title">Filter </h4>
                            </div>
                        </div>
                        <div class="card-body">
                            <form action="Commission" method="get">
                                <input type="hidden" name="action" value="etat">
                                <div class="form-group">
                                    <label for="date_min">date min</label>
                                    <input class="form-control input-square" type="date" name="date_min" id="date_min">    
                                    <label for="date_max">date max</label>
                                    <input type="date" name="date_max" id="date_max" class="form-control input-square">
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
                                
                                 <table  class="display table table-striped table-hover" >
                                     <thead>
                                         <tr>
                                            <th>Sexe</th>
                                            <th>commission</th>
                                            <th>pourcentage</th>
                                         </tr>
                                     </thead>
                                     <tbody>
                                        
                                         <%
                                         for (String s : sexe){
                                         %>
                                         <tr>
                                            <td><%=s%></td>
                                            <td><%=nombre.get(s)%></td>
                                            <td><%
                                                if(nombre.get(s)==0)
                                                out.print(0);
                                                else
                                                out.print(nombre.get(s)/somme*100);
                                                %> 
                                            %</td>
                                         </tr>
                                         <%
                                         }
                                         %>
                                     </tbody>
                                 </table>
                                 
                             </div>

                            <canvas id="myChart" width="200" height="200"></canvas>
	                        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
                                        
                            <script>
                                var ctx = document.getElementById('myChart').getContext('2d');
                                new Chart(ctx, {
                                    type: 'pie',
                                    data: {
                                        labels: [ 'Female','Male'],
                                        datasets: [{
                                            label: '# of People',
                                            // data: [5400 ,1624] ,
                                            data: [ <%= nombre.get("female") %>,<%= nombre.get("male")%>],
                                            backgroundColor: [
                                                'rgba(255, 99, 132, 0.8)',
                                                'rgba(54, 162, 235, 0.8)'
                                            ],
                                            borderColor: [
                                                'rgba(255, 99, 132, 1)',
                                                'rgba(54, 162, 235, 1)'
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

