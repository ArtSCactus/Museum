package controller;

import database.Database;
import exceptions.DriverNotFoundException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    private Database database;
    private String url;
    private String username;
    private String password;

    public Controller(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        database = new Database();
    }

    public Controller() {
        url = "jdbc:postgresql://127.0.0.1:5432/postgres";
        username = "postgres";
        password = "root";
        database = new Database();
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ResultSet getCurrentLocalData() {
        return database.getCurrentLocalData();
    }

    public String getLastRequest() {
        return database.getLastRequest();
    }

    public boolean connect() throws SQLException, DriverNotFoundException {
        database.connect(url, username, password);
        return true;
    }

    public void disconnect() throws SQLException {
        database.disconnect();
    }

    public ResultSet executeRequest(String request) throws SQLException {
        return database.executeRequest(request);
    }


    public int executeUpdate(String statementLine) throws SQLException {
        return database.executeUpdate(statementLine);
    }

    public int executePreparedUpdate(String statement, List<Object> arguments) throws SQLException {
        return database.executePreparedUpdate(statement, arguments);
    }

    public void executeInsert(String statementLine) throws SQLException {
        database.executeInsert(statementLine);
    }

    public void executePreparedInsert(String statementLine, List<Object> args) throws SQLException {
        database.executePreparedInsert(statementLine, args);
    }

    public void executePreparedUpdateForDate(String statementLine, List<Date> values) throws SQLException {
        database.executePreparedUpdateForDate(statementLine, values);
    }

    public ResultSet executePreparedRequest(String statementRow, List<String> args) throws SQLException {
       return database.executePreparedRequest(statementRow, args);
    }

    public ResultSet executePreparedRequestForColumn(String statementRow, List<Object> args) throws SQLException {
        return database.executePreparedRequestForColumn(statementRow, args);
    }

    public ResultSet executePreparedRequest(String statementRow) throws SQLException{
        return database.executePreparedRequest(statementRow);
    }

    public void executeDelete(String statement) throws SQLException {
        database.executeRemove(statement);
    }

    public void executePreparedStatement(String statement, List<Object> args) throws SQLException {
        database.executePreparedStatement(statement, args);
    }

    public Connection getConnection() {
        return database.getConnection();
    }

    public String getLastRemove() {
        return database.getLastRemove();
    }

}
