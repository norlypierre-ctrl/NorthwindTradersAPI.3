package com.pluralsight.NorthwindTradersAPI3.dao.iml;

import com.pluralsight.NorthwindTradersAPI3.dao.Interfaces.ICategoryDAO;
import com.pluralsight.NorthwindTradersAPI3.models.Category;
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
public class JDBCCategoryDAO implements ICategoryDAO {

    private final DataSource dataSource;

    @Autowired
    public JDBCCategoryDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String getAllQuery = "SELECT * FROM categories";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(getAllQuery);
             ResultSet resultSet = selectStatement.executeQuery()) {

            while (resultSet.next()) {
                int categoryId = resultSet.getInt("category_id");
                categories.add(new Category(categoryId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
    @Override
    public Category getById(int categoryID) {
        Category category = null;
        String getByIdQuery = "SELECT * FROM Categories WHERE category_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(getByIdQuery)) {

            selectStatement.setInt(1, categoryID);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {

                    category = new Category(categoryID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }
}

