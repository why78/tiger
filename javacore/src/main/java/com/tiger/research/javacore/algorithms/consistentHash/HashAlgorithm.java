package com.tiger.research.javacore.algorithms.consistentHash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * hash 算法
 * 
 * @author Administrator 提供默认的hash的不同算法实现
 */
public enum HashAlgorithm implements IHashInterface {
	RS_HASH, JS_HASH, FNV_HASH, KETAMA_HASH;
	
	/**
	 * hash algorithm 对外接口
	 */
	public Integer hash(Object obj) {
		if (null == obj) {
			return null;
		} else {
			switch (this) {
			case RS_HASH:
				return RSHash.hash(obj);
			case JS_HASH:
				return JSHash.hash(obj);
			case FNV_HASH:
				return FNVHash.hash(obj);
			case KETAMA_HASH:
				return ketamaHash.hash(obj);
			default:
				return null;
			}
		}
	}

	/**
	 * RS Hash
	 * 结合magic number 计算
	 */
	IHashInterface RSHash = new IHashInterface() {
		public Integer hash(Object obj) {
			String str = obj.toString();
			int b = 378551;
			int a = 63689;
			long hash = 0;

			for (int i = 0; i < str.length(); i++) {
				hash = hash * a + str.charAt(i);
				a = a * b;
			}

			return (int) hash;
		}
	};

	/**
	 * JS Hash
	 * 
	 */
	IHashInterface JSHash = new IHashInterface() {
		public Integer hash(Object obj) {
			// 防止空字符串 直接返回0
			if (null == obj) {
				return 0;
			} else {
				String str = obj.toString();
				long hash = 1315423911;
				for (int i = 0; i < str.length(); i++) {
					hash ^= ((hash << 5) + str.charAt(i) + (hash >> 2));
				}
				return (int) hash;
			}
		}
	};
	
	/**
	 * PJ hash
	 */
	IHashInterface PJHash = new IHashInterface() {
		public Integer hash(Object obj) {
			if (null == obj) {
				return 0;
			} else {
				String str = obj.toString();
				long BitsInUnsignedInt = (long) (4 * 8);
				long ThreeQuarters = (long) ((BitsInUnsignedInt * 3) / 4);
				long OneEighth = (long) (BitsInUnsignedInt / 8);
				long HighBits = (long) (0xFFFFFFFF) << (BitsInUnsignedInt - OneEighth);
				long hash = 0;
				long test = 0;

				for (int i = 0; i < str.length(); i++) {
					hash = (hash << OneEighth) + str.charAt(i);

					if ((test = hash & HighBits) != 0) {
						hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
					}
				}
				return (int) hash;
			}
		}
	};
	
	/**
	 * ELF hash
	 */
	IHashInterface ELFHash = new IHashInterface() {
		public Integer hash(Object obj) {
			if (null == obj) {
				return 0;
			} else {
				String str = obj.toString();
				long hash = 0;
				long x = 0;

				for (int i = 0; i < str.length(); i++) {
					hash = (hash << 4) + str.charAt(i);

					if ((x = hash & 0xF0000000L) != 0) {
						hash ^= (x >> 24);
					}
					hash &= ~x;
				}

				return (int) hash;
			}
		}
	};
	
	/**
	 * BKDR hash
	 * 根据指定的seed 针对指定的字符串不同位置的char叠加计算
	 * 这种方式也是目前字符串hash计算的常用方式
	 */
	IHashInterface BKDRHash = new IHashInterface() {
		public Integer hash(Object obj) {
			if (null == obj) {
				return 0;
			} else {
				String str = obj.toString();
				long seed = 131; // 31 131 1313 13131 131313 etc..
				long hash = 0;

				for (int i = 0; i < str.length(); i++) {
					hash = (hash * seed) + str.charAt(i);
					// 如上计算也可以使用如下的计算方式
					// hash = hash << 7 + hash << 2 + hash + str.charAt(i)
					
				}
				return (int) hash;
			}
		}
	};
	
	/**
	 * SDBMHash
	 * 该算法其实和BKDRHash差不多 只不过seed不一样而已
	 */
	IHashInterface SDBMHash = new IHashInterface() {
		public Integer hash(Object obj) {
			if (null == obj) {
				return 0;
			} else {
				String str = obj.toString();
				long hash = 5381;

				for (int i = 0; i < str.length(); i++) {
					hash = ((hash << 5) + hash) + str.charAt(i);
					// 也可以使用如下方式
					// hash = 5381 * hash + str.charAt(i)
				}

				return (int) hash;
			}
		}
	};
	
	/**
	 * DJB hash
	 */
	IHashInterface DJBHash = new IHashInterface() {
		public Integer hash(Object obj) {
			if (null == obj) {
				return 0;
			} else {
				String str = obj.toString();
				long hash = 0;
				for (int i = 0; i < str.length(); i++) {
					hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
				}

				return (int) hash;
			}
		}
	};
	
	/**
	 * DEK hash
	 */
	IHashInterface DEKHash = new IHashInterface() {
		public Integer hash(Object obj) {
			if (null == obj) {
				return 0;
			} else {
				String str = obj.toString();
				long hash = str.length();

				for (int i = 0; i < str.length(); i++) {
					hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
				}

				return (int) hash;
			}
		}
	};
	
	/**
	 * BP hash
	 */
	IHashInterface BPHash = new IHashInterface() {
		public Integer hash(Object obj) {
			if (null == obj) {
				return 0;
			} else {
				String str = obj.toString();
				long hash = 0;

				for (int i = 0; i < str.length(); i++) {
					hash = hash << 7 ^ str.charAt(i);
				}

				return (int) hash;
			}
		}
	};
	
	/**
	 * FNV hash
	 */
	IHashInterface FNVHash = new IHashInterface() {
		public Integer hash(Object obj) {
			if (null == obj) {
				return 0;
			} else {
				String str = obj.toString();
				long fnv_prime = 0x811C9DC5;
				long hash = 0;

				for (int i = 0; i < str.length(); i++) {
					hash *= fnv_prime;
					hash ^= str.charAt(i);
				}

				return (int) hash;
			}
		}
	};
	
	/**
	 * ap hash 
	 */
	IHashInterface APHash = new IHashInterface() {
		public Integer hash(Object obj) {
			if (null == obj) {
				return 0;
			} else {
				String str = obj.toString();
				long hash = 0xAAAAAAAA;

				for (int i = 0; i < str.length(); i++) {
					if ((i & 1) == 0) {
						hash ^= ((hash << 7) ^ str.charAt(i) * (hash >> 3));
					} else {
						hash ^= (~((hash << 11) + str.charAt(i) ^ (hash >> 5)));
					}
				}
				return (int) hash;
			}
		}
	};
	
	
	/**
	 * 
	 * ketama hash
	 */
	private static MessageDigest md5;
	IHashInterface ketamaHash = new IHashInterface() {
		public Integer hash(Object obj) {
			if (null == obj) {
				return 0;
			} else {
				String str = obj.toString();
				long hash = 0xAAAAAAAA;
				if (md5 == null) {
					try {
						md5 = MessageDigest.getInstance("MD5");
					} catch (NoSuchAlgorithmException e) {
						throw new IllegalStateException("no md5 algorythm found");
					}
				}
				md5.reset();
				md5.update(str.getBytes());
				byte[] bKey = md5.digest();
				long res = ((long) (bKey[3] & 0xFF) << 24) | ((long) (bKey[2] & 0xFF) << 16)
						| ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF);
				hash = res & 0xffffffffL;
				return (int) hash;
			}
		}
	};

}
