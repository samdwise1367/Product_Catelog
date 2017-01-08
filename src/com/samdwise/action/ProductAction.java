/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samdwise.action;

import com.samdwise.to.ProductTO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author samdwise
 */
public class ProductAction {
    ConnectionClass connect = new ConnectionClass();
    
    //Resize image
    public ImageIcon ResizeImage(String path, byte[] image,int width, int height){
        ImageIcon imageObject = null;
        
        if(path != null){
            imageObject = new ImageIcon(path);
        }else{
            imageObject = new ImageIcon(image);
        }
        
        Image img = imageObject.getImage();
        Image getImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(getImg);
        return imageIcon;
        
    }
    
    
    //upload image and return path
    public File uploadImage(JLabel label){
        JFileChooser getFile = new JFileChooser();
        getFile.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filterExten= new FileNameExtensionFilter("*.image","jpg","png");
        getFile.addChoosableFileFilter(filterExten);
        int result = getFile.showSaveDialog(null);
        
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = getFile.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            label.setIcon(ResizeImage(path, null, label.getWidth(), label.getHeight()));
            //System.out.println(path);
            return selectedFile;
        }else{
            JOptionPane.showMessageDialog(null, "No image selected");
            return null;
        }
    
    }
    
    public byte[] imageByte(File path) throws FileNotFoundException, IOException{
        BufferedImage bufImage = ImageIO.read(path);
         WritableRaster raster = bufImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }
    
    public void insertData(ProductTO productTo){
        
        try{
        
        Connection con = connect.getConnecion();
        String sql = "INSERT INTO products(name, price, add_date,image)"+ "values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, productTo.getName());
        ps.setFloat(2, productTo.getPrice());
        ps.setString(3, productTo.getAddDate());
        ps.setBytes(4, productTo.getImage());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Record inserted");
        
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error inserting");
        }
        
    }
    
    
    public ArrayList<ProductTO> getTableData(){
        ArrayList<ProductTO> listProduct = new ArrayList<ProductTO>();
        Connection con = connect.getConnecion();
        String query = "SELECT * FROM products";
        try{
            Statement statement = con.createStatement();
             ResultSet resultSet= statement.executeQuery(query);
             ProductTO productTo;
             
             while(resultSet.next()){
                 productTo = new ProductTO(resultSet.getInt("id"),resultSet.getString("name"),
                         Float.parseFloat(resultSet.getString("price")),resultSet.getString("add_date"),resultSet.getBytes("image"));
                 listProduct.add(productTo);
                 
             }
            
        }catch(SQLException ex){
        
        }
        
        return listProduct;
    }
    
    
   
    
    //check if  any of the field is missing
    public boolean checkInput(String name, String price, String addDate){
        if(!(name.isEmpty() || price.isEmpty()|| addDate.isEmpty())){
            return true;
        }else
            return false;
    
    }
}
