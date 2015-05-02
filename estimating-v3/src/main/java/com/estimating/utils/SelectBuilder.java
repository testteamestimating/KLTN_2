package com.estimating.utils;

import java.util.ArrayList;
import java.util.List;

public class SelectBuilder {

    private List<String> columns = new ArrayList<String>();

    private List<String> tables = new ArrayList<String>();

    private List<String> joins = new ArrayList<String>();

    private List<String> leftJoins = new ArrayList<String>();

    private List<String> wheres = new ArrayList<String>();

    private List<String> orderBys = new ArrayList<String>();

    private List<String> groupBys = new ArrayList<String>();

    private List<String> havings = new ArrayList<String>();
    
    private List<String> onTable = new ArrayList<String>();
    
    private List<String> inner = new ArrayList<String>();

    public SelectBuilder() {

    }

    public SelectBuilder(String table) {
        tables.add(table);
    }
    
    public List<String> getColumns() {
		return columns;
	}

    private void appendList(StringBuilder sql, List<String> list, String init,
String sep) {
        boolean first = true;
        for (String s : list) {
            if (first) {
                sql.append(init);
            } else {
                sql.append(sep);
            }
            sql.append(s);
            first = false;
        }
    }

    
    
    public SelectBuilder column(String name) {
        columns.add(name);
        return this;
    }

    public SelectBuilder column(String name, boolean groupBy) {
        columns.add(name);
        if (groupBy) {
            groupBys.add(name);
        }
        return this;
    }

    public SelectBuilder from(String table) {
        tables.add(table);
        return this;
    }

    public SelectBuilder groupBy(String expr) {
        groupBys.add(expr);
        return this;
    }

    public SelectBuilder having(String expr) {
        havings.add(expr);
        return this;
    }

    public SelectBuilder join(String join) {
        joins.add(join);
        return this;
    }
    
    public SelectBuilder on(String col1, String col2) {
    	onTable.add(col1 + " = " + col2);
    	
    	return this;
    }
    
    public SelectBuilder inner(String table, String col1, String col2) {
    	inner.add(" inner join " + table + " on " + col1 + " = " + col2);
    	return this;
    }
    
    public SelectBuilder leftJoin(String join) {
        leftJoins.add(join);
        return this;
    }

    public SelectBuilder orderBy(String name) {
        orderBys.add(name);
        return this;
    }

    @Override
    public String toString() {

        StringBuilder sql = new StringBuilder("select ");

        if (columns.size() == 0) {
            sql.append("*");
        } else {
            appendList(sql, columns, "", ", ");
        }

        appendList(sql, tables, " from ", ", ");
        appendList(sql, inner, " ", " ");
        appendList(sql, joins, " inner join ", " inner join ");
        appendList(sql, onTable, " on " , " on ");
        appendList(sql, leftJoins, " left join ", " left join ");
        appendList(sql, wheres, " where ", " and ");
        appendList(sql, groupBys, " group by ", ", ");
        appendList(sql, havings, " having ", " and ");
        appendList(sql, orderBys, " order by ", ", ");

        return sql.toString();
    }

    public SelectBuilder where(String expr) {
        wheres.add(expr);
        return this;
    }
}