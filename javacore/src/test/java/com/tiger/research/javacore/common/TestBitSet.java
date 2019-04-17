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
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.longlong.LongBitmapDataProvider;
import org.roaringbitmap.longlong.LongConsumer;
import org.roaringbitmap.longlong.LongIterator;
import org.roaringbitmap.longlong.Roaring64NavigableMap;

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
    	int loop = 0;
    	for (int i = bitSet.nextSetBit(1); i >= 0; i = bitSet.nextSetBit(i + 1)) {
			list.add(i);
			loop++;
		}
    	System.out.println("loop n[" + loop + "]");

    	System.out.println("last: " + list.get(list.size()-1));
    	int count = 5;
    	int n = 0;
    	for(int i= list.size()-1; i>=0; i--) {
    		System.out.println(list.get(i));
    		if(n >= count)
    			break;
    		n++;
    	}
    	
    	byte[] arrBytes = tmpSet.toByteArray();
    	BitSet nbs = BitSet.valueOf(arrBytes);
    	System.out.println("New bitset total[" + nbs.cardinality() + "]:" + nbs);
    }
    
    @Test
    public void test() {
    	long start = 1547527564509610l;
    	LongBitmapDataProvider r = Roaring64NavigableMap.bitmapOf(start);
    	for(int i=0; i<10; i++){
    		r.addLong(start + i);
    	}
    	long n = r.getLongCardinality();
    	System.out.println(n);
    	
    	r.forEach(new LongConsumer() {

    	    @Override
    	    public void accept(long value) {
    	      System.out.println(value);
    	      
    	    }
    	});
    }
//    
    @Test
    public void test1() {
    	RoaringBitmap rb = new RoaringBitmap();
        rb.add(0L, 1L << 32);// the biggest bitmap we can create
        System.out.println("memory usage: "+ rb.getSizeInBytes()*1.0/(1L << 32)+" byte per value");
        if(rb.getLongCardinality() != ( 1L << 32))
          throw new RuntimeException("bug!");
    }

}
