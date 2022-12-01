package com.inn.cafe.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.cafe.JWT.JwtFilter;
import com.inn.cafe.dao.BillDao;
import com.inn.cafe.dao.CategoryDao;
import com.inn.cafe.dao.ProductDao;
import com.inn.cafe.dao.UserDao;
import com.inn.cafe.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService{

	@Autowired
	BillDao billDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Override
	public ResponseEntity<Map<String, Object>> getCount() {
		try {
			Map<String, Object> map = new HashMap<>();
			if(jwtFilter.isAdmin()) {
				map.put("category", categoryDao.count());
				map.put("product", productDao.count());
				map.put("bill", billDao.count());
				map.put("users", userDao.count());
				return new ResponseEntity<>(map, HttpStatus.OK);
			}
			else {
				map.put("category", categoryDao.count());
				map.put("product", productDao.getProductByStatus());
				map.put("bill", billDao.getBillCountByUserName(jwtFilter.getCurrentuser()));
				return new ResponseEntity<>(map, HttpStatus.OK);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
