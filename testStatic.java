class Solution
{
	public static int OFFSET = -1;
	public static void main(String[] args)
	{
		int currOffset = OFFSET;
		OFFSET = -2;
		System.out.println(currOffset);
	}
}
