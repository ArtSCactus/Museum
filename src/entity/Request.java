package entity;

import java.util.List;

public class Request {
    private String statement;
    private List<Object> args;
    public Request(String statement, List<Object> args){
        this.statement=statement;
        this.args=args;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }
}
