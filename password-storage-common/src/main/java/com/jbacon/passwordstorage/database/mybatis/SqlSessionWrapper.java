package com.jbacon.passwordstorage.database.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

public final class SqlSessionWrapper {

	private final SqlSession databaseConnection;

	protected SqlSessionWrapper(final SqlSession openSession) {
		databaseConnection = openSession;
	}

	private void after() {
		databaseConnection.commit();
		databaseConnection.close();
	}

	public int delete(final String statement) {
		int result = databaseConnection.delete(statement);
		after();
		return result;
	}

	public int delete(final String statement, final Object parameter) {
		int result = databaseConnection.delete(statement, parameter);
		after();
		return result;
	}

	public SqlSession getDatabaseConnection() {
		return databaseConnection;
	}

	public int insert(final String statement) {
		int result = databaseConnection.insert(statement);
		after();
		return result;
	}

	public int insert(final String statement, final Object parameter) {
		int result = databaseConnection.insert(statement, parameter);
		after();
		return result;
	}

	public void select(final String statement, final Object parameter, final ResultHandler handler) {
		databaseConnection.select(statement, parameter, handler);
		after();
	}

	public void select(final String statement, final Object parameter, final RowBounds rowBounds, final ResultHandler handler) {
		databaseConnection.select(statement, parameter, rowBounds, handler);
		after();
	}

	public void select(final String statement, final ResultHandler handler) {
		databaseConnection.select(statement, handler);
		after();
	}

	public List<?> selectList(final String statement) {
		List<?> result = databaseConnection.selectList(statement);
		after();
		return result;
	}

	public List<?> selectList(final String statement, final Object parameter) {
		List<?> result = databaseConnection.selectList(statement, parameter);
		after();
		return result;
	}

	public List<?> selectList(final String statement, final Object parameter, final RowBounds rowBounds) {
		List<?> result = databaseConnection.selectList(statement, parameter, rowBounds);
		after();
		return result;
	}

	public Map<?, ?> selectMap(final String statement, final Object parameter, final String mapKey) {
		Map<?, ?> result = databaseConnection.selectMap(statement, parameter, mapKey);
		after();
		return result;
	}

	public Map<?, ?> selectMap(final String statement, final Object parameter, final String mapKey, final RowBounds rowBounds) {
		Map<?, ?> result = databaseConnection.selectMap(statement, parameter, mapKey, rowBounds);
		after();
		return result;
	}

	public Map<?, ?> selectMap(final String statement, final String mapKey) {
		Map<?, ?> result = databaseConnection.selectMap(statement, mapKey);
		after();
		return result;
	}

	public Object selectOne(final String statement) {
		Object result = databaseConnection.selectOne(statement);
		after();
		return result;
	}

	public Object selectOne(final String statement, final Object parameter) {
		Object result = databaseConnection.selectOne(statement, parameter);
		after();
		return result;
	}

	public int update(final String statement) {
		int result = databaseConnection.update(statement);
		after();
		return result;
	}

	public int update(final String statement, final Object parameter) {
		int result = databaseConnection.update(statement, parameter);
		after();
		return result;
	}
}
