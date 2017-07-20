package com.wuchao.blog.user.bo.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuchao.blog.user.bo.intf.IuserBo;
import com.wuchao.blog.user.controller.LoginController;
import com.wuchao.blog.user.dao.intf.IuserDao;
import com.wuchao.blog.user.po.Iuser;
import com.wuchao.utils.exception.DAOException;

@Service("iuserBo")
@Transactional
public class IuserBoImpl implements IuserBo {
	@Resource(name="iuserDao")
	public IuserDao iuserDao;
	
	private static Logger log = Logger.getLogger(IuserBoImpl.class);  
	

	public void addIuser(Iuser user) throws DAOException {
		iuserDao.addIuser(user);
	}


	public Iuser getIuserByUsername(String username) throws DAOException {
		// TODO Auto-generated method stub
		Iuser user = null;
		try {
			user = iuserDao.getIuserByUsername(username);
		}catch(DAOException e) {
			log.error(e.getDetailMessage());
			throw new DAOException();
		}
		return user;
		
	}


	@Override
	public void updateIuser(Iuser user) throws DAOException {
		// TODO Auto-generated method stub
		iuserDao.updateIuser(user);
	}

}
