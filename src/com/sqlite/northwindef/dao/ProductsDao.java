package com.sqlite.northwindef.dao;

import com.sqldalmaker.DataStore;

import com.sqlite.northwindef.dto.ProductSales;
import java.util.ArrayList;
import java.util.List;

// This code was generated by a tool. Don't modify it manually.
// http://sqldalmaker.sourceforge.net

public class ProductsDao {

    protected final DataStore ds;

    public ProductsDao(DataStore ds) {
        this.ds = ds;
    }

    public int count() throws Exception {
        String sql = "select count(*) from Products";
        return ds.query(int.class, sql);
    }

    public List<ProductSales> getProductSales(String d1, String d2) throws Exception {
        String sql = "SELECT DISTINCT" +
        "\n Categories.CategoryName," +
        "\n Products.ProductName," +
        "\n Sum(([OrderDetails].[UnitPrice]*[Quantity]*(1-[Discount])/100)*100) AS ProductSales" +
        "\n FROM (Categories INNER JOIN Products ON Categories.CategoryID = Products.CategoryID) " +
        "\n  INNER JOIN (Orders INNER JOIN [OrderDetails] ON Orders.OrderID = [OrderDetails].OrderID)" +
        "\n  ON Products.ProductID = [OrderDetails].ProductID" +
        "\n WHERE (((Orders.ShippedDate) Between ? And ?))" +
        "\n GROUP BY Categories.CategoryName, Products.ProductName;";
        final List<ProductSales> res = new ArrayList<ProductSales>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                ProductSales obj = new ProductSales();
                obj.setCategoryName(rd.getValue(String.class, "CategoryName"));  // q <- q
                obj.setProductName(rd.getValue(String.class, "ProductName"));  // q <- q
                obj.setProductSales(rd.getValue(Double.class, "ProductSales"));  // q <- q
                res.add(obj);
            }
        }, d1, d2);
        return res;
    }
}