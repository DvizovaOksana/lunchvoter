package ru.lunchvoter.web;

import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestaurantsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoggerFactory.getLogger(RestaurantsServlet.class).debug("redirect to rests");
        resp.sendRedirect("restaurants.jsp");
    }
}
