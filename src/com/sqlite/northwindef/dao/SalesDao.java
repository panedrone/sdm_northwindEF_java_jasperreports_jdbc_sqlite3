package com.sqlite.northwindef.dao;

import com.sqldalmaker.DataStore;

import com.sqlite.northwindef.dto.Sales;
import com.sqlite.northwindef.dto.SalesByYear;
import java.util.ArrayList;
import java.util.List;

// Code generated by a tool. DO NOT EDIT.
// https://sqldalmaker.sourceforge.net/

public class SalesDao {

    protected final DataStore ds;

    public SalesDao(DataStore ds) {
        this.ds = ds;
    }

    public List<Sales> getSalesByCategory(String d1, String d2) throws Exception {
        String sql = "SELECT DISTINCT Categories.CategoryID, Categories.CategoryName, Products.ProductName, Sum([OrderDetails].UnitPrice * [OrderDetails].Quantity) AS ProductSales" +
        "\n FROM Categories INNER JOIN (Products INNER JOIN (Orders INNER JOIN [OrderDetails] ON Orders.OrderID = [OrderDetails].OrderID) ON Products.ProductID = [OrderDetails].ProductID) ON Categories.CategoryID = Products.CategoryID" +
        "\n WHERE (((Orders.OrderDate) Between ? And ?))" +
        "\n GROUP BY Categories.CategoryID, Categories.CategoryName, Products.ProductName" +
        "\n ORDER BY Products.ProductName;";
        final List<Sales> res = new ArrayList<Sales>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                Sales obj = new Sales();
                obj.setCategoryID(rd.getValue(Integer.class, "CategoryID"));  // q <- q
                obj.setCategoryName(rd.getValue(String.class, "CategoryName"));  // q <- q
                obj.setProductName(rd.getValue(String.class, "ProductName"));  // q <- q
                obj.setProductSales(rd.getValue(Object.class, "ProductSales"));  // q <- q
                res.add(obj);
            }
        }, d1, d2);
        return res;
    }

    public List<SalesByYear> getSalesByYear(String d1, String d2) throws Exception {
        String sql = "SELECT DISTINCT Orders.ShippedDate, Orders.OrderID, Sum([OrderDetails].UnitPrice * [OrderDetails].Quantity) AS ProductSales, strftime('%Y', [OrderDate]) AS ShippedYear" +
        "\n FROM Orders INNER JOIN [OrderDetails] ON Orders.OrderID = [OrderDetails].OrderID" +
        "\n WHERE (((Orders.ShippedDate) Is Not Null And (Orders.ShippedDate) Between ? And ?));";
        final List<SalesByYear> res = new ArrayList<SalesByYear>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                SalesByYear obj = new SalesByYear();
                obj.setShippedDate(rd.getValue(String.class, "ShippedDate"));  // q <- q
                obj.setOrderID(rd.getValue(Integer.class, "OrderID"));  // q <- q
                obj.setProductSales(rd.getValue(Double.class, "ProductSales"));  // q <- q
                obj.setShippedYear(rd.getValue(String.class, "ShippedYear"));  // q <- q
                res.add(obj);
            }
        }, d1, d2);
        return res;
    }
}