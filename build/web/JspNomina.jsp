<%@page import="Model.Empleado"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Nómina</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <link rel="shortcut icon" href="css/img/form.ico">
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/stylesNomina.css">
  </head>
  <body>
      <div class="contenedor">
        <div class="wrap">
          <section class="main">
            <div class="titulo">
              <h2>Nómina</h2>
            </div>
              
            <form class="formulario" action="ServletSearch" method="post" name="formulario_registro">
                <div class="grupo">
                  <label for="nombre" class="label">Nombre de empleado:</label>
                  <input type="text"   name="nombre" value="" id="nombre" required/>
                  <input type="submit" name="submit" class="enlace" value="Buscar">
                </div>
            </form>

              <div class="tabla-container">
              <table class="table">
                <thead>
                  <tr>
                    <td> Empleado: </td>
                    <td> Sueldo Base: </td>
                    <td> AFP: </td>
                    <td> SFS: </td>
                    <td> IRS: </td>
                    <td> Sueldo Final: </td>
                  </tr>
                </thead>
                
            <%ArrayList <Empleado> lista =  (ArrayList <Empleado>) request.getAttribute("datos");
            for(int i = 0; i < lista.size() ;i++ )
            {%>
                <tr>
                  <td> <%= lista.get(i).getNombre()%> </td>
                  <td> <%= lista.get(i).getSueldo() %> </td>
                  <td> <%= lista.get(i).getAfp() %> </td>
                  <td> <%= lista.get(i).getSfs() %> </td>
                  <td> <%= lista.get(i).getIrs() %> </td>
                  <td> <%= lista.get(i).getSueldoF() %> </td>
                </tr>
            <%}%>
            
              </table>
            </div>
              
              <A href="http://localhost:8080/NominaEmpleados/" class="enlace"> Calcular Nómina </a>
          </section>
        </div>
      </div>
  </body>
</html>
