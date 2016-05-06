package com.hogan.staticfactorydemo;


/**
 * @���� ch
 * @���� �����̳߳أ����ٶ��ڴ��ռ��<��̬������ Message��MotionEvent�Լ�Parcel�������ַ���>
 * @ʱ�� 2015��5��26�� ����10:45:08
 */
public final class PeopleBean {
	private String name;
	private String age;

	// ���������е���һ������
	private PeopleBean nextObject;
	// ͬ����
	private static final Object sPoolSync = new Object();

	// ������е�һ�����õĶ���
	private static PeopleBean sPool;

	private static int sPoolSize = 0;

	private static final int MAX_POOL_SIZE = 30;

	/**
	 * ֻ����obtain()������ȡ����
	 * */
	private PeopleBean() {
	}

	/**
	 * ���ػ��ն�����ߵ������Ϊ��ʱ����һ���µĶ���
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
	 * ���ոö��󡣵��ø÷�������Ҫ�ͷ����иö���ʵ��������
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
