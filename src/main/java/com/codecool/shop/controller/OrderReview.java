package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.Line;
import java.io.IOException;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.order.*;


@WebServlet(urlPatterns = {"/order"})
public class OrderReview extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Production code
//        HttpSession session = req.getSession(true);
//        Order order = (Order) session.getAttribute("order");


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        //Production code
        //context.setVariable("lineItems", shoppingCart.getLineItems());

        //Test code until parts of the app are not implemented
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        Product product = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        LineItem lineItem = new LineItem(product, 2);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addLineItem(lineItem);
        Order order = new Order();
        order.setShoppingCart(shoppingCart);

        context.setVariable("order", order);
        engine.process("product/order.html", context, resp.getWriter());
    }
}
