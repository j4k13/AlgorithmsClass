import java.util.Arrays;

/**
 * Created by jackie on 4/26/16.
 */
public class LastAssignment {


	/**
	 * Returns the data packed into bins.
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

	public static void alg_best_fit() {

	}

	public static void alg_worst_fit() {

	}

	public static void populate(double[] arr) {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Math.random();
		}
	}

	public static void main(String[] args) {
		int size = 100;
		double[] data = new double[size];

		populate(data);

		double[] data_1 = data.clone();
		double[] data_2 = data.clone();



	}
}
