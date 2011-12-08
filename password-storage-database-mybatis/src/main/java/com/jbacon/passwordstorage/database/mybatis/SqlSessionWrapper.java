package com.jbacon.passwordstorage.database.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public final class SqlSessionWrapper {

	private static SqlSession DATABASE_CONNECTION;
	private final SqlSessionFactory sqlSessionFactory;

	protected SqlSessionWrapper(final SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private void after() {
		DATABASE_CONNECTION.commit();
		DATABASE_CONNECTION.close();
	}

	private void before() {
		DATABASE_CONNECTION = sqlSessionFactory.openSession();
	}

	public int delete(final String statement) {
		before();
		int result = DATABASE_CONNECTION.delete(statement);
		after();
		return result;
	}

	public int delete(final String statement, final Object parameter) {
		before();
		int result = DATABASE_CONNECTION.delete(statement, parameter);
		after();
		return result;
	}

	public int insert(final String statement) {
		before();
		int result = DATABASE_CONNECTION.insert(statement);
		after();
		return result;
	}

	public int insert(final String statement, final Object parameter) {
		before();
		int result = DATABASE_CONNECTION.insert(statement, parameter);
		after();
		return result;
	}

	public void select(final String statement, final Object parameter, final ResultHandler handler) {
		before();
		DATABASE_CONNECTION.select(statement, parameter, handler);
		after();
	}

	public void select(final String statement, final Object parameter, final RowBounds rowBounds, final ResultHandler handler) {
		before();
		DATABASE_CONNECTION.select(statement, parameter, rowBounds, handler);
		after();
	}

	public void select(final String statement, final ResultHandler handler) {
		before();
		DATABASE_CONNECTION.select(statement, handler);
		after();
	}

	public List<?> selectList(final String statement) {
		before();
		List<?> result = DATABASE_CONNECTION.selectList(statement);
		after();
		return result;
	}

	public List<?> selectList(final String statement, final Object parameter) {
		before();
		List<?> result = DATABASE_CONNECTION.selectList(statement, parameter);
		after();
		return result;
	}

	public List<?> selectList(final String statement, final Object parameter, final RowBounds rowBounds) {
		before();
		List<?> result = DATABASE_CONNECTION.selectList(statement, parameter, rowBounds);
		after();
		return result;
	}

	public Map<?, ?> selectMap(final String statement, final Object parameter, final String mapKey) {
		before();
		Map<?, ?> result = DATABASE_CONNECTION.selectMap(statement, parameter, mapKey);
		after();
		return result;
	}

	public Map<?, ?> selectMap(final String statement, final Object parameter, final String mapKey, final RowBounds rowBounds) {
		before();
		Map<?, ?> result = DATABASE_CONNECTION.selectMap(statement, parameter, mapKey, rowBounds);
		after();
		return result;
	}

	public Map<?, ?> selectMap(final String statement, final String mapKey) {
		before();
		Map<?, ?> result = DATABASE_CONNECTION.selectMap(statement, mapKey);
		after();
		return result;
	}

	public Object selectOne(final String statement) {
		before();
		Object result = DATABASE_CONNECTION.selectOne(statement);
		after();
		return result;
	}

	public Object selectOne(final String statement, final Object parameter) {
		before();
		Object result = DATABASE_CONNECTION.selectOne(statement, parameter);
		after();
		return result;
	}

	public int update(final String statement) {
		before();
		int result = DATABASE_CONNECTION.update(statement);
		after();
		return result;
	}

	public int update(final String statement, final Object parameter) {
		before();
		int result = DATABASE_CONNECTION.update(statement, parameter);
		after();
		return result;
	}
}
