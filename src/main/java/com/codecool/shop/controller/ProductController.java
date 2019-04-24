package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name="home", urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    private static String HOME_URL;

    public static String getHomeUrl() {
        return HOME_URL;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        int productCategoryId = 0;
        int supplierId = 0;

        if (req.getParameter("category_id") != null) {
            productCategoryId = Integer.parseInt(req.getParameter("category_id"));
        }
        if (req.getParameter("supplier_id") != null) {
            supplierId = Integer.parseInt(req.getParameter("supplier_id"));
        }

        List<Product> products = getProducts(productDataStore, productCategoryDataStore, supplierDataStore, productCategoryId, supplierId);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("recipient", "World");
        context.setVariable("categoryList", productCategoryDataStore.getAll());
        context.setVariable("supplierList", supplierDataStore.getAll());
        context.setVariable("products", products);
        engine.process("product/index.html", context, resp.getWriter());

        HOME_URL = getRequestURL(req);
    }

    private List<Product> getProducts(ProductDao productDataStore, ProductCategoryDao productCategoryDataStore, SupplierDao supplierDataStore, int productCategoryId, int supplierId) {
        List<Product> products;
        ProductCategory category;
        Supplier supplier;
        if (productCategoryId == 0 && supplierId == 0) {
            products = productDataStore.getBy(productCategoryDataStore.find(1));
        }
        else if (productCategoryId != 0) {
            category = productCategoryDataStore.find(productCategoryId);
            products = productDataStore.getBy(category);
        } else {
            supplier = supplierDataStore.find(supplierId);
            products = productDataStore.getBy(supplier);
        }
        return products;
    }

    private String getRequestURL(HttpServletRequest req) {
        String requestURL = req.getRequestURL().toString();
        String queryString = req.getQueryString();

        if (queryString == null) {
            return requestURL;
        } else {
            return requestURL.concat("?").concat(queryString);
        }
    }
}

