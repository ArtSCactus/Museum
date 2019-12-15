package database;

import exceptions.DriverNotFoundException;

import java.sql.*;
import java.util.List;

public class Database {
    private Connection connection;
    private ResultSet currentLocalData;
    private String lastRequest;
    private String lastUpdate;
    private String lastInsert;
    private String lastRemove;

    public Database() {
    }

    public Database(Connection connection) throws DriverNotFoundException {
        checkDriver();
        this.connection = connection;
        currentLocalData = null;
    }

    public Database(String url, String username, String password) throws SQLException, DriverNotFoundException {
        checkDriver();
        connection = DriverManager.getConnection(url, username, password);
    }

    public ResultSet getCurrentLocalData() {
        return currentLocalData;
    }

    public String getLastRequest() {
        return lastRequest;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public static void checkDriver() throws DriverNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DriverNotFoundException("SQL driver not found.");
        }
    }


    public void connect(String url, String username, String password) throws SQLException, DriverNotFoundException {
        checkDriver();
        connection = DriverManager.getConnection(url, username, password);
    }


    public void connect(Connection connection) throws SQLException, DriverNotFoundException {
        checkDriver();
        this.connection = connection;
    }

    public void disconnect() throws SQLException {
        connection.close();
    }


    public Connection getConnection() {
        return connection;
    }

    public String getLastInsert() {
        return lastInsert;
    }

    public String getLastRemove() {
        return lastRemove;
    }

    /**
     * Should be used only to request data from database.
     * <p>
     * Saves last request row in {@code String lastRequest}
     * Also saves last ResultSet in {@code ResultSet currentLocalData}
     *
     * @param request query string record
     * @return result of request
     * @throws SQLException
     */
    public ResultSet executeRequest(String request) throws SQLException {
        if (connection.isClosed()) {
            // here will be DatabaseConnectionException
        }
        lastRequest = request;
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet result = statement.executeQuery(request);
        currentLocalData = result;
        return result;
    }



    public ResultSet executePreparedRequest(String statementRow, List<String> args) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(statementRow, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);

        for (int index = 0; index < args.size(); index++) {
            statement.setString(index + 1, args.get(index));
        }
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public ResultSet executePreparedRequest (String statementRow) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(statementRow, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    /**
     * Should be used only to build or change table.
     * <p>
     * Saves update request row in {@code String lastUpdate}.
     *
     * @param statementRow
     * @throws SQLException
     */
    public int executeUpdate(String statementRow) throws SQLException {
        if (statementRow == null) {
            throw new NullPointerException("Cannot execute null statement");
        }
        if (connection.isClosed()) {
            // here will be DatabaseConnectionException
        }
        lastUpdate = statementRow;
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(statementRow);
        }
    }


    public int executePreparedUpdate(String updateRequest, List<String> array) throws SQLException {
        if (updateRequest == null) {
            throw new NullPointerException("Cannot execute null statement");
        }
        if (connection.isClosed()) {
            // here will be DatabaseConnectionException
        }
        try (PreparedStatement statement = connection.prepareStatement(updateRequest)) {
            for (int index = 0; index < array.size(); index++) {
                statement.setString(index + 1, array.get(index));
            }
            return statement.executeUpdate();
        }
    }

    public int executePreparedUpdateForDate(String updateRequest, List<Date> array) throws SQLException {
        if (updateRequest == null) {
            throw new NullPointerException("Cannot execute null statement");
        }
        if (connection.isClosed()) {
            // here will be DatabaseConnectionException
        }
        try (PreparedStatement statement = connection.prepareStatement(updateRequest)) {
            for (int index = 0; index < array.size(); index++) {
                statement.setDate(index + 1, array.get(index));
            }
            return statement.executeUpdate();
        }
    }

    //TODO: добавить в execute PreparedStatement
    public void executeInsert(String statementRow) throws SQLException {
        if (statementRow == null) {
            throw new NullPointerException("Cannot execute null statement");
        }
        if (connection.isClosed()) {
            // here will be DatabaseConnectionException
        }
        lastInsert = statementRow;
        Statement statement = connection.createStatement();
        statement.execute(statementRow);
        statement.close();
    }

    public void executePreparedInsert(String statementRow, List<Object> args) throws SQLException {
        if (statementRow == null){
            throw new NullPointerException("Cannot execute null statement");
        }
        if (connection.isClosed()) {
            // here will be DatabaseConnectionException
        }

       try( PreparedStatement statement = connection.prepareStatement(statementRow)) {
           int statementIndex=1;
           for (int index = 0; index < args.size(); index++, statementIndex++) {
               if (args.get(index) == null) {
                   statementIndex--;
               } else {
                   if (args.get(index).getClass().getName().equals(Date.class.getName())) {
                       Date date = (Date) args.get(index);
                       statement.setDate(statementIndex, date);
                   } else {
                       statement.setString(statementIndex, (String) args.get(index));
                   }
               }
           }
           statement.execute();
       }
    }

    public void executeRemove(String statementRow) throws SQLException {
        if (statementRow == null) {
            throw new NullPointerException("Cannot execute null statement");
        }
        if (connection.isClosed()) {
            // here will be DatabaseConnectionException
        }
        lastRemove = statementRow;
        Statement statement = connection.createStatement();
        statement.execute(statementRow);
        statement.close();
    }

}
