package com.robtova.modern;

import com.jme3.math.Vector3f;

public class Maths {
	
	public static Vector3f maxVec(Vector3f vec, Vector3f vec1) {
		if(vec.length() > vec1.length()) return vec;
		else return vec1;
	}
}
