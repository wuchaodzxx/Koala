package com.wuchao.blog.user.dao.intf;

import com.wuchao.blog.user.po.Iuser;
import com.wuchao.utils.exception.DAOException;

public interface IuserDao {
	public void addIuser(Iuser user) throws DAOException;
	public void updateIuser(Iuser user) throws DAOException;
	public Iuser getIuserByUsername(String username) throws DAOException;
	
}
