package nl.topicus.pages;

import java.util.Random;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import nl.topicus.BasePage;

public class StatischModelPage extends BasePage
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		addStaticModels();
	}

	private void addStaticModels()
	{
		add(new Label("staticModel", new Model<>("world")));

		int random = new Random().nextInt();
		add(new Label("staticModel2", new Model<>(random)));

		// Waarom noem ik dit een statisch model? Omdat na het initialiseren van het model de waarde
		// niet meer wijzigd.
	}

	@Override
	protected Class< ? extends BasePage> getNextPageClass()
	{
		return AjaxPauzePage.class;
	}
}
