package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
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
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        int emptyId = -1;
        int productCategoryId = (req.getParameter("category_id") != null) ? Integer.parseInt(req.getParameter("category_id")) : emptyId;
        int supplierId = (req.getParameter("supplier_id") != null) ? Integer.parseInt(req.getParameter("supplier_id")) : emptyId;

        List<Product> products = getProducts(productCategoryId, supplierId);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("categoryList", productCategoryDataStore.getAll());
        context.setVariable("supplierList", supplierDataStore.getAll());
        context.setVariable("products", products);
        context.setVariable("linkText", "Shopping cart");
        context.setVariable("url", "/order");
        context.setVariable("linkId", "cart-link");
        engine.process("product/index.html", context, resp.getWriter());

        HOME_URL = getRequestURL(req);
    }

    private List<Product> getProducts(int productCategoryId, int supplierId) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        List<Product> products;
        ProductCategory category;
        Supplier supplier;
        int defaultCategory = 1;

        if (isEmptyId(productCategoryId) && isEmptyId(supplierId)) {
            products = productDataStore.getBy(productCategoryDataStore.find(defaultCategory));
        }
        else if (!isEmptyId(productCategoryId)) {
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

    private boolean isEmptyId(int id) {
        int emptyId = -1;
        return id == emptyId;
    }
}

