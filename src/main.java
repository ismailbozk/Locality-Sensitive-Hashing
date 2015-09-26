import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.LineNumberReader;


public class main {	
	private static main self;
	private final static int k = 10;
	
	private final static int SIZEOFBANDS = 4;
	private final static int SIZEOFHASHFUNCT = 5;
	
	private final static int DIMENSIONOFSPACE = 3;
	private final static float BUCKETINTERVAL = 0.5f;
	
	private final float[][] randomUnitVectors = {
         {-0.4853f,  0.7556f,	 0.4400f},
         { 0.5946f,	 0.7735f,	 0.2195f},
         { 0.7986f, -0.5972f,   -0.0754f},
         { 0.0887f,	-0.9888f,    0.1198f},
         { 0.3019f,	-0.2605f,   -0.9170f},
         { 0.8275f,	-0.4936f,   -0.2676f},
         {-0.5007f,	 0.1468f,    0.8531f},
         {-0.5028f,	-0.8089f,    0.3046f},
         {-0.3116f,	 0.9461f,    0.0883f},
         {-0.5386f,  0.3968f,   -0.7433f},
         { 0.8024f, -0.5966f,   -0.0147f},
         {-0.6678f, -0.7229f,    0.1772f},
         {-0.4925f, -0.7544f,    0.4340f},
         {-0.7489f, -0.5342f,   -0.3921f},
         {-0.9470f, -0.2585f,   -0.1907f},
         {-0.0946f,  0.0061f,    0.9955f},
         {-0.0723f, -0.7996f,   -0.5961f},
         { 0.5579f, -0.1667f,    0.8130f},
         {-0.1899f,  0.9475f,    0.2574f},
         {-0.5885f, -0.5898f,    0.5530f},
         {0.5301f,   0.8285f,    0.1806f},
         {0.8033f,   -0.3890f,   -0.4511f},
        {-0.9211f,    0.0408f,    0.3871f},
         {0.8096f,   -0.5113f,    0.2883f},
         {0.6923f,    0.6592f,    0.2936f},
        {-0.8556f,   -0.3928f,    0.3371f},
        {-0.6154f,    0.6954f,    0.3710f},
         {0.2752f,    0.5938f,   -0.7561f},
         {0.9013f,    0.4312f,   -0.0409f},
         {0.9962f,   -0.0698f,   -0.0525f},
        {-0.5224f,   -0.8275f,   -0.2059f},
         {0.8501f,   -0.2352f,    0.4712f},
         {0.4102f,    0.7659f,   -0.4951f},
        {-0.0535f,   -0.9102f,   -0.4107f},
         {0.5130f,    0.6897f,   -0.5110f},
        {-0.1710f,    0.1035f,   -0.9798f},
        {-0.0853f,    0.9876f,   -0.1321f},
         {0.6012f,   -0.7914f,   -0.1106f},
         {0.6744f,   -0.0946f,    0.7323f},
         {0.7544f,   -0.6429f,   -0.1326f},
         {0.2119f,    0.9178f,   -0.3359f},
        {-0.5564f,    0.3802f,    0.7388f},
         {0.3613f,    0.6947f,    0.6220f},
         {0.8330f,   -0.5407f,   -0.1173f},
         {0.2960f,   -0.2838f,   -0.9120f},
         {0.8930f,   -0.2352f,   -0.3837f},
         {0.5480f,    0.8282f,   -0.1176f},
        {-0.6124f,   -0.5608f,    0.5572f},
         {0.3654f,    0.8724f,   -0.3248f},
        {-0.3512f,   -0.9152f,    0.1978f},
         {0.8596f,   -0.3424f,    0.3792f},
        {-0.6081f,   -0.4366f,   -0.6630f},
        {-0.5023f,   -0.7411f,   -0.4455f},
        {-0.6694f,    0.4200f,   -0.6128f},
        {-0.9811f,    0.0940f,   -0.1692f},
         {0.9758f,    0.0227f,   -0.2175f},
         {0.2369f,   -0.9715f,    0.0091f},
        {-0.2274f,    0.3397f,   -0.9126f},
         {0.9219f,    0.2356f,   -0.3075f},
        {-0.8013f,   -0.1400f,    0.5817f},
        {-0.0954f,    0.0214f,   -0.9952f},
        {-0.2416f,   -0.2622f,    0.9343f},
         {0.1760f,   -0.9652f,    0.1932f},
         {0.7368f,   -0.6727f,   -0.0683f},
        {-0.7127f,   -0.6851f,    0.1504f},
        {-0.0163f,   -0.5303f,   -0.8476f},
        {-0.1408f,   -0.9874f,   -0.0722f},
         {0.3481f,   -0.2959f,    0.8895f},
         {0.4787f,   -0.8769f,    0.0431f},
         {0.7544f,    0.6558f,    0.0281f},
        {-0.6925f,    0.4170f,   -0.5887f},
         {0.9033f,   -0.2338f,   -0.3598f},
        {-0.9818f,   -0.0281f,    0.1879f},
        {-0.7760f,   -0.5563f,    0.2972f},
        {-0.0063f,    0.9391f,   -0.3437f},
         {0.9847f,   -0.0856f,   -0.1519f},
        {-0.3376f,   -0.3134f,    0.8876f},
         {0.1397f,    0.5084f,   -0.8497f},
        {-0.1002f,   -0.0998f,    0.9900f},
        { 0.8546f,   -0.4505f,    0.2582f},
        {-0.7224f,   -0.1949f,    0.6634f},
         {0.0174f,   -0.4539f,   -0.8909f},
         {0.4000f,   -0.8109f,   -0.4271f},
         {0.3974f,    0.9121f,   -0.1004f},
         {0.6705f,    0.7188f,    0.1835f},
         {0.0505f,    0.1809f,   -0.9822f},
        {-0.7432f,   -0.6264f,    0.2350f},
        {-0.4459f,   -0.5199f,   -0.7286f},
        {-0.9846f,   -0.1637f,    0.0614f},
         {0.9165f,    0.3086f,    0.2544f},
        {-0.4095f,   -0.8860f,    0.2175f},
         {0.2796f,   -0.8707f,    0.4046f},
        {-0.1084f,   -0.8166f,    0.5669f},
         {0.7721f,    0.2898f,   -0.5656f},
        {-0.8529f,    0.4364f,    0.2866f},
        {-0.8013f,    0.2581f,   -0.5397f},
        {-0.7309f,   -0.0669f,   -0.6792f},
         {0.4598f,    0.1730f,    0.8710f},
        {-0.3491f,   -0.9371f,    0.0001f},
        {-0.2213f,    0.9732f,   -0.0620f},
         {0.6548f,   -0.6282f,    0.4203f},
         {0.3629f,    0.5663f,    0.7400f},
         {0.2106f,   -0.9037f,    0.3729f},
         {0.7751f,   -0.1635f,    0.6103f},
        {-0.5968f,    0.4101f,    0.6897f},
         {0.5469f,    0.8158f,    0.1882f},
         {0.5365f,   -0.7180f,   -0.4435f},
        {-0.1693f,    0.8755f,   -0.4525f},
         {0.1563f,    0.4785f,    0.8641f},
        {-0.5857f,   -0.0341f,   -0.8098f},
        {-0.9856f,   -0.1676f,   -0.0210f},
         {0.0534f,   -0.1108f,   -0.9924f},
         {0.5614f,   -0.2356f,    0.7933f},
         {0.9487f,    0.0085f,    0.3162f},
        {-0.9971f,    0.0767f,    0.0017f},
         {0.2204f,    0.9718f,   -0.0833f},
        {-0.0283f,    0.5231f,   -0.8518f},
        {-0.9330f,    0.2254f,    0.2805f},
        {-0.1955f,   -0.0934f,   -0.9763f},
        {-0.5985f,    0.2085f,   -0.7735f}         
	};
	
