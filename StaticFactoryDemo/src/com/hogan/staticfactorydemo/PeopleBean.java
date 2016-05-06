package com.hogan.staticfactorydemo;


/**
 * @作者 ch
 * @描述 运用线程池，减少对内存的占用<静态工厂法 Message、MotionEvent以及Parcel都是这种方法>
 * @时间 2015年5月26日 上午10:45:08
 */
public final class PeopleBean {
	private String name;
	private String age;

	// 引入对象池中的下一个对象
	private PeopleBean nextObject;
	// 同步锁
	private static final Object sPoolSync = new Object();

	// 对象池中第一个可用的对象
	private static PeopleBean sPool;

	private static int sPoolSize = 0;

	private static final int MAX_POOL_SIZE = 30;

	/**
	 * 只能用obtain()方法获取对象
	 * */
	private PeopleBean() {
	}

	/**
	 * 返回回收对象或者当对象池为空时创建一个新的对象
	 * */

	public static PeopleBean obtain() {
		synchronized (sPoolSync) {
			if (sPool != null) {
				PeopleBean del = sPool;
				sPool = del.nextObject;
				del.nextObject = null;
				sPoolSize--;
				return del;
			}
		}
		return new PeopleBean();
	}

	/**
	 * 回收该对象。调用该方法后需要释放所有该对象实例的引用
	 * */

	public void recycle() {
		synchronized (sPoolSync) {
			if (sPoolSize < MAX_POOL_SIZE) {
				nextObject = sPool;
				sPool = this;
				sPoolSize++;
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
