package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
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
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/supplier"})
public class ProductFilterBySupplier extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ProductDao productDataStore = ProductDaoMem.getInstance();
            ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            SupplierDao supplierDataStore= SupplierDaoMem.getInstance();
            //ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            SupplierDao supplierList = SupplierDaoMem.getInstance();
            List<Product> productsBySupplier = new ArrayList<>();


            //Getting the supplierId from response, attach it to supplier and get the products by supplier
            int supplier_id= Integer.parseInt(req.getParameter("supplier_id"));
            Supplier supplierFrom = supplierList.find(supplier_id);
            productsBySupplier= productDataStore.getBy(supplierFrom);



            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());

            //Rendering back the result
            context.setVariable("products",productsBySupplier);
            context.setVariable("categoryList", productCategoryDataStore.getAll());
            context.setVariable("supplierList", supplierDataStore.getAll());
            engine.process("product/index.html", context, resp.getWriter());


        }
    }