	private final static float[][] trainingData2_ = {
		{1.0f, 1.0f,  1.0f},	//0
		{0.0f, 0.0f,  3.0f},	//1
		{0.0f, 1.0f,  0.0f},	//2
		{0.0f, 2.0f,  0.0f},	//3
		{1.0f, 0.0f,  1.0f},	//4
		{0.0f, 0.0f,  0.0f},	//5
		{1.0f, 10.0f,  30.0f},	//6
		{2.0f, 0.0f,  1.0f},	//7
		{0.0f, 0.0f,  2.0f},	//8
		{1.0f, 10.0f,  40.0f},	//9
		{1.0f, 0.0f,  0.0f},	//10
		{2.0f, 0.0f,  0.0f},	//11
		{1.0f, 50.0f,  20.0f}	//12
		};
	private final static float[][] testData2_ = {
		{0.0f, 0.0f,  0.0f},
		{1.0f, 0.0f,  0.0f}
	};

	private ArrayList<HashMap<String, ArrayList<Integer>>> hashTablesInBands ;
	private float[][] trainingData;
	private float[][] testData;
	
	private ArrayList<knnParams> testDataExhaustiveSearchResults;
	private ArrayList<knnParams> testDataQuerySearchResults;
	
	private float[] RECALLs;
	
