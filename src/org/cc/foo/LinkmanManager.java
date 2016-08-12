package org.cc.foo;

import java.util.Arrays;

/**
 * 业务逻辑核心 （Controller）
 * LinkmanManager不关心界面如何实现，只是提供底层的一个实现
 * @author cc
 *
 */
public class LinkmanManager {

	//用来存放Linkman的容器
	private Linkman data[];
	//指向当前的有效数据下标的下一个位置，类似于栈顶指针一类的玩意儿
	private int index;
	
	//单例
	private LinkmanManager() {
		data=new Linkman[1];
	}
	
	private static LinkmanManager instance;

	public static LinkmanManager getInstance() {
		if(instance==null) instance=new LinkmanManager();
		return instance;
	}
	
	//空间不够用的话就动态扩展，默认策略是变长2，暂时不做复杂考虑
	private void expand(){
		data=Arrays.copyOf(data,data.length*2);
	}
	
	//添加联系人,返回是否添加成功
	public boolean add(Linkman e){
//		for(int i=0;i<data.length;i++){
//			if(data[i]==null){
//				data[i]=e;
//				return true;
//			}
//		}
//		//如果没找到位置的话，就拓展，同时递归调用，直到找到位置插入(事实上，第二次就会找到的，如果扩展成功的话)
//		expand();
//		return add(e);
		
		/*-------------重写，改成数据是连续的-----------------*/
		//快要越界的时候扩展数组空间
		if(index==data.length) expand();
		data[index++]=e;
		return true;
	}
	
	//根据名字删除,返回是否删除成功
	public boolean remove(String name){
//		for(int i=0;i<data.length;i++){
//			if(data[i]!=null && name.equalsIgnoreCase(data[i].getName())){
//				data[i]=null;
//				return true;
//			}
//		}
//		return false;
		
		
		/*-------------重写，改成数据是连续的-----------------*/
		//挨个移动的策略太逊了，反正是无序的，只要直接将最后一个非空拿到这里填上就可以啦 ，这个复杂度是 O(n)，降了一个数量级
		for(int i=0;i<index;i++){
			if(name.equalsIgnoreCase(data[i].getName())){
				//直接将最后一个拿过来填上就可以了
				data[i]=data[--index];
				return true;
			}
		}
		return false;
	}
	
	//修改,返回是否修改成功
	public boolean modify(String name,Linkman e){
//		for(int i=0;i<data.length;i++){
//			if(data[i]!=null && name.equalsIgnoreCase(data[i].getName())){
//				data[i]=e;
//				return true;
//			}
//		}
//		return false;
		
		
		/*-------------重写，改成数据是连续的-----------------*/
		for(int i=0;i<index;i++){
			if(name.equalsIgnoreCase(data[i].getName())){
				data[i]=e;
				return true;
			}
		}
		return false;
	}
	
	//根据名字加载，未找到则返回null
	public Linkman load(String name){
//		for(int i=0;i<data.length;i++){
//			if(data[i]!=null && name.equalsIgnoreCase(data[i].getName())){
//				return data[i];
//			}
//		}
//		return null;
		
		
		/*-------------重写，改成数据是连续的-----------------*/
		for(int i=0;i<index;i++){
			if(name.equalsIgnoreCase(data[i].getName())) return data[i];
		}
		return null;
	}
	
	//列出所有，以无缝隙(null)的形式返回
	public Linkman[] list(){
//		Linkman b[]=new Linkman[data.length];
//		int index=0;
//		for(int i=0;i<data.length;i++){
//			if(data[i]!=null){
//				b[index++]=data[i];
//			}
//		}
//		return Arrays.copyOf(b,index);
		
		
		/*-------------重写，改成数据是连续的-----------------*/
		return Arrays.copyOf(data,index);
	}
	
	//返回联系人统计
	public int count(){
		return index;
	}
}
