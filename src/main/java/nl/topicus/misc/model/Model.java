package nl.topicus.misc.model;

import java.io.Serializable;

import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IObjectClassAwareModel;
import org.apache.wicket.util.lang.Objects;

public class Model<T extends Serializable> implements IObjectClassAwareModel<T>
{
	private static final long serialVersionUID = 1L;

	private T object;

	public Model(T object)
	{
		this.object = object;
	}

	public static <T extends Serializable> Model<T> of(T object)
	{
		return new Model<>(object);
	}

	@Override
	public T getObject()
	{
		return object;
	}

	@Override
	public void setObject(T object)
	{
		this.object = object;
	}

	@Override
	public void detach()
	{
		if (object instanceof IDetachable)
		{
			((IDetachable) object).detach();
		}
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder("Model:classname=[");
		sb.append(getClass().getName()).append(']');
		sb.append(":object=[").append(object).append(']');
		return sb.toString();
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(object);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof Model< ? >))
		{
			return false;
		}
		Model< ? > that = (Model< ? >) obj;
		return Objects.equal(object, that.object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getObjectClass()
	{
		return object != null ? (Class<T>) object.getClass() : null;
	}
}
