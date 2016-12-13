package com.ylw.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ylw.dao.FavoriteDao;
import com.ylw.entity.Favorite;
import com.ylw.impl.FavoriteImpl;

public class AddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null && action.equals("add")) {
			add(req, resp);
		} else if (action != null && action.equals("edit")) {
			edit(req, resp);
		} else if (action != null && action.equals("list")) {
			list(req, resp);
		} else if (action != null && action.equals("delete")) {
			delete(req, resp);
		} else {
		}

	}

	private void add(HttpServletRequest req, HttpServletResponse resp) {
		Favorite favorite = new Favorite();
		FavoriteDao fd = new FavoriteImpl();
		String name = req.getParameter("name");
		String url = req.getParameter("url");
		favorite.setName(name);
		favorite.setUrl(url);
		fd.add(favorite);
		list(req, resp);
	}

	private void edit(HttpServletRequest req, HttpServletResponse resp) {
		FavoriteDao favoriteDao = new FavoriteImpl();
		Favorite favorite = new Favorite();

		favorite.setId(Integer.parseInt(req.getParameter("id")));
		favorite.setName(req.getParameter("name"));
		favorite.setUrl(req.getParameter("url"));
		System.out.println("edit:  " + favorite);
		favoriteDao.update(favorite);
		list(req, resp);
	}

	private void list(HttpServletRequest req, HttpServletResponse resp) {
		FavoriteDao favoriteDao = new FavoriteImpl();
		List<Favorite> favorites = new ArrayList<Favorite>();

		try {
			favorites = favoriteDao.list();
			System.out.println("favorites" + favorites.size());
			req.setAttribute("favorites", favorites);
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		FavoriteDao favoriteDao = new FavoriteImpl();
		Favorite favorite = null;
		String[] ids = req.getParameterValues("ids");
		for (String id : ids) {
			System.out.println("delete: " + id);
			favorite = favoriteDao.getById(Integer.parseInt(id));
			System.out.println("delete: " + favorite);
			favoriteDao.delete(favorite);
		}
		list(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Favorite favorite = new Favorite();
		// List<Favorite> favorites = new ArrayList<Favorite>();
		// FavoriteDao favoriteDao = new FavoriteImpl();
		// favorite.setName(req.getParameter("name"));
		// favorite.setUrl(req.getParameter("url"));
		// System.out.println(favorite);
		// req.setAttribute("favorite", favoriteDao.add(favorite));
		// req.setAttribute("favorites", favoriteDao.list());
		// req.getRequestDispatcher("/index.jsp").forward(req, resp);
		doGet(req, resp);
	}

}
