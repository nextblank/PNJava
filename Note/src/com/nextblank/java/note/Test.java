package com.nextblank.java.note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.nextblank.java.note.bean.User;

public class Test {

	public static void main(String[] args) {
		testSplit();
		testAsList();
		testRemoveIemFromList();
		testMapNPE();
		testToArray();
	}
	
	/**
	 * split֮��Ҫ�ж����ָ�����
	 */
	private static void testSplit() {
		String str = "a, b, c, ,";
		String[] array = str.split(",");
		System.out.println(array.length);
	}
	
	/**asList�ķ��ض�����һ�� Arrays �ڲ��࣬��û��ʵ�ּ��ϵ��޸ķ�����
	  * Arrays.asList���ֵ���������ģʽ��ֻ��ת���ӿڣ���̨�������������顣
	  * add/remove/clear �������׳� UnsupportedOperationException �쳣
	  */
	private static void testAsList() {
		String[] str = new String[] { "you", "wu" };
		 List list = Arrays.asList(str);
		 try {
			 list.add("yangguanbao");
		} catch (Exception e) {
			System.out.println("testAsList Exception" +e.getMessage());
		}
		 str[0] = "gujin";
		 System.out.println(list.get(0));
	}
	
	/**
	 * ��Ҫ�� foreach ѭ�������Ԫ�ص� remove/add ������
	 * remove Ԫ����ʹ�� Iterator��ʽ�����������������Ҫ�� Iterator ���������
	 */
	private static void testRemoveIemFromList() {
		List<String> listIterator = new ArrayList<>();
		listIterator.add("1");
		listIterator.add("2");
		Iterator<String> iterator = listIterator.iterator();
		while (iterator.hasNext()) {
			String item = iterator.next();
			if ("2".equals(item)) {
				iterator.remove();
			}
		}
		System.out.println(listIterator.toString());
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		for (String item : list) {
			if ("2".equals(item)) {
				try {
//					list.remove(item);
					System.out.println("testRemoveIemFromList Exception");
				} catch (Exception e) {
					System.out.println("testRemoveIemFromList Exception" + e.getMessage());
				}
			}
		}
	}
	
	/**
	 * concurrentHashMap key �� value ����Ϊnull
	 */
	private static void testMapNPE() {
		User user = new User();
		Map<Integer, Object> hashMap = new HashMap<>();
        hashMap.put(user.getId(), user.getName());
        
        Map<Integer, Object> concurrentHashMap = new ConcurrentHashMap<>();
		try {
			concurrentHashMap.put(user.getId(), user.getName());//�������NullPointerException
		} catch (Exception e) {
			System.out.println("testMapNPE Exception" + e.getMessage());
		}
	}
	
	/**
	 * toArray()���������ʹ�õ����޲εķ������ᱨClassCastException�쳣��
	 * ֱ��ʹ��toArray()�޲η�������ֵֻ����Object[]�࣬��ǿת�������������齫���׳��쳣��
	 */
	private static void testToArray() {
		List<String> list = new ArrayList<>(2);
		list.add("Hello");
		list.add("junjun");
		String[] array = new String[list.size()];
		array = (String[])list.toArray(array);
//		array = (String[])list.toArray();
		System.out.println(array[1]);
	}
	
}
