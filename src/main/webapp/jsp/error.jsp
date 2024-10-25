<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Admin Hotel Bellmunt</title>
    <!--Estilos-->
    <link rel="stylesheet" href="css/cssAdmin/admin.css">
    <!--Bootstrap 5-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!--Fuente Tigtree-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Figtree:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    <!--Font awesome-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
     <!-- Incluir el CSS y JS de Awesome Alerts (SweetAlert2) -->
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.all.min.js"></script>
    
</head>
<style>
    :root {
        --background-azul: #1E212D;
        --text-rosa: #E78F81;
        --text-marron: #FAF3E0;
        --text-blanco: #F5F5F5;
        --text-negro: #1E1E1E;
        --background-rosa: #EABF9F;
    }

    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    body {
        font-family: "Figtree";
            height: 100vh; 
            width: 100%;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            color: var(--text-marron);
            background-color: var(--background-azul) !important;
    }

    .container {
        text-align: center;
    }

    .subtitle {
        color: var(--text-rosa);
    }
</style>
<body>
    <div class="container">
        <h1 class="title">Oops! Algo salio mal.</h1>
        <p>Lo sentimos, la pagina que estas buscando no existe o ha ocurrido un error inesperado.</p>

        <h2 class="subtitle">Detalles del error:</h2>
        <p><%= request.getAttribute("error") %></p> 
    </div>
</body>
</html>