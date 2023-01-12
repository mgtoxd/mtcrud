package pers.mtx.connect;


/**
 * 数据库连接池接口
 */
public interface DataSource {

	/**
	 * 获取数据库连接
	 * @return 连接
	 */
	PoolConnection getDataSource();
}
