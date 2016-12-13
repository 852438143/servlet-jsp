package com.ylw.test;

import org.junit.Test;

import com.ylw.dao.FavoriteDao;
import com.ylw.entity.Favorite;
import com.ylw.impl.FavoriteImpl;

public class TestDemo {
	@Test
	public void test1() {
		FavoriteDao dao = new FavoriteImpl();
		for (Favorite temp : dao.list()) {
			System.out.println(temp);
		}
	}

	@Test
	public void test_get() {
		FavoriteDao dao = new FavoriteImpl();
		System.out.println(dao.getById(8));
	}

	@Test
	public void test_delete() {
		FavoriteDao dao = new FavoriteImpl();
		System.out.println(dao.delete(dao.getById(1)));
	}

	@Test
	public void test_update() {
		FavoriteDao dao = new FavoriteImpl();
		Favorite favorite = dao.getById(2);
		System.out.println(favorite);
		favorite.setUrl("http://wwwwwww");
		System.out.println(dao.update(favorite));
	}

	@Test
	public void test01() {
		int t = Integer.getInteger("123");
		System.out.println(t);
	}
}
