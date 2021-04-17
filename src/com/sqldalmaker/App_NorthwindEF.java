package com.sqldalmaker;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sqlite.northwindef.dao.ProductsDao;
import com.sqlite.northwindef.dto.ProductSales;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class App_NorthwindEF {

    public static InputStream get_resource_as_stream(String res_path) throws Exception {
        ClassLoader cl = App_NorthwindEF.class.getClassLoader();
        InputStream is = cl.getResourceAsStream(res_path);
        if (is == null) {
            is = cl.getResourceAsStream("/" + res_path);
        }
        if (is == null) {
            throw new Exception("Resource not found: " + res_path);
        }
        return is;
    }

    public static void main(String[] args) {
        System.out.println("Hello, World");
        DataStoreManager dsm = new DataStoreManager();
        try {
            dsm.open();
            try {
//				Crud_All_Dao dao = dsm.createCrud_All_Dao();
//				Category c = new Category();
//				c.setCategoryName("Category 1");
//				c.setDescription("Description 1");
//				dao.createCategory(c);
//				System.out.println(c.getCategoryID());
                ///////////////////////////////////
//				CustomersDao cdao = dsm.createCustomersDao();
//				List<CustomersAndSuppliersByCity> list = cdao.getCustomersAndSuppliersByCity();
//				System.out.println(list.size());
                ///////////////////////////////////
                ProductsDao pdao = dsm.createProductsDao();
                String dt1 = "1997-01-01";
                String dt2 = "1998-01-01";
                List<ProductSales> pss = pdao.getProductSales(dt1, dt2);
//				for (ProductSales ps : pss) {
//					System.out.println(String.format("%s\t\t%s\t\t%.2f", ps.getCategoryName(), ps.getProductName(),
//							ps.getProductSales()));
//				}
                // https://www.youtube.com/watch?v=AfC6MlWrXqY&ab_channel=CoolITHelp
                JRBeanCollectionDataSource jr_ds = new JRBeanCollectionDataSource(pss);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("Parameter1", jr_ds);
                params.put("Parameter2", String.format("Northwind Product Sales %s...%s", dt1, dt2));
                InputStream is = get_resource_as_stream("My_A4.jrxml");
                JasperDesign jd = JRXmlLoader.load(is);
                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, params, new JREmptyDataSource());
                JasperViewer.viewReport(jp);
            } finally {
                dsm.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
