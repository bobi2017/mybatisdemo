package configure;

public class Function {

    private String sqlType;

    private String functionName;

    private String sql;

    private Object resultType;

    private String parameterType;

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object getResultType() {
        return resultType;
    }

    public void setResultType(Object resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    @Override
    public String toString() {
        return "Function{" +
                "sqlType='" + sqlType + '\'' +
                ", functionName='" + functionName + '\'' +
                ", sql='" + sql + '\'' +
                ", resultType=" + resultType +
                ", parameterType='" + parameterType + '\'' +
                '}';
    }
}