	public static void main(String[] args) {
		self = new main();
		
		self.trainingData = self.getDataFromCSVFile("src/2000Training.csv");
		self.testData = self.getDataFromCSVFile("src/2000Test.csv");
		//self.trainingData = trainingData2_;
		//self.testData = testData2_;
		
		self.prepareBands();

		self.exhaustiveSearch();
		self.querySearch();
		
		self.calculateRECALLs();
		
		Boolean x = false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private void querySearch(){
		long startTime = System.nanoTime();
		self.testDataQuerySearchResults = new ArrayList<knnParams>(self.testData.length);
		
		// STEP 1: obtain all candidates indices for single test data
		
		for(int indexOfTestData = 0; indexOfTestData < self.testData.length; indexOfTestData++)
		{
			int[] candidateIndexHolder;	//1 means "added", 0 means "not added". initially all arrays were not added. To prevent duplicate candidates
			candidateIndexHolder = new int[self.trainingData.length];
			ArrayList<Integer> candidateIndices = new ArrayList<Integer>();
			
			for(int indexOfBand = 0; indexOfBand < SIZEOFBANDS; indexOfBand++)
			{
				float[][] unitVectorsForSingleBand = Arrays.copyOfRange(randomUnitVectors, SIZEOFHASHFUNCT * indexOfBand, (SIZEOFHASHFUNCT * (indexOfBand + 1) ));
				String keyForSingleTestData = self.getHashFunctionKey(BUCKETINTERVAL, unitVectorsForSingleBand, self.testData[indexOfTestData]);
				
				if(self.hashTablesInBands.get(indexOfBand).containsKey(keyForSingleTestData) )
				{//if some candidates were stored in the hash table for that key
					ArrayList<Integer> candidateIndicesForSingleBand = self.hashTablesInBands.get(indexOfBand).get(keyForSingleTestData);
					for(int candidatesArrayIndex = 0; candidatesArrayIndex < candidateIndicesForSingleBand.size(); candidatesArrayIndex++)
					{
						if(candidateIndicesForSingleBand.get(candidatesArrayIndex) != null){
							int candidateIndex = candidateIndicesForSingleBand.get(candidatesArrayIndex);
							if(candidateIndexHolder[candidateIndex] == 0)//the reason I check here to prevent duplications
							{//if the candidate was not taken as an candidate before
								candidateIndexHolder[candidateIndex] = 1;
								candidateIndices.add(candidateIndex);
							}
							else
							{//for debug purpose only

							}
						}
						else
						{//for debug purpose only
							boolean s = false;
						}
					}
				}				
			}
			
			knnParams knnParamTemp = new knnParams();
			if(candidateIndices.size() != 0){
				//  STEP 2: obtain the real datas by using the candidateIndices
				float[][] candidates = new float[candidateIndices.size()][DIMENSIONOFSPACE];
				for(int candInd = 0 ; candInd < candidateIndices.size(); candInd++)
				{
					candidates[candInd] = self.trainingData[candidateIndices.get(candInd)];
				}

				// STEP 3: after all candidate were achieved, Lets run kNN on the candidates
				knnParams knnParamsForSingleTestData = self.knnEuclideanSearch(candidates, self.testData[indexOfTestData], k);
				knnParamTemp = knnParamsForSingleTestData;

				// STEP 4: convert the localIndices (which is an output of knnsearch AKA idx) into the global indices
				ArrayList<Integer> indicesTemp = knnParamTemp.indices;
				for(int indexOfKnnParams = 0 ; indexOfKnnParams < knnParamTemp.indices.size(); indexOfKnnParams++)
				{
					int realCandidateIndex = candidateIndices.get(knnParamTemp.indices.get(indexOfKnnParams));
					indicesTemp.set(indexOfKnnParams, realCandidateIndex);
				}
				knnParamTemp.indices = indicesTemp;
			}
			
			//and save the results for the RECALL calculation
			self.testDataQuerySearchResults.add(knnParamTemp);
		}
		
		self.calculateElapseTime(startTime, "Query Search Finilized!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	private void exhaustiveSearch(){
		//Exhaustive Search
		long st = System.nanoTime();
		self.testDataExhaustiveSearchResults = new ArrayList<knnParams>(self.testData.length);
		for(int indexOftestData = 0; indexOftestData < self.testData.length; indexOftestData++)
		{
			knnParams knnTest = self.knnEuclideanSearch(self.trainingData, self.testData[indexOftestData], k);
			self.testDataExhaustiveSearchResults.add(knnTest);
		}
		self.calculateElapseTime(st, "Exhaustive Search Finilized!!!!!!!!!!!!!!!!!!!!!!!!!");
		//----
	}
	
	private void calculateRECALLs(){
		self.RECALLs = new float[self.testData.length];
		for (int indexOFTestData = 0; indexOFTestData < self.testData.length; indexOFTestData++)
		{
			float TPPlusFN = 0;
			float TP = 0;
			
			int numberOfrealPositives = self.testDataExhaustiveSearchResults.get(indexOFTestData).indices.size();
			for(int realCandidateIndex = 0; realCandidateIndex < numberOfrealPositives; realCandidateIndex++)
			{
				TPPlusFN++;
				int realPositive = self.testDataExhaustiveSearchResults.get(indexOFTestData).indices.get(realCandidateIndex);
				if(self.testDataQuerySearchResults.get(indexOFTestData).indices != null)
				{
					int numberOfCandidatePositive = self.testDataQuerySearchResults.get(indexOFTestData).indices.size();
					for(int candidateIndex = 0; candidateIndex < numberOfCandidatePositive; candidateIndex++)
					{
						int candidatePositive = self.testDataQuerySearchResults.get(indexOFTestData).indices.get(candidateIndex);
						if(realPositive == candidatePositive)
						{
							TP++;
							break;
						}
					}
				}
			}
			float recall = (TP / TPPlusFN);
			self.RECALLs[indexOFTestData] = recall;
		}
		
		//calculate weighted average of RECALLs
		float aveRECALL = 0;
		for(int i = 0; i < self.RECALLs.length; i++)
		{
			aveRECALL += self.RECALLs[i];
			//System.out.println("RECALL " + i + " : "  + self.RECALLs[i]);
		}
		aveRECALL = aveRECALL/self.RECALLs.length;
		System.out.println("Average Recall: " + aveRECALL);
		
	}
	
	private void prepareBands(){
		long startTime = System.nanoTime();
		//prepare hashTablesInBands
		self.hashTablesInBands = new ArrayList<HashMap<String, ArrayList<Integer>>>(SIZEOFBANDS);
		for(int i = 0; i < SIZEOFBANDS; i++)
			self.hashTablesInBands.add(new HashMap<String, ArrayList<Integer>>()); 
		
		//generate hashtableKeys for each training data & put the training data into the hashTables
		int numberOfTrainingData = self.trainingData.length;
		for(int indexOfBand = 0; indexOfBand < SIZEOFBANDS; indexOfBand++)
		{
			for(int indexOfData = 0; indexOfData < numberOfTrainingData; indexOfData++)
			{
				float[][] unitVectorsForsingleBand = Arrays.copyOfRange(randomUnitVectors, SIZEOFHASHFUNCT * indexOfBand, (SIZEOFHASHFUNCT * (indexOfBand + 1) ));
				String hashTableKey = self.getHashFunctionKey(BUCKETINTERVAL, unitVectorsForsingleBand, self.trainingData[indexOfData]);
				
				if(!self.hashTablesInBands.get(indexOfBand).containsKey(hashTableKey))
					self.hashTablesInBands.get(indexOfBand).put(hashTableKey, new ArrayList<Integer>(1));
				else
				{
					Boolean ddsd =false;
				}
				self.hashTablesInBands.get(indexOfBand).get(hashTableKey).add(indexOfData);
			}
		}
		self.calculateElapseTime(startTime, "HashTables/Bands were prepared");
	}

	public float getEuclideanDistance(float[] vector1, float[] vector2){
		float euclideanDist = -1.0f;
		if(vector1.length == vector2.length)
		{
			double distSquare = 0.0f;
			for(int i = 0; i < vector1.length; i++)
				distSquare += ( Math.pow(vector1[i] - vector2[i], 2));
			euclideanDist = (float) Math.sqrt(distSquare);
		}
		else
		{
			System.out.println("(float getEuclideanDistance): vector sizes do not matches!! ");
		}
		return euclideanDist;
	}
	
	public String getHashFunctionKey(float bucketInterval, float[][] unitVectors, float[] dataVector){
		int[] hashFct = new int[SIZEOFHASHFUNCT];
		
		for(int i = 0; i < unitVectors.length; i++)
			hashFct[i] = self.getBucketIndex(bucketInterval, unitVectors[i], dataVector);

		return Arrays.toString(hashFct);
	}
	
	public int getBucketIndex(float bucketInterval, float[] unitVector, float[] dataVector){
		
		int bucketIndex = -999999999;
		if(unitVector.length == dataVector.length)
		{
			float bucket = 0;
			for(int i = 0; i < unitVector.length; i++)
				bucket += (unitVector[i] * dataVector[i]);
			bucketIndex = (int)Math.floor(bucket/bucketInterval);
		}
		else
		{
			System.out.println("(int getBucketIndex): vector sizes do not matches!!!");
		}
		return bucketIndex;
	}

	public knnParams knnEuclideanSearch(float[][] trainingPoints, float[] testPoint, int k){
		//prepare arrayLists
		int invalidIndex = -1;
		float invalidDist = Float.MAX_VALUE;
		ArrayList<Integer> indices = new ArrayList<Integer>(k + 1);
		ArrayList<Float> distances = new ArrayList<Float>(k + 1);
		
		for(int i = 0 ; i < k; i++){
			indices.add(invalidIndex);
			distances.add(invalidDist);
		}
		
		//now find nearest neighbors 
		for(int rowInd = 0; rowInd < trainingPoints.length; rowInd++) // the reaon why it started from 1 is 0 index already put in the arrays
		{
			float candDist = getEuclideanDistance(trainingPoints[rowInd], testPoint);
			//look at the distances array to see that if the candidate is eligible enough to be inserted into the knn List
			for(int candInd = 0; candInd < indices.size(); candInd++)
			{
				//search fin the current candidate to see if the curren candidate is better than these candidates
				if(candDist < distances.get(candInd))
				{//if the candidate is better than the previous candidates then insert it into the proper index
					distances.add(candInd, candDist);
					indices.add(candInd, rowInd);
					//remove unnecessary ones
					if(indices.size() >= k)
					{//remove the unnecessary ones
						distances.remove(k);
						indices.remove(k);
					}
					break;
				}
			}
		}
		
		int sizeOfIndices = indices.size();
		for(int i = sizeOfIndices - 1; i >= 0; i--){
			if(indices.get(i) == null || indices.get(i) == invalidIndex || i >= k){
				indices.remove(i);
				distances.remove(i);
			}
		}
		
		knnParams result = new knnParams();
		result.distinces = distances;
		result.indices = indices;
		return result;
	}
	
	public void calculateElapseTime(long startTime, String message){
		long endTime = System.nanoTime();
		long difference = endTime - startTime;
		
		System.out.println("Elapsed time: " + (double)difference/1000000 + " milliseconds. " + message);
	}
	
	public float[][] getDataFromCSVFile(String fileLocation){
		String csvFile = fileLocation; //  "/Users/Grey/Downloads/test.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		//first get the number of vectors by looking the number of lines in the file
		int numberOfLines = 0;
		LineNumberReader reader = null;
	    try {
	        reader = new LineNumberReader(new FileReader(fileLocation));
	        while ((reader.readLine()) != null);
	        numberOfLines = reader.getLineNumber();
	    } catch (Exception ex) {
	    	numberOfLines = -1;
	    } finally { 
	        if(reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    }
		
	    //then write the vectors
	    float[][] vectors = null;
	    int lineIndex = 0;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				float[] singleVector;
				if(line != "")
				{
					String[] values = line.split(cvsSplitBy);
					singleVector = new float[values.length];
					for(int col = 0; col < values.length; col++)
						singleVector[col] = Float.parseFloat(values[col]);
					if(vectors == null)
						vectors = new float[numberOfLines][values.length];

					vectors[lineIndex] = singleVector;
				}
				else
				{break;}	
				
				lineIndex++;
			}
		}catch(Exception e){
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return vectors;
	}
}


