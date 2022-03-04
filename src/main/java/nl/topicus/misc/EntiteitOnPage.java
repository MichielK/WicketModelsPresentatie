package nl.topicus.misc;

import org.apache.wicket.markup.html.WebPage;

public class EntiteitOnPage extends WebPage
{
	private static final long serialVersionUID = 1L;

	private Student student;

	public EntiteitOnPage(Student student)
	{
		super();
		this.student = student;
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		// doe slimme dingen
	}

	public Student getStudent()
	{
		return student;
	}

	public void setStudent(Student student)
	{
		this.student = student;
	}
}
