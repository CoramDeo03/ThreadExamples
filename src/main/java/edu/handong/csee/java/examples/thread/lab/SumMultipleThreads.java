package edu.handong.csee.java.examples.thread.lab;

import java.util.ArrayList;

public class SumMultipleThreads extends Thread {
	public static void main(String[] args) {
		SumMultipleThreads myThread = new SumMultipleThreads();
	       myThread.start();
		long to = 10000000;
		ArrayList<SumRunner> sumRunners = new ArrayList<SumRunner>();
		ArrayList<Thread> threadsForSubSum = new ArrayList<Thread>();

		/* Let a thread compute a sub-sum.
		 * sumRunners.add(new SumMultipleThreads(1,1000000));
		 * sumRunners.add(new SumMultipleThreads(1000001,2000000));
		 * sumRunners.add(new SumMultipleThreads(2000001,3000000));
		 * sumRunners.add(new SumMultipleThreads(3000001,4000000));
		 * sumRunners.add(new SumMultipleThreads(4000001,5000000));
		 * sumRunners.add(new SumMultipleThreads(5000001,6000000));
		 * sumRunners.add(new SumMultipleThreads(6000001,7000000));
		 * sumRunners.add(new SumMultipleThreads(7000001,8000000));
		 * sumRunners.add(new SumMultipleThreads(8000001,9000000));
		 * sumRunners.add(new SumMultipleThreads(9000001,10000000));*/
		for(long i=0; i<to/1000000; i++) {
			SumRunner currentRunner = new SumRunner((i*1000000)+1, (i+1)*1000000); // 초기값 설정
			sumRunners.add(currentRunner); // class 넣는 list에 추가. 
			Thread thread = new Thread(currentRunner);//thread 생성
			thread.start();//해당 thread 시작
			threadsForSubSum.add(thread);//thread 넣는 list
			System.out.println("Thread-" + i + " started!");
		}

		long grandTotal = 0;
		for (Thread thread : threadsForSubSum) {//모든 thread가 끝날 때까지
		    try { 
		        thread.join(); // 각 스레드의 종료를 기다림
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}
		for(SumRunner runner:sumRunners) {// 결과 값들 모두 저장
			grandTotal += runner.totalSum;
		}
		System.out.println("Grand Total = " + grandTotal);
		
	}

}
