package nl.topicus.pages.getobject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import nl.topicus.pages.BasePage;

public class HomePage extends BasePage
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		add(new Label("staticModel", new Model<>("world")));

		// de veel gebruikte Label-constructor
		// new Label("staticModel", "world")
		// maakt onder water voor jou een (static) model aan

		String labelWaarde = "world";
		IModel<String> labelModel = Model.of(labelWaarde);
		add(new Label("staticModel2", labelModel));
	}

	@Override
	protected Class< ? extends BasePage> getNextPageClass()
	{
		return StatischModelPage.class;
	}
}
