package com.wuchao.blog.system.bo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuchao.blog.system.bo.intf.IsystemBo;
import com.wuchao.blog.system.dao.intf.IsystemDao;
import com.wuchao.blog.system.po.Isystem;
import com.wuchao.blog.user.dao.intf.IuserDao;
import com.wuchao.utils.exception.DAOException;
@Service("isystemBo")
@Transactional
public class IsystemBoImpl implements IsystemBo {
	@Resource(name="isystemDao")
	public IsystemDao isystemDao;

	public void addIsystem(Isystem isystem) throws DAOException {
		isystemDao.addIsystem(isystem);
	}


	public Isystem getIsystemByDefault() throws DAOException {
		// TODO Auto-generated method stub
		return isystemDao.getIsystemByDefault();
	}


	@Override
	public void saveIsystem(Isystem isystem) throws DAOException {
		isystemDao.saveIsystem(isystem);
	}

}
