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
	 * split之后要判断最后分隔内容
	 */
	private static void testSplit() {
		String str = "a, b, c, ,";
		String[] array = str.split(",");
		System.out.println(array.length);
	}
	
	/**asList的返回对象是一个 Arrays 内部类，并没有实现集合的修改方法。
	  * Arrays.asList体现的是适配器模式，只是转换接口，后台的数据仍是数组。
	  * add/remove/clear 方法会抛出 UnsupportedOperationException 异常
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
	 * 不要在 foreach 循环里进行元素的 remove/add 操作。
	 * remove 元素请使用 Iterator方式，如果并发操作，需要对 Iterator 对象加锁。
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
	 * concurrentHashMap key 和 value 不能为null
	 */
	private static void testMapNPE() {
		User user = new User();
		Map<Integer, Object> hashMap = new HashMap<>();
        hashMap.put(user.getId(), user.getName());
        
        Map<Integer, Object> concurrentHashMap = new ConcurrentHashMap<>();
		try {
			concurrentHashMap.put(user.getId(), user.getName());//这里出现NullPointerException
		} catch (Exception e) {
			System.out.println("testMapNPE Exception" + e.getMessage());
		}
	}
	
	/**
	 * toArray()方法，如果使用的是无参的方法，会报ClassCastException异常。
	 * 直接使用toArray()无参方法返回值只能是Object[]类，若强转成其它类型数组将会抛出异常。
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
