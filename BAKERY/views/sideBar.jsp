<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>BAKERY</title>
	<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
	<link rel="icon" href="assets/img/icon.ico" type="image/x-icon"/>
	
	<!-- Fonts and icons -->
	<script src="assets/js/plugin/webfont/webfont.min.js"></script>
	<script>
		WebFont.load({
			google: {"families":["Open+Sans:300,400,600,700"]},
			custom: {"families":["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands"], urls: ['assets/css/fonts.css']},
			active: function() {
				sessionStorage.fonts = true;
			}
		});
	</script>


	<!-- CSS Files -->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/azzara.min.css">
	<!-- CSS Just for demo purpose, don't include it in your project -->
	<link rel="stylesheet" href="assets/css/demo.css">
</head>
<body>
	<div class="wrapper">
		<!--
				Tip 1: You can change the background color of the main header using: data-background-color="blue | purple | light-blue | green | orange | red"
		-->
		<div class="main-header" data-background-color="purple">
			<!-- Logo Header -->
			<div class="logo-header">
				
				<a href="/BAKERY_FINAL" class="logo" style="font-weight: bolder; letter-spacing : 2px">
					BAKERY
				</a>
				<button class="navbar-toggler sidenav-toggler ml-auto" type="button" data-toggle="collapse" data-target="collapse" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon">
						<i class="fa fa-bars"></i>
					</span>
				</button>
				<button class="topbar-toggler more"><i class="fa fa-ellipsis-v"></i></button>
				<div class="navbar-minimize">
					<button class="btn btn-minimize btn-rounded">
						<i class="fa fa-bars"></i>
					</button>
				</div>
			</div>
			<!-- End Logo Header -->

			<!-- Navbar Header -->
			<nav class="navbar navbar-header navbar-expand-lg">
				
				<div class="container-fluid">
					<div class="collapse" id="search-nav">
						<form class="navbar-left navbar-form nav-search mr-md-3">
							<div class="input-group">
								<div class="input-group-prepend">
									<button type="submit" class="btn btn-search pr-1">
										<i class="fa fa-search search-icon"></i>
									</button>
								</div>
								<input type="text" placeholder="Search ..." class="form-control">
							</div>
						</form>
					</div>
					
					
				</div>
			</nav>
			<!-- End Navbar -->
		</div>
		<!-- Sidebar -->
		<div class="sidebar">
			
			<div class="sidebar-wrapper scrollbar-inner">
				<div class="sidebar-content">
					<ul class="nav">
						<li class="nav-item">
							<a href="/BAKERY_FINAL">
								<i class="fas fa-home"></i>
								<p>Dashboard</p>
								<span class="badge badge-count">5</span>
							</a>
						</li>
						<li class="nav-section">
							<span class="sidebar-mini-icon">
								<i class="fa fa-ellipsis-h"></i>
							</span>
							<h4 class="text-section">Components</h4>
						</li>
                        
						<li class="nav-item submenu">
							<a data-toggle="collapse" href="#tables">
								<i class="fas fa-table"></i>
								<p>Tables</p>
								<span class="caret"></span>
							</a>
							<div class="collapse" id="tables">
								<ul class="nav nav-collapse">
									<li>
										<a href="Ingredient?action=liste">
											<span class="sub-item">Liste des Ingredient</span>
										</a>
									</li>
									<li>
										<a href="Product?action=liste">
											<span class="sub-item">Liste des Produits</span>
										</a>
									</li>
                                    <li>
										<a href="Production?action=listAll">
											<span class="sub-item">Liste des Productions</span>
										</a>
									</li>
									<li>
										<a href="Sale?action=listAll">
											<span class="sub-item">Liste des Ventes</span>
										</a>
									</li>
									<li>
										<a href="Recommended?action=listAll">
											<span class="sub-item">Liste des Recommendations</span>
										</a>
									</li>
									<li>
										<a href="Commission?action=listAll">
											<span class="sub-item">Liste des Commissions</span>
										</a>
									</li>
									
									<li>
										<a href="Product?action=price">
											<span class="sub-item">Historiques des prix de produits</span>
										</a>
									</li>
									
								</ul>
							</div>
						</li>
						
                        <li class="nav-item">
							<a data-toggle="collapse" href="#maps">
								<i class="fas fa-book-open"></i>
								<p>Insertion</p>
								<span class="caret"></span>
							</a>
							<div class="collapse" id="maps">
								<ul class="nav nav-collapse">
									<li>
										<a href="Ingredient?action=pageInsert">
											<span class="sub-item">Ingredient</span>
										</a>
									</li>
									<li>
										<a href="Product?action=pageInsert">
											<span class="sub-item">Produit</span>
										</a>
									</li>
									<li>
										<a href="Recommended?action=pageInsert">
											<span class="sub-item">Recommended</span>
										</a>
									</li>
									<li>
										<a href="Production?action=pageInsert">
											<span class="sub-item">Production</span>
										</a>
									</li>
									
									<li>
										<a href="Sale?action=pageInsert">
											<span class="sub-item">Vente</span>
										</a>
									</li>
									
								</ul>
							</div>
						</li>
						
                        <li class="nav-item">
							<a data-toggle="collapse" href="#stat">
								<i class="fas fa-clock"></i>
								<p>Statistique</p>
								<span class="caret"></span>
							</a>
							<div class="collapse" id="stat">
								<ul class="nav nav-collapse">
									<li>
										<a href="Statistic?action=stat">
											<span class="sub-item">General</span>
										</a>
									</li>
									<li>
										<a href="Commission?action=etat">
											<span class="sub-item">Etat Commission</span>
										</a>
									</li>
									
									
								</ul>
							</div>
						</li>

					</ul>
				</div>
			</div>
		</div>

