<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Empleado</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <link rel="shortcut icon" href="css/img/form.ico">
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/stylesEmp.css">
  </head>
  <body>
      <div class="contenedor">
        <div class="wrap">

          <div class="titulo">
            <h2>Información del Empleado</h2>
          </div>
          <br />

          <section class="main">
          <div class="label">
          <label> Empleado: <br /> <span class="contenido"> <%= request.getAttribute("nombre") %> </span> </label>
          </div>
          <br />

          <div class="label">
          <label> Sueldo base: <br /> <span class="contenido"> <%= request.getAttribute("sueldoBase") %> </span> </label>
          </div>
          <br />

          <div class="label">
          <label>Administradora de fondo de pensiones (AFP): <br /> <span class="contenido"> <%= request.getAttribute("afp") %> </span>
          </label>
          </div>
          <br />

          <div class="label">
          <label>Seguro familiar de salud (SFS): <br /> <span class="contenido"> <%= request.getAttribute("sfs") %> </span> </label>
          </div>
          <br />

          <div class="label">
          <label>Impuesto sobre la renta (IRS): <br /> <span class="contenido"> <%= request.getAttribute("irs") %> </span></label>
          </div>
          <br />

          <div class="label">
          <label>Sueldo final: <br /> <span class="contenido"> <%= request.getAttribute("sueldoF") %> </span></label>
          </div>
          <br />

          <A href="ServletNomina" class="enlace"> Nomina </a>

          </section>

        </div>
      </div>
  </body>
</html>

