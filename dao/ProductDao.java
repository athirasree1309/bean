package dao;

import java.sql.*;
import java.util.*;

import bean.Product;

public class ProductDao {
    private static final String URL = "jdbc:mysql://localhost:3306/ultras";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    public ProductDao() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    public List<Product> getAllProducts() throws Exception {
        List<Product> productList = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);    
        String query = "SELECT p.id, p.name, p.brand_id, b.brand_name, p.price, p.color, p.specification, p.image FROM products p JOIN brands b ON p.brand_id = b.id";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setBrand_id(rs.getInt("brand_id"));
            product.setBrand_name(rs.getString("brand_name"));
            product.setPrice(rs.getInt("price"));
            product.setColor(rs.getString("color"));
            product.setSpecification(rs.getString("specification"));
            product.setImage(rs.getString("image"));
            productList.add(product);
        }
        return productList;
    }
    // Method to retrieve all brands with IDs and names
    public List<Map<String, Object>> getAllBrands() throws SQLException {
        List<Map<String, Object>> brands = new ArrayList<>();
        String SELECT_ALL_BRANDS = "SELECT id, name FROM brands"; // Adjust the table and column names as per your database
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_BRANDS);
             ResultSet rs = preparedStatement.executeQuery()) {
            
            while (rs.next()) {
                Map<String, Object> brand = new HashMap<>();
                brand.put("id", rs.getInt("id"));
                brand.put("name", rs.getString("name"));
                brands.add(brand);
            }
        }
        return brands;
    }

    // Method to retrieve a product by its ID
    public Map<Integer, Product> getProductsByIds(Set<Integer> ids) throws SQLException {
        Map<Integer, Product> products = new HashMap<>();
        String SELECT_PRODUCTS_BY_IDS = "SELECT p.id, p.name, p.brand_id, b.brand_name as brand_name, p.price, p.color, p.specification, p.image FROM products p JOIN brands b ON p.brand_id = b.id WHERE p.id IN (" + String.join(",", ids.stream().map(String::valueOf).toArray(String[]::new)) + ")";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_PRODUCTS_BY_IDS)) {
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int brandId = rs.getInt("brand_id");
                String brandName = rs.getString("brand_name");
                int price = rs.getInt("price");
                String color = rs.getString("color");
                String specification = rs.getString("specification");
                String image = rs.getString("image");
                
                Product product = new Product(id, name, brandId, brandName, price, color, specification, image);
                products.put(id, product);
            }
        }
        return products;
    }

    // Method to update a product
    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET name = ?, brand_id = ?, price = ?, color = ?, specification = ?, image_url = ? WHERE product_id = ?";
        
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getBrand_id());
            stmt.setInt(3, product.getPrice());
            stmt.setString(4, product.getColor());
            stmt.setString(5, product.getSpecification());
            stmt.setString(6, product.getImage());
            stmt.setInt(7, product.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
