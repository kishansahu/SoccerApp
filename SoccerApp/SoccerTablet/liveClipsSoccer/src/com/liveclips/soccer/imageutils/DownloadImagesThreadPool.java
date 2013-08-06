/**
 * 
 */
package com.liveclips.soccer.imageutils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import android.widget.ImageView;

	/**
	 * @author mohitkumar
	 * 
	 */
	public class DownloadImagesThreadPool {
	
		private ExecutorService executorService;
		private CompletionService<Boolean> completionService;
		private BlockingQueue<Future<Boolean>> completionQueue;
		private static AtomicInteger totalTaskCount = new AtomicInteger(0);
	
		/**
		 * 
		 */
		public DownloadImagesThreadPool() {
			this.executorService = Executors.newFixedThreadPool(1);
			this.completionQueue = new java.util.concurrent.LinkedBlockingQueue<Future<Boolean>>();
			this.completionService = new ExecutorCompletionService<Boolean>(executorService, completionQueue);
			
		}

		/**
		 * 
		 * @param input
		 * @return
		 */
		synchronized public Future<Boolean> submit(ImageView bmImage, String input) {
			totalTaskCount.incrementAndGet();
			return completionService.submit(new DownloadImageTask(bmImage, input));
		}
	
		/**
		 * 
		 * @return
		 */
		synchronized public Boolean take() {
	
			Boolean result = null;
			try {
				result = completionService.take().get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
	
			return result;
		}
	
		/**
		 * 
		 * @return
		 */
		synchronized public Boolean poll() {
	
			Boolean result = null;
			try {
				Future<Boolean> future = completionService.poll();
				if (future != null) {
					result = future.get();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
	
			return result;
		}
	
		/**
		 * 
		 * @return
		 */
		public int getActiveCount() {
			return ((ThreadPoolExecutor) executorService).getActiveCount();
		}
	
		/**
		 * 
		 * @return
		 */
		public ThreadPoolExecutor getThreadPoolExecutor() {
			return ((ThreadPoolExecutor) executorService);
		}
	
		/**
		 * 
		 * @return
		 */
		public CompletionService<Boolean> getCompletionService() {
			return completionService;
		}
	
		/**
		 * 
		 * @return
		 */
		public BlockingQueue<Runnable> getBlockingQueue() {
			return getThreadPoolExecutor().getQueue();
		}
	
		/**
		 * 
		 */
		public static void decrementTotalTaskCount() {
			totalTaskCount.decrementAndGet();
		}
	
		/**
		 * 
		 * @return
		 */
		public boolean isPoolExecutionComplete() {
			return totalTaskCount.get() == 0;
		}
		public void tearDown(int lenth){
			for(int index =0; index < lenth; index++){
				poll();
			}
			executorService = null;
			completionService = null;
			completionQueue = null;
			
		}
	}
