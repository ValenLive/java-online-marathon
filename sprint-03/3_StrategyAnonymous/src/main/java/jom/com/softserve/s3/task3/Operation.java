package jom.com.softserve.s3.task3;

public class Operation {
	public static double addAtoB(int a, int b) {
		return execute(a, b, (a1, b1) -> (a1 + b1));
	}

	public static double subtractBfromA(int a, int b) {
		return execute(a, b, (a1, b1) -> (a1 - b1));
	}

	public static double multiplyAbyB(int a, int b) {
		return execute(a, b, (a1, b1) -> (a1 * b1));
	}

	public static double divideAbyB(int a, int b) {
		return execute(a, b, (a1, b1) -> (a1 / b1));
	}
}
