import java.util.Arrays;

import LastAssignment.Alg;

/**
 * Created by jackie on 4/26/16.
 */
public class LastAssignment {

	/**
	 * Returns the data packed into bins using a Sorted First Fit Algorithm.
	 * The element at index 0 is the # of Bins Used.
	 * The rest are the resulting packed bins.
	 * @param data
	 * @return bins
	 */
	public static double[] alg_sorted_first_fit(double[] data) {
		double[] bins = new double[data.length + 1];
		Arrays.sort(data);
		int binsUsed = 0;
		int binLoc;
		//Heh, double d
		for(double d : data) {
			binLoc = 1;
			while(bins[binLoc] + d > 1) {
				binLoc++;
			}
			bins[binLoc] += d;
			binsUsed = Math.max(binsUsed, binLoc);
		}

		bins[0] = binsUsed;

		return bins;
	}

	/**
	 * Returns the data packed into bins using a Best Fit algorithm.
	 * The element at index 0 is the # of Bins Used.
	 * The rest are the resulting packed bins.
	 * @param data
	 * @return bins
	 */
	public static double[] alg_best_fit(double[] data) {
		double[] bins = new double[data.length + 1];

		int binsUsed = 0;
		int binLoc = 0;
		int bestBin = 0;
		double bestBinValue = 0.0;
		double sum = 0.0;
		for(double d : data) {
			//Start at bin 1
			//For each bin that HAS been used...
			binLoc = 1;
			bestBinValue = 0.0;
			bestBin = 0;

			while(binLoc < (binsUsed + 1)) {
				sum = d + bins[binLoc];
				if(sum < 1 && sum > bestBinValue) {
					//A best fit has been found! Remember it.
					bestBin = binLoc;
					bestBinValue = sum;
				}
				binLoc++;
			}

			//If a best bin was found...
			if(bestBin > 0) {
				//Sum has already been calculated.
				bins[bestBin] = bestBinValue;
			} else {
				binsUsed++;
				bins[binsUsed] = d;
 			}

		}
		bins[0] = binsUsed;
		return bins;
	}

	public static void populate(double[] arr) {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Math.random();
		}
	}

	private enum Alg { A, B; }

	public static void main(String[] args) {
		int size = 100;
		int totalTrials = 1000;
		int trialInterval = 10;

		Alg[] winnersTime = new Alg[totalTrials];
		Alg[] winnersBins = new Alg[totalTrials];

		double[] rawData = new double[size];
		double[] data_1, data_2, bins_1 = null, bins_2 = null;

		long startTime_1, endTime_1, startTime_2, endTime_2, diff_1, diff_2;

		long avgTimeA = 0, avgTimeB = 0;
		double avgBinsA = 0, avgBinsB = 0;


		startTime_2 = 0L; endTime_2 = 0L;

		for(int trial = 1; trial < totalTrials+1; trial++) {
			populate(rawData);

			data_1 = rawData.clone();
			data_2 = rawData.clone();

			startTime_1 = System.nanoTime();
			bins_1 = alg_sorted_first_fit(data_1);
			endTime_1 = System.nanoTime();
			diff_1 = endTime_1 - startTime_1;

			startTime_2 = System.nanoTime();
			bins_2 = alg_best_fit(data_2);
			endTime_2 = System.nanoTime();
			diff_2 = endTime_2 - startTime_2;

			if(diff_1 < diff_2)
				winnersTime[trial - 1] = Alg.A;
			else
				winnersTime[trial - 1] = Alg.B;

			if(bins_1[0] < bins_2[0])
				winnersBins[trial - 1] = Alg.A;
			else
				winnersBins[trial - 1] = Alg.B;

			avgTimeA += diff_1;
			avgTimeB += diff_2;
			avgBinsA += bins_1[0];
			avgBinsB += bins_2[0];

			if(trial % trialInterval == 0) {
				System.out.printf("TRIAL %" + (int)(Math.log10(totalTrials)+1) +
					"d -- ALG1: %,7dns | ALG2: %,7dns%n",
					trial, endTime_1 - startTime_1,
					       endTime_2 - startTime_2);
			}
		}

		avgTimeA /= totalTrials;
		avgTimeB /= totalTrials;
		avgBinsA /= totalTrials;
		avgBinsB /= totalTrials;

		int aTimeSum = 0, bTimeSum = 0;

		for(Alg x : winnersTime) {
			if(x == Alg.A) aTimeSum++;
			else           bTimeSum++;
		}

		int aBinsSum = 0, bBinsSum = 0;

		for(Alg x : winnersBins) {
			if(x == Alg.A) aBinsSum++;
			else           bBinsSum++;
		}

		double timePercent = (double)(Math.max(aTimeSum, bTimeSum)) / totalTrials;
		double binsPercent = (double)(Math.max(aBinsSum, bBinsSum)) / totalTrials;

		System.out.printf("RESULTS!%nRUNTIME:  %s won %3d%% of the trials! (%d to %d)%n", aTimeSum > bTimeSum ? "A" : "B", (int)(100 * timePercent), aTimeSum, bTimeSum);
		System.out.printf("BINCOUNT: %s won %3d%% of the trials! (%d to %d)%n", aBinsSum > bBinsSum ? "A" : "B", (int)(100 * binsPercent), aBinsSum, bBinsSum);
		System.out.printf("On average, Algorithm A took %,6dns and produced %2d bins.%n", (int)avgTimeA, (int)avgBinsA);
		System.out.printf("On average, Algorithm B took %,6dns and produced %2d bins.%n", (int)avgTimeB, (int)avgBinsB);
	}
}
