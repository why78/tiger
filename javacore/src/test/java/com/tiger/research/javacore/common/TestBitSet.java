/**
 *
 * Project Name:	hbec-app-message-migration
 * File Name:	TestBitSet.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年6月13日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.common;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
 * @author WangHuiyuan
 *
 */
public class TestBitSet {

	//全量bitset
	private static BitSet allBitSet = new BitSet();
	//偶数bitset
	private static BitSet evenBitSet = new BitSet();
	//奇数bitset
	private static BitSet oddBitSet = new BitSet();
	//空bitset
	private static BitSet emptyBitSet = new BitSet();

	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < 63; i++) {
		    allBitSet.set(i);
		    if (i % 2 == 0) {
		        evenBitSet.set(i);
		    }else{
		        oddBitSet.set(i);
		    }
		}
	}
	
    //测试初始化
    @Test
    public void testInit(){
        //断点进去看
        BitSet initBitSet1 = new BitSet(55);
        BitSet initBitSet2 = new BitSet(129);
    }
    
    //测试基础的and\or\xor运算
    @Test
    public void testOper(){
        //System.out.println(evenBitSet.toByteArray());
        evenBitSet.and(allBitSet);
        System.out.println("偶数Bit and 全量Bit："+evenBitSet);
        evenBitSet.xor(allBitSet);
        System.out.println("偶数Bit xor 全量Bit："+evenBitSet);
        evenBitSet.or(allBitSet);
        System.out.println("偶数Bit or 全量Bit："+evenBitSet);
    }

    @Test
    public void testIndex() {
    	BitSet bitSet = new BitSet();
    	for(int i=0; i<=9; i++)
    		bitSet.set(i);
    	BitSet readSet = new BitSet();
    	readSet.set(5);
    	readSet.set(6);
    	List<Integer> list = new ArrayList<>();
    	for (int i = bitSet.nextSetBit(1); i >= 0; i = bitSet.nextSetBit(i + 1)) {
			list.add(i);
		}
    	System.out.println(JSON.toJSONString(list));
    }
    
    @Test
    public void testXor() {
    	BitSet bitSet = new BitSet();
    	for(int i=0; i<=9; i++)
    		bitSet.set(i);
    	bitSet.set(2,false);
    	bitSet.set(4,false);
    	BitSet readSet = new BitSet();
    	readSet.set(5);
    	readSet.set(6);
    	BitSet tmpSet = (BitSet) readSet.clone();
    	tmpSet.xor(bitSet);
    	
    	System.out.println("total[" + tmpSet.cardinality() + "]:" + tmpSet);
    	System.out.println("len: " + tmpSet.length());
    	System.out.println("size: " + tmpSet.size());
    	System.out.println("string: " + tmpSet.toString());

    	List<Integer> list = new ArrayList<>();
    	for (int i = bitSet.nextSetBit(1); i >= 0; i = bitSet.nextSetBit(i + 1)) {
			list.add(i);
		}
    	

    	System.out.println("last: " + list.get(list.size()-1));
    	int count = 5;
    	int n = 0;
    	for(int i= list.size()-1; i>=0; i--) {
    		System.out.println(list.get(i));
    		if(n >= count)
    			break;
    		n++;
    	}
    	
    }

}
