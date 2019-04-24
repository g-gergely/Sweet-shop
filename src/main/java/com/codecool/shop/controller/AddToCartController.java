package com.codecool.shop.controller;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.order.LineItem;
import com.codecool.shop.model.order.Order;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name="add-to-cart", urlPatterns = {"/add/"})
public class AddToCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();

        Product product = productDataStore.find(Integer.parseInt(req.getParameter("id")));

        HttpSession session = req.getSession();
        Order order;
        if (session.getAttribute("order") == null) {
            order = new Order();
        } else {
            order = (Order) session.getAttribute("order");
        }
        LineItem lineItem = new LineItem(product, 1);
        order.getShoppingCart().addLineItem(lineItem);
        session.setAttribute("order", order);

        System.out.println(((Order) session.getAttribute("order")).getShoppingCart().getLineItems());

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
