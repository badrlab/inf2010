import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0) return;
		if(array.size() == 1)
		{
			a = b = 0;
			data = (QuadraticSpacePerfectHashing<AnyType>[]) new Object[1];
			data[0].items[0] = array.get(0);
			return;
		}

		data = new QuadraticSpacePerfectHashing[array.size()];
		a = generator.nextInt(p-1) + 1;
		b = generator.nextInt(p);
		int m = array.size();
		int index;
		for (int i = 0; i < array.size(); i++)
		{
			AnyType x = array.get(i);
			index = (a * x.hashCode() + b % p) % m;
			if (data[index] == null)
			{
				ArrayList<AnyType> tmp = new ArrayList<AnyType>();
				tmp.add(x);
				data[index] = new QuadraticSpacePerfectHashing<AnyType>(tmp);
				data[index].items[0] = x; 
			}
			else
			{
				ArrayList<AnyType> tmp = new ArrayList<AnyType>();
				for (int j = 0; j < data[index].items.length; j++) if (data[index].items[j] != null) tmp.add(data[index].items[j]);
				tmp.add(x);
				data[index] = new QuadraticSpacePerfectHashing<AnyType>(tmp);
			}
		}
	}

	public int Size()
	{
		if( data == null ) return 0;
		int size = 0;
		for(int i=0; i<data.length; ++i) size += (data[i] == null ? 1 : data[i].Size());
		return size;
	}

	public boolean contains(AnyType x )
	{
		int position  = (a * x.hashCode() + b % p) % data.length;
		if (data[position] == null) return false;
		return data[position].contains(x);
	}
}