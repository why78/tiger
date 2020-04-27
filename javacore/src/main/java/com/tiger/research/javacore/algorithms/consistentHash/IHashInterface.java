package com.tiger.research.javacore.algorithms.consistentHash;

/**
 * 
 * @author Administrator
 *
 *	计算hash
 */
public interface IHashInterface {
	/**
	 * 计算指定的内容的hash
	 * @param hash
	 * @return
	 */
	Integer hash(Object hash);
}
