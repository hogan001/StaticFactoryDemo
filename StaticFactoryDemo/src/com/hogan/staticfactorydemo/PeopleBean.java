package com.hogan.staticfactorydemo;


/**
 * @author chenhu
 * @描述  静态工厂法定义一个Bean
 * @时间 2016-5-6 下午3:13:53
 * */
public final class PeopleBean {
	private String name;
	private String age;

	private PeopleBean nextObject;
	
	private static final Object sPoolSync = new Object();


	private static PeopleBean sPool;

	private static int sPoolSize = 0;

	private static final int MAX_POOL_SIZE = 30;


	private PeopleBean() {
	}



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
