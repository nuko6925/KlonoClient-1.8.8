package net.klono.lib;

import java.util.ArrayList;

public abstract class Manager<obj>
{
	protected abstract obj[] objects();
	
	public Manager()
	{
		objects = new ArrayList();
		for (obj object: objects())
			if (object != null)
				objects.add(object);
	}
	
	private ArrayList<obj> objects;
	
	public ArrayList<obj> getObjects()
	{
		return objects;
	}
	
	public obj getObj(Class <? extends obj> clazz){
		for(obj s: objects){
			if(s.getClass() == clazz)
			{
				return s;
			}
		}
		return null;
	}
}