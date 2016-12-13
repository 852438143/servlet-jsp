package com.ylw.dao;

import java.util.List;

import com.ylw.entity.Favorite;

public interface FavoriteDao {
	public List<Favorite> list();

	public Favorite add(Favorite favorite);

	public Boolean delete(Favorite favorite);

	public Favorite update(Favorite favorite);

	public Favorite getById(int id);
}
