class Solution
{
public:
	int brokenCalc(int startValue, int target)
	{
		int ops = 0;
		while (target > startValue)
		{
			if (target & 1)
			{
				target += 1;
			}
			else
			{
				target >>= 1;
			}
			ops++;
		}
		if (target < startValue)
		{
			ops += startValue - target;
		}
		return ops;
	}
};