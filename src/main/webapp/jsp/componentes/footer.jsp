 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <style>
    /* Footer */
    footer {
        font-family: "Figtree";
    }

    .footer-background {
                        background-color: #F5EFE6;
                        color: #1E212D;
                    }

                    .footer-icono {
                        color: #1E212D;
                        font-size: 25px;
                        transition: all .4s ease-in-out;
                    }

                    .footer-icono i:hover {
                        transform: scale(1.2);
                        color: #E78F81;
                    }

                    .footer-text {
                        color: #1E212D;
                    }

 </style>

 <!--Footer-->
  <footer class="footer-background">
    <div class="container py-4">
        <div class="row">
            <!-- Sección de redes sociales -->
            <div class="col-md-4">
                <h5>Síguenos</h5>
                <br>
                <a href="#" class="footer-icono me-3"><i class="fab fa-youtube"></i></a>
                <a href="#" class="footer-icono me-3"><i class="fab fa-facebook"></i></a>
                <a href="#" class="footer-icono me-3"><i class="fab fa-twitter"></i></a>
                <a href="#" class="footer-icono me-3"><i class="fab fa-instagram"></i></a>
            </div>

            <!-- Sección de contacto -->
            <div class="col-md-4">
                <h5>Contacto</h5>
                <br>
                <p><i class="fas fa-phone"></i> +34 666 66 66 66</p>
                <p><i class="fas fa-envelope"></i> info@bellmunt.es</p>
                <p><i class="fas fa-map-marker-alt"></i> Carrer de Deusto, 8,
                    Llucmajor, illes Balears</p>
            </div>

            <!-- Sección de enlaces -->
            <div class="col-md-4">
                <h5>Informacion</h5>
                <br>
                <ul class="list-unstyled">
                    <li><a href="#" class="footer-text">Inicio</a></li>
                    <li><a href="#" class="footer-text">Acerca de</a></li>
                    <li><a href="#" class="footer-text">Servicios</a></li>
                    <li><a href="#" class="footer-text">Contacto</a></li>
                </ul>
            </div>
        </div>
    </div>
</footer>