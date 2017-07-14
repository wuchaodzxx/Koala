package com.wuchao.blog.system.bo.intf;

import com.wuchao.blog.system.po.Isystem;
import com.wuchao.utils.exception.DAOException;

public interface IsystemBo {
	//系统表的操作，理论上系统表只有一条记录
	public void addIsystem(Isystem isystem) throws DAOException;
	//更新系统表
	public void saveIsystem(Isystem isystem) throws DAOException;
	//获取系统表记录，默认系统名为admin
	public Isystem getIsystemByDefault() throws DAOException;
}
