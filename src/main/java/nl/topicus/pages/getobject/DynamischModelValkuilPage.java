package nl.topicus.pages.getobject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.google.common.base.Stopwatch;

import nl.topicus.pages.BasePage;

public class DynamischModelValkuilPage extends BasePage
{
	private static final long serialVersionUID = 1L;

	private Stopwatch stopwatchTotalTime = Stopwatch.createStarted();

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		System.out.println("============");

		addListView();
	}

	private void addListView()
	{

		add(new ListView<String>("demoListView", this::getStringList)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<String> item)
			{
				item.add(new Label("label", item.getModel()));
			}
		});
	}

	private List<String> getStringList()
	{
		Stopwatch stopwatch = Stopwatch.createStarted();

		List<String> list = new ArrayList<>();

		for (int i = 0; i < 11; i++)
		{
			doeZwareOperatie();
			list.add("Nummer " + (i + 1));
		}

		System.out.println("getObject aangeroepen - duurde ~" + stopwatch.elapsed(TimeUnit.SECONDS) + " seconde(n)");
		return list;
	}

	// Simuleer een zware operatie zoals een berekening of een database-call
	private void doeZwareOperatie()
	{
		try
		{
			int aantalMilliseconden = 100;
			Thread.sleep(aantalMilliseconden);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void onDetach()
	{
		super.onDetach();
		System.out.println("Totaal duurde het ~" + stopwatchTotalTime.elapsed(TimeUnit.SECONDS) + " seconden");
		System.out.println("============");
	}

	@Override
	protected Class< ? extends BasePage> getNextPageClass()
	{
		return null;
	}
}
