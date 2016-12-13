package com.ylw.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ylw.dao.FavoriteDao;
import com.ylw.entity.Favorite;
import com.ylw.util.JdbcUtil;

public class FavoriteImpl implements FavoriteDao {

	@Override
	public Favorite getById(int id) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from tb_favorites where id=?";
		Favorite favorite = new Favorite();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			if (pstmt.execute()) {
				ResultSet rs = pstmt.getResultSet();
				while (rs.next()) {
					favorite.setId(id);
					favorite.setName(rs.getString(2));
					favorite.setUrl(rs.getString(3));
				}
				return favorite;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}

		return null;
	}

	@Override
	public List<Favorite> list() {
		Connection conn = null;
		String sql = "select * from tb_favorites";
		List<Favorite> favorites = new ArrayList<Favorite>();
		try {
			conn = JdbcUtil.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			Favorite favorite = null;
			while (rs.next()) {
				favorite = new Favorite();
				favorite.setId(rs.getInt(1));
				favorite.setName(rs.getString(2));
				favorite.setUrl(rs.getString(3));
				favorites.add(favorite);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return favorites;
	}

	@Override
	public Favorite add(Favorite favorite) {
		Connection conn = JdbcUtil.getConnection();
		// 因为id是自增，不用添加id的值，所以要在前面表示添加数据的列
		String sql = "insert into tb_favorites(name,url) values(?,?)";
		Favorite favorite1 = new Favorite();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, favorite.getName());
			pstmt.setString(2, favorite.getUrl());
			if (pstmt.execute()) {
				ResultSet rs = pstmt.getResultSet();
				while (rs.next()) {
					favorite1.setName(rs.getString(2));
					favorite1.setUrl(rs.getString(3));
				}
				return favorite1;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}

		return null;
	}

	@Override
	public Boolean delete(Favorite favorite) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "delete from tb_favorites where id=? and name =? and url = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, favorite.getId());
			pstmt.setString(2, favorite.getName());
			pstmt.setString(3, favorite.getUrl());
			pstmt.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}

		return false;

	}

	@Override
	public Favorite update(Favorite favorite) {
		Connection conn = JdbcUtil.getConnection();
		// update是col_name 和col2_name之间用“，”隔开不是and
		String sql = "update tb_favorites set name =? , url = ? where id = ?";
		Favorite favorite1 = new Favorite();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, favorite.getName());
			pstmt.setString(2, favorite.getUrl());
			pstmt.setInt(3, favorite.getId());
			System.out.println("--------" + favorite);
			if (pstmt.execute()) {
				ResultSet rs = pstmt.getResultSet();
				System.out.println("rs" + rs.getRow());
				while (rs.next()) {
					favorite1.setId(rs.getInt(1));
					favorite1.setName(rs.getString(2));
					favorite1.setUrl(rs.getString(3));
				}
				return favorite1;
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}

		return null;
	}
}
