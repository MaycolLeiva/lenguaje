/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maycol;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author mayco
 */
public class alumnos {
    int id;
    String nombre;
    String apellido;
    String edad;
    String direccion;
    String fechanac;
    String genero;
    String cuidad;
    String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCuidad() {
        return cuidad;
    }

    public void setCuidad(String cuidad) {
        this.cuidad = cuidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public void InsertarAlumno(JTextField paramnombre, JTextField paramapellido, JTextField paramedad, JTextField paramdireccion, JTextField paramfechanac, JTextField paramgenero, JTextField paramciudad, JTextField paramemail){
        
        setNombre(paramnombre.getText());
        setApellido(paramapellido.getText());
        setEdad(paramedad.getText());
        setDireccion(paramdireccion.getText());
        setFechanac(paramfechanac.getText());
        setGenero(paramgenero.getText());
        setCuidad(paramciudad.getText());
        setEmail(paramemail.getText());
        conexion objetoConexion = new conexion();
        String Consulta = "INSERT INTO alumnos (nombre,apellido,edad,direccion,fechanac,cuidad,genero,email) VALUES(?,?,?,?,?,?,?,?);";
        try{
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(Consulta);
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getEdad());
            cs.setString(4, getDireccion());
            cs.setString(5, getFechanac());
            cs.setString(6, getGenero());
            cs.setString(7, getCuidad());
            cs.setString(8, getEmail());
            cs.execute();
            JOptionPane.showMessageDialog(null,"Se creo correctamente!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Hubo un error en la base de datos!, error:"+e.toString());
        }
    }
    public void MostrarTabla (JTable paramtabla){
        conexion objetoConexion = new conexion();
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter <TableModel> OrdenarTabla= new TableRowSorter <TableModel> (model);
        paramtabla.setRowSorter(OrdenarTabla);
        String sql="";
        model.addColumn("id");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Edad");
        model.addColumn("Direccion");
        model.addColumn("Fecha de nacimiento");
        model.addColumn("Genero");
        model.addColumn("Ciudad");
        model.addColumn("Email");
        
        sql="select * from alumnos";
        String [] datos = new String[9];
        Statement st;
        try{
            st=objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                datos[7]=rs.getString(8);
                datos[8]=rs.getString(9);
                model.addRow(datos);
            }
            paramtabla.setModel(model);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al mostrar los registros");
        }
    }
    public void SelecionarAlumnos(JTable paramtabla,JTextField paramid, JTextField paramnombre, JTextField paramapellido, JTextField paramedad, JTextField paramdireccion, JTextField paramfechanac, JTextField paramgenero, JTextField paramciudad, JTextField paramemail ){
        try{
            int fila = paramtabla.getSelectedRow();
            if (fila>=0){
                paramid.setText(paramtabla.getValueAt(fila, 0).toString());
                paramnombre.setText(paramtabla.getValueAt(fila, 1).toString());
                paramapellido.setText(paramtabla.getValueAt(fila, 2).toString());
                paramedad.setText(paramtabla.getValueAt(fila, 3).toString());
                paramdireccion.setText(paramtabla.getValueAt(fila, 4).toString());
                paramfechanac.setText(paramtabla.getValueAt(fila, 5).toString());
                paramgenero.setText(paramtabla.getValueAt(fila, 6).toString());
                paramciudad.setText(paramtabla.getValueAt(fila, 7).toString());
                paramemail.setText(paramtabla.getValueAt(fila, 8).toString());
            }else{
                JOptionPane.showMessageDialog(null, "Error al seleccionar");
            }
        }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error al seleccionar, error :"+e.toString());

        }
    }
    public void Editar(JTextField paramid, JTextField paramnombre, JTextField paramapellido, JTextField paramedad, JTextField paramdireccion, JTextField paramfechanac, JTextField paramgenero, JTextField paramciudad, JTextField paramemail ){
        setId(Integer.parseInt(paramid.getText()));
        setNombre(paramnombre.getText());
        setApellido(paramapellido.getText());
        setEdad(paramedad.getText());
        setDireccion(paramdireccion.getText());
        setFechanac(paramfechanac.getText());
        setGenero(paramgenero.getText());
        setCuidad(paramciudad.getText());
        setEmail(paramemail.getText());
        conexion objetoConexion = new conexion();
        String editar="UPDATE alumnos set alumnos.nombre=?, alumnos.apellido=?, alumnos.edad=?, alumnos.direccion=?, alumnos.fechanac=?, alumnos.genero=?, alumnos.cuidad=?, alumnos.email=? where alumnos.id=?";
        try{
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(editar);
            cs.setString(1,getNombre());
            cs.setString(2,getApellido());
            cs.setString(3,getEdad());
            cs.setString(4,getDireccion());
            cs.setString(5, getFechanac());
            cs.setString(6, getGenero());
            cs.setString(7, getCuidad());
            cs.setString(8, getEmail());
            cs.setInt(9, getId());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se edito correctamente");
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, "Se edito correctamente, error :"+e.toString());
        }
    }
     public void eliminar(JTextField paramid){
       conexion objetoConexion = new conexion();
       setId(Integer.parseInt(paramid.getText()));
       String eliminar="DELETE FROM alumnos WHERE id= ?";
  try{
    CallableStatement cs = objetoConexion.establecerConexion().prepareCall(eliminar);
       cs.setInt(1,getId());
       cs.execute();
        JOptionPane.showMessageDialog(null,"Eliminado correctamente");
  }catch(Exception e){
       JOptionPane.showMessageDialog(null,"Error al eliminar, error:"+e.toString());
      } 
    }
}
