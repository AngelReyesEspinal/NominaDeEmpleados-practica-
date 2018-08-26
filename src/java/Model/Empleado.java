package Model;

import DAO.Conexion;
import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Empleado implements Serializable
{
    //Atributos
    private String nombre;
    private String email;
    private String cedula;
    private double sueldo;
    
    //Atributos buscados
    private double afp = 0;
    private double sfs = 0;
    private double irs = 0;
    private double sueldoF = 0;
   
    //Constructores
    public Empleado() 
    {
    
    }

    public Empleado(String nombre, String email, String cedula, double sueldo) 
    {
        this.nombre = nombre;
        this.email = email;
        this.cedula = cedula;
        this.sueldo = sueldo;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public double getAfp() {
        return afp;
    }

    public void setAfp(double afp) {
        this.afp = afp;
    }

    public double getSfs() {
        return sfs;
    }

    public void setSfs(double sfs) {
        this.sfs = sfs;
    }

    public double getIrs() {
        return irs;
    }

    public void setIrs(double irs) {
        this.irs = irs;
    }

    public double getSueldoF() {
        return sueldoF;
    }

    public void setSueldoF(double sueldoF) {
        this.sueldoF = sueldoF;
    }
    
    //METODOS
    public double calcularAFP()
    {   
        if (sueldo > 172900)
        {
            afp = 4962;
        } else {
            afp = sueldo * 0.0287;
        }
        
        return afp;
    }
    
    public double calcularSFS()
    {   
        if(sueldo > 56450)
        {
            sfs = 2628;
        } else {
            sfs = sueldo * 0.0304;
        }
        
        return sfs;
    }
    
    public double calcularIRS()
    {
        double sueldoAnual = 0;
        
        sueldoAnual = (sueldo * 12);
        
        if(sueldoAnual > 567123)
        {
            irs = (79776+((sueldoAnual-567123)*0.25))/12;
        } 
        else if(sueldoAnual > 624329) 
        {
            irs = (31216 + ((sueldoAnual - 624329)*0.2))/12;
        }
        else if (sueldoAnual > 416220)
        {
            irs = ((sueldoAnual - 416220)*0.15)/12;
        }
        
        return irs;
    }
    
    public double calcularSueldoF()
    {
        sueldoF = sueldo - afp - sfs - irs;
        return sueldoF;
    }
    
    //CONEXION DB
    Conexion conexion = new Conexion();
    
    //Insertando.
    public void enviarDatos() 
    {
        try 
        {   
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("INSERT INTO empleado (nombre, email, cedula, sueldoBase, afp, sfs, irs, sueldoFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, getNombre());
            pst.setString(2, getEmail());
            pst.setString(3, getCedula());
            pst.setDouble(4, getSueldo());
            pst.setDouble(5, getAfp());
            pst.setDouble(6, getSfs());
            pst.setDouble(7, getIrs());
            pst.setDouble(8, getSueldoF());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("El problema es: " + e);
        }
    }
    
    //Consultando.
    public void llamarEmpleadoActual()
    {
        try 
        {
          Connection cn = conexion.conectar();
          String sql = "SELECT nombre, sueldoBase, afp, sfs, irs, sueldoFinal FROM empleado where nombre =  '"+nombre+"' ";
          PreparedStatement pst = cn.prepareStatement(sql);
          ResultSet rs = pst.executeQuery(sql);
                
          while(rs.next())
          { 
            nombre = rs.getString("nombre");
            sueldo = rs.getDouble("sueldoBase");
            afp = rs.getDouble("afp");
            sfs = rs.getDouble("sfs");
            irs = rs.getDouble("irs");
            sueldoF = rs.getDouble("sueldoFinal");
          }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Excepcion " + ex);
        }
    }
    
    public ArrayList <Empleado> llamarDatos()
    {
        ArrayList <Empleado> lista = new ArrayList();
        try 
        {
          Connection cn = conexion.conectar();
          String sql = "SELECT nombre, email, cedula, sueldoBase, afp, sfs, irs, sueldoFinal FROM empleado";
          PreparedStatement pst = cn.prepareStatement(sql);
          ResultSet rs = pst.executeQuery(sql);
                
          while(rs.next())
          {
            Empleado empleado = new Empleado();
            empleado.setNombre(rs.getString("nombre"));
            empleado.setEmail(rs.getString("email"));
            empleado.setCedula(rs.getString("cedula"));
            empleado.setSueldo(rs.getDouble("sueldoBase"));
            empleado.setAfp(rs.getDouble("afp"));
            empleado.setSfs(rs.getDouble("sfs"));
            empleado.setIrs(rs.getDouble("irs"));
            empleado.setSueldoF(rs.getDouble("sueldoFinal"));
            lista.add(empleado);
          }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Excepcion " + ex);
        }
        return lista;   
    }
    
    public ArrayList <Empleado> llamarUnEmpleado(String nombreEmpleado)
    {
        ArrayList <Empleado> listaEmpleado = new ArrayList();
        try 
        {
          Connection cn = conexion.conectar();
          String sql = "SELECT nombre, email, cedula, sueldoBase, afp, sfs, irs, sueldoFinal FROM empleado where Nombre like '%"+nombreEmpleado+"%' ";
          PreparedStatement pst = cn.prepareStatement(sql);
          ResultSet rs = pst.executeQuery(sql);
                
          while(rs.next())
          {
            Empleado empleado = new Empleado();
            empleado.setNombre(rs.getString("nombre"));
            empleado.setEmail(rs.getString("email"));
            empleado.setCedula(rs.getString("cedula"));
            empleado.setSueldo(rs.getDouble("sueldoBase"));
            empleado.setAfp(rs.getDouble("afp"));
            empleado.setSfs(rs.getDouble("sfs"));
            empleado.setIrs(rs.getDouble("irs"));
            empleado.setSueldoF(rs.getDouble("sueldoFinal"));
            listaEmpleado.add(empleado);
          }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Excepcion " + ex);
        }
        return listaEmpleado;   
    }
    
    
}
