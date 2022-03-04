package nl.topicus.misc;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class EntiteitInModelOnPage extends WebPage
{
	private static final long serialVersionUID = 1L;

	private IModel<Student> studentModel;

	public EntiteitInModelOnPage(Student student)
	{
		super();
		this.studentModel = new Model<>(student);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		// doe slimme dingen
	}

	public Student getStudent()
	{
		return studentModel.getObject();
	}
}
