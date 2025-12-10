package com.pluralsight.NorthwindTradersAPI3.dao.iml;

import com.pluralsight.NorthwindTradersAPI3.dao.Interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersAPI3.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    @Component
    public class JDBCProductDAO implements IProductDAO {

        private final DataSource dataSource;

        @Autowired
        public JDBCProductDAO(DataSource dataSource) {
            this.dataSource = dataSource;
        }


        @Override
        public List<Product> getAll() {
            List<Product> products = new ArrayList<>();
            String getAllQuery = "SELECT * FROM products";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement selectStatement = connection.prepareStatement(getAllQuery);
                 ResultSet resultSet = selectStatement.executeQuery()) {

                while (resultSet.next()) {
                    int productId = resultSet.getInt("product_id");
                    products.add(new Product(productId));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return products;
        }

        @Override
        public Product getById(int productId) {
            Product product = null;
            String getByIdQuery = "SELECT * FROM products WHERE product_id = ?";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement selectStatement = connection.prepareStatement(getByIdQuery)) {

                selectStatement.setInt(1, productId);

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {

                        product = new Product(productId);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return product;

        }
    }

