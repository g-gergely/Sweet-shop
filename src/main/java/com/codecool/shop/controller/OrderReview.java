package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.codecool.shop.model.order.*;


@WebServlet(urlPatterns = {"/order"})
public class OrderReview extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Order order = (session.getAttribute("order") == null) ? new Order() : (Order) session.getAttribute("order");


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("order", order);
        context.setVariable("linkText", "Back");
        context.setVariable("url", req.getContextPath() + ProductController.getHomeUrl());
        context.setVariable("linkId", "back-link");
        engine.process("product/order.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         HttpSession session = req.getSession(true);
         Order order = (Order) session.getAttribute("order");
         int lineItemNumber = order.getShoppingCart().getLineItems().size();

         for (int i = 0; i < lineItemNumber; i++) {
             int updatedValue = Integer.parseInt(req.getParameter("quantity_" + i));
             LineItem lineItem = order.getShoppingCart().getLineItems().get(i);
             lineItem.setQuantity(updatedValue);
         }
         for (LineItem lineItem: order.getShoppingCart().getLineItems()) {
             if (lineItem.getQuantity() == 0) {
                order.getShoppingCart().removeLineItem(lineItem);
             }
         }
         doGet(req, resp);
    }
}
