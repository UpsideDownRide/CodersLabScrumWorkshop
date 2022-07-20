package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipePlanDao {
    // ZAPYTANIA SQL
    private static final String FIND_ALL_RECIPE_PLAN_BY_PLAN_ID_QUERY = "select * from recipe_plan where plan_id=? order by display_order";
    private static final String CREATE_RECIPE_PLAN_QUERY = "INSERT INTO recipe_plan(recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?,?,?,?,?);";
    private static final String DELETE_RECIPE_PLAN_QUERY = "DELETE FROM recipe_plan where id = ?;";
    private static final String FIND_ALL_RECIPE_PLANS_QUERY = "SELECT * FROM recipe_plan;";
    private static final String READ_RECIPE_PLAN_QUERY = "SELECT * from recipe where id = ?;";
    private static final String UPDATE_RECIPE_PLAN_QUERY = "UPDATE recipe_plan SET" +
            " recipe_id = ?," +
            " meal_name = ?," +
            " display_order = ?," +
            " day_name_id = ?," +
            " plan_id = ?" +
            " WHERE	id = ?;";

    /**
     * Get book by id
     *
     * @param recipeId
     * @return
     */
    public RecipePlan read(Integer recipeId) {
        RecipePlan recipePlan = new RecipePlan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_PLAN_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipePlan.setId(resultSet.getInt("id"));
                    recipePlan.setRecipeId(resultSet.getInt("recipe_id"));
                    recipePlan.setMealName(resultSet.getString("meal_name"));
                    recipePlan.setDisplayOrder(resultSet.getInt("display_order"));
                    recipePlan.setDayNameId(resultSet.getInt("day_name_id"));
                    recipePlan.setPlanId(resultSet.getInt("plan_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipePlan;
    }

    /**
     * Return all RecipePlans
     *
     * @return
     */
    public List<RecipePlan> findAll() {
        List<RecipePlan> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPE_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                RecipePlan recipePlanToAdd = new RecipePlan();
                recipePlanToAdd.setId(resultSet.getInt("id"));
                recipePlanToAdd.setRecipeId(resultSet.getInt("recipe_id"));
                recipePlanToAdd.setMealName(resultSet.getString("meal_name"));
                recipePlanToAdd.setDisplayOrder(resultSet.getInt("display_order"));
                recipePlanToAdd.setDayNameId(resultSet.getInt("day_name_id"));
                recipePlanToAdd.setPlanId(resultSet.getInt("plan_id"));
                recipeList.add(recipePlanToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    /**
     * Create book
     *
     * @param recipePlan
     * @return
     */
    public RecipePlan create(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setInt(1, recipePlan.getRecipeId());
            insertStm.setString(2, recipePlan.getMealName());
            insertStm.setInt(3, recipePlan.getDisplayOrder());
            insertStm.setInt(4, recipePlan.getDayNameId());
            insertStm.setInt(5, recipePlan.getPlanId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipePlan.setId(generatedKeys.getInt(1));
                    return recipePlan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Remove Recipe Plan by id
     *
     * @param recipePlanId
     */
    public void delete(Integer recipePlanId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_PLAN_QUERY)) {
            statement.setInt(1, recipePlanId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Recipe plan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update book
     *
     * @param recipePlan
     */
    public void update(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_PLAN_QUERY)) {

            statement.setInt(6, recipePlan.getId());
            statement.setInt(1, recipePlan.getRecipeId());
            statement.setString(2, recipePlan.getMealName());
            statement.setInt(3, recipePlan.getDisplayOrder());
            statement.setInt(4, recipePlan.getDayNameId());
            statement.setInt(5, recipePlan.getPlanId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<RecipePlan> findRecipePlan(int planId) {
        List<RecipePlan> recipePlanList = new ArrayList<>();
        
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPE_PLAN_BY_PLAN_ID_QUERY )) {
            statement.setInt(1, planId);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                RecipePlan recipePlan = new RecipePlan();
                recipePlan.setId(rs.getInt("id"));
                recipePlan.setRecipeId(rs.getInt("recipe_id"));
                recipePlan.setMealName(rs.getString("meal_name"));
                recipePlan.setDisplayOrder(rs.getInt("display_order"));
                recipePlan.setDayNameId(rs.getInt("day_name_id"));
                recipePlan.setPlanId(rs.getInt("plan_id"));
                recipePlanList.add(recipePlan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlanList;
        
    }

}